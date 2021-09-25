package br.ufpe.cin.compilers.lexer;

public class Token {

    public TipoToken tipo;
    public String lexema;
    
    public Token(TipoToken t, String l) {
        tipo = t;
        lexema = l;
    }

    public static TipoToken checaPalavraChave(String nome) {
        for (TipoToken t : TipoToken.values()) {
            if (nome.toUpperCase().equals(t.name()) && t.getTipo() > 100 && t.getTipo() < 200) {
                return t;
            }
        }
        return TipoToken.IDENT;
    }

    @Override
    public String toString() {
        return "Token [tipo = " + tipo + ", lexema = '"+lexema+"']";
    }
}
