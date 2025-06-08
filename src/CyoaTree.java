import java.util.Stack;

public class CyoaTree 
{
    private String title;
    private CyoaNode root;

    public CyoaTree(String title)
    {
        this.title = title;
        this.root = new CyoaNode();
    }

    public String getTitle()
    {
        return title;
    }

    public CyoaNode getRoot()
    {
        return root;
    }

    public void setTitle(String title)
    {
        this.title = title;
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
