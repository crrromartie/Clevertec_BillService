package ru.clevertec.aop.aspects;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import ru.clevertec.bill.annotations.LoggingAnnotation;
import ru.clevertec.bill.annotations.LoggingLevel;
import ru.clevertec.bill.parser.CustomJsonParser;
import ru.clevertec.bill.parser.CustomJsonParserImpl;

@Aspect
public class LoggingAspect {
    static Logger logger = LogManager.getLogger();

    private static final String SPACE = " ";
    private static final String ARGS = "args=";
    private static final String RESULT = "result=";
    private static final String VOID = "void";

    @Pointcut("execution(@ru.clevertec.bill.annotations.* * *(..))")
    private void methodToBeProfiled() {
    }

    @AfterReturning(pointcut = "methodToBeProfiled()", returning = "o")
    public void loginParameters(JoinPoint joinPoint, Object o) throws IllegalAccessException {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String log = createLog(joinPoint, o);
        LoggingAnnotation annotation = (LoggingAnnotation) methodSignature.getMethod().getAnnotation(LoggingAnnotation.class);
        LoggingLevel level = (LoggingLevel) annotation.value();
        switch (level) {
            case OFF -> logger.log(Level.OFF, log);
            case FATAL -> logger.log(Level.FATAL, log);
            case ERROR -> logger.log(Level.ERROR, log);
            case WARN -> logger.log(Level.WARN, log);
            case INFO -> logger.log(Level.INFO, log);
            case DEBUG -> logger.log(Level.DEBUG, log);
            case TRACE -> logger.log(Level.TRACE, log);
            case ALL -> logger.log(Level.ALL, log);
        }
    }

    private String createLog(JoinPoint joinPoint, Object o) throws IllegalAccessException {
        StringBuilder log = new StringBuilder();
        CustomJsonParser parser = new CustomJsonParserImpl();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String methodName = methodSignature.getName();
        String methodClassName = joinPoint.getSignature().getDeclaringTypeName();
        log.append(methodClassName)
                .append(SPACE);
        log.append(methodName)
                .append(SPACE)
                .append(ARGS);
        if (joinPoint.getArgs() != null) {
            log.append(parser.parseToJson(joinPoint.getArgs()));
        }
        log.append(SPACE)
                .append(RESULT);
        String returnType = o.getClass().getSimpleName();
        if (!returnType.equals(void.class.getSimpleName())) {
            log.append(parser.parseToJson(o));
        } else {
            log.append(VOID);
        }
        return log.toString();
    }
}