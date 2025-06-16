import java.util.Stack;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class CyoaTree 
{
    private String title;
    private final CyoaNode root;
    private final DefaultTreeModel model;

    public CyoaTree(String title)
    {
        this.title = title;
        model = new DefaultTreeModel(null);
        this.root = new CyoaNode(model);
        model.setRoot(root.getGraphicNode());
    }

    public String getTitle()
    {
        return title;
    }

    public CyoaNode getRoot()
    {
        return root;
    }

    public DefaultTreeModel getModel()
    {
        return model;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public CyoaNode findTreeNode(DefaultMutableTreeNode graphicNode)
    {
        Stack<CyoaNode> nodes = new Stack<>();
        nodes.add(root);
        while(!nodes.empty())
        {
            CyoaNode currNode = nodes.peek();
            if(graphicNode.equals(currNode.getGraphicNode()))
                return currNode;
            int length = currNode.getNextNodeLength();
            nodes.pop();
            for(int i = 0; i < length; i++)
            {
                nodes.push(currNode.getNextNode(i));
            }
        }
        return null;
    }

    public CyoaNode findTreeNode(TreePath path)
    {
        if(path == null)
            return null;
        DefaultMutableTreeNode graphicNode = (DefaultMutableTreeNode)path.getLastPathComponent();
        Stack<CyoaNode> nodes = new Stack<>();
        nodes.add(root);
        System.out.println("Run");
        while(!nodes.empty())
        {
            CyoaNode currNode = nodes.peek();
            if(graphicNode.equals(currNode.getGraphicNode()))
                return currNode;
            int length = currNode.getNextNodeLength();
            nodes.pop();
            for(int i = 0; i < length; i++)
            {
                nodes.push(currNode.getNextNode(i));
            }
        }
        return null;
    }

    @Override
    public String toString()
    {
        Stack<CyoaNode> nodes = new Stack<>();
        nodes.add(root);
        String totalText = "";
        while(!nodes.empty())
        {
            CyoaNode currNode = nodes.peek();
            int length = currNode.getNextNodeLength();
            nodes.pop();
            for(int i = 0; i < length; i++)
            {
                nodes.push(currNode.getNextNode(i));
            }
            totalText += currNode + "\n";
        }
        return totalText;
    }
}
