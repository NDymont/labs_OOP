package tetris.Model;

import tetris.View.RegistrationView;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class ModelStatistic {
    public static HashMap<String, Integer> sortedMap = new HashMap<String, Integer>();
    private static FileWriter writer;
    private static FileReader reader;


    public static HashMap<String, Integer> getSortedMap() throws IOException {
        try {
            reader = new FileReader("statistic.txt");

            Scanner scanner = new Scanner(reader);
            String s;
            int k = 0;
            String name1 = null;
            int size = 0;
            while (scanner.hasNext()) {

                s = scanner.next();
                if (k == 0) {
                    name1 = s;
                }
                if (k == 1) {
                    try {
                        size = Integer.valueOf(s);
                        sortedMap.put(name1, size);
                        k = 0;
                        continue;
                    } catch (NumberFormatException error) {
                        error.printStackTrace();
                    }
                }
                ++k;
            }
        } catch (IOException error) {
            error.printStackTrace();
        }
        reader.close();
        int lines = Model.getLine();
        String name = RegistrationView.getName();
        writer = new FileWriter("statistic.txt");
        writer.write(name + " " + lines + "\n");
        writer.close();
        sortedMap.put(name, lines);
        HashMap<String, Integer> map = sortedMap.entrySet().stream()
                .sorted(Comparator.comparingInt(e -> -e.getValue()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> {
                            throw new AssertionError();
                        },
                        LinkedHashMap::new
                ));
        return map;
    }
}
