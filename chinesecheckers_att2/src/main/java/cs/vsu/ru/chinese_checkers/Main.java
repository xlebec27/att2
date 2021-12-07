package cs.vsu.ru.chinese_checkers;

import cs.vsu.ru.chinese_checkers.FrameApplication.ApplicationFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    public static void main(String[] args){
        log.info("Application started");
        ApplicationFrame.launch(ApplicationFrame.class, args);
        log.info("Application closed");
    }

    private static final Logger log = LoggerFactory.getLogger(Main.class);
}
