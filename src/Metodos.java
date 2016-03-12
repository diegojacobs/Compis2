import java.util.ArrayList;

/**
Diego Jacobs
Feb 15, 2016
 */

public class Metodos {

    ArrayList<Simbolo> param = new ArrayList<Simbolo>();
    Type return_val;
    String name;

    public Metodos(String name, Type return_val, ArrayList<Simbolo> parameters)
    {
        this.param = parameters;
        this.return_val = return_val;
        this.name = name;
    }

    /*****************************************
    * @return devolvemos la firma del metodo
    *****************************************/
    public String getMethodSignature()
    {
        String retorno = name + "(";
        if(param.size()>0)
        {
            retorno += param.get(0).getType().getType_name() + ",";
            for(int i = 1; i<this.param.size(); i++)
            {
                Simbolo a  = param.get(i);
                retorno += ""+a.getType().getType_name() + ",";
            }
            retorno = retorno.substring(0, retorno.length()-1);
        }
        
        retorno += ")";
        
        return retorno;
    }


    /*****************************************
     * @return devolvemos una tabla con toda la informacion
     *****************************************/
    public String[] getInfo() 
    {
        String[] a = new String[4];
        a[0] = this.return_val.getType_name();
        a[1] = this.name+"";
        a[2] = this.param.toString();
        a[3] = this.getMethodSignature();
        
        return a;
    }

    public String toString()
    {
        String temp  = "";
        temp += "\t" + this.getMethodSignature();
        return temp;
    }
    
}
