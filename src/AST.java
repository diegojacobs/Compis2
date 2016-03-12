import java.util.ArrayList;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;


public class AST {

    private final Object payload;

    private final List<AST> children;
    
    private JTree tree;
    private DefaultMutableTreeNode root;
    
    
    public AST(ParseTree tree) {
        this(null, tree);
        AST ast = this;
        String caption;
        if (ast.payload instanceof Token) {
            Token token = (Token) ast.payload;
            caption = String.format("%s",token.getText().replace("\n", "\\n"));
        }
        else {
            caption = String.valueOf(ast.payload);   
        }
        root = new DefaultMutableTreeNode(caption);
    }

    private AST(AST ast, ParseTree tree) {
        this(ast, tree, new ArrayList<AST>());
    }

    private AST(AST parent, ParseTree tree, List<AST> children) {

        this.payload = getPayload(tree);
        this.children = children;

        if (parent == null) {
            walk(tree, this);
        }
        else {
            parent.children.add(this);
        }
    }

    public Object getPayload() {
        return payload;
    }

    public List<AST> getChildren() {
        return new ArrayList<>(children);
    }

    private Object getPayload(ParseTree tree) {
        if (tree.getChildCount() == 0) {
            return tree.getPayload();
        }
        else {
            String ruleName = tree.getClass().getSimpleName().replace("Context", "");
            return Character.toLowerCase(ruleName.charAt(0)) + ruleName.substring(1);
        }
    }

    private static void walk(ParseTree tree, AST ast) {

        if (tree.getChildCount() == 0) {
            new AST(ast, tree);
        }
        else if (tree.getChildCount() == 1) {
            walk(tree.getChild(0), ast);
        }
        else if (tree.getChildCount() > 1) {

            for (int i = 0; i < tree.getChildCount(); i++) {

                AST temp = new AST(ast, tree.getChild(i));

                if (!(temp.payload instanceof Token)) {
                    walk(tree.getChild(i), temp);
                }
            }
        }
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        AST ast = this;
        List<AST> firstStack = new ArrayList<>();
        firstStack.add(ast);

        List<List<AST>> childListStack = new ArrayList<>();
        childListStack.add(firstStack);

        while (!childListStack.isEmpty()) {

            List<AST> childStack = childListStack.get(childListStack.size() - 1);

            if (childStack.isEmpty()) {
                childListStack.remove(childListStack.size() - 1);
            }
            else {
                ast = childStack.remove(0);
                String caption;
                
                if (ast.payload instanceof Token) {
                    Token token = (Token) ast.payload;
                    caption = String.format("%s",token.getText().replace("\n", "\\n"));
                }
                else {
                    caption = String.valueOf(ast.payload);
                    
                }
                
                String indent = "";

                for (int i = 0; i < childListStack.size() - 1; i++) {
                    indent += (childListStack.get(i).size() > 0) ? "  " : "   ";
                }

                builder.append(indent)
                        .append(childStack.isEmpty() ? " " : " ")
                        .append(caption)
                        .append("\n");
                
                if (ast.children.size() > 0) {
                    List<AST> children = new ArrayList<>();
                    for (int i = 0; i < ast.children.size(); i++) {
                        children.add(ast.children.get(i));
                        
                        if (ast.children.get(i).payload instanceof Token) 
                        {
                            Token token = (Token) ast.children.get(i).payload;
                            caption = String.format("%s",token.getText().replace("\n", "\\n"));
                        }
                        else 
                            caption = String.valueOf(ast.children.get(i).payload);
                        
                        DefaultMutableTreeNode nodechild = new DefaultMutableTreeNode();
                        
                        root.add(nodechild);
                    }
                    childListStack.add(children);
                }
            }
        }
        tree = new JTree(root);
        return builder.toString();
    }

	public JTree getTree() {
		return tree;
	}

	public void setTree(JTree tree) {
		this.tree = tree;
	}

	public DefaultMutableTreeNode getRoot() {
		return root;
	}

	public void setRoot(DefaultMutableTreeNode root) {
		this.root = root;
	}
}