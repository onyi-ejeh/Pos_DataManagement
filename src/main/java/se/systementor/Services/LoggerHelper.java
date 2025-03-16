package se.systementor.Services;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * LoggerHelper is a utility class that provides methods for logging messages at different log levels.
 * The class uses the built-in Java logging framework to output logs to the console.
 *
 * This class is designed to log messages with different severity levels including:
 * - INFO: General informational messages
 * - SEVERE: Critical errors
 * - WARNING: Warnings about potential issues
 * - FINE: Debug-level messages for detailed tracing
 */
public class LoggerHelper {

    // Logger instance used to log messages
    private static final Logger logger = Logger.getLogger(LoggerHelper.class.getName());

    /**
     * Static block to configure the logger with a console handler and set the default logging level to ALL.
     * This ensures that all messages, regardless of severity, are output to the console.
     */
    static {
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);  // Set the console handler to log all messages
        logger.addHandler(consoleHandler);   // Add the console handler to the logger
        logger.setLevel(Level.ALL);          // Set the logger level to ALL
    }

    /**
     * Logs an informational message at the INFO level.
     *
     * @param message The message to be logged at the INFO level.
     */
    public static void logInfo(String message) {
        logger.info(message);
    }

    /**
     * Logs an error message at the SEVERE level.
     *
     * @param message The error message to be logged at the SEVERE level.
     */
    public static void logError(String message) {
        logger.severe(message);
    }

    /**
     * Logs a warning message at the WARNING level.
     *
     * @param message The warning message to be logged at the WARNING level.
     */
    public static void logWarning(String message) {
        logger.warning(message);
    }

    /**
     * Logs a debug message at the FINE level.
     *
     * @param message The debug message to be logged at the FINE level.
     */
    public static void logDebug(String message) {
        logger.fine(message);
    }
}
