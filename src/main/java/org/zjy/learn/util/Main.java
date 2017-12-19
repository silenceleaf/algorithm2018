package org.zjy.learn.util;


import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by Junyan Zhang on 12/14/2017.
 */
public class Main {
    private static final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
    private static Logger logger = Logger.getLogger(Main.class.getCanonicalName());

    public static void main(String[] args) {
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

    private static Class<Runnable> scanRunnableClasses(String packageName) {
        Reflections reflections = new Reflections(packageName);
        Set<Class<?>> classesList = reflections.getTypesAnnotatedWith(Application.class);
        Class<Runnable> latestClass = null;
        long timeStamp = 0;
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
                if (date.getTime() > timeStamp && Runnable.class.isAssignableFrom(runnableClass)) {
                    latestClass = runnableClass;
                }
            }
        }
        return latestClass;
    }
}
