
/**
@author Diego Jacobs
Date: Apr 15, 2016
 */
public class StackPointer {
	
	private String id;
	private int pos;
    private String value;
    private String type;
    public static int cont = 0;
    
	public StackPointer(String id, int pos, String type) {
		this.id = id;
		this.pos = pos + StackPointer.cont;
		StackPointer.cont = this.pos;
		this.type = type;
	}
	
	public void incPos(int pos)
	{
		this.pos = pos + StackPointer.cont;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}  
}
