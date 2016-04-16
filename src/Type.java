import java.util.ArrayList;

public class Type {

    private boolean simple;    
    private boolean struct; 
    private boolean param;     
    private boolean array;   
    
    private int length_array;
    
    private ArrayList<Simbolo> items = new ArrayList<Simbolo>();
    private String type_name;
    private String var_name;
    private int type_len;

    public Type(String type_name)
    {
    	this.var_name = "";
        this.type_name = type_name;
        this.simple = false;
        this.struct = false;
        this.array = false;
        this.param = false;
        this.length_array = 0;
        
        if (type_name != null)
        {	
	        if (type_name.equals("int"))
	        {
	        	this.type_len = 4;
	        }
	        
	        if (type_name.equals("char"))
	        {
	        	this.type_len = 2;
	        }
	        
	        if (type_name.equals("boolean"))
	        {
	        	this.type_len = 1;
	        }
        }
    }
    
    public Type(String nombre, String tipo) {
        this.var_name = nombre;
        this.type_name = tipo;
        this.param = false;
        this.array =false;
        this.struct = false;
        this.length_array = 0;
        
        if (type_name != null)
        {	
	        if (type_name.equals("int"))
	        {
	        	this.type_len = 4;
	        }
	        
	        if (type_name.equals("char"))
	        {
	        	this.type_len = 2;
	        }
	        
	        if (type_name.equals("boolean"))
	        {
	        	this.type_len = 1;
	        }
        }
    }

   
    public String getNombreVariable() {
        return var_name;
    }

    public void setNombreVariable(String nombre) {
        this.var_name = nombre;
    }

    public boolean isSimple() 
    {
        return simple;
    }

    public void setSimple(boolean simple) 
    {
        this.simple = simple;
    }

    public boolean isStruct() 
    {
        return struct;
    }

    public void setStruct(boolean struct) 
    {
        this.struct = struct;
    }

    public boolean isArray() 
    {
        return array;
    }

    public void setArray(boolean array, int array_len) 
    {
        this.array = array;
        this.length_array = array_len;
        
        if (array)
        {
        	this.type_len = this.type_len * this.length_array;
        }
    }
    
    public void setArray(boolean array) 
    {
        this.array = array;
        
        if (array)
        {
        	this.type_len = this.type_len * this.length_array;
        }
    }

    public ArrayList<Simbolo> getItems() 
    {
        return items;
    }

    public void setItems(ArrayList<Simbolo> items) 
    {
        this.items = items;
    }

    /*****************************************
     * @return devolvemos el nombre de tipo con "Struct"
     *****************************************/
    public String getType_name() 
    {
        String type = type_name;
        
        if(this.isArray())
            type = type + "[]";
        
        if(this.isStruct())
            type = "struct:" + type;
        
        return type;
    }

    /*****************************************
     * @return devolvemos el nombre de tipo sin "Struct"
     *****************************************/
    public String getType_name2() 
    {
        String type = type_name;
        
        if(this.isArray())
            type = type + "[]";
        
        if(this.isStruct())
            type = "" + type;
        
        return type;
    }

    public void setType_name(String type_name) 
    {
        this.type_name = type_name;
    }

    public boolean isParam() 
    {
        return param;
    }

    public void setParam(boolean param) 
    {
        this.param = param;
    }

	public int getLength_array() {
		return length_array;
	}

	public void setLength_array(int length_array) {
		this.length_array = length_array;
	}

	public int getType_len() {
		return type_len;
	}

	public void setType_len(int type_len) {
		this.type_len = type_len;
	}
}
