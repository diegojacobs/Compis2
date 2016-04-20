import java.util.ArrayList;
import java.util.Map;

public class TablaSimbolos {

    ArrayList<Simbolo> Table;

    public TablaSimbolos()
    {
        Table = new ArrayList<Simbolo>();
    }

    /*****************************************
    * @param simbolo que vamos a agregar
    * @return decimos si podemos agregar el simbolo
    *****************************************/
    public boolean addSymbol(Simbolo symbol)
    {
        return Table.add(symbol);
    }

    /*****************************************
    * @param simbolos que vamos a agregar
    * @return decimos si podemos agregar los simbolos
    *****************************************/
    public boolean addAllSymbol(ArrayList<Simbolo> symbol)
    {
        return Table.addAll(symbol);
    }

    /*****************************************
    * @param ambit ambito que buscamos
    * @return decimos si existe
    *****************************************/
    public boolean existSymbolInAmbit(String id, Ambito ambit)
    {
        ArrayList<Simbolo> list = this.getAllSymbolInAmbit(ambit);
        for(int i=0;i<list.size();i++)
        {
            String name = list.get(i).getId();
            if(name.equals(id))
                return true;
        }
        return false;
    }
   

    /*****************************************
    * @param ambito que buscamos
    * @return decimos si existe
    *****************************************/
    public boolean existTypeInAmbit(String type_name, Ambito ambit)
    {
        ArrayList<Simbolo> list = this.getAllSymbolInAmbit(ambit);
        for(int i=0;i<list.size();i++)
        {
            String name = list.get(i).getType().getType_name();
            if(name.equals(type_name))
                return true;
        }
        return false;
    }

    /*****************************************
    * @param ambito en el que estamos buscando
    * @return la lista de simbolos en el ambito
    *****************************************/
    public ArrayList<Simbolo> getAllSymbolInAmbit(Ambito ambit)
    {
        ArrayList<Simbolo> list = new ArrayList<Simbolo>();

        for(Simbolo a : this.Table)
            if(a.getAmbito().getName() == ambit.getName())
                list.add(a);
  
        return list;
    }

    /*****************************************
    * @param Ambito en el que vamos a buscar
    * @return lista de simbolos en el ambito
    *****************************************/
    public Simbolo getSymbol(String id, Ambito ambit)
    {
        Ambito local_ambit = ambit;

        while(local_ambit != null)
        {
            ArrayList<Simbolo> list = getAllSymbolInAmbit(local_ambit);

            for(int i=0;i<list.size();i++)
            {
                String name = list.get(i).getId();
                if(name.equals(id))
                    return list.get(i);
                
            }
            local_ambit = local_ambit.getPrev();
        }
        return null;
    }

    /*****************************************
    * @param ambito en el que vamos a buscar
    * @return lista de simbolos en el ambito
    *****************************************/
    public boolean existTypeStruct(String id, Ambito ambit)
    {
        Ambito local_ambit = ambit;

        while(local_ambit != null)
        {
            ArrayList<Simbolo> list = getAllSymbolInAmbit(local_ambit);

            for(int i=0;i<list.size();i++)
            {
                String type_name = list.get(i).getType().getType_name();
                boolean isStruct = list.get(i).getType().isStruct();
                if(type_name.equals("struct:" + id) && isStruct)
                    return true;
                
            }
            local_ambit = local_ambit.getPrev();
        }
        return false;
    }

    /*****************************************
    * @param ambito en el que vamos a buscar
    * @return lista de simbolos en el ambito
    *****************************************/
    public Type getTypeStruct(String id, Ambito ambit)
    {
        Ambito local_ambit = ambit;

        while(local_ambit != null)
        {
            ArrayList<Simbolo> list = getAllSymbolInAmbit(local_ambit);

            for(int i=0;i<list.size();i++)
            {
                String type_name = list.get(i).getType().getType_name();
                boolean isStruct = list.get(i).getType().isStruct();
                if(type_name.equals("struct:" + id) && isStruct)
                    return list.get(i).getType();
                
            }
            local_ambit = local_ambit.getPrev();
        }
        return null;
    }
    
    public Simbolo findAllScopes(String nombreVar){
        
        try{ 
           for (Simbolo sim:this.Table) 
           {
               if (sim.getId().trim().equals(nombreVar.trim()))
                   return sim;
           }
        
        }catch(Exception e){}
       return null;
   
   }
    
    /*****************************************
    * @return devolvemos la tabla con la informacion
    *****************************************/
    public String[][] getInfo()
    {
        String[][] a = new String[this.Table.size()][9];
        for(int i=0;i<a.length;i++)
        {
            String[] b = this.Table.get(i).getInfo();
            
            a[i][0] = b[0];
            a[i][1] = b[1];
            a[i][2] = b[2];
            a[i][3] = b[3];
            a[i][4] = b[4];
            a[i][5] = b[5];
            a[i][6] = b[6];
            a[i][7] = b[7];
            a[i][8] = b[8];
        }
        return a;
    }

    /*****************************************
    * @return devolvemos la tabla de informacion vacia
    *****************************************/
    public String[][] getDefaultInfo()
    {
        String[][] a = new String[1][9];
        a[0][0] = "";
        a[0][1] = "";
        a[0][2] = "";
        a[0][3] = "";
        a[0][4] = "";
        a[0][5] = "";
        a[0][6] = "";
        a[0][7] = "";
        a[0][8] = "";
        
        return a;
    }

    /*****************************************
    * @return devolvemos los titulos de la tabla
    *****************************************/
    public String[] getColumsTitles()
    {
        String columNames[] = {"id", "Ambito", "Type", "Length", "Simple","Struct","Param","Array","Items"};
        return columNames;
    }  

}
