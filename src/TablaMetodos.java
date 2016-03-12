import java.util.ArrayList;

public class TablaMetodos {

    ArrayList<Metodos> Table = new ArrayList<Metodos>();

    public TablaMetodos()
    {
    	
    }

    /*****************************************
     * Cuando queremos agregar un nuevo metodo
     * @param nombre del metodo
     * @param tipo del return
     * @param parameteros
     * @return si puede ser agregado o no
     *****************************************/
    public boolean addMethod(String name, Type ret, ArrayList<Simbolo> parameters)
    {
        Metodos met = new Metodos(name, ret, parameters);
        
        for(Metodos a : this.Table)
            if(a.getMethodSignature().equals(met.getMethodSignature()))
            	return false;
            
        this.Table.add(met);
        return true;
    }

    /*****************************************
     * Cuando queremos agregar un metodo ya existente
     * @param metodo que deseamos agregar a la tabla
     * @return devolvemos si puede ser agregado
     *****************************************/
    public boolean addMethod(Metodos metodo)
    {
        for(Metodos a : this.Table)
            if(a.getMethodSignature().compareTo(metodo.getMethodSignature())==0)
            	return false;
            
        this.Table.add(metodo);
        return true;
    }

    /*****************************************
     * @param la firma del metodo
     * @return devolvemos el metodo
     *****************************************/
    public Metodos getMethod(String signature)
    {
        for(Metodos a : this.Table)
            if(a.getMethodSignature().compareTo(signature)==0)
            	return a;
        
        return null;
    }
    
    /*************************************
     * Devolvemos la tabla que contiene todos los metodos
     ************************************/
    public ArrayList<Metodos> getAllMethods()
    {
    	return this.Table;
    }

    /*****************************************
    * @return la tabla de metodos
    *****************************************/
    public String[][] getInfo()
    {
        String[][] a = new String[this.Table.size()][4];
      
        for(int i=0;i<a.length;i++)
        {
            String[] b = this.Table.get(i).getInfo();
            a[i][0]= b[0];
            a[i][1]= b[1];
            a[i][2]= b[2];
            a[i][3]= b[3];
        }
        
        return a;
    }

    /*****************************************
    * @return una tabla vacia
    *****************************************/
    public String[][] getDefaultInfo()
    {
        String[][] a = new String[1][4];
        a[0][0]= "";
        a[0][1]= "";
        a[0][2]= "";
        a[0][3]= "";
        
        return a;
    }

    /*****************************************
    * @return los titulos de la tabla
    *****************************************/
    public static String[] getColumsTitles()
    {
        String columNames[] = {"Return", "Name", "Parameters", "Signature"};
        return columNames;
    }

    public String toString()
    {
        String temp  = "";
        
        for(Metodos a: this.Table)
            temp +=a.toString()+"\n";
        
        return temp;
    }


    
}