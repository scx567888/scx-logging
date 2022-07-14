package cool.scx.logging;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * ScxLoggerFactory
 *
 * @author scx567888
 * @version 0.0.1
 */
public final class ScxLoggerFactory {

    /**
     * Constant <code>LOGGER_CACHE</code>
     */
    private static final Map<String, ScxLogger> LOGGER_CACHE = new HashMap<>();

    /**
     * 日志级别
     */
    private static ScxLoggingLevel defaultLevel = ScxLoggingLevel.ERROR;

    /**
     * 日志类型
     */
    private static ScxLoggingType defaultType = ScxLoggingType.CONSOLE;

    /**
     * 存储目录
     */
    private static Path defaultStoredDirectory = null;

    /**
     * 是否启用堆栈跟踪
     */
    private static boolean defaultStackTrace = false;

    /**
     * 获取默认级别
     *
     * @return a {@link cool.scx.logging.ScxLoggingLevel} object
     */
    static ScxLoggingLevel defaultLevel() {
        return defaultLevel;
    }

    /**
     * 获取默认类型
     *
     * @return a {@link cool.scx.logging.ScxLoggingType} object
     */
    static ScxLoggingType defaultType() {
        return defaultType;
    }

    /**
     * 获取默认存储目录
     *
     * @return a {@link java.nio.file.Path} object
     */
    static Path defaultStoredDirectory() {
        return defaultStoredDirectory;
    }

    /**
     * 获取默认是否启用堆栈跟踪
     *
     * @return a
     */
    static boolean defaultStackTrace() {
        return defaultStackTrace;
    }

    /**
     * a
     *
     * @param clazz a
     * @return a
     */
    public static ScxLogger getLogger(Class<?> clazz) {
        return getLogger(clazz.getName());
    }

    /**
     * a
     *
     * @param name a
     * @return a
     */
    public static ScxLogger getLogger(String name) {
        var logger = LOGGER_CACHE.get(name);
        if (logger == null) {
            var scxLogger = new ScxLogger(name);
            LOGGER_CACHE.put(name, scxLogger);
            logger = scxLogger;
        }
        return logger;
    }

    /**
     * 更新指定的 Logger 信息
     *
     * @param name            a {@link java.lang.String} object
     * @param level           a {@link cool.scx.logging.ScxLoggingLevel} object
     * @param type            a {@link cool.scx.logging.ScxLoggingType} object
     * @param storedDirectory a {@link java.nio.file.Path} object
     * @param stackTrace      a {@link java.lang.Boolean} object
     */
    public static void updateLogger(String name, ScxLoggingLevel level, ScxLoggingType type, Path storedDirectory, Boolean stackTrace) {
        getLogger(name).update(level, type, storedDirectory, stackTrace);
    }

    /**
     * 更新指定的 Logger 信息
     *
     * @param clazz           a
     * @param level           a
     * @param type            a
     * @param storedDirectory a
     * @param stackTrace      a
     */
    public static void updateLogger(Class<?> clazz, ScxLoggingLevel level, ScxLoggingType type, Path storedDirectory, Boolean stackTrace) {
        getLogger(clazz).update(level, type, storedDirectory, stackTrace);
    }

    /**
     * 更新默认日志信息
     *
     * @param newDefaultLevel           a {@link cool.scx.logging.ScxLoggingLevel} object
     * @param newDefaultType            a {@link cool.scx.logging.ScxLoggingType} object
     * @param newDefaultStoredDirectory a {@link java.nio.file.Path} object
     * @param newDefaultStackTrace      a {@link java.lang.Boolean} object
     */
    public static void updateDefault(ScxLoggingLevel newDefaultLevel, ScxLoggingType newDefaultType, Path newDefaultStoredDirectory, boolean newDefaultStackTrace) {
        Objects.requireNonNull(newDefaultLevel, "newDefaultLevel 不能为 null");
        Objects.requireNonNull(newDefaultType, "newDefaultType 不能为 null");
        defaultLevel = newDefaultLevel;
        defaultType = newDefaultType;
        defaultStoredDirectory = newDefaultStoredDirectory;
        defaultStackTrace = newDefaultStackTrace;
    }

}
