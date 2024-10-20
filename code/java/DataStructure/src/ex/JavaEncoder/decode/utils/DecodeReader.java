package decode.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DecodeReader {
    private String token;

    public DecodeReader(String filePath) {
        this.readTokenFile(filePath);
    }

    private void readTokenFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            token = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getToken() {
        return token;
    }
}
