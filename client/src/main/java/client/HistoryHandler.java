package client;

import java.io.*;
import java.util.ArrayList;

public class HistoryHandler { // Класс для обработки хистори-файлов
    String login;
    private File file;
    private BufferedReader reader;
    private PrintWriter writer;

    public HistoryHandler(String login) { // Конструктор, проверяет, есть ли файл с таким именем, если нет - создает
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

    public ArrayList <String> stringsInFile (File file) { // Метод для вывода последних строк из хистори.
        ArrayList<String> strings;    // У меня очень медленно работает, поэтому сократил число строк до 20
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
                strings.subList(0, head + 1).clear();
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
