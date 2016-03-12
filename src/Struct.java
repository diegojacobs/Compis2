import java.util.ArrayList;

public class Struct {

	private ArrayList<Simbolo> items = new ArrayList<Simbolo>();
    private Ambito ambit;
    private String id;

    public Struct()
    {
        this.id = "";
        ambit = null;
    }
    
    
    public Struct(String id, Ambito ambit, ArrayList<Simbolo> items)
    {
        this.id = id;
        this.ambit = ambit;
        this.items = items;
    }

    public ArrayList<Simbolo> getItems() 
    {
        return items;
    }

    public Ambito getAmbito() 
    {
        return ambit;
    }

    public String getId() 
    {
        return id;
    }

    /*****************************************
     * @return devolvemos el tipo de la estructura
     *****************************************/
    public Type getType() 
    {
        Type type = new Type(this.id);
        
        type.setItems(items);
        type.setStruct(true);
        
        return type;
    }

    /*****************************************
     * @return devolvemos una tabla con toda la informacion de la estructura
     *****************************************/
    public String[] getInfo() 
    {
        String[] a = new String[3];
        
        a[0] = this.id;
        a[1] = this.ambit.getName()+"";
        a[2] = this.items.toString();
        
        return a;
    }

    public String toString()
    {
        String temp = "";
        
        temp += "\t" + id + " - ambit: " + ambit.getName() + "\n";
        
        for(Simbolo a : this.items)
            temp += "\t * " + a.getType().getType_name() + " " + a.getId() + "\n";
        
        return temp;
    }
}
