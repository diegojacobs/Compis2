/**
Diego Jacobs
Feb 15, 2016
 */
public class Simbolo {

    private String id;
    private Ambito ambit;
    private Type type;

    public Simbolo()
    {
        this.id = "";
    }

    public Simbolo(String id, Type type, Ambito ambit)
    {
        this.id = id;
        this.ambit = ambit;
        this.type = type;
    }

    public String getId() 
    {
        return id;
    }

    public void setId(String id) 
    {
        this.id = id;
    }

    public Ambito getAmbito() 
    {
        return ambit;
    }

    public void setAmbito(Ambito ambit) 
    {
        this.ambit = ambit;
    }

    public Type getType() 
    {
        return type;
    }

    public void setType(Type type) 
    {
        this.type = type;
    }

    /*****************************************
     * @return devolvemos un array con la información del simbolo
     *****************************************/
    public String[] getInfo() 
    {
        String[] a = new String[9];
        
        a[0] = this.id;
        a[1] = this.ambit.getName() + "";
        a[2] = this.getType().getType_name();
        a[3] = this.getType().getType_len() + "";
        a[4] = this.getType().isSimple() + "";
        a[5] = this.getType().isStruct() + "";
        a[6] = this.getType().isParam() + "";
        a[7] = this.getType().isArray() + "";
        a[8] = this.getType().getItems().toString();
        
        return a;
    }


    public String toString()
    {
        String temp = "";
        
        temp += id + ": " + type.getType_name();
        
        return temp;
    }
    
}
