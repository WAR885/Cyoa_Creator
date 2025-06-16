
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.TreePath;

public class TreeMenu implements MouseListener, ActionListener
{
    JFrame frame;
    JTextField title;
    JPanel treePanel;
    JTree graphicTree;
    JScrollPane scrollPane;
    JPopupMenu options;
    JMenuItem addNew;
    JMenuItem delete;
    JMenuItem edit;
    CyoaTree treeInfo;
    CyoaNode root;
    CyoaNode currNode;
    EditorCard cardViewer;


    public TreeMenu(JFrame frame, CyoaTree tree, JPanel cardPanel, EditorCard cardViewer)
    {
        this.frame = frame;
        this.cardViewer = cardViewer;

        treeInfo = tree;
        root = tree.getRoot();
        currNode = null;

        treePanel = new JPanel();
        treePanel.setLayout(new BorderLayout());
        treePanel.setBorder(new EmptyBorder(50,50,50,50));

        title = new JTextField();
        title.setText(treeInfo.getTitle());
        title.setFont(new Font("Times New Roman",Font.PLAIN,50));
        title.setBorder(new EmptyBorder(0,50,0,0));


        graphicTree = new JTree(treeInfo.getModel());
        graphicTree.setFont(new Font("Times New Roman",Font.PLAIN,25));
        graphicTree.setBorder(new EmptyBorder(10,10,10,10));
        graphicTree.addMouseListener(this);

        scrollPane = new JScrollPane(graphicTree);
        scrollPane.setBorder(new EmptyBorder(50,50,50,50));

        options = new JPopupMenu();

        addNew = new JMenuItem("Add new choice");
        delete = new JMenuItem("Delete current choice");
        edit = new JMenuItem("Edit");

        addNew.addActionListener(this);
        delete.addActionListener(this);
        edit.addActionListener(this);

        treePanel.add(title,BorderLayout.NORTH);
        treePanel.add(scrollPane,BorderLayout.WEST);

        options.add(addNew);
        options.add(delete);
        options.add(edit);

        frame.setVisible(true);
     
        cardPanel.add(treePanel, "Tree Menu");
    }

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) 
    {
        if(e.getButton() != MouseEvent.BUTTON3)
            return;
        TreePath path = graphicTree.getPathForLocation(e.getX(),e.getY());
        currNode = treeInfo.findTreeNode(path);
        if(currNode != null)
        {
            if(currNode.isRoot())
            {
                delete.setForeground(Color.GRAY);
            }
            else
            {
                delete.setForeground(Color.BLACK);
            }
            options.show(e.getComponent(),e.getX(),e.getY());
        }
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {}

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {}

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {}

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {}

    @Override
    public void actionPerformed(ActionEvent e) {
        if(currNode == null)
            return;
        if(e.getSource().equals(addNew))
        {
            CyoaNode addedNode = currNode.addNextNode();
            cardViewer.switchToChoiceEditor(addedNode);
            treePanel.revalidate();
            treePanel.repaint();
        }
        else if(e.getSource().equals(delete))
        {
            if(!currNode.isRoot())
                currNode.deleteNode();
            treePanel.revalidate();
            treePanel.repaint();
        }
        else if(e.getSource().equals(edit))
        {
            cardViewer.switchToChoiceEditor(currNode);
        }
    }
    
}
