package encode.utils;

// Utilitário para realizar o print das informações geradas
// pela classe Tree
public class TreeParser {
    private char[] charContent;
    private char[] characters;
    private String[] codes;
    private String token;

    public TreeParser(char[] charContent, char[] characters, String[] codes) {
        this.charContent = charContent;
        this.characters = characters;
        this.codes = codes;
        this.genEncodedString();
    }

    private void genEncodedString() {
        StringBuilder encodedText = new StringBuilder();
        for (char c : this.charContent) {
            for (int i = 0; i < this.characters.length; i++) {
                if (characters[i] == c) {
                    encodedText.append(this.codes[i]);
                    break;
                }
            }
        }
        this.token = encodedText.toString();
    }

    public void getCharCodeTable() {
        for (int i = 0; i < this.characters.length; i++) {
            System.out.println("char: " + characters[i] + ", código: " + codes[i]);
        }
    }

    public char[] getCharContent() {
        return charContent;
    }

    public void setCharContent(char[] charContent) {
        this.charContent = charContent;
    }

    public char[] getCharacters() {
        return characters;
    }

    public void setCharacters(char[] characters) {
        this.characters = characters;
    }

    public String[] getCodes() {
        return codes;
    }

    public String getToken() {
        return this.token;
    }

    public void setCodes(String[] codes) {
        this.codes = codes;
    }
}
