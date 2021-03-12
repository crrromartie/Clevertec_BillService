package ru.clevertec.aop.aspects;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import ru.clevertec.custom.CustomJsonParser;
import ru.clevertec.custom.LoggingAnnotation;
import ru.clevertec.custom.LoggingLevel;
import ru.clevertec.custom.impl.CustomJsonParserImpl;

@Aspect
public class LoggingAspect {
    private static final String EMPTY_STRING = "";

    @Pointcut("@annotation(ru.clevertec.custom.LoggingAnnotation)")
    private void methodToBeProfiled() {
    }

    @AfterReturning(pointcut = "methodToBeProfiled()", returning = "o")
    public void loginParameters(JoinPoint joinPoint, Object o) throws IllegalAccessException {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        LoggingAnnotation annotation = methodSignature.getMethod().getAnnotation(LoggingAnnotation.class);
        LoggingLevel level = annotation.value();
        Logger logger = LogManager.getLogger(joinPoint.getTarget());
        CustomJsonParser parser = new CustomJsonParserImpl();
        String args = EMPTY_STRING;
        if (joinPoint.getArgs() != null) {
            parser.parseToJson(joinPoint.getArgs());
        }
        String result = EMPTY_STRING;
        if (o != null) {
            result = parser.parseToJson(o);
        }
        switch (level) {
            case OFF -> {
                logger.log(Level.OFF, "{} args={}", methodSignature.getName(), args);
                logger.log(Level.OFF, "{} result={}", methodSignature.getName(), result);
            }
            case FATAL -> {
                logger.log(Level.FATAL, "{} args={}", methodSignature.getName(), args);
                logger.log(Level.FATAL, "{} result={}", methodSignature.getName(), result);
            }
            case ERROR -> {
                logger.log(Level.ERROR, "{} args={}", methodSignature.getName(), args);
                logger.log(Level.ERROR, "{} result={}", methodSignature.getName(), result);
            }
            case WARN -> {
                logger.log(Level.WARN, "{} args={}", methodSignature.getName(), args);
                logger.log(Level.WARN, "{} result={}", methodSignature.getName(), result);
            }
            case DEBUG -> {
                logger.log(Level.DEBUG, "{} args={}", methodSignature.getName(), args);
                logger.log(Level.DEBUG, "{} result={}", methodSignature.getName(), result);
            }
            case INFO -> {
                logger.log(Level.INFO, "{} args={}", methodSignature.getName(), args);
                logger.log(Level.INFO, "{} result={}", methodSignature.getName(), result);
            }
            case TRACE -> {
                logger.log(Level.TRACE, "{} args={}", methodSignature.getName(), args);
                logger.log(Level.TRACE, "{} result={}", methodSignature.getName(), result);
            }
            case ALL -> {
                logger.log(Level.ALL, "{} args={}", methodSignature.getName(), args);
                logger.log(Level.ALL, "{} result={}", methodSignature.getName(), result);
            }
        }
    }
}