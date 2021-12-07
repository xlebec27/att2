package cs.vsu.ru.chinese_checkers.Utils;

import java.io.*;
import java.nio.file.FileSystems;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ScriptedCommandsProvider {

    private BufferedReader br;

    public ScriptedCommandsProvider(File file) throws FileNotFoundException {
        br = new BufferedReader(new FileReader(file));
    }

    public ScriptedCommandsProvider() {
        try {
            br = new BufferedReader(new FileReader("src\\demoplay.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


        public String getNextLine() {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
            try {
                return br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
}
