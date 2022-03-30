package calculator;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Workflow {
    private Queue<Pair<String, List<String>>> commandExecutionQueue;

    public Workflow() {
        commandExecutionQueue = new LinkedList<Pair<String, List<String>>>();
        String line;
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine())
        {
            line = scanner.nextLine();
            List<String> words = getListWordsFromString(line);
            String comand = words.get(0);
            words.remove(0);
            commandExecutionQueue.add(new Pair<>(comand, words));
        }
    }

    public Workflow(String fileName) throws IOException {
        commandExecutionQueue = new LinkedList<Pair<String, List<String>>>();
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            List<String> words = getListWordsFromString(line);
            String comand = words.get(0);
            words.remove(0);
            commandExecutionQueue.add(new Pair<>(comand, words));
        }
    }

    private List<String> getListWordsFromString(String string) {
        StringBuilder sourceString = new StringBuilder(string);
        StringBuilder buffer = new StringBuilder("");
        List<String> words = new ArrayList<>();
        char symbol;
        for (int i = 0; i < sourceString.length(); ++i) {
            symbol = sourceString.charAt(i);
            if (symbol != ' ') {
                buffer.append(symbol);
            } else {
                if (buffer.length() > 0) {
                    words.add(String.valueOf(buffer));
                    buffer.setLength(0);
                }
            }
        }
        if (buffer.length() > 0) {
            words.add(String.valueOf(buffer));
            buffer.setLength(0);
        }
        return words;
    }

    public void print() {
        System.out.println(commandExecutionQueue);
    }

    public Pair<String, List<String>> poll() {
        return commandExecutionQueue.poll();
    }

    public Pair<String, List<String>> peek() {
        return commandExecutionQueue.peek();
    }

    public int getSize() {
        return commandExecutionQueue.size();
    }
}
