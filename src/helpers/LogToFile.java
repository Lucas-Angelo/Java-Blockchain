package src.helpers;

import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class LogToFile {
    public static void log(String msg) {
        Logger logger = Logger.getLogger(LogToFile.class.getName());
        
        try {
            FileHandler fileHandler = new FileHandler("app.log", true);
            logger.addHandler(fileHandler);
            logger.info(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}