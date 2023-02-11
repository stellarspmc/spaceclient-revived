package net.optifine.shaders;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class SMCLog
{
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String PREFIX = "[Shaders] ";

    public static void severe(String message)
    {

    }

    public static void warning(String message)
    {

    }

    public static void info(String message)
    {

    }

    public static void fine(String message)
    {

    }

    public static void severe(String format, Object... args)
    {
        String s = String.format(format, args);
    }

    public static void warning(String format, Object... args)
    {
        String s = String.format(format, args);
    }

    public static void info(String format, Object... args)
    {
        String s = String.format(format, args);
    }

    public static void fine(String format, Object... args)
    {
        String s = String.format(format, args);
    }
}
