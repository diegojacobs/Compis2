import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.BorderLayout;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JEditorPane;
import javax.swing.JTextArea;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import java.awt.ScrollPane;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import java.awt.TextArea;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTable;
import javax.swing.JSeparator;

public class myIDE {

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu mnMenu;
	private JMenuItem mntmCargarArchivo;
	private JPanel panel;
	private JScrollPane scrollPaneFile;
	private JScrollPane scrollPaneTree;
	private JScrollPane scrollPaneError;
	private myTextPanel textAreaFile;
	public static JTextArea textAreaError;
	private JMenuItem mntmCrearArchivo;
	private JButton btnTree;
	private JMenuItem mntmGuardarArchivo;
	public static JTabbedPane tabbedPane;
	public JTabbedPane tabbedPane_1;
	private JTree tree;
	private String PathFile = new String();
	private String NameFile;
	public static Font font = new Font("monospaced", Font.PLAIN, 12);
	public static String[] rules;
	public static JTable symbolTable;
	public static JTable methodTable;
	public static JTable structTable;
	private JTextArea textAreaInter;
	private JScrollPane scrollPaneInterCode;
	private JTable table;
	
	/***********************************************
     * expect
     * Reviews if all in the chain match with the regex
     * @param regex
     * @param chain
     * @return String
    ***********************************************/
    public static String expect(String regex, String chain) {

        String cadena_encontrada="";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(chain);

        if (matcher.find()) {
            cadena_encontrada=matcher.group();
            if(matcher.start()==0){
                return cadena_encontrada;
            }
        }
        return "";
    }
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					myIDE window = new myIDE();
					window.frame.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public myIDE() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//Inicializamos el frame
		frame = new JFrame();
		frame.setBounds(100, 100, 850, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//Inicializamos el panel donde pondremos los botones
		panel = new JPanel();
		panel.setBounds(0, 0, 834, 33);
		frame.getContentPane().add(panel);
		
		//Creamos las pestañas donde pondremos nuestros textos
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 34, 834, 481);
		frame.getContentPane().add(tabbedPane);
		
		//Creamos el arbol con scrollbar
		tree = new JTree();
		scrollPaneTree = new JScrollPane(tree);
		
		//Creamos nuestro scroll para el codigo intermedio
		textAreaInter = new JTextArea();
		textAreaInter.setBounds(1, 11, 748, 381);
		scrollPaneInterCode = new JScrollPane(textAreaInter);
		
		
		//Nuestro boton para compilar un archivo
		JButton btnCompilar = new JButton("Compilar");
		btnCompilar.setToolTipText("Run Program");
		btnCompilar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String program = textAreaFile.getText();
				ANTLRInputStream input = new ANTLRInputStream(program);

		        DECAFLexer lexer = new DECAFLexer(input);
		        
		        CommonTokenStream tokens = new CommonTokenStream(lexer);

		        DECAFParser parser = new DECAFParser(tokens);
		        
		        DescriptiveErrorListener.errores = "";
		
		        parser.removeErrorListeners();
		        parser.addErrorListener(DescriptiveErrorListener.INSTANCE);
		        
		        ParseTree parstree = parser.program(); // begin parsing at rule 'prog'
		        rules = parser.getRuleNames();
		        
		        //Arbol solo texto
		        AST ast = new AST(parstree);
		        System.out.println(ast.toString());
		        
		        //Creamos el arbol para mostrarlo luego
		        myTree mtree = new myTree(parstree,parser);
		        Font font = new Font("Verdana", Font.BOLD, 12);
		        tree = mtree.BuildTree();
		        tree.setFont(font);
		        tree.setShowsRootHandles(true);
				//tree.setFont(font);
				DefaultTreeCellRenderer render = (DefaultTreeCellRenderer) tree.getCellRenderer();
				render.setLeafIcon(null);
				render.setClosedIcon(null);
				render.setOpenIcon(null);
		        scrollPaneTree = new JScrollPane(tree);
		        
		        //Mostramos los errores si existen
		        textAreaError.setText(null);
		        textAreaError.setForeground(Color.RED);
		        textAreaError.setText(DescriptiveErrorListener.errores);
		        
				//Visitamos y revisamos las reglas semanticas
				GenerateTable visitor = new GenerateTable(parstree);
				
				//Desplegamos la tabla de simbolos
				tabbedPane.remove(symbolTable);
				symbolTable = new JTable(visitor.tablaSimbolos.getInfo(),visitor.tablaSimbolos.getColumsTitles());
				tabbedPane.addTab("Tabla Simbolos",null,symbolTable,null);
				
				//Desplegamos la tabla de metodos
				tabbedPane.remove(methodTable);
				methodTable = new JTable(visitor.tablaMetodos.getInfo(), visitor.tablaMetodos.getColumsTitles());
		        tabbedPane.addTab("Tabla Metodos",null,myIDE.methodTable,null);
				
				//Desplegamos la tabla de Structs
		        tabbedPane.remove(structTable);
		        structTable = new JTable(visitor.tablaStruct.getInfo(), visitor.tablaStruct.getColumsTitles());
		        tabbedPane.addTab("Tabla Struct",null,structTable,null);
		        
		        //Visitamos y generamos nuestro codigo intermedio
		        InterVisitor visitInter = new InterVisitor(parstree,visitor.tablaSimbolos,visitor.tablaMetodos,visitor.tablaStruct);
		        
		        textAreaInter.setText(visitInter.getCodigo().toString());
		        tabbedPane.addTab("Codigo Intermedio",null,scrollPaneInterCode,null);
			}
		});
		panel.add(btnCompilar);
		
		//Nuestro boton para visulizar el arbol generado
		btnTree = new JButton("Tree");
		btnTree.setToolTipText("View Tree");
		btnTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tabbedPane.remove(scrollPaneTree);
				tabbedPane.addTab("Tree", null, scrollPaneTree, null);
			}
		});
		panel.add(btnTree);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 515, 834, 5);
		frame.getContentPane().add(separator);
		
		tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(0, 515, 834, 189);
		frame.getContentPane().add(tabbedPane_1);
		
		//Creamos un textArea para mostar errores
		textAreaError = new JTextArea();
		textAreaError.setBounds(0, 0, 4, 22);
		scrollPaneError = new JScrollPane(textAreaError);
		tabbedPane_1.addTab("Consola de Errores", null, scrollPaneError, null);
	
		//Creamos un area de texto con scrollbar
		textAreaFile = new myTextPanel();
		textAreaFile.setBounds(1, 11, 748, 381);
		scrollPaneFile = textAreaFile.getScroll();
		
		//Creamos un menu donde podremos manejar nuestro archivo
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		mnMenu = new JMenu("Archivo");
		menuBar.add(mnMenu);
		
		mntmCargarArchivo = new JMenuItem("Cargar Archivo");
		mntmCargarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myFile file = new myFile();
				try{
					file.pickFile();
					
					if (file.getSb().toString().equals("No selecciono archivo"))
					{
						JOptionPane.showMessageDialog(frame, "No ha seleccionado ningun archivo.");
						PathFile = "";
					}
					else
					{
						textAreaFile.setText("");
						textAreaFile.setText(file.getSb().toString());
						tabbedPane.addTab(file.getName(), null, scrollPaneFile, null);
						NameFile = file.getName();
						PathFile = file.getPath();
					}
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}
				
			}
		});
		
		mntmCrearArchivo = new JMenuItem("Crear Archivo");
		mntmCrearArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Jframe here isn't strictly necessary, but it makes the example a little more real
			    JFrame frame = new JFrame("InputDialog Example #1");

			    //prompt the user to enter their name
			    String name = JOptionPane.showInputDialog(frame, "Nombre para el Archivo");
			    if (!name.equals(""))
			    {
			    	NameFile = name;
			    	PathFile = "";
					tabbedPane.addTab(name, null, scrollPaneFile, null);
					textAreaFile.setText("");
			    }
			}
		});
		mnMenu.add(mntmCrearArchivo);
		mnMenu.add(mntmCargarArchivo);
		
		mntmGuardarArchivo = new JMenuItem("Guardar Archivo");
		mntmGuardarArchivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (PathFile.equals(""))
				{
					myFile file = new myFile();
					file.pickPath();
					if (file.getPath().equals("No selecciono carpeta"))
					{
						JOptionPane.showMessageDialog(frame, "No ha seleccionado directorio, no se guardara su archivo.");
					}
					else
					{
						String text = textAreaFile.getText();
						PathFile = file.getPath();
						file.escribir(text, PathFile+"\\"+NameFile);
					}
				}
				else
				{
					myFile file = new myFile();
					String text = textAreaFile.getText();
					file.escribir(text, PathFile);
				}
			}
		});
		mnMenu.add(mntmGuardarArchivo);
		
		
	}
}
