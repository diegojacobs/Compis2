import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import org.antlr.v4.runtime.tree.ParseTree;


public class myTree {
    private DefaultMutableTreeNode Root;
    private JTree tree;
    private ParseTree RefTree;
    private DECAFParser Parser;
    
    public myTree(ParseTree Ref, DECAFParser P) 
    {
    	Root = new DefaultMutableTreeNode(Ref.toStringTree(P));
    	RefTree = Ref;
    	Parser = P;
    }
    
    public JTree BuildTree()
    {
    	// Tomo root 
        String[] split = RefTree.toStringTree(Parser).split(" ");
        String word = RefTree.toStringTree(Parser);
        if (split[0].charAt(0) == '(') {
            word = split[0] + ")";
        }
        DefaultMutableTreeNode Root = new DefaultMutableTreeNode(word);
        
        int count = RefTree.getChildCount();
        
        for(int i=0; i<count; i++)
        {
            ParseTree subtree = RefTree.getChild(i);
            
            String[] split2 = subtree.toStringTree(Parser).split(" ");
            String word2 = subtree.toStringTree(Parser);
            if (split2[0].charAt(0) == '(') {
                word2 = split2[0] + ")";
            }
            
            DefaultMutableTreeNode ch = new DefaultMutableTreeNode(word2);
            
            Root.add(BuildSubTree(ch, subtree, Parser));
        }
        tree = new JTree(Root);
        
        return tree;
    }
    
    public DefaultMutableTreeNode BuildSubTree(DefaultMutableTreeNode father, ParseTree Ref, DECAFParser p)
    {
        int count = Ref.getChildCount();
        for(int i=0; i<count; i++)
        {
            ParseTree subtree = Ref.getChild(i);
            
            String[] split2 = subtree.toStringTree(p).split(" ");
            String word2 = subtree.toStringTree(p);
            if (split2[0].charAt(0) == '(') {
                word2 = split2[0] + ")";
            }
            
            DefaultMutableTreeNode ch = new DefaultMutableTreeNode(word2);
                       
            father.add(BuildSubTree(ch, subtree, p));
        }
        return father;
    }
 }
