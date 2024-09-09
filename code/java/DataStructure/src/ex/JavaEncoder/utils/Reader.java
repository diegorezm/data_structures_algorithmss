package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
    private String content;

    public Reader(String path) {
        this.read(path);
    }

    public String read(String path) {
        StringBuilder contentBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (contentBuilder.length() > 0) {
                    contentBuilder.append(' ');
                }
                contentBuilder.append(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: Arquivo não encontrado.");
            return null;
        } catch (IOException e) {
            System.out.println("ERRO: " + e.getMessage());
            return null;
        }
        content = contentBuilder.toString();
        return content;
    }

    public char[] toCharArray() {
        return content.toCharArray();
    }

    public String getContent() {
        return content;
    }
}
