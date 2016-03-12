import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.JFileChooser;


public class myFile {
	private JFileChooser filechooser = new JFileChooser();
	private StringBuilder sb = new StringBuilder();
	private String name;
	private String path;
	
	public myFile()
	{
		// Path del directorio
        Path currentRelativePath_project = Paths.get("C:\\Users\\Diego Jacobs\\workspace\\Compis\\Pruebas");
        String path_project = currentRelativePath_project.toAbsolutePath().toString();

        //Propiedades del fileChooser
        filechooser.setDialogTitle("Escoja el archivo con la gramática");
        filechooser.setCurrentDirectory(new File(path_project));
	}

	public void pickFile() throws Exception
	{
		if (filechooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
		{    
			java.io.File file = filechooser.getSelectedFile();
			
			name = file.getName();
			path = file.getPath();
			
			Scanner input = new Scanner(file);
			
			while (input.hasNext())
			{
				sb.append(input.nextLine());
				sb.append("\n");
			}
			
			input.close();
		}
		else
			sb.append("No selecciono archivo");
	}
	
	public void pickPath()
	{
		JFileChooser jfc = new JFileChooser("C:\\Users\\Diego Jacobs\\workspace\\ANTLR Example\\Pruebas");
		jfc.setDialogTitle("Escoja un directorio");
		jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		int returnVal = jfc.showDialog(null, "Select");
		if (returnVal == JFileChooser.APPROVE_OPTION)
		{
			File file = jfc.getSelectedFile();
			String sfilename = file.getPath();
			path =  sfilename;
		}
		else
			path = "No selecciono carpeta";
	}
	
	public void escribir(String texto, String path)
    {
        File f = new File(path);
        
        try{
            FileWriter w = new FileWriter(f);
            
            BufferedWriter bw = new BufferedWriter(w);

            PrintWriter wr = new PrintWriter(bw);  

            w.write(texto); //sobre escribimos el archivo con el nuevo texto
            //ahora cerramos los flujos de canales de datos, al cerrarlos el archivo quedará guardado con información escrita
            //de no hacerlo no se escribirá nada en el archivo

            wr.close();

            bw.close();

        }catch(IOException e){System.out.println("No se pudo excribir en el archivo.");}
    }

	public JFileChooser getFilechooser() {
		return filechooser;
	}

	public void setFilechooser(JFileChooser filechooser) {
		this.filechooser = filechooser;
	}

	public StringBuilder getSb() {
		return sb;
	}

	public void setSb(StringBuilder sb) {
		this.sb = sb;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
