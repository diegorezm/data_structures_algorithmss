package encode.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class EncodeReader {
    private String content;

    public EncodeReader(String path) {
        this.read(path);
    }

    public void read(String path) {
        StringBuilder contentBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!contentBuilder.isEmpty()) {
                    contentBuilder.append(System.lineSeparator());
                }
                contentBuilder.append(line);
            }
            content = contentBuilder.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("ERRO: Arquivo não encontrado.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERRO: " + e.getMessage());
        }
    }

    public char[] toCharArray() {
        return content.toCharArray();
    }

    public String getContent() {
        return content;
    }
}
