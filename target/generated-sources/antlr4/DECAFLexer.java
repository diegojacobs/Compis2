// Generated from DECAF.g4 by ANTLR 4.4
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class DECAFLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__23=1, T__22=2, T__21=3, T__20=4, T__19=5, T__18=6, T__17=7, T__16=8, 
		T__15=9, T__14=10, T__13=11, T__12=12, T__11=13, T__10=14, T__9=15, T__8=16, 
		T__7=17, T__6=18, T__5=19, T__4=20, T__3=21, T__2=22, T__1=23, T__0=24, 
		IF=25, WHILE=26, ELSE=27, CLASS=28, STRUCT=29, VOID=30, INT=31, CHAR=32, 
		BOOL=33, TRUE=34, FALSE=35, RETURN=36, ID=37, NUM=38, CHR=39, WS=40, COMMENTS=41;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'", "'\\u001A'", "'\\u001B'", "'\\u001C'", "'\\u001D'", "'\\u001E'", 
		"'\\u001F'", "' '", "'!'", "'\"'", "'#'", "'$'", "'%'", "'&'", "'''", 
		"'('", "')'"
	};
	public static final String[] ruleNames = {
		"T__23", "T__22", "T__21", "T__20", "T__19", "T__18", "T__17", "T__16", 
		"T__15", "T__14", "T__13", "T__12", "T__11", "T__10", "T__9", "T__8", 
		"T__7", "T__6", "T__5", "T__4", "T__3", "T__2", "T__1", "T__0", "IF", 
		"WHILE", "ELSE", "CLASS", "STRUCT", "VOID", "INT", "CHAR", "BOOL", "TRUE", 
		"FALSE", "RETURN", "LETTER", "DIGIT", "CARACTER", "ID", "NUM", "CHR", 
		"WS", "COMMENTS"
	};


	public DECAFLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "DECAF.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2+\u0100\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\7\3\7"+
		"\3\b\3\b\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16"+
		"\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25"+
		"\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\32\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35"+
		"\3\35\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3 \3"+
		" \3 \3 \3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#"+
		"\3$\3$\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\3&\3&\3\'\3\'\3(\5(\u00da\n(\3"+
		")\3)\3)\7)\u00df\n)\f)\16)\u00e2\13)\3*\3*\7*\u00e6\n*\f*\16*\u00e9\13"+
		"*\3+\3+\3+\3+\3,\6,\u00f0\n,\r,\16,\u00f1\3,\3,\3-\3-\3-\3-\7-\u00fa\n"+
		"-\f-\16-\u00fd\13-\3-\3-\2\2.\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13"+
		"\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61"+
		"\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\2M\2O\2Q\'S(U)W*Y+\3\2\6"+
		"\4\2C\\c|\4\2\13\f\"\u0080\5\2\13\f\17\17\"\"\4\2\f\f\17\17\u0101\2\3"+
		"\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2"+
		"\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31"+
		"\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2"+
		"\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2"+
		"\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2"+
		"\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2"+
		"I\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\3[\3"+
		"\2\2\2\5]\3\2\2\2\7`\3\2\2\2\tc\3\2\2\2\13f\3\2\2\2\rh\3\2\2\2\17j\3\2"+
		"\2\2\21l\3\2\2\2\23o\3\2\2\2\25q\3\2\2\2\27t\3\2\2\2\31v\3\2\2\2\33x\3"+
		"\2\2\2\35z\3\2\2\2\37|\3\2\2\2!\177\3\2\2\2#\u0081\3\2\2\2%\u0083\3\2"+
		"\2\2\'\u0085\3\2\2\2)\u0087\3\2\2\2+\u0089\3\2\2\2-\u008b\3\2\2\2/\u008d"+
		"\3\2\2\2\61\u008f\3\2\2\2\63\u0091\3\2\2\2\65\u0094\3\2\2\2\67\u009a\3"+
		"\2\2\29\u009f\3\2\2\2;\u00a5\3\2\2\2=\u00ac\3\2\2\2?\u00b1\3\2\2\2A\u00b5"+
		"\3\2\2\2C\u00ba\3\2\2\2E\u00c2\3\2\2\2G\u00c7\3\2\2\2I\u00cd\3\2\2\2K"+
		"\u00d4\3\2\2\2M\u00d6\3\2\2\2O\u00d9\3\2\2\2Q\u00db\3\2\2\2S\u00e3\3\2"+
		"\2\2U\u00ea\3\2\2\2W\u00ef\3\2\2\2Y\u00f5\3\2\2\2[\\\7\61\2\2\\\4\3\2"+
		"\2\2]^\7#\2\2^_\7?\2\2_\6\3\2\2\2`a\7@\2\2ab\7?\2\2b\b\3\2\2\2cd\7~\2"+
		"\2de\7~\2\2e\n\3\2\2\2fg\7]\2\2g\f\3\2\2\2hi\7=\2\2i\16\3\2\2\2jk\7}\2"+
		"\2k\20\3\2\2\2lm\7?\2\2mn\7?\2\2n\22\3\2\2\2op\7>\2\2p\24\3\2\2\2qr\7"+
		"(\2\2rs\7(\2\2s\26\3\2\2\2tu\7?\2\2u\30\3\2\2\2vw\7_\2\2w\32\3\2\2\2x"+
		"y\7\177\2\2y\34\3\2\2\2z{\7@\2\2{\36\3\2\2\2|}\7>\2\2}~\7?\2\2~ \3\2\2"+
		"\2\177\u0080\7#\2\2\u0080\"\3\2\2\2\u0081\u0082\7\'\2\2\u0082$\3\2\2\2"+
		"\u0083\u0084\7*\2\2\u0084&\3\2\2\2\u0085\u0086\7+\2\2\u0086(\3\2\2\2\u0087"+
		"\u0088\7,\2\2\u0088*\3\2\2\2\u0089\u008a\7-\2\2\u008a,\3\2\2\2\u008b\u008c"+
		"\7.\2\2\u008c.\3\2\2\2\u008d\u008e\7/\2\2\u008e\60\3\2\2\2\u008f\u0090"+
		"\7\60\2\2\u0090\62\3\2\2\2\u0091\u0092\7k\2\2\u0092\u0093\7h\2\2\u0093"+
		"\64\3\2\2\2\u0094\u0095\7y\2\2\u0095\u0096\7j\2\2\u0096\u0097\7k\2\2\u0097"+
		"\u0098\7n\2\2\u0098\u0099\7g\2\2\u0099\66\3\2\2\2\u009a\u009b\7g\2\2\u009b"+
		"\u009c\7n\2\2\u009c\u009d\7u\2\2\u009d\u009e\7g\2\2\u009e8\3\2\2\2\u009f"+
		"\u00a0\7e\2\2\u00a0\u00a1\7n\2\2\u00a1\u00a2\7c\2\2\u00a2\u00a3\7u\2\2"+
		"\u00a3\u00a4\7u\2\2\u00a4:\3\2\2\2\u00a5\u00a6\7u\2\2\u00a6\u00a7\7v\2"+
		"\2\u00a7\u00a8\7t\2\2\u00a8\u00a9\7w\2\2\u00a9\u00aa\7e\2\2\u00aa\u00ab"+
		"\7v\2\2\u00ab<\3\2\2\2\u00ac\u00ad\7x\2\2\u00ad\u00ae\7q\2\2\u00ae\u00af"+
		"\7k\2\2\u00af\u00b0\7f\2\2\u00b0>\3\2\2\2\u00b1\u00b2\7k\2\2\u00b2\u00b3"+
		"\7p\2\2\u00b3\u00b4\7v\2\2\u00b4@\3\2\2\2\u00b5\u00b6\7e\2\2\u00b6\u00b7"+
		"\7j\2\2\u00b7\u00b8\7c\2\2\u00b8\u00b9\7t\2\2\u00b9B\3\2\2\2\u00ba\u00bb"+
		"\7d\2\2\u00bb\u00bc\7q\2\2\u00bc\u00bd\7q\2\2\u00bd\u00be\7n\2\2\u00be"+
		"\u00bf\7g\2\2\u00bf\u00c0\7c\2\2\u00c0\u00c1\7p\2\2\u00c1D\3\2\2\2\u00c2"+
		"\u00c3\7v\2\2\u00c3\u00c4\7t\2\2\u00c4\u00c5\7w\2\2\u00c5\u00c6\7g\2\2"+
		"\u00c6F\3\2\2\2\u00c7\u00c8\7h\2\2\u00c8\u00c9\7c\2\2\u00c9\u00ca\7n\2"+
		"\2\u00ca\u00cb\7u\2\2\u00cb\u00cc\7g\2\2\u00ccH\3\2\2\2\u00cd\u00ce\7"+
		"t\2\2\u00ce\u00cf\7g\2\2\u00cf\u00d0\7v\2\2\u00d0\u00d1\7w\2\2\u00d1\u00d2"+
		"\7t\2\2\u00d2\u00d3\7p\2\2\u00d3J\3\2\2\2\u00d4\u00d5\t\2\2\2\u00d5L\3"+
		"\2\2\2\u00d6\u00d7\4\62;\2\u00d7N\3\2\2\2\u00d8\u00da\t\3\2\2\u00d9\u00d8"+
		"\3\2\2\2\u00daP\3\2\2\2\u00db\u00e0\5K&\2\u00dc\u00df\5K&\2\u00dd\u00df"+
		"\5M\'\2\u00de\u00dc\3\2\2\2\u00de\u00dd\3\2\2\2\u00df\u00e2\3\2\2\2\u00e0"+
		"\u00de\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1R\3\2\2\2\u00e2\u00e0\3\2\2\2"+
		"\u00e3\u00e7\5M\'\2\u00e4\u00e6\5M\'\2\u00e5\u00e4\3\2\2\2\u00e6\u00e9"+
		"\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8T\3\2\2\2\u00e9"+
		"\u00e7\3\2\2\2\u00ea\u00eb\7)\2\2\u00eb\u00ec\5O(\2\u00ec\u00ed\7)\2\2"+
		"\u00edV\3\2\2\2\u00ee\u00f0\t\4\2\2\u00ef\u00ee\3\2\2\2\u00f0\u00f1\3"+
		"\2\2\2\u00f1\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3"+
		"\u00f4\b,\2\2\u00f4X\3\2\2\2\u00f5\u00f6\7\61\2\2\u00f6\u00f7\7\61\2\2"+
		"\u00f7\u00fb\3\2\2\2\u00f8\u00fa\n\5\2\2\u00f9\u00f8\3\2\2\2\u00fa\u00fd"+
		"\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fe\3\2\2\2\u00fd"+
		"\u00fb\3\2\2\2\u00fe\u00ff\b-\3\2\u00ffZ\3\2\2\2\t\2\u00d9\u00de\u00e0"+
		"\u00e7\u00f1\u00fb\4\b\2\2\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}