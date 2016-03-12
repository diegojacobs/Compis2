import java.util.ArrayList;

public class TablaStruct {

    ArrayList<Struct> Table = new ArrayList<Struct>();

    public TablaStruct()
    {
    	
    }

    /*****************************************
    * Para ver si podemos agregar un Struct nuevo
    * @param id del struct que agregaremos
    * @param Ambito donde se encuentra el Struct
    * @param la lista de items que contiene
    * @return decimos si puede ser agregado
    *****************************************/
    public boolean addStruct(String id, Ambito ambit, ArrayList<Simbolo> items)
    {
        return Table.add(new Struct(id, ambit, items));
    }
    
    /*****************************************
     * Para ver si podemos agregar un Struct ya existente
     * @param id del struct que agregaremos
     * @param Ambito donde se encuentra el Struct
     * @param la lista de items que contiene
     * @return decimos si puede ser agregado
     *****************************************/
    public boolean addStruct(Struct struct)
    {
        Struct st = this.getStruct(struct.getId(), struct.getAmbito());
        
        if(st == null)
        {
            return Table.add(struct);
        }
        
        return false;
    }



    /*****************************************
    * @return devolvemos la ultima Struct en nuestra tabla
    *****************************************/
    public Struct getLastStruct()
    {
        return this.Table.get(Table.size());
    }
    
    /*****************************************
    * @param Ambito en el que vamos a buscar el id
    * @return Una lista de Struct en el ambito que buscamos
    *****************************************/
    public Struct getStruct(String id, Ambito ambit)
    {
        Ambito local_ambit = ambit;

        while(local_ambit != null)
        {
            ArrayList<Struct> list = getAllStructsInambit(local_ambit);

            for(int i=0; i<list.size(); i++)
            {
                String name = list.get(i).getId();
                if(name.equals(id))
                {
                    return list.get(i);
                }
            }
            
            local_ambit = local_ambit.getPrev();
        }
        return null;
    }


    /*****************************************
    * @param ambito en el que queremos buscar
    * @return una lista con Structs que esten en el ambito
    *****************************************/
    public ArrayList<Struct> getAllStructsInambit(Ambito ambit)
    {
        ArrayList<Struct> list = new ArrayList<Struct>();

        for(Struct a : this.Table)
            if(a.getAmbito().getName() == ambit.getName())
                list.add(a);
       
        return list;
    }

    public ArrayList<Struct> getAllStructs()
    {
        return this.Table;
    }

    
    /*****************************************
    * @return Tabla con la informacion de todas las Structs
    *****************************************/
    public String[][] getInfo(){
        String[][] a = new String[this.Table.size()][3];
        
        for(int i=0; i<a.length; i++)
        {
            String[] b = this.Table.get(i).getInfo();
            
            a[i][0] = b[0];
            a[i][1] = b[1];
            a[i][2] = b[2];
        }
        
        return a;
    }

    /*****************************************
    * @return Tabla vacia
    *****************************************/
    public String[][] getDefaultInfo()
    {
        String[][] a = new String[1][3];
        
        a[0][0] = "";
        a[0][1] = "";
        a[0][2] = "";
        
        return a;
    }

    /*****************************************
    * @return Titulos de la tabla
    *****************************************/
    public static String[] getColumsTitles()
    {
        String columNames[] = {"Struct name", "Ambito", "Items"};
        return columNames;
    }

    public String toString()
    {
        String temp  = "";
        
        for(Struct a: this.Table)
            temp += a.toString()+"\n";
        
        return temp;
    }

}