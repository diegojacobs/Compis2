import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
Diego Jacobs
Jan 27, 2016
Tomado de: 
 */

public class DescriptiveErrorListener extends BaseErrorListener {
    public static DescriptiveErrorListener INSTANCE = new DescriptiveErrorListener();
    private static final boolean REPORT_SYNTAX_ERRORS = true;
    public static String errores = new String();
    
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg, RecognitionException e)
    {

        String sourceName = recognizer.getInputStream().getSourceName();
        if (!sourceName.isEmpty()) {
            sourceName = String.format("%s:%d:%d: ", sourceName, line, charPositionInLine);
        }
        
        String error = sourceName+"line "+line+":"+charPositionInLine+" "+msg;
        errores += error+"\n";
    }

	public static String getErrores() {
		return errores;
	}

	public static void setErrores(String errores) {
		DescriptiveErrorListener.errores = errores;
	}
}