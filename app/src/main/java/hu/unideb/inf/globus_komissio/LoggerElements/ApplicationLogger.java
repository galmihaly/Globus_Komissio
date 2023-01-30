package hu.unideb.inf.globus_komissio.LoggerElements;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

import hu.unideb.inf.globus_komissio.BuildConfig;

public class ApplicationLogger {

    private static List<LogObject> myLogObjects = new ArrayList<>();

    public static void logging(LogLevel logLevel, String message){
        MethodCallCounter.add();

        switch (logLevel){
            case DEBUG: myLogObjects.add(ApplicationLogger.debug(message));
            case ERROR: myLogObjects.add(ApplicationLogger.error(message));
            case FATAL: myLogObjects.add(ApplicationLogger.fatal(message));
            case TRACE: myLogObjects.add(ApplicationLogger.trace(message));
            case WARNING: myLogObjects.add(ApplicationLogger.warning(message));
            case INFORMATION: myLogObjects.add(ApplicationLogger.info(message));
            default:
        }
    }

    private static LogObject debug(String debugMessage){
        MethodCallCounter.add();

        /*
         - az aktuális Thread két függvényhívása miatt
         - a Stack Trace e két fügvényhívás után áll össze, ezért a Stack Trace szintje ezek miatt nő 2-vel
         */
        MethodCallCounter.addNumber(2);
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[MethodCallCounter.counter - 1];

        String zonedDateTime = getUTCDateTimeString();
        LogObject logObject = new LogObject(LogLevel.DEBUG, stackTraceElement, zonedDateTime, debugMessage);

        if(BuildConfig.DEBUG) Log.d(null, logObject.toString());
        MethodCallCounter.clear();

        return logObject;
    }

    private static LogObject error(String errorMessage){
        MethodCallCounter.add();

        MethodCallCounter.addNumber(2);
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[MethodCallCounter.counter - 1];

        String zonedDateTime = getUTCDateTimeString();
        LogObject logObject = new LogObject(LogLevel.ERROR, stackTraceElement, zonedDateTime, errorMessage);


        if(BuildConfig.DEBUG) Log.e(null, logObject.toString());
        MethodCallCounter.clear();

        return logObject;
    }

    private static LogObject fatal(String fatalMessage){
        MethodCallCounter.add();

        MethodCallCounter.addNumber(2);
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[MethodCallCounter.counter - 1];

        String zonedDateTime = getUTCDateTimeString();
        LogObject logObject = new LogObject(LogLevel.FATAL, stackTraceElement, zonedDateTime, fatalMessage);

        if(BuildConfig.DEBUG) Log.e(null, logObject.toString());
        MethodCallCounter.clear();

        return logObject;
    }

    private static LogObject trace(String traceMessage){
        MethodCallCounter.add();

        MethodCallCounter.addNumber(2);
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[MethodCallCounter.counter - 1];

        String zonedDateTime = getUTCDateTimeString();
        LogObject logObject = new LogObject(LogLevel.TRACE, stackTraceElement, zonedDateTime, traceMessage);

        if(BuildConfig.DEBUG) Log.i(null, logObject.toString());
        MethodCallCounter.clear();

        return logObject;
    }

    private static LogObject warning(String warningMessage){
        MethodCallCounter.add();

        MethodCallCounter.addNumber(2);
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[MethodCallCounter.counter - 1];

        String zonedDateTime = getUTCDateTimeString();
        LogObject logObject = new LogObject(LogLevel.WARNING, stackTraceElement, zonedDateTime, warningMessage);

        if(BuildConfig.DEBUG) Log.w(null, logObject.toString());
        MethodCallCounter.clear();

        return logObject;
    }

    private static LogObject info(String infoMessage){
        MethodCallCounter.add();

        /*
         - az aktuális Thread két függvényhívása miatt
         - a Stack Trace e két fügvényhívás után áll össze, ezért a Stack Trace szintje ezek miatt nő 2-vel
         */
        MethodCallCounter.addNumber(2);
        StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[MethodCallCounter.counter - 1];

        String zonedDateTime = getUTCDateTimeString();
        LogObject logObject = new LogObject(LogLevel.INFORMATION, stackTraceElement, zonedDateTime, infoMessage);

        if(BuildConfig.DEBUG) Log.i(null, logObject.toString());
        MethodCallCounter.clear();

        return logObject;
    }

    private static String getUTCDateTimeString(){
        Calendar date = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

        return df.format(date.getTimeInMillis()) + " " + date.getTimeZone().getID();
    }

}
