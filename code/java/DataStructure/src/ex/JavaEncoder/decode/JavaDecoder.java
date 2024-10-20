package decode;

import decode.lib.Decoder;
import decode.utils.DecodeReader;
import decode.utils.DecodeWriter;
import encode.lib.Node;

public class JavaDecoder {
    public static void run(Node root) throws Exception {
        String filePath = "out/token.txt";
        DecodeReader reader = new DecodeReader(filePath);
        String token = reader.getToken();
        Decoder decoder = new Decoder(token, root);
        String content = decoder.getDecodedString();
        System.out.println(content);
        DecodeWriter.write(content);
    }
}
