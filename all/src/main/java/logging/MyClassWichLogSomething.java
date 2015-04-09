package logging;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.logging.*;

public class MyClassWichLogSomething {

    public final Logger logger = LogManager.getLogManager().getLogger("myLogger");

    public MyClassWichLogSomething() {
        logger.finest("finest");
        logger.finer("finer");
        logger.fine("fine");
        logger.info("info");
        logger.config("config");
        logger.warning("warning");
        logger.severe("severe");
    }

    public static void main(String... args) {
        LogManager logManager = LogManager.getLogManager();
        Logger myLogger = logManager.getLogger("myLogger");
        if (myLogger == null) {
            myLogger = Logger.getLogger("myLogger");
            myLogger.setLevel(Level.ALL);
            myLogger.setUseParentHandlers(false);
            ConsoleHandler handler = new ConsoleHandler() {
                @Override
                protected synchronized void setOutputStream(OutputStream out) throws SecurityException {
                    super.setOutputStream(System.out);
                }
            };
            handler.setLevel(Level.ALL);
            Formatter myFormat = new Formatter() {

                public synchronized String format(LogRecord record) {
                    Date date = new Date(record.getMillis());
                    String message = formatMessage(record);
                    String throwable = "";
                    if (record.getThrown() != null) {
                        StringWriter sw = new StringWriter();
                        PrintWriter pw = new PrintWriter(sw);
                        pw.println();
                        record.getThrown().printStackTrace(pw);
                        pw.close();
                        throwable = sw.toString();
                    }
                    return String.format("%1$TF %1$TT'%1$TL %2$16s %3$s%4$s%n",
                            date,
                            record.getLoggerName(),
                            message,
                            throwable);
                }
            };
            handler.setFormatter(myFormat);
            myLogger.addHandler(handler);
        }
        new MyClassWichLogSomething();
    }

}
