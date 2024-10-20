package encode.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class EncodeWriter {
    public static void write(String token) throws Exception {
        File outDir = new File("out/");
        if (!outDir.exists()) {
            if (!outDir.mkdir()) {
                throw new Error();
            }
        }
        File decodedFile = new File(outDir, "token.txt");
        try (BufferedWriter file = new BufferedWriter(new FileWriter(decodedFile))) {
            file.write(token);
        }
    }
}
