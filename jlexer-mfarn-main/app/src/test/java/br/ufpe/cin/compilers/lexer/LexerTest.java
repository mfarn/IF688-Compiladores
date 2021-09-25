package br.ufpe.cin.compilers.lexer;

import org.junit.Test;
import static org.junit.Assert.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.io.IOException;


public class LexerTest {
    @Test
	public void testBinarySearch() throws IOException {
        String content = Files.readString(Path.of("src/test/resources/BinarySearch.java"), StandardCharsets.US_ASCII);
        Lexer lexer = new Lexer(content);
        List<Token> tokens = lexer.tokens();
        
		assertEquals(649, tokens.size());
        assertEquals(TipoToken.IDENT, tokens.get(321).tipo);
        assertEquals(TipoToken.L_PAREN, tokens.get(172).tipo);
        assertEquals(TipoToken.IDENT, tokens.get(415).tipo);
        assertEquals(TipoToken.COMMA, tokens.get(459).tipo);
        assertEquals(TipoToken.IDENT, tokens.get(450).tipo);
        assertEquals(TipoToken.SEMICOLON, tokens.get(508).tipo);
        assertEquals(TipoToken.IDENT, tokens.get(215).tipo);
        assertEquals(TipoToken.NUMBER, tokens.get(128).tipo);
        assertEquals(TipoToken.EQ, tokens.get(269).tipo);
        assertEquals(TipoToken.SEMICOLON, tokens.get(569).tipo);
        assertEquals(TipoToken.IDENT, tokens.get(115).tipo);
        assertEquals(TipoToken.SEMICOLON, tokens.get(39).tipo);
        assertEquals(TipoToken.NUMBER, tokens.get(142).tipo);
        assertEquals(TipoToken.SYSTEM_OUT_PRINTLN, tokens.get(86).tipo);
        assertEquals(TipoToken.STRING, tokens.get(8).tipo);
        assertEquals(TipoToken.NUMBER, tokens.get(524).tipo);
        assertEquals(TipoToken.COMMA, tokens.get(459).tipo);
        assertEquals(TipoToken.R_BRACK, tokens.get(643).tipo);
        assertEquals(TipoToken.MAIN, tokens.get(6).tipo);
        assertEquals(TipoToken.NUMBER, tokens.get(440).tipo);        
	}

	@Test
	public void testBinaryTree() throws IOException {
    	String content = Files.readString(Path.of("src/test/resources/BinaryTree.java"), StandardCharsets.US_ASCII);
        Lexer lexer = new Lexer(content);
        List<Token> tokens = lexer.tokens();
        
		assertEquals(1350, tokens.size());
        assertEquals(TipoToken.IDENT, tokens.get(330).tipo);
        assertEquals(TipoToken.IDENT, tokens.get(217).tipo);
        assertEquals(TipoToken.EQ, tokens.get(129).tipo);
        assertEquals(TipoToken.IDENT, tokens.get(1163).tipo);
        assertEquals(TipoToken.R_BRACK, tokens.get(1239).tipo);
        assertEquals(TipoToken.R_BRACK, tokens.get(943).tipo);
        assertEquals(TipoToken.R_BRACK, tokens.get(1348).tipo);
        assertEquals(TipoToken.IDENT, tokens.get(527).tipo);
        assertEquals(TipoToken.R_PAREN, tokens.get(529).tipo);
        assertEquals(TipoToken.BOOLEAN, tokens.get(370).tipo);
        assertEquals(TipoToken.IDENT, tokens.get(1074).tipo);
        assertEquals(TipoToken.DOT, tokens.get(769).tipo);
        assertEquals(TipoToken.DOT, tokens.get(1073).tipo);
        assertEquals(TipoToken.IDENT, tokens.get(137).tipo);
        assertEquals(TipoToken.L_BRACK, tokens.get(1276).tipo);
        assertEquals(TipoToken.IDENT, tokens.get(1205).tipo);
        assertEquals(TipoToken.IDENT, tokens.get(128).tipo);
        assertEquals(TipoToken.LT, tokens.get(534).tipo);
        assertEquals(TipoToken.R_PAREN, tokens.get(401).tipo);
	}

	@Test
	public void testBubbleSort() throws IOException {
        String content = Files.readString(Path.of("src/test/resources/BubbleSort.java"), StandardCharsets.US_ASCII);
        Lexer lexer = new Lexer(content);
        List<Token> tokens = lexer.tokens();
        
		assertEquals(377, tokens.size());
        assertEquals(TipoToken.IDENT, tokens.get(11).tipo);
        assertEquals(TipoToken.SEMICOLON, tokens.get(148).tipo);
        assertEquals(TipoToken.EQ, tokens.get(362).tipo);
        assertEquals(TipoToken.IDENT, tokens.get(207).tipo);
        assertEquals(TipoToken.IDENT, tokens.get(358).tipo);
        assertEquals(TipoToken.IDENT, tokens.get(232).tipo);
        assertEquals(TipoToken.EQ, tokens.get(74).tipo);
        assertEquals(TipoToken.EQ, tokens.get(369).tipo);
        assertEquals(TipoToken.IDENT, tokens.get(225).tipo);
        assertEquals(TipoToken.ELSE, tokens.get(218).tipo);
        assertEquals(TipoToken.R_SQBRACK, tokens.get(208).tipo);
        assertEquals(TipoToken.VOID, tokens.get(5).tipo);
        assertEquals(TipoToken.L_SQBRACK, tokens.get(310).tipo);
        assertEquals(TipoToken.SEMICOLON, tokens.get(137).tipo);
        assertEquals(TipoToken.L_SQBRACK, tokens.get(196).tipo);
        assertEquals(TipoToken.SEMICOLON, tokens.get(104).tipo);
        assertEquals(TipoToken.IDENT, tokens.get(38).tipo);
        assertEquals(TipoToken.INT, tokens.get(297).tipo);
        assertEquals(TipoToken.NEW, tokens.get(296).tipo);
        assertEquals(TipoToken.R_BRACK, tokens.get(277).tipo);
	}

	@Test
	public void testLinearSearch() throws IOException {
        String content = Files.readString(Path.of("src/test/resources/LinearSearch.java"), StandardCharsets.US_ASCII);
        Lexer lexer = new Lexer(content);
        List<Token> tokens = lexer.tokens();

        assertEquals(361, tokens.size());
        assertEquals(TipoToken.IDENT, tokens.get(268).tipo);
        assertEquals(TipoToken.R_BRACK, tokens.get(266).tipo);
        assertEquals(TipoToken.SEMICOLON, tokens.get(312).tipo);
        assertEquals(TipoToken.SEMICOLON, tokens.get(186).tipo);
        assertEquals(TipoToken.R_PAREN, tokens.get(124).tipo);
        assertEquals(TipoToken.R_BRACK, tokens.get(259).tipo);
        assertEquals(TipoToken.IDENT, tokens.get(334).tipo);
        assertEquals(TipoToken.SEMICOLON, tokens.get(183).tipo);
        assertEquals(TipoToken.NUMBER, tokens.get(311).tipo);
        assertEquals(TipoToken.SEMICOLON, tokens.get(265).tipo);
        assertEquals(TipoToken.IDENT, tokens.get(150).tipo);
        assertEquals(TipoToken.SEMICOLON, tokens.get(250).tipo);
        assertEquals(TipoToken.IDENT, tokens.get(255).tipo);
        assertEquals(TipoToken.R_PAREN, tokens.get(83).tipo);
        assertEquals(TipoToken.SEMICOLON, tokens.get(36).tipo);
        assertEquals(TipoToken.INT, tokens.get(169).tipo);
        assertEquals(TipoToken.SYSTEM_OUT_PRINTLN, tokens.get(14).tipo);
        assertEquals(TipoToken.SEMICOLON, tokens.get(348).tipo);
        assertEquals(TipoToken.R_PAREN, tokens.get(113).tipo);
	}

	@Test
	public void testLinkedList() throws IOException {
        String content = Files.readString(Path.of("src/test/resources/LinkedList.java"), StandardCharsets.US_ASCII);
        Lexer lexer = new Lexer(content);
        List<Token> tokens = lexer.tokens();

		assertEquals(1110, tokens.size());
        assertEquals(TipoToken.NUMBER, tokens.get(445).tipo);
        assertEquals(TipoToken.R_PAREN, tokens.get(404).tipo);
        assertEquals(TipoToken.PUBLIC, tokens.get(705).tipo);
        assertEquals(TipoToken.BOOLEAN, tokens.get(290).tipo);
        assertEquals(TipoToken.IDENT, tokens.get(745).tipo);
        assertEquals(TipoToken.IDENT, tokens.get(915).tipo);
        assertEquals(TipoToken.EQ, tokens.get(171).tipo);
        assertEquals(TipoToken.NUMBER, tokens.get(1032).tipo);
        assertEquals(TipoToken.BOOLEAN, tokens.get(383).tipo);
        assertEquals(TipoToken.SEMICOLON, tokens.get(191).tipo);
        assertEquals(TipoToken.SEMICOLON, tokens.get(567).tipo);
        assertEquals(TipoToken.IDENT, tokens.get(682).tipo);
        assertEquals(TipoToken.NEW, tokens.get(1021).tipo);
        assertEquals(TipoToken.IDENT, tokens.get(321).tipo);
        assertEquals(TipoToken.IDENT, tokens.get(163).tipo);
        assertEquals(TipoToken.IDENT, tokens.get(38).tipo);
        assertEquals(TipoToken.DOT, tokens.get(162).tipo);
        assertEquals(TipoToken.L_PAREN, tokens.get(491).tipo);
        assertEquals(TipoToken.IDENT, tokens.get(1092).tipo);
        assertEquals(TipoToken.L_PAREN, tokens.get(199).tipo);
	}

	@Test
	public void testQuicksort() throws IOException {
        String content = Files.readString(Path.of("src/test/resources/QuickSort.java"), StandardCharsets.US_ASCII);
        Lexer lexer = new Lexer(content);
        List<Token> tokens = lexer.tokens();

		assertEquals(499, tokens.size());
        assertEquals(TipoToken.INT, tokens.get(408).tipo);
        assertEquals(TipoToken.SEMICOLON, tokens.get(236).tipo);
        assertEquals(TipoToken.SEMICOLON, tokens.get(486).tipo);
        assertEquals(TipoToken.L_SQBRACK, tokens.get(269).tipo);
        assertEquals(TipoToken.EQ, tokens.get(296).tipo);
        assertEquals(TipoToken.NOT, tokens.get(239).tipo);
        assertEquals(TipoToken.SEMICOLON, tokens.get(130).tipo);
        assertEquals(TipoToken.IDENT, tokens.get(325).tipo);
        assertEquals(TipoToken.IDENT, tokens.get(387).tipo);
        assertEquals(TipoToken.NUMBER, tokens.get(357).tipo);
        assertEquals(TipoToken.SEMICOLON, tokens.get(352).tipo);
        assertEquals(TipoToken.R_PAREN, tokens.get(172).tipo);
        assertEquals(TipoToken.IDENT, tokens.get(421).tipo);
        assertEquals(TipoToken.SEMICOLON, tokens.get(136).tipo);
        assertEquals(TipoToken.SEMICOLON, tokens.get(293).tipo);
        assertEquals(TipoToken.R_SQBRACK, tokens.get(303).tipo);
        assertEquals(TipoToken.NUMBER, tokens.get(457).tipo);
        assertEquals(TipoToken.SEMICOLON, tokens.get(249).tipo);
        assertEquals(TipoToken.TRUE, tokens.get(297).tipo);
        assertEquals(TipoToken.INT, tokens.get(134).tipo);
	}
}
