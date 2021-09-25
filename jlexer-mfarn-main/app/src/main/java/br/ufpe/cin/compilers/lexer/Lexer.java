package br.ufpe.cin.compilers.lexer;

import java.util.List;
import java.util.ArrayList;

public class Lexer {
    private String codigoFonte;
    private char caractereAtual = ' ';
    private int posicaoAtual = -1;
    public final static char EOF = '\0';

    public Lexer(String entrada) {
        codigoFonte = entrada;
        proximoCaractere();
    }

    private void proximoCaractere() {
        posicaoAtual = posicaoAtual + 1;
        if (posicaoAtual >= codigoFonte.length()) {
            caractereAtual = EOF;
        }
        else {
            caractereAtual = codigoFonte.charAt(posicaoAtual);
        }
    }

    private char peek() {
        if (posicaoAtual + 1 >= codigoFonte.length()) {
            return EOF;
        }
        else {
            return codigoFonte.charAt(posicaoAtual+1);
        }
    }

    public Token getToken() {

        clean();

        Token t = null;

        if (caractereAtual == EOF) {
            t = new Token(TipoToken.EOF, "$");
        } else if (caractereAtual == '&') {
            if (peek() == '&') {
                char c = caractereAtual;
                proximoCaractere();
                t = new Token(TipoToken.AND, String.valueOf(c + caractereAtual));
            } else {
                erro("Token invalido: " + String.valueOf(caractereAtual));
            }
        } else if (caractereAtual == '<') {
            t = new Token(TipoToken.LT, String.valueOf(caractereAtual));
        } else if (caractereAtual == '=') {
            if (peek() == '=') {
                char c = caractereAtual;
                proximoCaractere();
                t = new Token(TipoToken.EQEQ, String.valueOf(c + caractereAtual));
            } else {
                t = new Token(TipoToken.EQ, String.valueOf(caractereAtual));
            }
        } else if (caractereAtual == '!') {
            if (peek() == '=') {
                char c = caractereAtual;
                proximoCaractere();
                t = new Token(TipoToken.NOTEQ, String.valueOf(c + caractereAtual));
            } else {
                t = new Token(TipoToken.NOT, String.valueOf(caractereAtual));
            }
        } else if (caractereAtual == '+') {
            t = new Token(TipoToken.PLUS, String.valueOf(caractereAtual));
        } else if (caractereAtual == '-') {
            t = new Token(TipoToken.MINUS, String.valueOf(caractereAtual));
        } else if (caractereAtual == '*') {
            t = new Token(TipoToken.MULT, String.valueOf(caractereAtual));
        } else if (caractereAtual == ';') {
            t = new Token(TipoToken.SEMICOLON, String.valueOf(caractereAtual));
        } else if (caractereAtual == '.') {
            t = new Token(TipoToken.DOT, String.valueOf(caractereAtual));
        } else if (caractereAtual == ',') {
            t = new Token(TipoToken.COMMA, String.valueOf(caractereAtual));
        } else if (caractereAtual == '(') {
            t = new Token(TipoToken.L_PAREN, String.valueOf(caractereAtual));
        } else if (caractereAtual == ')') {
            t = new Token(TipoToken.R_PAREN, String.valueOf(caractereAtual));
        } else if (caractereAtual == '{') {
            t = new Token(TipoToken.L_BRACK, String.valueOf(caractereAtual));
        } else if (caractereAtual == '}') {
            t = new Token(TipoToken.R_BRACK, String.valueOf(caractereAtual));
        } else if (caractereAtual == '[') {
            t = new Token(TipoToken.L_SQBRACK, String.valueOf(caractereAtual));
        } else if (caractereAtual == ']') {
            t = new Token(TipoToken.R_SQBRACK, String.valueOf(caractereAtual));
        } else if (Character.isDigit(caractereAtual)) {
            int inicio = posicaoAtual;
            while (Character.isDigit(peek())) {
                proximoCaractere();
            }
            int numero = Integer.parseInt(codigoFonte.substring(inicio, posicaoAtual+1));
            t = new Token(TipoToken.NUMBER, String.valueOf(numero));
        } else if (Character.isLetter(caractereAtual) || caractereAtual=='_') {
            int inicio = posicaoAtual;
            while (Character.isLetterOrDigit(peek()) || peek() == '_') {
                proximoCaractere();
            }
            String nome = codigoFonte.substring(inicio, posicaoAtual+1);
            if (nome.equals("System")) {
                proximoCaractere();
                if (caractereAtual == '.') {
                    while (Character.isLetterOrDigit(peek()) || peek() == '_') {
                        proximoCaractere();
                    }
                    nome = codigoFonte.substring(inicio, posicaoAtual + 1);
                    if (nome.equals("System.out")) {
                        proximoCaractere();
                        if (caractereAtual == '.') {
                            while (Character.isLetterOrDigit(peek()) || peek() == '_') {
                                proximoCaractere();
                            }
                            nome = codigoFonte.substring(inicio, posicaoAtual + 1);
                        }
                    }
                }
            }
            if(!nome.equals("System.out.println")) {
                TipoToken tipo = Token.checaPalavraChave(nome);
                t = new Token(tipo, nome);
            } else {
                t = new Token(TipoToken.SYSTEM_OUT_PRINTLN, String.valueOf(nome));
            }

        } else {
            erro("caractere invalido :" + caractereAtual);
        }
        proximoCaractere();

        return t;
    }

    private void erro(String msg) {
        throw new LexicalException(msg);
    }

    private void ignorarEspacosEmBranco() {
        while (caractereAtual == ' ' || caractereAtual == '\r' || caractereAtual == '\t' || caractereAtual =='\n') {
            proximoCaractere();
        }
        if (caractereAtual == '/') {
            ignorarComentarios();
        }
    }

    private void ignorarComentarios() {
        if (caractereAtual == '/') {
            if (peek() == '/')
                proximoCaractere();
            proximoCaractere();
            while (caractereAtual != '\n' && caractereAtual != '\r' && caractereAtual != '\t')
                proximoCaractere();
        } if (caractereAtual == ' ' || caractereAtual == '\r' || caractereAtual == '\t' || caractereAtual =='\n') {
            ignorarEspacosEmBranco();
        }
    }

    private void clean() {
        ignorarComentarios();
    }

    public List<Token> tokens() {
        List<Token> tokens = new ArrayList<>();
        Token t = getToken();
        do {
            tokens.add(t);
            t = getToken();
        } while (t.tipo != TipoToken.EOF);

        return tokens;
    }
}