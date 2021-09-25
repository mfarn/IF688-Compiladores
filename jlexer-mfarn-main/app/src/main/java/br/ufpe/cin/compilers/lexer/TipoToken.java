package br.ufpe.cin.compilers.lexer;

public enum TipoToken {

    EOF(-1),
    TEST(1), //token usado apenas para executar o App.java inicialmente e testar
    NUMBER(1), //Numeros
    IDENT(2), //Identificadores
    LITERAL(3),

    //PALAVRAS RESERVADAS
    BOOLEAN(101),
    CLASS(102),
    PUBLIC(103),
    EXTENDS(104),
    STATIC(105),
    VOID(106),
    MAIN(107),
    STRING(108),
    INT(109),
    WHILE(110),
    IF(111),
    ELSE(112),
    RETURN(113),
    LENGTH(114),
    TRUE(115),
    FALSE(116),
    THIS(117),
    NEW(118),
    SYSTEM_OUT_PRINTLN(119),

    //OPERADORES
    AND(201), // &&
    LT(202), // <
    EQEQ(203), // ==
    NOTEQ(204), // !=
    PLUS(205), // +
    MINUS(206), // -
    MULT(207), // *
    NOT(208), // !

    //DELIMITADORES
    SEMICOLON(251), // ;
    DOT(252), // .
    COMMA(253), // ,
    EQ(254), // =
    L_PAREN(255), // (
    R_PAREN(256), // )
    L_BRACK(257), // {
    R_BRACK(258), // }
    L_SQBRACK(259), // [
    R_SQBRACK(260) // ]
        ;
    private final int tipo;
    TipoToken(int tipo) {
        this.tipo = tipo;
    }
    public int getTipo() {
        return tipo;
    }
}