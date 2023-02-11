package net.optifine;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log
{
    private static final Logger LOGGER = LogManager.getLogger();
    public static void detail(String s)
    {
    }

    public static void dbg(String s)
    {
    }

    public static void warn(String s)
    {
    }

    public static void warn(String s, Throwable t)
    {
    }

    public static void error(String s)
    {
    }

    public static void error(String s, Throwable t)
    {
    }

    public static void log(String s)
    {
        dbg(s);
    }
}
