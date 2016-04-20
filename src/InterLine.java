
/**
@author Diego Jacobs
Date: Apr 17, 2016
 */
public class InterLine {
	private String dir1; 		//Resultado
	private String dir2; 		//Primer operador
	private String dir3; 		//Segundo Operador si existe
	private String op;   		//Operador
	private String etiqueta; 	//Si solo es etiqueta	//
	private String length;		//Tamaño que reservamos
	private InterLine gotoE; 	//Si es un salto
	private boolean global;		//Si es global
	private boolean IF;			//Si esta dentro de un if
	
	
	public InterLine(String dir2, String dir3, String op) {
		this.dir2 = dir2;
		this.dir3 = dir3;
		this.op = op;
		this.global = false;
	}
	
	public InterLine()
	{
		this.global = false;
	}

	public String getDir1() {
		return dir1;
	}

	public void setDir1(String dir1) {
		this.dir1 = dir1;
	}

	public String getDir2() {
		return dir2;
	}

	public void setDir2(String dir2) {
		this.dir2 = dir2;
	}

	public String getDir3() {
		return dir3;
	}

	public void setDir3(String dir3) {
		this.dir3 = dir3;
	}

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	public boolean isGlobal() {
		return global;
	}

	public void setGlobal(boolean global) {
		this.global = global;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public InterLine getGotoE() {
		return gotoE;
	}

	public void setGotoE(InterLine gotoE) {
		this.gotoE = gotoE;
	}
	
	public boolean isIF() {
		return IF;
	}

	public void setIF(boolean iF) {
		IF = iF;
	}
}
