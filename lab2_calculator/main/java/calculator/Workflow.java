package calculator;

import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Workflow {
    private static final Logger log = Logger.getLogger(Workflow.class.getName());
    private Queue<Pair<String, List<String>>> commandExecutionQueue;

    public Workflow() {
        commandExecutionQueue = new LinkedList<Pair<String, List<String>>>();
        String line;
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            List<String> words = getListWordsFromString(line);
            String comand = words.get(0);
            words.remove(0);
            commandExecutionQueue.add(new Pair<>(comand, words));
        }
    }

    public Workflow(String fileName) throws InitializationException {
        commandExecutionQueue = new LinkedList<Pair<String, List<String>>>();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(fileName);
        } catch (FileNotFoundException e) {
            InitializationException exception = new InitializationException(e);
            log.log(Level.SEVERE, "", exception);
            throw exception;
        }
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        while (true) {
            try {
                line = bufferedReader.readLine();
            } catch (IOException e) {
                InitializationException exception = new InitializationException(e);
                log.log(Level.SEVERE, "", exception);
                throw exception;
            }
            if (line == null) {
                break;
            }
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
