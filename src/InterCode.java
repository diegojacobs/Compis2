import java.util.ArrayList;


/**
@author Diego Jacobs
Date: Apr 17, 2016
 */
public class InterCode {
	private TablaSimbolos tablaSimbolos = new TablaSimbolos();
    private TablaMetodos tablaMetodos = new TablaMetodos();
    private TablaStruct tablaStruct = new TablaStruct();
	private ArrayList<InterLine> code;
	
	public InterCode(TablaSimbolos tableSymbol, TablaMetodos tableMethod, TablaStruct tableStruct) {
        this.code = new ArrayList<InterLine>();
        tablaSimbolos = tableSymbol;
        tablaMetodos = tableMethod;
        tablaStruct = tableStruct;
    }
    
    public void addCode(InterLine codeLine){
        this.code.add(codeLine);
    }
    
    public TablaSimbolos getTablaSimbolos() {
		return tablaSimbolos;
	}

	public void setTablaSimbolos(TablaSimbolos tablaSimbolos) {
		this.tablaSimbolos = tablaSimbolos;
	}

	public TablaMetodos getTablaMetodos() {
		return tablaMetodos;
	}

	public void setTablaMetodos(TablaMetodos tablaMetodos) {
		this.tablaMetodos = tablaMetodos;
	}

	public TablaStruct getTablaStruct() {
		return tablaStruct;
	}

	public void setTablaStruct(TablaStruct tablaStruct) {
		this.tablaStruct = tablaStruct;
	}

	public ArrayList<InterLine> getCode() {
		return code;
	}

	public void setCode(ArrayList<InterLine> code) {
		this.code = code;
	}

	public InterLine searchCodeGlobal(String var)
    {
        for (InterLine inter : this.code)
        {
           String etiqueta = inter.getEtiqueta().substring(0,inter.getEtiqueta().indexOf("_"));
           if (inter.isGlobal()&&etiqueta.equals(var))
           {
               return inter;
           }
        }
        
        return null;
    }
    
    public Simbolo searchSymbolLastScope(String var){
        ArrayList<Simbolo> symbols = new ArrayList<Simbolo>();
        for (Simbolo simbolo : tablaSimbolos.Table) 
        {
            if (simbolo.getId().equals(var))
            {
                symbols.add(simbolo);
            }
        }
        if (symbols.size() >= 1)
        	return symbols.get(symbols.size()-1);
        else 
        	return null;
        	
    }
    
    public boolean searchGlobalSymbol(String var)
    {
    	for (Simbolo simbolo : tablaSimbolos.Table) 
        {
    		if (simbolo.getAmbito().getName() == 0 && simbolo.getId().equals(var))
    		{
    			Simbolo sim = tablaSimbolos.getSymbol(var, simbolo.getAmbito());
    	        return sim.getAmbito().getName()==0;
    		}
        }
    	return false;
    }

    @Override
    public String toString() 
    {
        String returnCode = "";
        
        for (int i = 0;i<this.code.size();i++){
            String etiqueta = code.get(i).getEtiqueta();
            String dir1 = code.get(i).getDir1();
            String dir2 = code.get(i).getDir2();
            String dir3 = code.get(i).getDir3();
            String op = code.get(i).getOp();
            boolean global = code.get(i).isGlobal();
            boolean stIF = code.get(i).isIF();
            InterLine gotoE = code.get(i).getGotoE();
            
            if (etiqueta != null && global == false)
            {
                returnCode += etiqueta + "\n";
            }
            
            if (dir2 != null && dir3 != null && op != null && dir1 != null && !stIF)
            {
                returnCode += dir1 +" = "+ dir2 +" "+ op +" "+ dir3+"\n";
            }
            
            if (dir2 != null && dir3 == null && op != null && dir1 != null)
            {
                returnCode += dir1 +" "+ op +" "+ dir2+ "\n";
            }
            
            if (dir2 == null && op != null && dir3 != null)
            {
                returnCode += op +" "+ dir3 + "\n";
            }
            
            if (global == true)
            {
                returnCode += ".global " + etiqueta +"\n";
            }
            if (stIF)
            {
                returnCode += dir1 +" "+ dir2+" " + op +" "+ dir3 + "\n";
            }
            
            if (dir2 != null && dir3 != null && op == null && dir1 == null)
                returnCode += dir2 + " " + dir3 + "\n"; 
        }
        
       return returnCode;
    }    
}
