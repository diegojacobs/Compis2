import java.util.ArrayList;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.tree.ParseTree;


/**
@author Diego Jacobs
Date: Apr 15, 2016
 */
public class InterVisitor<T> extends DECAFBaseVisitor<Object> {
	private InterCode Codigo;
	private Ambito ScopeActual;	
	private ArrayList<StackPointer> sp;
	private String etiquetaActual;
    private int contEtiqueta;
    private int contTemporales;
    private String actualOp;
    private InterLine elseInt;
    private int contWhile;
    private TablaSimbolos tablaSimbolos;
    private TablaMetodos tableMethod;
    private TablaStruct tableStruct;
    private int globalCant;
    
	public InterVisitor(ParseTree tree, TablaSimbolos tableSymbol, TablaMetodos tableMethod, TablaStruct tableStruct) {
		this.sp = new ArrayList<StackPointer>();
		this.ScopeActual = new Ambito();
        this.ScopeActual.cont = 0;
        this.etiquetaActual = new String();
        this.contEtiqueta = 0;
        this.contTemporales = 0;
        this.Codigo = new InterCode(tableSymbol, tableMethod, tableStruct);
        this.elseInt = new InterLine();
        this.globalCant = 0;
        this.actualOp = new String();
        this.contWhile = 0;
        
        this.tablaSimbolos = tableSymbol;
        this.tableMethod = tableMethod;
        this.tableStruct = tableStruct;
        
        this.visit(tree);
	}
	
	public InterCode getCodigo() {
		return Codigo;
	}

	public void setCodigo(InterCode codigo) {
		Codigo = codigo;
	}

	@Override
	public Object visitProgram(DECAFParser.ProgramContext ctx) {
		for (int i = 0;i<ctx.getChildCount();i++)
		{
			this.visit(ctx.getChild(i));
	    }
		
		// TODO Auto-generated method stub
		return new String();
	}

	@Override
	public Object visitVarDeclaration(DECAFParser.VarDeclarationContext ctx) {
		
		InterLine code = new InterLine();
		
		switch (ctx.getChildCount())
		{
			case 3:
			{
		        if (this.ScopeActual.cont == 0) 
		        {  
		            code.setEtiqueta(ctx.getChild(1).getText() + "_global");
		            code.setGlobal(true);
		            this.Codigo.addCode(code);
		        }
		        else
		        {
			        String tipo = ctx.getChild(0).getText();
			        String id = ctx.getChild(1).getText();
			        if (tipo.equals("int"))
			        {
			            this.sp.add(new StackPointer(id, 4, tipo));
			        }
			        else 
			        	if (tipo.equals("char"))
			        	{
			        		this.sp.add(new StackPointer(id, 4,tipo));
			        	}
			        	else 
				        	if (tipo.equals("boolean"))
				        	{
				        		this.sp.add(new StackPointer(id, 4, tipo));
				        	}
			      
			        changeSP();
		        }
		        break;
			}
			case 6:
			{
				String tipo = ctx.getChild(0).getText();
		        String id = ctx.getChild(1).getText();
		        int tamaño = Integer.parseInt(ctx.getChild(3).getText());
		        
		        if (this.ScopeActual.cont == 0) 
		        {  
		            code.setEtiqueta(ctx.ID().getText()+"_global");
		            code.setGlobal(true);
		            this.Codigo.addCode(code);
		        }
		        else
		        {
			        if (tipo.equals("int"))
			        {
			            this.sp.add(new StackPointer(id, (4*tamaño), tipo));    
			        }
			        else 
			        	if (tipo.equals("char"))
			        	{
			        		this.sp.add(new StackPointer(id, (4*tamaño), tipo));
			        	}
			        	else 
			        		if (tipo.equals("boolean"))
			        		{
			        			this.sp.add(new StackPointer(id, (4*tamaño),tipo));
			        		}
			      
			        changeSP();
		        }
		        break;
			}
		}
		
		// TODO Auto-generated method stub
		return new String();
	}
	
	
	
	@Override
	public Object visitMethodDeclaration(DECAFParser.MethodDeclarationContext ctx) {
		Ambito ScopeMethod = new Ambito();
        
        this.ScopeActual.addKid(ScopeMethod);
        ScopeMethod.setPrev(ScopeActual);
        this.ScopeActual = ScopeMethod;
        
        InterLine code = new InterLine();
        String etiqueta = ctx.getChild(1).getText();
        this.etiquetaActual = etiqueta;
        this.contEtiqueta = 0;

        code.setEtiqueta(etiqueta+":");
        this.Codigo.addCode(code);
        
        super.visitMethodDeclaration(ctx);
        
        this.ScopeActual = this.ScopeActual.getPrev();
        StackPointer.cont = 0;
        
		// TODO Auto-generated method stub
		return new String();
	}

	@Override
	public Object visitStructDeclaration(DECAFParser.StructDeclarationContext ctx) {
		
		Ambito scope = new Ambito();
        
        this.ScopeActual.addKid(scope);
        scope.setPrev(this.ScopeActual);
        this.ScopeActual = scope;
        super.visitStructDeclaration(ctx);
        this.ScopeActual = this.ScopeActual.getPrev();
        
        return new String();
	}

	@Override
    public Object visitBlock(DECAFParser.BlockContext ctx) {
		for (int i = 0;i<ctx.getChildCount();i++)
			this.visit(ctx.getChild(i));

        return new String();
    }
	
	@Override
	public Object visitStatementIF(DECAFParser.StatementIFContext ctx) {
		
		InterLine label1 = new InterLine();
        InterLine label2 = new InterLine();
        
        int copyEt = contEtiqueta;
        this.globalCant = 1+ this.splitStrings(ctx.getChild(4).getText(), "if")*2;
        
        if (ScopeActual.getName() != 1)
        	globalCant++;
     
        label1.setEtiqueta(etiquetaActual+ ++copyEt + ":");
        
        ArrayList midExpr = (ArrayList)this.visit(ctx.getChild(2));
        
        if (midExpr.size()==1)
        {
        	String bool;
            if (actualOp.equals("&&"))
            	bool="IF_isFalse";
            else
            	bool="IF_isTrue";
            
            InterLine actualCode = (InterLine)midExpr.get(0);
            InterLine codigo = new InterLine();
            codigo.setIF(true);
            InterLine midCode = actualCode;
            this.Codigo.addCode(midCode);
            codigo.setDir1(bool);

            codigo.setDir2(midCode.getDir1());
            if (bool.equals("IF_isFalse"))
            	codigo.setDir3(etiquetaActual + this.globalCant);
            else
            	codigo.setDir3(etiquetaActual + contEtiqueta);
            
            codigo.setOp("goto");
            if (midCode.getDir1().contains("temp"))
            	this.Codigo.addCode(codigo);
                    
            
            InterLine afterExpr = new InterLine();

            afterExpr.setOp("goto");
            afterExpr.setDir2(etiquetaActual + (this.globalCant));

            this.Codigo.addCode(afterExpr);
        }
        
        Ambito scopeIF = new Ambito();
        
        ScopeActual.addKid(scopeIF);
        scopeIF.setPrev(ScopeActual);
        ScopeActual = scopeIF;
        
       
        if (ctx.getChildCount() > 5)
        {
            InterLine etiqueta = new InterLine();
            etiqueta.setEtiqueta(etiquetaActual + contEtiqueta++ + ":");
            
            this.Codigo.addCode(etiqueta);
            InterLine gotoElse = new InterLine();
            gotoElse.setOp("goto");
            gotoElse.setDir3(etiquetaActual + (++contEtiqueta));
            
            elseInt = gotoElse;
        }
        else
        {
            InterLine etiqueta = new InterLine();
            etiqueta.setEtiqueta(etiquetaActual + contEtiqueta++ + ":");
            
            this.Codigo.addCode(etiqueta);
        }
        
        this.visit(ctx.getChild(4));
        InterLine label = new InterLine();
        label.setEtiqueta(etiquetaActual + contEtiqueta++ + ":");
        this.Codigo.addCode(label);
        
        if (ctx.getChildCount() > 5)
        {
        	this.visit(ctx.getChild(5));
            label2.setEtiqueta(etiquetaActual+contEtiqueta+++":");
            this.Codigo.addCode(label2);
        }
        
        ScopeActual = ScopeActual.getPrev();
        
		// TODO Auto-generated method stub
		return new String();
	}
	
	@Override
	public Object visitStatementWHILE(DECAFParser.StatementWHILEContext ctx) {
		Ambito scopeWHILE = new Ambito();
        
		contWhile++;
        ScopeActual.addKid(scopeWHILE);
        scopeWHILE.setPrev(ScopeActual);
        ScopeActual = scopeWHILE;
       
        
        InterLine etiqueta1 = new InterLine();
        InterLine etiqueta2 = new InterLine();
        InterLine etiquetaWhile = new InterLine();
        
        etiquetaWhile.setEtiqueta("whileLoop" + "_" + etiquetaActual + contEtiqueta + ":");
        
        this.Codigo.addCode(etiquetaWhile);
            
        int copyEt = contEtiqueta;
        etiqueta1.setEtiqueta(etiquetaActual + ++copyEt + ":");
        ArrayList midExpr = (ArrayList)this.visit(ctx.getChild(2));
        
        String bool = "IF_isTrue";
        for (int j = 0 ;j<midExpr.size();j++)
        {
            T actualObj = (T)midExpr.get(j) ;
            if (j < midExpr.size()-1)
            {
                if (midExpr.get(j+1) instanceof String)
                {
                    if (midExpr.get(j+1).equals("&&"))
                        bool = "IF_isFalse";
                    else
                        bool = "IF_isTrue";
                }
            }
            
            if (actualObj instanceof InterLine)
            {
                InterLine actualCode = (InterLine)actualObj;
                InterLine codigo = new InterLine();
                codigo.setIF(true);
                InterLine midCode = actualCode;
                this.Codigo.addCode(midCode);
                codigo.setDir1(bool);

                codigo.setDir2(midCode.getDir1());
                if (bool.equals("IF_isFalse"))
                    codigo.setDir3(etiquetaActual + copyEt);
                else
                    codigo.setDir3(etiquetaActual + contEtiqueta);
                codigo.setOp("goto");

                this.Codigo.addCode(codigo);
            }
        }
        
        InterLine prevLast = new InterLine(); 
        if (bool.equals("IF_isFalse"))
        {
            
        }
        else
        { 
            if (ctx.getChild(4).getText().contains("while"))
            {
               int offset = this.splitStrings(ctx.getChild(4).getText(), "while");
               prevLast.setDir3(etiquetaActual + (copyEt+1+offset));
            }
            else
                prevLast.setDir3(etiquetaActual + (copyEt));
            
            prevLast.setOp("goto");
            this.Codigo.addCode(prevLast);
        }
        
       
       
        InterLine etiqueta = new InterLine();
        etiqueta.setEtiqueta(etiquetaActual + contEtiqueta++ + ":");
            
        this.Codigo.addCode(etiqueta);
        
        visit(ctx.getChild(4));
         
      
        InterLine finalGoto = new InterLine();
        finalGoto.setOp("goto");
        int copyCont =contEtiqueta;
        copyCont = --contWhile;
        finalGoto.setDir3("whileLoop" + "_" + etiquetaActual + (copyCont));
        this.Codigo.addCode(finalGoto);
        
        
        etiqueta2.setEtiqueta(etiquetaActual + contEtiqueta++ + ":");
        this.Codigo.addCode(etiqueta2);
        
        ScopeActual = ScopeActual.getPrev();
        
		// TODO Auto-generated method stub
		return new String();
	}

	@Override
	public Object visitStatementLOC(DECAFParser.StatementLOCContext ctx) {
		
		String res = "";
        ArrayList locArray = (ArrayList)visit(ctx.getChild(0));
        String nombreVar = (String)locArray.get(0);
        int posArray = (int)locArray.get(1)-1;
        
        InterLine codigo = new InterLine();
        boolean glbl = this.Codigo.searchGlobalSymbol(nombreVar);
        if (glbl)
        {
           InterLine globalCode = this.Codigo.searchCodeGlobal(nombreVar);
           Simbolo simbolo =  tablaSimbolos.findAllScopes(nombreVar);
           String tipo = ((Type)simbolo.getType()).getType_name();
           int index = 0;
           
           index =  posArray;
               
           res = globalCode.getEtiqueta()+"["+index+"]";
           
        }
        else
        {
           res = "stack["+(this.buscarStack(nombreVar)+posArray)+"]";
        }
        
        T returnValue = (T)visit(ctx.getChild(2));
        String dir1;
        
        if (returnValue instanceof ArrayList )
        {
            if (((ArrayList) returnValue).get(0) instanceof InterLine)
                dir1 =((InterLine)((ArrayList) returnValue).get(0)).getDir1();
            else
                dir1 = ((String)((ArrayList) returnValue).get(0));
        }
        else if (returnValue instanceof InterLine)
        {
            dir1 = ((InterLine)returnValue).getDir1();
        }
        else{
            dir1 = (String)returnValue;
        }
        if (!dir1.contains("\'")&&!dir1.contains("temp")&&!dir1.contains("[")){
            try {
                int num = Integer.parseInt(dir1);
            }catch(Exception e){
                boolean glbl2 = this.Codigo.searchGlobalSymbol(dir1);
                if (glbl2){
                       InterLine code = this.Codigo.searchCodeGlobal(dir1);
                       dir1 = code.getEtiqueta();
                }
                else { 
                    int pos = this.buscarStack(dir1);
                    dir1 = "stack[" + pos +"]";
                }
            }
        }
        
        codigo.setDir2(dir1);
       
        codigo.setOp("=");
        
        codigo.setDir1(res);
        this.Codigo.addCode(codigo);
        this.contTemporales= 0;
        
		// TODO Auto-generated method stub
		return new String();
	}

	@Override
	public Object visitStructLocation(DECAFParser.StructLocationContext ctx) {
		
		if (ctx.getChildCount() == 1)
		{
			return this.visit(ctx.getChild(0));
		}
		else
		{
			
		}
		// TODO Auto-generated method stub
		return super.visitStructLocation(ctx);
	}

	@Override
	public Object visitSimpleVariable(DECAFParser.SimpleVariableContext ctx) {

		ArrayList array = new ArrayList();
		array.add(ctx.ID().getText());
		array.add(1);
		// TODO Auto-generated method stub
		return array;
	}
	
	@Override
	public Object visitArrayVariable(DECAFParser.ArrayVariableContext ctx) {
		
		String var = ctx.getChild(0).getText();
        int locationSave = Integer.parseInt(ctx.getChild(2).getText());
        
        ArrayList array = new ArrayList();
        
        array.add(var);
        array.add(locationSave);
        
        return array; 
	}

	@Override
	public Object visitExpression(DECAFParser.ExpressionContext ctx) {
		
		ArrayList array = new ArrayList();
		
		switch(ctx.getChildCount())
		{
		case 1:
			T val = (T)visit(ctx.getChild(0));
	        if (val instanceof ArrayList)
	        	array.addAll((ArrayList) val);
	        else
	        	array.add(val);
	        
	        return array;
	        
		case 3:
			for (int i = 0;i<ctx.getChildCount();i++)
		    {
		        T expr = (T)visit(ctx.getChild(i));
		        
		        if (expr instanceof ArrayList)
		        	array.addAll((ArrayList)expr);
		        else 
		        	array.add(expr);
		    }
		        
		    return array;
			
		}
	    return new String();
	}

	@Override
	public Object visitAndExpr(DECAFParser.AndExprContext ctx) {
		
		switch (ctx.getChildCount())
		{
		case 1:
			return this.visit(ctx.getChild(0));
			
		case 3:
			ArrayList array = new ArrayList();
			
	        for (int i = 0;i<ctx.getChildCount();i++)
	        {
	            T val = (T)visit(ctx.getChild(i));
	            
	            if (val instanceof ArrayList)
	            {
	                array.addAll((ArrayList)val);
	            }
	            else
	                array.add(val);
	        }
	        
	        return array;
		}
		
		// TODO Auto-generated method stub
		return new String();
	}
	
	@Override
	public Object visitEqExpr(DECAFParser.EqExprContext ctx) {
		
		switch (ctx.getChildCount())
		{
		case 1:
			return this.visit(ctx.getChild(0));

		case 3:
			actualOp = ctx.getChild(1).getChild(0).getText();
	        if (ctx.getChildCount()>1)
	        {
	        	T val1 = (T)visit(ctx.getChild(0));
	            T val2 = (T)visit(ctx.getChild(2));
	         
	            String dir1 = new String();
	            String dir2 = new String();
	             
	            if (val1 instanceof String)
	                dir1 = (String)val1;
	             
	            if (val1 instanceof InterLine)
	                dir1 = ((InterLine)val1).getDir1();
	             
	            if (val1 instanceof ArrayList)
	            {
	            	ArrayList array = (ArrayList)val1;
	             	dir1 = (String)array.get(0);
	            }
	             
	            if (val2 instanceof String)
	                dir2 = (String)val2;
	         
	            if (val2 instanceof InterLine)
	                dir2 = ((InterLine)val2).getDir1();
	             
	            if (val2 instanceof ArrayList)
	            {
	            	ArrayList array = (ArrayList)val2;
	             	dir2 = (String)array.get(0);
	            }
	             
		        try
		        {
		            int test = Integer.parseInt(dir1);
		        }
		        catch(Exception e)
		        {
		        	if (!dir1.equals("true") && !dir1.equals("false") && !dir1.contains("'"))
		        	{
			            if (!dir1.contains("stack["))
			            {
			                boolean amb = Codigo.searchGlobalSymbol(dir1);
			                if (amb)
			                	dir1+="_"+"_"+amb;
			                else
			                	dir1 = "stack["+this.buscarStack(dir1)+"]";
			            }
		        	}
		            
		        }
		        try
		        {
		            int test = Integer.parseInt(dir2);
		        }
		        catch(Exception e)
		        {
		        	if (!dir2.equals("true") && !dir2.equals("false") && !dir2.contains("'"))
		        	{
		        		if (!dir2.contains("stack["))
		        		{
			                boolean amb = Codigo.searchGlobalSymbol(dir2);
			                
			                if (amb)
			                	dir2+="_"+"_"+amb;
			                else
			                	dir2 = "stack["+this.buscarStack(dir2)+"]";
		        		}
		        	}
		        }
		        
		        String op = ctx.getChild(1).getText();
		        InterLine line = new InterLine();
		        
		        line.setDir2(dir1);
		        line.setDir3(dir2);
		        line.setOp(op);
		        line.setDir1("temp"+this.contTemporales++);
		        
		        return line; 
	        }
			break;
		}
		// TODO Auto-generated method stub
		return super.visitEqExpr(ctx);
	}

	@Override
	public Object visitRelationExpr(DECAFParser.RelationExprContext ctx) {
		
		switch(ctx.getChildCount())
		{
		case 1:
			return this.visit(ctx.getChild(0));
		
		case 3:
			T val1 = (T)visit(ctx.getChild(0));
            T val2 = (T)visit(ctx.getChild(2));
         
            String dir1 = new String();
            String dir2 = new String();
             
            if (val1 instanceof String)
                dir1 = (String)val1;
             
            if (val1 instanceof InterLine)
                dir1 = ((InterLine)val1).getDir1();
             
            if (val1 instanceof ArrayList)
            {
            	ArrayList array = (ArrayList)val1;
             	dir1 = (String)array.get(0);
            }
             
            if (val2 instanceof String)
                dir2 = (String)val2;
         
            if (val2 instanceof InterLine)
                dir2 = ((InterLine)val2).getDir1();
             
            if (val2 instanceof ArrayList)
            {
            	ArrayList array = (ArrayList)val2;
             	dir2 = (String)array.get(0);
            }
             
	        try
	        {
	            int test = Integer.parseInt(dir1);
	        }
	        catch(Exception e)
	        {
	        	if (!dir1.equals("true") && !dir1.equals("false") && !dir1.contains("'"))
	        	{
		            if (!dir1.contains("stack["))
		            {
		                boolean amb = Codigo.searchGlobalSymbol(dir1);
		                if (amb)
		                	dir1+="_"+"_"+amb;
		                else
		                	dir1 = "stack["+this.buscarStack(dir1)+"]";
		            }
	        	}
	            
	        }
	        try
	        {
	            int test = Integer.parseInt(dir2);
	        }
	        catch(Exception e)
	        {
	        	if (!dir2.equals("true") && !dir2.equals("false") && !dir2.contains("'"))
	        	{
	        		if (!dir2.contains("stack["))
	        		{
		                boolean amb = Codigo.searchGlobalSymbol(dir2);
		                
		                if (amb)
		                	dir2+="_"+"_"+amb;
		                else
		                	dir2 = "stack["+this.buscarStack(dir2)+"]";
	        		}
	        	}
	        }
	        
	        String op = ctx.getChild(1).getText();
	        InterLine line = new InterLine();
	        
	        line.setDir2(dir1);
	        line.setDir3(dir2);
	        line.setOp(op);
	        line.setDir1("temp"+this.contTemporales++);
	        
	        return line;
		}
		
		return new String();
	}

	@Override
	public Object visitAddExpr(DECAFParser.AddExprContext ctx) {
		
		switch (ctx.getChildCount())
		{
		case 1:
			return this.visit(ctx.getChild(0));
			
		case 3:
			String opTemp = new String();
	        String opTemp2 = new String();
	       
	        String op1 = new String();
	       
	        if (opTemp.isEmpty())
	        {
	            T val1 = (T)this.visit(ctx.getChild(2));
	            
	            if (val1 instanceof String)
	                op1 = (String)val1;
	            
	            if (val1 instanceof InterLine)
	                op1= ((InterLine)val1).getDir1();
	            
	            if (val1 instanceof ArrayList)
	            {
	            	ArrayList array = (ArrayList)val1;
	            	op1 = (String)array.get(0);
	            }
	        } 
	       
	        String op2="";
	       
	        if (opTemp.isEmpty())
	        {
	            T val2 = (T)this.visit(ctx.getChild(0));
	            
	            if (val2 instanceof String)
	                op2 = (String)val2;
	        
	            if (val2 instanceof InterLine)
	                op2 = ((InterLine)val2).getDir1();
	            
	            if (val2 instanceof ArrayList)
	            {
	            	ArrayList array = (ArrayList)val2;
	            	op2 = (String)array.get(0);
	            }
	            
	        } 
	        
	        try
	        {
	           int valor = Integer.parseInt(op1);
	        }
	        catch(Exception e)
	        {
	            if (!op1.contains("temp")&&!op1.contains("[")&&!op1.contains("global"))
	                op1 = "stack"+"["+this.buscarStack(op1)+"]";
	        }
	        try 
	        {
	            int valor = Integer.parseInt(op2);
	        }
	        catch(Exception e)
	        {
	           if (!op2.contains("temp")&&!op2.contains("[")&&!op2.contains("global"))
	                op2 = "stack"+"["+this.buscarStack(op2)+"]";
	        }
	        
	       InterLine line = new InterLine();
	       line.setDir2(op2);
	       line.setDir3(op1);
	       line.setOp(ctx.getChild(1).getText());
	       line.setDir1("temp"+this.contTemporales++);
	       this.Codigo.addCode(line);
	        
	        
	       return line;
		}
		
		// TODO Auto-generated method stub
		return super.visitAddExpr(ctx);
	}

	@Override
	public Object visitMultExpr(DECAFParser.MultExprContext ctx) {
		
		switch (ctx.getChildCount())
		{
		case 1:
			return this.visit(ctx.getChild(0));
			
		case 3:
			String opTemp = new String();
		      
		     
	        String op1 = new String();
	     
	        if (opTemp.isEmpty())
	        {
	            T val1 = (T)this.visit(ctx.getChild(2));
	            if (val1 instanceof String)
	            	op1 = (String)val1;
	            
	            if (val1 instanceof InterLine)
	                op1= ((InterLine)val1).getDir1();
	            
	            if (val1 instanceof ArrayList)
	            {
	            	ArrayList array = (ArrayList)val1;
	            	op1 = (String)array.get(0);
	            }
	        } 
	       
	        String op2 = new String();
	       
	        if (opTemp.isEmpty())
	        {
	            T val2 = (T)this.visit(ctx.getChild(0));
	        
	            if (val2 instanceof String)
	                op2 = (String)val2;
	            
	            if (val2 instanceof InterLine)
	                op2 = ((InterLine)val2).getDir1();
	            
	            if (val2 instanceof ArrayList)
	            {
	            	ArrayList array = (ArrayList)val2;
	            	op2 = (String)array.get(0);
	            }
	        } 
	        
	        try
	        {
	           int valor = Integer.parseInt(op1);
	        }
	        catch(Exception e)
	        {
	        	if (!op1.contains("temp")&&!op1.contains("[")&&!op1.contains("global"))
	        		op1 = "stack"+"["+this.buscarStack(op1)+"]";
	        }
	        try 
	        {
	            int valor = Integer.parseInt(op2);
	        }
	        catch(Exception e)
	        {
	            if (!op2.contains("temp")&&!op2.contains("[")&&!op2.contains("global"))
	                op2 = "stack"+"["+this.buscarStack(op2)+"]";
	        }
	        
	       
	        InterLine line = new InterLine();
	        line.setDir2(op2);
	        line.setDir3(op1);
	        line.setOp(ctx.getChild(1).getText());
	        line.setDir1("temp"+this.contTemporales++);
	        this.Codigo.addCode(line);
	        
			return line;
		}
		// TODO Auto-generated method stub
		return new String();
	}
	
	
	
	@Override
	public Object visitResExpr(DECAFParser.ResExprContext ctx) {
		
		switch (ctx.getChildCount())
		{
		case 1:
			return this.visit(ctx.getChild(0));
			
		case 3:
			String opTemp = new String();
		      
		     
	        String op1 = new String();
	     
	        if (opTemp.isEmpty())
	        {
	            T val1 = (T)this.visit(ctx.getChild(2));
	            if (val1 instanceof String)
	            	op1 = (String)val1;
	            
	            if (val1 instanceof InterLine)
	                op1= ((InterLine)val1).getDir1();
	            
	            if (val1 instanceof ArrayList)
	            {
	            	ArrayList array = (ArrayList)val1;
	            	op1 = (String)array.get(0);
	            }
	        } 
	       
	        String op2 = new String();
	       
	        if (opTemp.isEmpty())
	        {
	            T val2 = (T)this.visit(ctx.getChild(0));
	        
	            if (val2 instanceof String)
	                op2 = (String)val2;
	            
	            if (val2 instanceof InterLine)
	                op2 = ((InterLine)val2).getDir1();
	            
	            if (val2 instanceof ArrayList)
	            {
	            	ArrayList array = (ArrayList)val2;
	            	op2 = (String)array.get(0);
	            }
	        } 
	        
	        try
	        {
	           int valor = Integer.parseInt(op1);
	        }
	        catch(Exception e)
	        {
	        	if (!op1.contains("temp")&&!op1.contains("[")&&!op1.contains("global"))
	        		op1 = "stack"+"["+this.buscarStack(op1)+"]";
	        }
	        try 
	        {
	            int valor = Integer.parseInt(op2);
	        }
	        catch(Exception e)
	        {
	            if (!op2.contains("temp")&&!op2.contains("[")&&!op2.contains("global"))
	                op2 = "stack"+"["+this.buscarStack(op2)+"]";
	        }
	        
	       
	        InterLine line = new InterLine();
	        line.setDir2(op2);
	        line.setDir3(op1);
	        line.setOp(ctx.getChild(1).getText());
	        line.setDir1("temp"+this.contTemporales++);
	        this.Codigo.addCode(line);
	        
			return line;
		}
		
		// TODO Auto-generated method stub
		return new String();
	}

	@Override
	public Object visitUnaryExpr(DECAFParser.UnaryExprContext ctx) {
		if (ctx.getChildCount()==1)
			return visit(ctx.getChild(0));
		else
		{
			return new String();
		}
	}

	@Override
	public Object visitMethodCall(DECAFParser.MethodCallContext ctx) {
		for (int i = 0;i<ctx.getChild(2).getChildCount();i++)
		{
			T param = (T)this.visit(ctx.getChild(2).getChild(i));
			
	        if (param instanceof InterLine)
	        {
	        	InterLine ParamLine = new InterLine();
	        	ParamLine.setDir2("Param: ");
	        	ParamLine.setDir3(((InterLine)param).getDir1());
	            this.Codigo.addCode(ParamLine);
	        }
	        
	        if (param instanceof ArrayList)
	        {
	        	InterLine ParamLine = new InterLine();
	        	ParamLine.setDir2("Param: ");
	            if (((ArrayList)param).get(0) instanceof String)
	            {
	            	String var = (String)((ArrayList)param).get(0);
	                boolean amb = this.Codigo.searchGlobalSymbol(var);
	                if (amb)
	                	ParamLine.setDir3( "stack_global[" + this.buscarGlobalStack(var) +"]");
	                else
	                	ParamLine.setDir3( "stack[" + this.buscarStack(var) +"]");
	            }
	            else
	            	ParamLine.setDir3(((InterLine)((ArrayList)param).get(0)).getDir1());
	            
	            this.Codigo.addCode(ParamLine);
	        }
		}
	        	        

		InterLine line  = new InterLine();
		line.setDir2("goto");
		line.setDir3(ctx.getChild(0).getText());
		this.Codigo.addCode(line);
		
		return  line;
	}

	@Override
	public Object visitLiteral(DECAFParser.LiteralContext ctx) {
		
		InterLine line = new InterLine();
        line.setDir1(ctx.getChild(0).getText());
        return line;
	}

	public void changeSP()
	{
		int tamaño = this.sp.size();
	       
	    if (tamaño >= 2)
	    {
	    	for (int i = tamaño-1;i!=0;i--)
	    	{
	    		StackPointer last = this.sp.get(i);
	            StackPointer prev = this.sp.get(i-1);
	            int lastPos = last.getPos();
	            int prevPos = prev.getPos();
	            last.setPos(prevPos);
	            prev.setPos(lastPos);
	        }
	    }   
	}
	
	public int buscarStack(String id)
	{
		for(StackPointer stack: this.sp)
		{
			if (stack.getId().equals(id))
			{
	               return stack.getPos();
	        }
	    }
	    return -1;
	}
	
	public int buscarGlobalStack(String id)
	{
		for(StackPointer stack : this.sp)
		{
			if (stack.getId().equals(id))
				return stack.getPos();           
	    }
		
	    return -1;
	}
    
    public int splitStrings(String text, String node)
    { 
        return text.split(Pattern.quote(node), -1).length - 1;
    }
}
