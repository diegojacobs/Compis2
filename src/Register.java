import java.util.ArrayList;


/**
@author Diego Jacobs
Date: May 30, 2016
 */
public class Register {
	 private String registro;
	 private int numRegistro;
	 private ArrayList variables =  new ArrayList();; 
    public static int numStatic = 0;
  
    public Register() {
        this.registro = "R";
        this.numRegistro=numStatic;
        this.registro +=this.numRegistro;
        numStatic += 1;
       
     
    }
    
    public Register(ArrayList vals){
         this.registro = "R";
        this.numRegistro=numStatic;
        this.registro +=this.numRegistro;
        this.variables = vals;
        numStatic += 1;
        
    }
    
      public Register(String val){
        this.registro = "R";
        this.numRegistro=numStatic;
        this.registro +=this.numRegistro;
        this.variables.add(val);
        numStatic += 1;
         
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public int getNumRegistro() {
        return numRegistro;
    }

    public void setNumRegistro(int numRegistro) {
        this.numRegistro = numRegistro;
    }

    public ArrayList getVariables() {
        return variables;
    }

    public void setVariables(ArrayList variables) {
        this.variables = variables;
    }
    
    public void addVariable(String var){
        this.variables.add(var);
    } 
   
    
    @Override
    public String toString() {
        return this.registro + this.variables.toString();
    }
}
