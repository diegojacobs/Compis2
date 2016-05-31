import java.util.ArrayList;


/**
@author Diego Jacobs
Date: May 30, 2016
 */
public class AddressDescriptor {
	private ArrayList<Address> direcciones; 

    public AddressDescriptor() {
        this.direcciones = new ArrayList();
    }
    
    
    public String revisarDireccion(String nombreVar){
       
        for(Address adr: direcciones){
            if (adr.getValor().equals(nombreVar)){
                return adr.getDireccion();
            }
            
        }
        
        return "";
    }
    
    public void addDireccion(String nombreVar, String direccion) {
        this.direcciones.add(new Address(nombreVar, direccion));
    }
}
