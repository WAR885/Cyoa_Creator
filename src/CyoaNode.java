import java.util.ArrayList;
import java.util.Objects;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class CyoaNode 
{
    private final ArrayList<CyoaNode> nextNodes;
    private final CyoaNode prevNode;
    private String choice;
    private String text;
    private final DefaultMutableTreeNode graphicNode;
    private final DefaultTreeModel model;

    public CyoaNode(CyoaNode prevNode, DefaultTreeModel model, String text, String choice)
    {
        this.prevNode = prevNode;
        nextNodes = new ArrayList<>();
        this.text = text;
        this.choice = choice;
        graphicNode = new DefaultMutableTreeNode(choice);
        prevNode.getGraphicNode().add(graphicNode);
        this.model = model;
    }
    public CyoaNode(CyoaNode prevNode, DefaultTreeModel model)
    {
        this.prevNode = prevNode;
        nextNodes = new ArrayList<>();
        text = "";
        choice = "";
        graphicNode = new DefaultMutableTreeNode(choice);
        this.model = model;
    }
    public CyoaNode(DefaultTreeModel model)
    {
        this.prevNode = null;
        nextNodes = new ArrayList<>();
        text = "";
        choice = "beginning";
        graphicNode = new DefaultMutableTreeNode(choice);
        this.model = model;
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

    public boolean isRoot()
    {
        return prevNode == null;
    }

    public String getText()
    {
        return text;
    }

    public DefaultMutableTreeNode getGraphicNode()
    {
        return graphicNode;
    }
    
    public CyoaNode addNextNode()
    {
        CyoaNode newNode = new CyoaNode(this,model);
        nextNodes.add(newNode);
        model.insertNodeInto(newNode.getGraphicNode(), graphicNode, graphicNode.getChildCount());
        return newNode;
    }
    public CyoaNode addNextNode(String choice, String text)
    {
        CyoaNode newNode = new CyoaNode(this,model,text,choice);
        nextNodes.add(newNode);
        model.insertNodeInto(newNode.getGraphicNode(), graphicNode, graphicNode.getChildCount());
        return newNode;
    }

    public void removeNextNode(int index)
    {
        nextNodes.remove(index);
        model.removeNodeFromParent(nextNodes.get(index).graphicNode);
    }

    public void removeNextNode(CyoaNode removedNode)
    {
        nextNodes.remove(removedNode);
        model.removeNodeFromParent(removedNode.graphicNode);
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public void setChoice(String choice)
    {
        this.choice = choice;
        graphicNode.setUserObject(choice);
        model.nodeChanged(graphicNode);
    }

    public void deleteNode()
    {
        prevNode.removeNextNode(this);
    }

    @Override
    public String toString()
    {
        return String.format("%d|::[%s]|::%s",getNextNodeLength(),text,choice);
    }

    @Override
    public boolean equals(Object o)
    {
        if(o instanceof CyoaNode checkedNode)
        {
            return checkedNode.hashCode() == hashCode();
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(choice,text,graphicNode,model);
    }
}
