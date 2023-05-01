package autotelegabot;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class FtpKey {

      public static HashMap<String, String> getFtpKey() throws IOException {

        File file = new File("Notes2.txt");
        Scanner scanner = new Scanner(new FileReader(file));
        String[] string = new String[2];
        HashMap<String,String> map = new HashMap<>();
        while (scanner.hasNextLine()) {
            string = scanner.nextLine().split(":");
            map.put(string[0],string[1]);
        }
          return map;
    }

}
