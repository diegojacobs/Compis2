
/**
@author Diego Jacobs
Date: May 30, 2016
 */
public class Compilation {
	private AddressDescriptor address;
    private RegisterDescriptor registers;
    private CodeGenerator asm = new CodeGenerator();
    private int stackPointer = -4;
    private int offset = 0;
    private String lastMethod="";
    private String lastOp = "";
    private int paramsCounter = 0;
   
    
   
    public Compilation(InterCode table){
        this.address = new AddressDescriptor();
        this.registers = new RegisterDescriptor();
        
        genCode(table);
        asm.crearArchivo();
    } 
    
    //generar c�digo base de un archivo .s
    //separar estructura de m�todo main y subm�todos
    private void genCode(InterCode tabla){
        for (InterLine inter: tabla.getCode()){
             String etiqueta = inter.getEtiqueta();
            String dir1 = inter.getDir2();
            String dir2 = inter.getDir3();
            String op = inter.getOp();
            String res = inter.getDir1();
            boolean global = inter.isGlobal();
            boolean stIF = inter.isIF();
            boolean declaration =inter.isDeclaration();
            StackPointer localStack = inter.getLocalStack();
            boolean salidaMetodo = inter.isSalidaMetodo();
            boolean method = inter.isMethod();
            //cuadrupla de c�digo intermedio
            if (dir1 != null && dir2 != null && op != null &&
                  res != null && !stIF){
               getReg(inter);
            }
            //cuando se asigna un valor
            else if (dir1 != null &&op!= null &&
                 res != null && !stIF&&!global){
               getRegDir1(inter);
            }
            //cuando es una etiqueta
            else if (etiqueta != null && global == false && declaration == false){
                //insertar codigo especial cuando es la etiqueta main
                if (etiqueta.equals("main:")){
                    asm.insertCode(etiqueta, 0, 1);
                    asm.insertCode("stmfd sp!, {fp, lr}", 1, 2);
                    lastMethod = "main";
                    //registro de activaci�n
                    //cargar offset para el m�todo
                    //cargar parametros
                }
                else if (etiqueta.equals("printNum:")){
                    asm.insertCode(etiqueta, 0, 1);
                    //asm.insertCode("LDR R5, =offset",1,1);
                    //asm.insertCode("LDR R5, [R5]", 1,2, "Cargar offset actual");
                    asm.insertCode("PUSH {LR} ",1,2);
                   // asm.insertCode("ADD R6, R5, #0", 1, 1, "Calcular offset donde viene el param");
                    //this.stackPointer -= 4;
                    lastMethod = "printNum";
                   
                }
                else{
                    asm.insertCode(etiqueta, 0, 1);
                   // asm.insertCode("LDR R5, =offset",1,1);
                   // asm.insertCode("LDR R5, [R5]", 1,1, "Cargar offset actual");
                    if (method){
                        asm.insertCode("PUSH {LR} ",1,2);
                        lastMethod = etiqueta;
                   // asm.insertCode("ADD R6, R5, #0", 1, 1, "Calcular offset donde viene el param");
                    }
                    
                }
                
            }
            else if(localStack != null){
                genDeclarations(inter);
            }
            else if (salidaMetodo){
                //registro de activaci�n para el retorono del m�todo
                 this.registers.resetAll();
                if (stackPointer >=0){
                  
                    stackPointer+=4;
                   
                    asm.insertCode("ADD SP, SP, #"+stackPointer, 1, 2, "Mover StackPointer para olvidar variables");
                    this.stackPointer = -4;
                }
                if (lastMethod.equals("main")){
       
                    asm.insertCode("MOV R0, #0", 1, 1, "Salida al SO");
                    asm.insertCode("MOV R3, #0", 1, 1);
                    asm.insertCode("ldmfd sp!, {fp, pc}", 1, 1);
                    asm.insertCode("bx lr", 1, 1);
                }
                else {
                    //AL FINALIZAR UNA SUBRUTINA
                    asm.insertCode("POP {pc}", 1,2);
                    paramsCounter = 0;
                }
            }
            //PARAMS    
            else if (dir1 != null && dir2 != null && op == null && res == null){
                //DIR1 LITERAL PARAM
                //DIR2 VALUE PARAM
              
                 String param = this.registers.revisarRegistros(dir2);
                 boolean cambia = false;
                 if (param.isEmpty()){
                       Register rg = this.registers.agregarRegistro(dir2);
                       param = rg.getRegistro();
                       cambia = true;
                 }
                try{
                    int num = Integer.parseInt(dir2);
                    String output = "MOV " + param + ", #"+ num;
                    asm.insertCode(output, 1, 1);
                    String push = "PUSH {"+ param+ "}";
                    asm.insertCode(push, 1, 2, "push param");
                }catch(Exception e){
                    //aqui entra si el param no es un n�mero
                    if (!dir2.contains("global")){
                        int num = Integer.parseInt(dir2.substring(dir2.indexOf("[")+1, dir2.indexOf("]")));
                        String instruccion = "LDR " + param+", " + "[sp , #"+(this.stackPointer-num)+"]";
                        
                        if (cambia)
                            asm.insertCode(instruccion, 1, 1, "Set value param " + dir2);
                        String push = "PUSH {"+ param+ "}";
                        asm.insertCode(push, 1, 2, "push param localStack");
                    }else{
                        int num = Integer.parseInt(dir2.substring(dir2.indexOf("[")+1, dir2.indexOf("]")));
                        String loadGlobal = "LDR " + param + ", =global";
                        if (cambia)
                            this.asm.insertCode(loadGlobal, 1, 1, "Cargar etiqueta global");
                        String instruccion = "LDR " +param+ ", "+ "["+param +", #"+(num)+"]";
                        asm.insertCode(instruccion, 1, 1, "Set value param " + dir2);
                        String push = "PUSH {"+ param+ "}";
                        asm.insertCode(push, 1, 2, "push param localStack");
                    }
                    this.stackPointer += 4;
                }
               
            }
            //method call
            else if (dir1 != null && dir2 != null && res != null && op == null){
                //DIR1 CALL literal
                //dir2 method that is called
                //res temporal value saved
                this.offset += this.stackPointer;
                //String saveOffset = "LDR R0, =offset";
                //String save = "MOV R1, #"+this.offset;
                //String off = "STR R1, [R0]";
                //asm.insertCode(saveOffset, 1, 1);
                //asm.insertCode(save, 1, 1);
               // asm.insertCode(off, 1, 2, "Guardar offset actual");
                asm.insertCode("bl " + dir2, 1, 2);
                paramsCounter = 0;
              
            }
            else if (stIF){
            
                booleanStatement(inter);
            }
            //Goto statements
            else if (dir1 == null && op != null && dir2 != null){
                String goTo = "b " + dir2;
                asm.insertCode(goTo, 1, 2, "Goto " + dir2);
            }
            else {
                 System.out.println("Caso no contemplado" + inter);
            }
        }
    }
    
    public void getReg(InterLine codigo){
        String res =  codigo.getDir1();
        String dir1 = codigo.getDir2();
        String dir2 = codigo.getDir3();
        String op = codigo.getOp();
        String output ="";
       
        //if dir1 is not in Rdir1 (according to the address descriptor of Rdir)
        String registerDescriptionR1 = this.registers.revisarRegistros(dir1);
        
        if (registerDescriptionR1.isEmpty()){
            Register rg = this.registers.agregarRegistro(dir1);
            System.out.println(rg);
            String instruccion="";
            //int num1 = Integer.parseInt(dir1.substring(dir1.indexOf("[")+1, dir1.indexOf("]")));
            //String instruccion = "LDR " + rg.getRegistro()+", " + "[sp , #"+(this.stackPointer-num1)+"]";
            try {
                int num = Integer.parseInt(dir1);
                if (op.equals("/")){
                    instruccion = "MOV " +"R0" + ", #"+ num; 
                }else{
                    instruccion = "MOV " + rg.getRegistro()+", #"+num;
                }
            }        
            catch(Exception e){
                //cargar desde memoria, stackpointer, etc.
                int num1 = Integer.parseInt(dir1.substring(dir1.indexOf("[")+1, dir1.indexOf("]")));
                //use R4 as offset
                //String offSet = "ADD R6, R5, #" + (this.stackPointer-num1);
               // asm.insertCode(offSet, 1, 1, "Cargar offset a un registro");
                instruccion = "LDR " + rg.getRegistro()+", " + "[sp ,#"+(this.stackPointer-num1)+"]";
            }
            asm.insertCode(instruccion, 1, 1);  
            registerDescriptionR1 = rg.getRegistro();
        }
     
        
       
        try {
            int num = Integer.parseInt(dir2);
            if (!op.equals("*")&&!op.equals("/")){
                Register rRes = this.registers.buscarRegistroMenor();
                //modificar descriptor de registros;
                this.registers.setRegistroValor(rRes, res);
                if (op.equals("+")){
                    output = "ADD "+ rRes.getRegistro() +", "+ registerDescriptionR1 + ", #"+dir2;
                }
                if (op.equals("-")){
                    output = "SUB "+ rRes.getRegistro() +", "+ registerDescriptionR1 + ", #"+dir2;
                }
                if (op.equals("==")){
                    lastOp = ">";
                    String preOutPut = "MOV " + rRes.getRegistro()+ ", #" +dir2;
                    asm.insertCode(preOutPut, 1, 1, "Cargar valor literal");
                    output = "CMP " + registerDescriptionR1 + ", " + rRes.getRegistro();
                }
                if (op.equals("<=")){
                    lastOp = ">";
                    String preOutPut = "MOV " + rRes.getRegistro()+ ", #" +dir2;
                    asm.insertCode(preOutPut, 1, 1, "Cargar valor literal");
                    output = "CMP " + registerDescriptionR1 + ", " + rRes.getRegistro();
                }
                if (op.equals("<")){
                  lastOp = ">";
                    String preOutPut = "MOV " + rRes.getRegistro()+ ", #" +dir2;
                    asm.insertCode(preOutPut, 1, 1, "Cargar valor literal");
                    output = "CMP " + registerDescriptionR1 + ", " + rRes.getRegistro();
                }
                  if (op.equals(">=")){
                    lastOp = ">";
                    String preOutPut = "MOV " + rRes.getRegistro()+ ", #" +dir2;
                    asm.insertCode(preOutPut, 1, 1, "Cargar valor literal");
                    output = "CMP " + registerDescriptionR1 + ", " + rRes.getRegistro();
                }
                    if (op.equals(">")){
                    lastOp = ">";
                    String preOutPut = "MOV " + rRes.getRegistro()+ ", #" +dir2;
                    asm.insertCode(preOutPut, 1, 1, "Cargar valor literal");
                    output = "CMP " + registerDescriptionR1 + ", " + rRes.getRegistro();
                }
                if (op.equals("!=")||op.equals("<>")){
                    lastOp = ">";
                    String preOutPut = "MOV " + rRes.getRegistro()+ ", #" +dir2;
                    asm.insertCode(preOutPut, 1, 1, "Cargar valor literal");
                    output = "CMP " + registerDescriptionR1 + ", " + rRes.getRegistro();
                }

                System.out.println(output);
                asm.insertCode(output, 1, 1, codigo.getDir1() + " = " + dir1 +" " + op + " " + dir2);
            }
            else {
                num = 1/0;//throw exception
            }
            
                
        }
        catch(Exception e){
            
            String registerDescriptionR2 = this.registers.revisarRegistros(dir2);
            if (registerDescriptionR2.isEmpty()){
              Register rg2 = this.registers.agregarRegistro(dir2);
              registerDescriptionR2 = rg2.getRegistro();
                System.out.println(dir2);
                if (dir2.contains("[")){
                    int num = Integer.parseInt(dir2.substring(dir2.indexOf("[")+1, dir2.indexOf("]")));
                    //String offSet = "ADD R6, R5, #" + (this.stackPointer-num);
                   // asm.insertCode(offSet, 1, 1, "Cargar offset a un registro");
                    String instruccion = "LDR " + rg2.getRegistro()+", " + "[sp , #"+ (this.stackPointer-num)+"]";
                    asm.insertCode(instruccion, 1, 1, "Set value " + dir2);
              }
                //es u numero
              else {
                    String instruccion;
                    if (op.equals("/")){
                        instruccion = "MOV R1 , #"+dir2;
                    }
                    else {
                        instruccion = "MOV " + rg2.getRegistro() + ", #" + dir2;
                    }
                    asm.insertCode(instruccion, 1, 1, "Cargar valor literal");
                }
            }
            
              Register rRes = this.registers.buscarRegistroMenor();
                //modificar descriptor de registros;
                this.registers.setRegistroValor(rRes, res);
                if (op.equals("+")){
                    output = "ADD "+ rRes.getRegistro() +", "+ registerDescriptionR1 + ", " + registerDescriptionR2;
                }
                if (op.equals("-")){
                    output = "SUB "+ rRes.getRegistro() +", "+ registerDescriptionR1 + ", " + registerDescriptionR2;
                }
                if (op.equals("*")){
                    output = "MUL "+ rRes.getRegistro() +", "+ registerDescriptionR1 + ", " + registerDescriptionR2;
                }
                if (op.equals("/")){
                    //cargar valores en r0 y r1
                    asm.insertCode("bl dividir", 1, 2, "Llamar a la subrutina de division");
                }
                if (op.equals("==")){
                    lastOp = "==";
                    output = "CMP " + registerDescriptionR1 + ", " + registerDescriptionR2;
                }
                if (op.equals("<=")){
                    lastOp = "<=";
                     output = "CMP " + registerDescriptionR1 + ", " + registerDescriptionR2;
                }
                if (op.equals("<")){
                    lastOp = "<";
                    output = "CMP " + registerDescriptionR1 + ", " + registerDescriptionR2;
                }
                if (op.equals(">=")){
                    lastOp = ">=";
                    output = "CMP " + registerDescriptionR1 + ", " + registerDescriptionR2;
                }
                if (op.equals(">")){
                    lastOp = ">";
                    output = "CMP " + registerDescriptionR1 + ", " + registerDescriptionR2;
                }
                if (op.equals("!=")||op.equals("<>")){
                    lastOp = "!=";
                    output = "CMP " + registerDescriptionR1 + ", " + registerDescriptionR2;
                }

                System.out.println(output);
                asm.insertCode(output, 1, 1, codigo.getDir1() + " = " + dir1 +" " + op + " " + dir2);
            }
        }
        
    public void genDeclarations(InterLine codigo){
        StackPointer localStack = codigo.getLocalStack();
     
        this.stackPointer = this.stackPointer + 4;
        if (codigo.isParam()== false){
           
            asm.insertCode("MOV R0, #0", 1, 1, "Valor default");
            asm.insertCode("push {r0}", 1, 2, "Reservar espacio para " + localStack.getId());
            //modificar descriptor de registros
            this.registers.setRegistroValor("R0", "0");
        }
        //si es  parametro se le pone el valor que trae y se le reserva el espacio en pila.
        else{
            //asm.insertCode("ADD R6, R5, #0", 1, 1, "Offset del param dentro la pila");
            paramsCounter += 1;
            asm.insertCode("LDR R0, [SP, #"+(stackPointer+paramsCounter*4)+"]", 1, 1, "Valor del param se saca de la pila");
            asm.insertCode("push {r0}", 1, 2, "Reservar espacio para param " + localStack.getId());
            //this.stackPointer = this.stackPointer + 4;
            if (lastMethod.equals("printNum")){
                //colocar instruccion de print despu�s de reservar espacio para el param.
                asm.insertCode("LDR R1, [sp, #0]",1, 1, "Cargar en offset");
                asm.insertCode("LDR R0, =salida_num", 1, 1);
                asm.insertCode("bl printf", 1, 2);
            }
          
        }
    }
    
    
    public void getRegDir1(InterLine codigo){
        String res =  codigo.getDir1();
        String dir1 = codigo.getDir2();
        String op = codigo.getOp();
      
         //if dir1 is not in Rdir1 (according to the address descriptor of Rdir)
        String registerDescriptionR1 = this.registers.revisarRegistros(dir1);
        
        if (registerDescriptionR1.isEmpty()){
            Register rg = this.registers.agregarRegistro(dir1);
            System.out.println(rg);
            String instruccion = "";
            try {
                int num = Integer.parseInt(dir1);
                instruccion = "MOV " + rg.getRegistro()+", #"+num;
            }
            catch(Exception e){
                //cargar desde memoria, stackpointer, etc.
                int num = Integer.parseInt(dir1.substring(dir1.indexOf("[")+1, dir1.indexOf("]")));
               // String offSet = "ADD R6, R5, #" + (this.stackPointer-num);
               // asm.insertCode(offSet, 1, 1, "Cargar offset a un registro");
                instruccion = "LDR " + rg.getRegistro() + ", [sp, #"+(this.stackPointer-num)+"]";
            }
            asm.insertCode(instruccion, 1, 1);  
            registerDescriptionR1 = rg.getRegistro();
        }
        String output = "";
       
        Register rRes = this.registers.buscarRegistroMenor();
        //output = "MOV "+rRes.getRegistro()+ ", " + registerDescriptionR1;
        
        //System.out.println("aqui");
        //System.out.println(registerDescriptionR1);
        //this.asm.insertCode(output, 1, 1, "Assgin " + codigo.getRes() + " " + op + " " + dir1);
        if (!codigo.getDir1().contains("global")){
            int num = Integer.parseInt(codigo.getDir1().substring(codigo.getDir1().indexOf("[")+1, codigo.getDir1().indexOf("]")));
            //String offSet = "ADD R6, R5, #" + (this.stackPointer-num);
            //asm.insertCode(offSet, 1, 1, "Cargar offset a un registro");
            String saveLocal = "STR " + registerDescriptionR1 + ", [sp, #"+(this.stackPointer-num)+"]";
            this.asm.insertCode(saveLocal, 1, 2, "LocalStack " + (this.stackPointer-num));
          
        }
        else {
            //es una asignacion global
            int num = Integer.parseInt(codigo.getDir1().substring(codigo.getDir1().indexOf("[")+1, codigo.getDir1().indexOf("]")));
          
            String rGlobal = this.registers.revisarRegistros(res);
            if (rGlobal.isEmpty()){
                Register rg = this.registers.agregarRegistro(res);
                rGlobal = rg.getRegistro();
                
            }
            String loadGlobal = "LDR " + rGlobal + ", =global";
            this.asm.insertCode(loadGlobal, 1, 1, "Cargar etiqueta global");
            String saveLocal = "STR " +rRes.getRegistro()+ ", "+ "["+rGlobal +", #"+(num)+"]";
            this.asm.insertCode(saveLocal, 1, 2, "GlobalStack " + (num));
        }
        
    }
    
    public void booleanStatement(InterLine codigo){
        String res = codigo.getDir1(); //la condicion
        String dir2 = codigo.getDir3(); //la etiqueta a saltar
        String branch ="Error";
        if (res.contains("True")&&lastOp.equals("==")){
            //insertar branch con eq
             branch = "beq " + dir2;
        }
        if (res.contains("True")&&lastOp.equals("<")){
            branch = "blt " + dir2;
        }
        if (res.contains("True")&&lastOp.equals("<=")){
            branch = "ble " + dir2; 
        }
        if (res.contains("True")&&lastOp.equals(">")){
            branch = "bgt " + dir2;
        }
        if (res.contains("True")&&lastOp.equals(">=")){
            branch = "bge " + dir2;
        }
        if (res.contains("True")&&lastOp.equals("!=")){
            branch = "bne " + dir2;
        }
        if (res.contains("False")&&lastOp.equals("==")){
            //insertar branch con eq
             branch = "bne " + dir2;
        }
        if (res.contains("False")&&lastOp.equals("<")){
            branch = "bgt " + dir2;
        }
        if (res.contains("False")&&lastOp.equals("<=")){
            branch = "bge " + dir2; 
        }
        if (res.contains("False")&&lastOp.equals(">")){
            branch = "blt " + dir2;
        }
        if (res.contains("False")&&lastOp.equals(">=")){
            branch = "ble " + dir2;
        }
        if (res.contains("False")&&lastOp.equals("!=")){
            branch = "beq " + dir2;
        }
         
       
        asm.insertCode(branch, 1, 1, "Realizar salto condicional");
    }
}
