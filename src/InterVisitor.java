import java.util.ArrayList;
import java.util.Stack;


/**
@author Diego Jacobs
Date: Apr 15, 2016
 */
public class InterVisitor<T> extends DECAFBaseVisitor<Object> {
	
	private Ambito ScopeActual;
	private String Etiqueta;
	private Stack pila;
	private ArrayList<StackPointer> sp;
	
	public InterVisitor(String etiqueta, Stack pila) {
		this.Etiqueta = new String();
		this.pila = new Stack();
	}


	@Override
	public Object visitProgram(DECAFParser.ProgramContext ctx) {
		for (int i = 0;i<ctx.getChildCount();i++){
	           this.visit(ctx.getChild(i));
	        }
		
		// TODO Auto-generated method stub
		return new String();
	}
	
	
}
