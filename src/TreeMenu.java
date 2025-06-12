
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class TreeMenu implements MouseListener
{
    JFrame frame;
    JLabel title;
    JPanel treePanel;
    JPanel cardPanel;
    JTree graphicTree;
    JScrollPane scrollPane;
    JPopupMenu options;
    JMenuItem addNew;
    JMenuItem delete;
    CardLayout cardLayout;
    CyoaTree treeInfo;
    CyoaNode root;
    EditorCard cardViewer;


    public TreeMenu(JFrame frame, CyoaTree tree, JPanel cardPanel, CardLayout cardLayout, EditorCard cardViewer)
    {
        this.cardLayout = cardLayout;
        this.frame = frame;
        this.cardPanel = cardPanel;
        this.cardViewer = cardViewer;

        treeInfo = tree;
        root = tree.getRoot();

        treePanel = new JPanel();
        treePanel.setLayout(new BorderLayout());
        treePanel.setBorder(new EmptyBorder(50,50,50,50));

        title = new JLabel();
        title.setText(treeInfo.getTitle());
        title.setFont(new Font("Times New Roman",Font.PLAIN,50));
        title.setBorder(new EmptyBorder(0,50,0,0));


        graphicTree = new JTree(root.getGraphicNode());
        graphicTree.setFont(new Font("Times New Roman",Font.PLAIN,25));
        graphicTree.setBorder(new EmptyBorder(10,10,10,10));
        graphicTree.addMouseListener(this);

        scrollPane = new JScrollPane(graphicTree);
        scrollPane.setBorder(new EmptyBorder(50,50,50,50));

        options = new JPopupMenu();
        addNew = new JMenuItem("Add new choice");
        delete = new JMenuItem("Delete current choice");

        options.add(addNew);
        options.add(delete);

        treePanel.add(title,BorderLayout.NORTH);
        treePanel.add(scrollPane,BorderLayout.WEST);

        frame.setVisible(true);
     
        cardPanel.add(treePanel);
    }
    

    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) 
    {
        TreePath path = graphicTree.getPathForLocation(e.getX(),e.getY());
        CyoaNode currNode = null;
        if(path != null)
            currNode = treeInfo.findTreeNode((DefaultMutableTreeNode)path.getLastPathComponent());
        if(e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1)
        {
            cardViewer.switchToChoiceEditor(currNode);
        }
        else if(e.getButton() == MouseEvent.BUTTON3)
        {
            if(path != null)
            {
                options.show(e.getComponent(),e.getX(),e.getY());
                if(currNode.equals(root))
                    delete.setForeground(Color.GRAY);
                else
                    delete.setForeground(Color.BLACK);
            }
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
    
}
