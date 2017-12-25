package org.zjy.learn.util;


import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.logging.*;

/**
 * Created by Junyan Zhang on 12/14/2017.
 *
 * 九章算法面试题
 * https://www.jiuzhang.com/article/?tags=interview-questions
 */


public class Main {
    private static final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    private static Logger logger;

    public static void main(String[] args) {
        logger = setupLogger();

        Class<Runnable> latestAppClass = scanRunnableClasses("org.zjy.learn.code");
        if (latestAppClass == null) {
            logger.warning("no runnable class found!");
            return;
        }
        logger.info("runnable class: " + latestAppClass.getCanonicalName());
        try {
            Runnable runnableApplication = latestAppClass.newInstance();
            Method runMethod = latestAppClass.getDeclaredMethod("run");
            long start = System.currentTimeMillis();
            runMethod.invoke(runnableApplication);
            logger.info("time elapse: " + (System.currentTimeMillis() - start) + " ms");
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }

    private static Logger setupLogger() {
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new SimpleFormatter() {
            private static final String format = "[%1$tF %1$tT] [%2$s] %3$s %n";

            @Override
            public synchronized String format(LogRecord lr) {
                return String.format(format,
                        new Date(lr.getMillis()),
                        lr.getLevel().getLocalizedName(),
                        lr.getMessage()
                );
            }
        });
        Logger mainLogger = Logger.getLogger("algorithm_lean");
        mainLogger.setUseParentHandlers(false);
        mainLogger.addHandler(handler);
        return mainLogger;
    }

    @SuppressWarnings("unchecked")
    private static Class<Runnable> scanRunnableClasses(String packageName) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> classesList = reflections.getTypesAnnotatedWith(Application.class);
        Class<Runnable> latestClass = null;
        Date timeStamp = new Date(0);
        for (Class runnableClass : classesList) {
            Application appAnnotation = null;
            for (Annotation annotation : runnableClass.getAnnotations()) {
                if (annotation instanceof Application)
                    appAnnotation = (Application) annotation;
            }
            if (appAnnotation != null) {
                Date date;
                try {
                    date = dateFormat.parse(appAnnotation.time());
                } catch (ParseException e) {
                    logger.warning("Can not parse time: " + appAnnotation.time());
                    continue;
                }
                if (date.after(timeStamp) && Runnable.class.isAssignableFrom(runnableClass)) {
                    latestClass = runnableClass;
                    timeStamp = date;

                }
            }
        }
        return latestClass;
    }
}
