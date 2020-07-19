package encryptdecrypt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        String op = "enc";
        int key = 0;
        String data = "";
        Boolean useData = false;
        String in = "";
        String out = "";
        Algorithm alg = new Shift();

        for (int i = 0; i < args.length - 1; i += 2) {
            switch (args[i]) {
                case "-mode":
                    op = args[i + 1];
                    break;
                case "-key":
                    key = Integer.parseInt(args[i + 1]);
                    break;
                case "-data":
                    data = args[i + 1];
                    useData = true;
                    break;
                case "-in":
                    in = args[i + 1];
                    break;
                case "-out":
                    out = args[i + 1];
                    break;
                case "-alg":
                    if ("unicode".equals(args[i + 1])) {
                        alg = new Unicode();
                    }
                    break;
                default:
                    System.out.println("Invalid argument");
            }
        }

        if (!useData) {
            try {
                data = readFileAsString(in);
            }
            catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        String output;
        AlgSender encDec = new AlgSender(alg);
        if ("enc".equals(op)) {
            output = encDec.encrypt(data, key);
        } else if ("dec".equals(op)) {
            output = encDec.decrypt(data, key);
        } else {
            output = "Invalid Operation";
        }

        if ("".equals(out)) {
            System.out.println(output);
        } else {
            try (FileWriter writer = new FileWriter(out)) {
                writer.write(output);
            }
            catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public static String readFileAsString(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }
}

interface Algorithm {
    String encrypt(String text, int key);

    String decrypt(String text, int key);
}

class Unicode implements Algorithm {
    public String encrypt(String text, int key) {
        StringBuilder encrypted = new StringBuilder();
        for (char ch : text.toCharArray()) {
            encrypted.append((char) (ch + key));
        }

        return encrypted.toString();
    }

    public String decrypt(String text, int key) {
        StringBuilder decrypted = new StringBuilder();
        for (char ch : text.toCharArray()) {
            decrypted.append((char) (ch - key));
        }

        return decrypted.toString();
    }
}

class Shift implements Algorithm {
    public String encrypt(String text, int key) {
        StringBuilder encrypted = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int shift;

            if (c >= 'a' && c <= 'z') {
                shift = (int) 'a';
            } else if (c >= 'A' && c <= 'Z') {
                shift = (int) 'A';
            } else {
                encrypted.append(c);
                continue;
            }

            char e = (char) (((c - shift + key) % 26) + shift);
            encrypted.append(e);
        }

        return encrypted.toString();
    }

    public String decrypt(String text, int key) {
        StringBuilder decrypted = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int shift = 0;
            if (c >= 'a' && c <= 'z') {
                shift = (int) 'a';
            } else if (c >= 'A' && c <= 'Z') {
                shift = (int) 'A';
            }

            int shifted = c - shift - key;
            char e;
            if (shifted < 0) {
                e = (char) (26 + shifted + shift);
            } else {
                e = (char) (shifted + shift);
            }
            decrypted.append(e);
        }

        return decrypted.toString();
    }
}

class AlgSender {
    Algorithm method;

    AlgSender(Algorithm method) {
        this.method = method;
    }

    public String encrypt(String text, int key) {
        return method.encrypt(text, key);
    }

    public String decrypt(String text, int key) {
        return method.decrypt(text, key);
    }
}
