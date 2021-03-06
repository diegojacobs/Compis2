
/**
@author Diego Jacobs
Date: May 30, 2016
 */
public class Address {
	   private String valor;
	    private String direccion;

	    public Address(String valor, String direccion) {
	        this.valor = valor;
	        this.direccion = direccion;
	    }

	    public Address() {
	    }

	    public String getValor() {
	        return valor;
	    }

	    public void setValor(String valor) {
	        this.valor = valor;
	    }

	    public String getDireccion() {
	        return direccion;
	    }

	    public void setDireccion(String direccion) {
	        this.direccion = direccion;
	    }

	    @Override
	    public String toString() {
	        return "Address{" + "valor=" + valor + ", direccion=" + direccion + '}';
	    }
}
