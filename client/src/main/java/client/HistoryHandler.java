package client;

import java.io.*;
import java.util.ArrayList;

public class HistoryHandler {
    String login;
    private File file;
    private BufferedReader reader;
    private PrintWriter writer;

    public HistoryHandler(String login) {
        this.login = login;
        String path = "history_" + login + ".txt";
        file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("history of " + login + " is being logging");
    }

    public boolean writeToHistory (String string) {
        try {
            this.writer = new PrintWriter(new FileOutputStream(file,true));
            writer.write(string + "\n");
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            writer.close();
        }
    }

    public ArrayList <String> stringsInFile (File file) {
        ArrayList<String> strings = new ArrayList<>();
        strings = new ArrayList<>();
        String str;
        try {
            this.reader = new BufferedReader(new FileReader(file));
            while ((str = reader.readLine()) != null) {
                    strings.add(str);
            }
            int head = strings.size() - 20;
            if (head <= 0) {
                return strings;
            } else {
                for (int i = head; i >= 0; i--) {
                    strings.remove(i);
                }
                strings.trimToSize();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return strings;
    }

    public BufferedReader getReader() {
        return reader;
    }

    public PrintWriter getWriter() {
        return writer;
    }

    public File getFile() {
        return file;
    }
}
