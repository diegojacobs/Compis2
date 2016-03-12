import java.util.ArrayList;
import java.util.LinkedList;

/**
Diego Jacobs
Feb 15, 2016
 */

public class Ambito {
    
    private int name;
    private Ambito prev;
    private ArrayList<Ambito> next = new ArrayList<Ambito>();
    private int count_child = 0;
    public static int cont = 0;

    public Ambito()
    {
        name = (cont++);
        this.prev = null;
    }

    public Ambito(Ambito prev)
    {
        name = (cont++);
        this.prev = prev;
        prev.addKid(this);
    }

    /*****************************************
     * @return the name
     *****************************************/
    public int getName() 
    {
        return name;
    }

    /*****************************************
     * @param name the name to set
     *****************************************/
    public void setName(int name) 
    {
        this.name = name;
    }

    /*****************************************
     * @return the previous
     *****************************************/
    public Ambito getPrev() 
    {
        return prev;
    }

    /*****************************************
     * @param the previous to set
     *****************************************/
    public void setPrev(Ambito prev) 
    {
        this.prev = prev;
    }

    /*****************************************
     * @param prev the prev to set
     *****************************************/
    public void addKid(Ambito hijo) 
    {
        this.next.add(hijo);
    }

    /*****************************************
     * @param scope the scope to set
     *****************************************/
    public Ambito getKid() 
    {
        if(count_child<this.next.size())
            return this.next.get(count_child++);
        
        return null;
    }

    /*****************************************
     * @return the next
     *****************************************/
    public ArrayList<Ambito> getNext() 
    {
        return next;
    }

    /*****************************************
     * @param next the next to set
     *****************************************/
    public void setNext(ArrayList<Ambito> next) 
    {
        this.next = next;
    }
}

