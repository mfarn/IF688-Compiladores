package br.ufpe.cin.compilers.lexer;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.io.IOException;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        try {
            String content = Files.readString(Path.of("src/test/resources/Simple.java"), StandardCharsets.US_ASCII);
            Lexer lexer = new Lexer(content);
            List<Token> tokens = lexer.tokens();
            System.out.println(tokens.size());
            //System.out.println(tokens);
            for (Token t : tokens) {
                System.out.println("TokenType." + t.tipo + " ("+t.lexema+")");
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        
    }
}
