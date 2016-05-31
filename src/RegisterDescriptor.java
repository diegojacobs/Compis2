import java.util.ArrayList;


/**
@author Diego Jacobs
Date: May 30, 2016
 */
public class RegisterDescriptor {
private ArrayList<Register> registros; 
    
    
    public RegisterDescriptor() {
        this.registros = new ArrayList();
    }
    
    public String revisarRegistros(String nombreVar)
    {   
        for(Register rg: registros)
        {
            if (rg.getVariables().contains(nombreVar))
            {
                return "R"+rg.getNumRegistro();
            }   
        }     
        return "";
    }
    
    public Register agregarRegistro(String nombreVar) 
    {
        if (this.registros.size() == 4)
        {
            return this.buscarRegistroMenor();
        }
        Register rg  = new Register(nombreVar);
        this.registros.add(rg);
        return rg;
    }
    

    public Register buscarRegistroMenor(){
        if (this.registros.size()>0){
            Register p1 = this.registros.get(0);
            for (int i = 1;i<this.registros.size();i++){
                if (this.registros.get(i).getVariables().size()<p1.getVariables().size()){
                    p1 = this.registros.get(i);
                }
            }
            return p1;
        }
        return null;
        
    }
     public Register buscarRegistroMenor(Register actual){
         Register metodo = buscarRegistroMenor();
         if (metodo.getRegistro().equals(actual.getRegistro())){
            Register nuevo = agregarRegistro(actual.getVariables().get(0).toString());
            metodo = nuevo;
         }
        return metodo;
    }
     
     public void resetAll(){
         for(Register rg: this.registros){
             rg.getVariables().clear();
         }
     }
    
    public void setRegistroValor(Register r, String var){
        r.getVariables().clear();
        r.addVariable(var);
    }
    
    public void setRegistroValor(String registro, String var){
        for(Register r: this.registros){
            if (r.getRegistro().equals(registro)){
                r.getVariables().clear();
                r.addVariable(var);
            }
        }
    }
}
