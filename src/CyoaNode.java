import java.util.ArrayList;

public class CyoaNode 
{
    ArrayList<CyoaNode> nextNodes;
    CyoaNode prevNode;
    String choice;
    String text;

    public CyoaNode(CyoaNode prevNode)
    {
        this.prevNode = prevNode;
        nextNodes = new ArrayList<>();
        text = "";
        choice = "";
    }
    public CyoaNode()
    {
        this.prevNode = null;
        nextNodes = new ArrayList<>();
        text = "";
    }


    public CyoaNode getNextNode(int index)
    {
        return nextNodes.get(index);
    }

    public int getNextNodeLength()
    {
        return nextNodes.size();
    }

    public CyoaNode getLastNode()
    {
        return prevNode;
    }

    public String getChoice()
    {
        return choice;
    }

    public String getText()
    {
        return text;
    }
    
    public void addNextNode()
    {
        nextNodes.add(new CyoaNode(this));
    }

    public void removeNextNode(int index)
    {
        nextNodes.remove(index);
    }

    public void setText(String text)
    {
        this.text = text;
    }

     public void setChoice(String choice)
    {
        this.choice = text;
    }

    @Override
    public String toString()
    {
        return String.format("%d|::[%s]|::%s",getNextNodeLength(),text,choice);
    }
}
