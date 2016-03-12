import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;

public class GenerateTable<T> extends DECAFBaseVisitor<Object> {
    
	public TablaSimbolos tablaSimbolos = new TablaSimbolos();
    public TablaMetodos tablaMetodos = new TablaMetodos();
    public TablaStruct tablaStruct = new TablaStruct();
    public Ambito scopeActual;
    
    public GenerateTable(ParseTree tree) {
    	
        this.visit(tree);
        System.out.println("***************** Tabla Simbolos *****************");
        Object rows[][] = tablaSimbolos.getInfo();
        Object columns[] = tablaSimbolos.getColumsTitles();
        
        myIDE.symbolTable = new JTable(rows,columns);
        myIDE.tabbedPane.remove(myIDE.symbolTable);
        myIDE.tabbedPane.addTab("Tabla Simbolos",null,myIDE.symbolTable,null);
        
        System.out.println("***************** Tabla Metodos *****************");
        
        Object rowsM[][] = tablaMetodos.getInfo();
        Object columnsM[] = tablaMetodos.getColumsTitles();
        
        myIDE.methodTable = new JTable(rowsM, columnsM);
        myIDE.tabbedPane.remove(myIDE.methodTable);
        myIDE.tabbedPane.addTab("Tabla Metodos",null,myIDE.methodTable,null);
        
        System.out.println("***************** Tabla Struct *****************");
        Object rowsS[][] = tablaStruct.getInfo();
        Object columnsS[] = tablaStruct.getColumsTitles();
        
        myIDE.structTable = new JTable(rowsS, columnsS);
        myIDE.tabbedPane.remove(myIDE.structTable);
        myIDE.tabbedPane.addTab("Tabla Struct",null,myIDE.structTable,null);
        
        scopeActual.cont = 0;
    }
    
    
    /******************************************
     * Program
     * CLASS ID { declaration }
     *****************************************/
    @Override
    public T visitProgram(DECAFParser.ProgramContext ctx) {
        System.out.println("Program: " + ctx.getText());
        scopeActual = new Ambito();
        for (int i = 0;i<ctx.getChildCount();i++){
            this.visit(ctx.getChild(i));
         }
        return (T)new String();
    }
    
    
    /****************************************
     * Declaration
     * structDeclaration | varDeclaration | methodDeclaration
     ***************************************/
    @Override
    public T visitDeclaration(DECAFParser.DeclarationContext ctx){
        for (int i = 0;i<ctx.getChildCount();i++){
        	this.visit(ctx.getChild(i));
        }
        
        return (T)"void";
    }
    
    
    /******************************************
     * VarDeclaration
     * Si tiene 3 hijos es: tipo id ;
     * Si tiene 6 hijos es: tipo id [ num ] ;
     ******************************************/
    @Override
    public T visitVarDeclaration(DECAFParser.VarDeclarationContext ctx) {
        String type = new String();
        String id = new String();
        
        Type tipoDato = new Type(null);
        
        switch(ctx.getChildCount()) {
            case 3:
            	//Si es struct lo mandamos a structDeclaration
            	//Si no solo agregamos el simbolo
            	if (ctx.varType().getText().contains("struct"))
            	{
            		Struct struct = (Struct)visit(ctx.varType());
            		tipoDato = new Type(struct.getId());
            		id = ctx.ID().getText();
            		tipoDato.setStruct(true);
            	}
            	else
            	{
            		type = ctx.getChild(0).getText();
            		id = ctx.ID().getText();
            		tipoDato = new Type(type);
            		tipoDato.setSimple(true);
            	}
                break;
            case 6:
            	if (ctx.varType().getText().contains("struct"))
            	{
            		Struct struct = (Struct)visit(ctx.varType());
            		tipoDato = new Type(struct.getId());
            		id = ctx.ID().getText();
            		
            		int num = Integer.valueOf(ctx.getChild(3).getText());
                	if  (num<=0)
                		Error("",ctx.getStart().getLine());
                	tipoDato.setStruct(true);
                	tipoDato.setArray(true, num);
            	}
            	else
            	{
            		type = ctx.getChild(0).getText();
            		id = ctx.ID().getText();
            		tipoDato = new Type(type);
            		
            		int num = Integer.valueOf(ctx.getChild(3).getText());
                	if  (num<=0)
                		Error("",ctx.getStart().getLine());
                	tipoDato.setArray(true, num);
            	}
            	
                break;
        }
        
        for (Simbolo s : this.tablaSimbolos.Table)
        {
        	if (s.getId().equals(id) && s.getAmbito().equals(scopeActual))
        		Error("Variable ya declarada",ctx.getStart().getLine());
        }
        
        Simbolo simbolo = new Simbolo();
        simbolo.setId(id);
        simbolo.setType(tipoDato);
        simbolo.setAmbito(scopeActual);
        
        tablaSimbolos.addSymbol(simbolo);
        
        return (T)simbolo;
    }
    
    
    /*****************************************
     * StructDeclaration
     * struct id { varDeclaratio } ;
     *****************************************/
    @Override
    public T visitStructDeclaration(DECAFParser.StructDeclarationContext ctx) {
        Ambito nuevoScope = new Ambito();
        scopeActual.addKid(nuevoScope);
        nuevoScope.setPrev(scopeActual);
        
        scopeActual = nuevoScope;
        
        String structName = ctx.getChild(1).getText();
        ArrayList<Simbolo> items = new ArrayList();
        
        for (int i = 3; i < ctx.getChildCount() - 1; i++) {
        	Object var = this.visit(ctx.getChild(i));
	        Simbolo simbolo = (Simbolo)var;
	        items.add(simbolo);		
        }
        
        scopeActual = scopeActual.getPrev();
        
        Struct struct = new Struct(structName, scopeActual, items);
        tablaStruct.addStruct(struct);
        
        return (T)struct;
    }
   
    
    
    /************************************
     * MethodDeclaration
     * Tipo id ( param ) block
     ************************************/
    @Override
    public T visitMethodDeclaration(DECAFParser.MethodDeclarationContext ctx) {
    	System.out.println(ctx.getText());
        Ambito nuevoScope = new Ambito();
        scopeActual.addKid(nuevoScope);
        nuevoScope.setPrev(scopeActual);
        ArrayList<Simbolo> parametros = new ArrayList();
        scopeActual = nuevoScope;
        
        String ret = ctx.getChild(0).getText();
        Type returnType = new Type(ret);
        returnType.setSimple(true);
        
        String id = ctx.getChild(1).getText();        
        for (int i = 3; i < ctx.getChildCount(); i++) 
        {
        	Object var = this.visit(ctx.getChild(i));
            
        	if (Simbolo.class.isInstance(var)) 
        	{
        		Simbolo simbolo = (Simbolo)var;
                
        		if (simbolo.getType().isParam()) 
        		{
        			parametros.add(simbolo);
        		}
        	}
        }
   
        
        Metodos metodo = new Metodos(id, returnType, parametros);
        tablaMetodos.addMethod(metodo);
        
        tablaSimbolos.addAllSymbol(parametros);
        
        scopeActual = scopeActual.getPrev();
        return (T)new String();
    }
    
    
    /*******************************************
     * Parameter
     * Si tiene 2 hijos: tipo id ;
     * Si tiene 5 hijos: tipo id [ num ] ;
     *******************************************/
    @Override
    public T visitParameter(DECAFParser.ParameterContext ctx) {
        String type = ctx.getChild(0).getText();
        String id = ctx.getChild(1).getText();
        
        Type tipoDato = new Type(type);
        
        switch(ctx.getChildCount()) {
            // En caso de que sea una declaración de variable simple
            case 2:
                tipoDato.setParam(true);
                tipoDato.setSimple(true);
                break;
            // En caso de que sea una declaración de array
            case 5:
            	int num = Integer.valueOf(ctx.getChild(3).getText());
            	if  (num<=0)
            		Error("",ctx.getStart().getLine());
                tipoDato.setParam(true);
                tipoDato.setArray(true, Integer.valueOf(ctx.getChild(3).getText()));
                break;
        }
        
        Simbolo parametro = new Simbolo();
        parametro.setId(id);
        parametro.setType(tipoDato);
        parametro.setAmbito(scopeActual);
        
        return (T)parametro;
    }
    

    /********************************
     * Block
     * Solo aumentamos ambito
     ********************************/
    @Override
    public T visitBlock(DECAFParser.BlockContext ctx) {
    	Ambito nuevoScope = new Ambito();
        scopeActual.addKid(nuevoScope);
        nuevoScope.setPrev(scopeActual);
        
        scopeActual = nuevoScope;
        
    	for (int i = 0;i<ctx.getChildCount();i++){
            this.visit(ctx.getChild(i));
         }
    	
    	scopeActual = scopeActual.getPrev();
    	
    	return (T)new String();
    }

    
    /***************************************************************************
     * StatementIF
     * if ( expression ) block StatementELSE
     **************************************************************************/
    @Override
    public T visitStatementIF(DECAFParser.StatementIFContext ctx) {
        
        Ambito nuevoScope = new Ambito();
        scopeActual.addKid(nuevoScope);
        nuevoScope.setPrev(scopeActual);
        
        scopeActual = nuevoScope;
        
        for (int i = 0; i < ctx.getChildCount(); i++) {
            this.visit(ctx.getChild(i));
            if (i == 2)
            {
            	if (ctx.getChild(2) != (T)"boolean")
            	{
            		Error("Expression en if no es boolean",ctx.getStart().getLine());
            	}
            }
        }
        
        scopeActual = scopeActual.getPrev();
        //return (T)visitChildren(ctx);
        return (T)new String();
    }

    
    /***************************************************************************
     * StatementELSE
     * else block 
     **************************************************************************/
    @Override 
    public T visitStatementELSE(DECAFParser.StatementELSEContext ctx) {
        Ambito nuevoScope = new Ambito();
        scopeActual.addKid(nuevoScope);
        nuevoScope.setPrev(scopeActual);
        
        scopeActual = nuevoScope;
        
        for (int i = 0; i < ctx.getChildCount(); i++) {
            this.visit(ctx.getChild(i));
        }
        
        scopeActual = scopeActual.getPrev();
        return (T)new String();
    }

    
    /***************************************************************************
     * StatementWHILE
     * while ( expression ) block 
     **************************************************************************/
    @Override
    public T visitStatementWHILE(DECAFParser.StatementWHILEContext ctx) {
        Ambito nuevoScope = new Ambito();
        scopeActual.addKid(nuevoScope);
        nuevoScope.setPrev(scopeActual);
        
        scopeActual = nuevoScope;
        for (int i = 0; i < ctx.getChildCount(); i++) {
            this.visit(ctx.getChild(i));
        }
        
        scopeActual = scopeActual.getPrev();
        
        return (T)new String();
    }
    
    
    @Override 
    public T visitStatementLOC(@NotNull DECAFParser.StatementLOCContext ctx) { 
    	String location = (String)visit(ctx.getChild(0));
    	String expression = (String)visit(ctx.getChild(2));
    	if (location.equals(expression))
    	{
    		return (T)"void";
    	}
    	else
    	{
    		Error("Asignacion de tipos diferentes", ctx.getStart().getLine());
    		return (T)"Error";
    	}
    }
    
    /*
	@Override 
	public T visitStatementRETURN(@NotNull DECAFParser.StatementRETURNContext ctx) { 
		
		int position = tablaMetodos.Table.size() - 1;
		Metodos metodo = tablaMetodos.Table.get(position);
		String type = metodo.return_val.getType_name2();
		if (type.equals("void"))
		{
			if (ctx.getChildCount()==2)
				return (T)"void";
			else
			{
				Error ("Metodo void intentando devolver un tipo",ctx.getStart().getLine());
				return (T)"Error";
			}
		}
		else
		{
			String expression = (String)visit(ctx.getChild(1));
			if (expression.equals(metodo.return_val.getType_name2()))
			{
				return (T)"void";
			}
			else
			{
				Error ("Metodo intentando devolver otro tipo",ctx.getStart().getLine());
				return (T)"Error";
			}
		}
	}*/

	
	/****************************************
	 * StructLocation
	 * miramos si tiene mas de un hijo visitamos todos sus location
	 * y devolvemos el tipo del ultimo
	 *****************************************/
	@Override public T visitStructLocation(@NotNull DECAFParser.StructLocationContext ctx) { 
		String struct = (String)visit(ctx.getChild(0));
		
		boolean flag = false;
		Struct temp = null;
		for (Struct s :this.tablaStruct.getAllStructs())
		{
			if (s.getId().equals(struct))
			{
				temp = s;
				flag = true;
			}
		}
		
		if (flag)
		{
			if (ctx.getChildCount()>1)
			{
				return (T)temp.getType().getType_name2();
			}
			else
			{
				return (T)visit(ctx.location());
			}
		}
		else
		{
			Error("Struct no existe",ctx.getStart().getLine());
			return (T)"Error";
		}
		}
	
	
    /*************************************
     * SimpleVariable
     * Miro si la variable ya existe 
     * en la tabla de simbolos si existe
     * devuelvo su tipo
     ************************************/
    @Override 
    public T visitSimpleVariable(DECAFParser.SimpleVariableContext ctx) { 
    	if (this.existSymbolInScope(ctx.ID().getText()))
    	{
    		Simbolo symbol = tablaSimbolos.getSymbol(ctx.ID().getText(), scopeActual);
    		return (T)symbol.getType().getType_name2();
    	}
    	else
    	{
    		Error ("Variable no declarada", ctx.getStart().getLine());
    		return (T)"Error";
    	} 
    }
 
    /***********************************************
     * ArrayVariable
     * Devolvemos el tipo de la variable del array
     ***********************************************/
    @Override 
    public T visitArrayVariable(DECAFParser.ArrayVariableContext ctx) { 
    	if (this.existSymbolInScope(ctx.ID().getText()))
    	{
    		Simbolo symbol = tablaSimbolos.getSymbol(ctx.ID().getText(), scopeActual);
    		return (T)symbol.getType().getType_name2();
    	}
    	else
    	{
    		Error ("Variable no declarada", ctx.getStart().getLine());
    		return (T)"Error";
    	} 
    }
    
    
    /********************************************
     * MethodCall
     * Visitamos la llamda al metod y devolvemos el tipo de este
     * si los parametros devuelven error devuelve error
     *******************************************/
	@Override 
	public T visitMethodCall(@NotNull DECAFParser.MethodCallContext ctx) { 
		String id = ctx.ID().getText();
		
		Metodos m = this.findMethod(id);
		
		if (m==null)
		{
			Error("El metodo no a sido declarado.",ctx.getStart().getLine());
			return (T)"Error";
		}
		else
		{
			String params = (String)visit(ctx.arg());
			if (m.getMethodSignature().equals(id+params))
			{
				return (T)m.return_val.getType_name2();
			}
			else
			{
				Error ("Parametro malo.",ctx.getStart().getLine());
				return (T)"Error";
			}
		}
	}
    
	/*******************************************
	 * Arg
	 * Visitamos los parametros 
	 ********************************************/
	@Override 
	public T visitArg(@NotNull DECAFParser.ArgContext ctx) 
	{ 
		String params = new String();
		params = "(";
		for (int i=0;i<ctx.getChildCount();i++)
		{
			if (!ctx.getChild(i).getText().equals(","))
			{
				String tipo = (String)this.visit(ctx.getChild(i));
				params += tipo;
			}
			if (i<ctx.getChildCount()-1)
				if (ctx.getChild(i+1).getText().equals(","))
				{
					params += ",";
				}
		}
		params += ")";
		return (T)params; 
	}
	
    /**********************************************
     * Expression
     * Si tiene 1 hijo: andExpr
     * Si tiene 3 hijos: Expression || andExpr
     *********************************************/
    @Override 
    public T visitExpression(DECAFParser.ExpressionContext ctx) { 
    	switch(ctx.getChildCount()){
	    	case 1:
	    		String and = (String)visit(ctx.andExpr());
	    		return (T)and;
	    	case 3:
	    		String expr = (String)visit(ctx.expression());
	    		String and2 = (String)visit(ctx.andExpr());
	    		if (expr.equals(and2))
	    		{
	    			return (T)"boolean";
	    		}
	    		else
	    		{
	    			Error("Comparacion de tipos diferentes", ctx.getStart().getLine());
	    			return (T)"Error";
	    		}
    	}
    	return (T)"Error";
    }
    
    
    /**********************************************
     * AndExpr
     * Si tiene 1 hijo: eqExpr
     * Si tiene 3 hijos: andExpr && eqExpr
     *********************************************/
    @Override 
    public T visitAndExpr(DECAFParser.AndExprContext ctx) { 
    	switch(ctx.getChildCount()){
	    	case 1:
	    		String eqExpr = (String)(this.visit(ctx.eqExpr()));
	    		return (T)eqExpr;
	    	case 3:
	    		String and = (String)visit(ctx.andExpr());
	    		String eq = (String)visit(ctx.eqExpr());
	    		if (and.equals(eq))
	    		{
	    			return (T)"boolean";
	    		}
	    		else
	    		{
	    			Error("Comparacion de tipos diferentes", ctx.getStart().getLine());
	    			return (T)"Error";
	    		}
    	}
    	return (T)"Error";
    }
    
    
    /************************************************
     * EqExpr
     * Si tiene 1 hijo: relationExpr
     * Si tiene 3 hijos: eqExpr == | != relatioExpr
     ************************************************/
    @Override 
    public T visitEqExpr(DECAFParser.EqExprContext ctx) { 
    	switch(ctx.getChildCount()){
    	case 1:
    		String rel = (String)visit(ctx.relationExpr()); 
    		return (T)rel;
    	case 3:
    		String eq = (String)visit(ctx.eqExpr());
    		String rela = (String)visit(ctx.relationExpr());
    		if (rela.equals(eq))
    		{
    			return (T)"boolean";
    		}
    		else
    		{
    			Error("Comparacion de tipos diferentes", ctx.getStart().getLine());
    			return (T)"Error";
    		}
    	}
    	return (T)"Error"; 
    }
    
    /**********************************************
     * RelationExpr
     * Si tiene 1 hijo: addExpr
     * Si tiene 3 hijos: relationExpr >= | < | >  | <= addExpr
     **********************************************/
    @Override 
    public T visitRelationExpr(DECAFParser.RelationExprContext ctx) { 
    	switch(ctx.getChildCount()){
	    	case 1:
	    		String add = (String)visit(ctx.addExpr());
	    		return (T)add;
	    	case 3:
	    		String rel = (String)visit(ctx.relationExpr());
	    		String add2 = (String)visit(ctx.addExpr());
	    		if (rel.equals(add2))
	    		{
	    			return (T)"boolean";
	    		}
	    		else
	    		{
	    			Error("Comparacion de tipos diferentes", ctx.getStart().getLine());
	    			return (T)"Error";
	    		}
    	}
    	return (T)"Error"; 
    }
    
    
    /***************************************************
     * AddExpr
     * Si tiene 1 hijo: multExpr
     * Si tiene 3 hijos: addExpr +|- multExpr
     ***************************************************/
	@Override 
	public T visitAddExpr(DECAFParser.AddExprContext ctx) { 
		switch(ctx.getChildCount()){
	    	case 1:
	    		return (T)this.visit(ctx.getChild(0));
	    	case 3:
	    		String add = (String)this.visit(ctx.getChild(0));
	    		String mult = (String)this.visit(ctx.getChild(2));
	    		if ((add.equals("int")) && (add.equals(mult)))
	    		{
	    			return (T)"int";
	    		}
	    		else
	    		{
	    			Error("Operación de tipos que no son int", ctx.getStart().getLine());
	    			return (T)"Error";
	    		}
		}
		return (T)"Error"; 
	}
	
	
	/***************************************************
     * MultExpr
     * Si tiene 1 hijo: resExpr
     * Si tiene 3 hijos: multExpr *|/ resExpr
     ***************************************************/
	@Override 
	public T visitMultExpr(DECAFParser.MultExprContext ctx) { 
		switch(ctx.getChildCount()){
	    	case 1:
	    		return (T)(this.visit(ctx.resExpr()));
	    	case 3:
	    		if ((this.visit(ctx.multExpr()).equals("int")) && (this.visit(ctx.multExpr()) == this.visit(ctx.resExpr())))
	    		{
	    			return (T)"int";
	    		}
	    		else
	    		{
	    			Error("Operación de tipos que no son int", ctx.getStart().getLine());
	    			return (T)"Error";
	    		}
		}
		return (T)"Error"; 
	}
	
	
	/***************************************************
     * ResExpr
     * Si tiene 1 hijo: unaryExpr
     * Si tiene 3 hijos: resExpr % unaryExpr
     ***************************************************/	
	@Override 
	public T visitResExpr(DECAFParser.ResExprContext ctx) { 
		switch(ctx.getChildCount()){
	    	case 1:
	    		return (T)(this.visit(ctx.unaryExpr()));
	    	case 3:
	    		if (this.visit(ctx.resExpr()) == this.visit(ctx.unaryExpr()))
	    		{
	    			return (T)"int";
	    		}
	    		else
	    		{
	    			Error("Operación de tipos que no son int", ctx.getStart().getLine());
	    			return (T)"Error";
	    		}
		}
		return (T)"Error";  
	}
	
	
	/*************************************************
	 * UnaryExpr
	 * Si tiene 1 Hijo: value
	 * Si tiene 2 Hijos: 
	 * 	Si comienza con - es -value
	 * Si comienza con ! es !value
	 *************************************************/
	@Override 
	public T visitUnaryExpr(DECAFParser.UnaryExprContext ctx) { 
		switch(ctx.getChildCount()){
	    	case 1:
	    		String value = (String)visit(ctx.value()); 
	    		return (T)value;
	    	case 2:
	    		if (ctx.getChild(0).getText().contains("-"))
	    		{
	    			if (this.visit(ctx.value()).equals("int"))
	    				return (T)"int";
	    			else
	    			{
		    			Error("Se esta tratando de hacer una operacion int con otro tipo", ctx.getStart().getLine());
		    			return (T)"Error";
	    			}
	    		}
	    		else
	    		{
	    			if (this.visit(ctx.value()).equals("boolean"))
	    				return (T)"boolean";
	    			else
	    			{
	    				Error("Operación booleana con otro tipo", ctx.getStart().getLine());
	    				return (T)"Error";
	    			}
	    		}
		}
		return (T)"Error";  
	}

    
    /*************************************************
     * Int_literal
     * NUM = int
     *************************************************/
    @Override 
    public T visitInt_literal(DECAFParser.Int_literalContext ctx) { 
    	return (T)"int"; 
    }
    
    
    /**************************************************
     * Char_literal
     * CHR = char
     *************************************************/
    @Override 
    public T visitChar_literal(DECAFParser.Char_literalContext ctx) { 
    	return (T)"char"; 
    }
    
    
    /*******************************
     * Bool_literal
     * true | false = boolean
     *******************************/
    @Override 
    public T visitBool_literal(DECAFParser.Bool_literalContext ctx) { 
    	return (T)"boolean"; 
    }
    
    //Buscamos un simbolo en los scopes y devolvemos el simbolo
    public Simbolo findSymbolInScopes(String id) 
    {
        Ambito actual = this.scopeActual;
        
        while (actual != null) 
        {
            for (Simbolo s: this.tablaSimbolos.getAllSymbolInAmbit(actual)) 
                if (s.getId().equals(id)) 
                    return s;
                
            
            actual = actual.getPrev();
        }
        
        return null;
    }
    
    public Boolean existSymbolInScope(String id) 
    {
        Ambito actual = this.scopeActual;
        
        while (actual != null) 
        {
            for (Simbolo s: this.tablaSimbolos.getAllSymbolInAmbit(actual)) 
                if (s.getId().equals(id)) 
                    return true;
                
            
            actual = actual.getPrev();
        }
        
        return false;
    }
    
    //buscamos simbolo en scope especifico
    public Simbolo findSymbolInScopes(String id, Ambito buscarAmbito) {
        for (Simbolo s: this.tablaSimbolos.getAllSymbolInAmbit(buscarAmbito)) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        
        return null;
    }
    
    //Devolvemos el metodo del que estan buscando el simbolo
    public Metodos findMethod(String id) {
        for (Metodos m: this.tablaMetodos.getAllMethods()) 
            if (m.name.equals(id)) 
                return m;
       
        return null;
    }
    
    public void Error(String message, int line)
    {    
    	myIDE.textAreaError.setText(myIDE.textAreaError.getText()+"\n"+
                "line: " + line +  " " + message
                );
        
    }
}
