package net.optifine.http;

import net.minecraft.src.Config;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.Proxy;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpPipeline {
    private static final Map mapConnections = new HashMap<>();

    public static HttpRequest makeRequest(String urlStr, Proxy proxy) throws IOException {
        URL url = URI.create(urlStr).toURL();

        if (!url.getProtocol().equals("http")) {
            throw new IOException("Only protocol http is supported: " + url);
        } else {
            String s = url.getFile();
            String s1 = url.getHost();
            int i = (url.getPort() <= 0) ? 80 : url.getPort();

            String s2 = "GET";
            String s3 = "HTTP/1.1";
            Map<String, String> map = new LinkedHashMap<>();
            map.put("User-Agent", "Java/" + System.getProperty("java.version"));
            map.put("Host", s1);
            map.put("Accept", "text/html, image/gif, image/png");
            map.put("Connection", "keep-alive");
            return new HttpRequest(s1, i, proxy, s2, s, s3, map, new byte[0]);
        }
    }

    public static void addRequest(HttpPipelineRequest pr) {
        HttpRequest httprequest = pr.getHttpRequest();

        for (HttpPipelineConnection httppipelineconnection = getConnection(httprequest.getHost(), httprequest.getPort(), httprequest.getProxy()); !httppipelineconnection.addRequest(pr); httppipelineconnection = getConnection(httprequest.getHost(), httprequest.getPort(), httprequest.getProxy())) {
            removeConnection(httprequest.getHost(), httprequest.getPort(), httprequest.getProxy(), httppipelineconnection);
        }
    }

    private static synchronized HttpPipelineConnection getConnection(String host, int port, Proxy proxy) {
        String s = makeConnectionKey(host, port, proxy);
        HttpPipelineConnection httppipelineconnection = (HttpPipelineConnection) mapConnections.get(s);

        if (httppipelineconnection == null) {
            httppipelineconnection = new HttpPipelineConnection(host, port, proxy);
            mapConnections.put(s, httppipelineconnection);
        }

        return httppipelineconnection;
    }

    private static synchronized void removeConnection(String host, int port, Proxy proxy, HttpPipelineConnection hpc) {
        String s = makeConnectionKey(host, port, proxy);
        HttpPipelineConnection httppipelineconnection = (HttpPipelineConnection) mapConnections.get(s);

        if (httppipelineconnection == hpc) {
            mapConnections.remove(s);
        }
    }

    private static String makeConnectionKey(String host, int port, Proxy proxy) {
        return host + ":" + port + "-" + proxy;
    }

    public static byte[] get(String urlStr) throws IOException {
        return get(urlStr, Proxy.NO_PROXY);
    }

    public static byte[] get(String urlStr, Proxy proxy) throws IOException {
        if (urlStr.startsWith("file:")) {
            URL url = URI.create(urlStr).toURL();
            InputStream inputstream = url.openStream();
            return Config.readAll(inputstream);
        } else {
            HttpRequest httprequest = makeRequest(urlStr, proxy);
            HttpResponse httpresponse = executeRequest(httprequest);

            if (httpresponse.getStatus() / 100 != 2) {
                throw new IOException("HTTP response: " + httpresponse.getStatus());
            } else {
                return httpresponse.getBody();
            }
        }
    }

    public static HttpResponse executeRequest(HttpRequest req) throws IOException {
        final Map<String, Object> map = new HashMap<>();
        HttpListener httplistener = new HttpListener() {
            public void finished(HttpRequest req, HttpResponse resp) {
                synchronized (map) {
                    map.put("Response", resp);
                    map.notifyAll();
                }
            }

            public void failed(HttpRequest req, Exception e) {
                synchronized (map) {
                    map.put("Exception", e);
                    map.notifyAll();
                }
            }
        };

        synchronized (map) {
            HttpPipelineRequest httppipelinerequest = new HttpPipelineRequest(req, httplistener);
            addRequest(httppipelinerequest);

            try {
                map.wait();
            } catch (InterruptedException var10) {
                throw new InterruptedIOException("Interrupted");
            }

            Exception exception = (Exception) map.get("Exception");

            if (exception != null) {
                if (exception instanceof IOException) throw (IOException) exception;
                else if (exception instanceof RuntimeException) throw (RuntimeException) exception;
                else throw new RuntimeException(exception.getMessage(), exception);
            } else {
                HttpResponse httpresponse = (HttpResponse) map.get("Response");

                if (httpresponse == null) throw new IOException("Response is null");
                else return httpresponse;
            }
        }
    }
}
