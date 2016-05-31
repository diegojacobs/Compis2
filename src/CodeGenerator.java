
/**
@author Diego Jacobs
Date: May 30, 2016
 */
public class CodeGenerator {
	FileCreator file = new FileCreator();
    String strFile ="";
    public CodeGenerator(){
        String header;
        header = "/*" +"\n" +
                "* Diego Jacobs " + "\n"+
                "* 13160 " + "\n"+
                "*/"+"\n"+"\n"+
                ".data"+"\n"+
                ".align 2"+"\n"+"\n"+
                "print_num:    .asciz \"%d \\n\""+"\n"+
                "print_str:    .asciz \"%f \\n\""+"\n"+
                "global: .space"+"\n"+
                "offset: .space"+"\n"+"\n"+
                ".text"+"\n"+
                ".align 2"+"\n"+"\n"+
                ".global main"+"\n"+
                ".type main, %function"+"\n";
             
                
        
                
         strFile += header;
       
    }
    
    public void insertCode(String str, int cantTabs, int cantEnters, String comment){
        str = str + "\t" + "//" + comment + "\n";
        for (int i = 0; i<cantTabs;i++){
            str = "\t" + str;
        }
        if (!comment.isEmpty()){
            cantEnters--;
        }
         for (int i = 0; i<cantEnters;i++){
            str = str + "\n";
        }
        
        strFile += str;
    }
    
     public void insertCode(String str, int cantTabs, int cantEnters){
        for (int i = 0; i<cantTabs;i++){
            str = "\t" + str;
        }
         for (int i = 0; i<cantEnters;i++){
            str = str + "\n";
        }
       
        strFile += str;
    }
    
    public void crearArchivo(){
         file.crear(strFile);
    }
}
