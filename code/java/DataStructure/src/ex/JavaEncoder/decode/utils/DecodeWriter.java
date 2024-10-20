package decode.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class DecodeWriter {
    public static void write(String content) throws Exception{
        File outDir = new File("out/");
        if (!outDir.exists()) {
            if (!outDir.mkdir()) {
                throw new Error();
            }
        }
        File decodedFile = new File(outDir, "decoded_string.txt");
        try (BufferedWriter file = new BufferedWriter(new FileWriter(decodedFile))) {
            file.write(content);
        }
    }
}
