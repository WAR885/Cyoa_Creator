
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
    JPanel buttonPanel;
    JTree graphicTree;
    JButton save;
    JButton goBack;
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

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT,100,0));

        save = new JButton("Save Changes");
        save.setFont(new Font("Times New Roman",Font.PLAIN,20));
        save.setMaximumSize(new Dimension(200,50));
        save.addActionListener(this);

        goBack = new JButton("Exit");
        goBack.setFont(new Font("Times New Roman",Font.PLAIN,20));
        goBack.setMaximumSize(new Dimension(200,50));
        goBack.addActionListener(this);

        title = new JTextField();
        title.setText(treeInfo.getTitle());
        title.setFont(new Font("Times New Roman",Font.PLAIN,50));
        title.setBorder(new EmptyBorder(0,50,0,0));

        graphicTree = new JTree(treeInfo.getModel());
        graphicTree.setFont(new Font("Times New Roman",Font.PLAIN,25));
        graphicTree.setBorder(new EmptyBorder(10,0,0,10));
        graphicTree.addMouseListener(this);

        scrollPane = new JScrollPane(graphicTree);
        scrollPane.setBorder(new EmptyBorder(50,0,10,50));

        options = new JPopupMenu();

        addNew = new JMenuItem("Add new choice");
        delete = new JMenuItem("Delete current choice");
        edit = new JMenuItem("Edit");

        addNew.addActionListener(this);
        delete.addActionListener(this);
        edit.addActionListener(this);

        treePanel.add(title,BorderLayout.NORTH);
        treePanel.add(scrollPane,BorderLayout.WEST);

        buttonPanel.add(save);
        buttonPanel.add(goBack);

        options.add(addNew);
        options.add(delete);
        options.add(edit);

        treePanel.add(buttonPanel,BorderLayout.SOUTH);
     
        cardPanel.add(treePanel, "Tree Menu");

        frame.setVisible(true);
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
        if(e.getSource().equals(save))
        {
            treeInfo.setTitle(title.getText());
            cardViewer.saveWork(treeInfo);
        }
        else if(e.getSource().equals(goBack))
        {
            int result = JOptionPane.showConfirmDialog(frame,"Do you want to save your work before you quit?","Save Work?",JOptionPane.YES_NO_OPTION,JOptionPane.WARNING_MESSAGE);
            if(result == JOptionPane.YES_OPTION)
            {
                cardViewer.saveWork(treeInfo);
                Gui.clear(frame);
                new TitlePage(frame);
            }
            else
            {
                Gui.clear(frame);
                new TitlePage(frame);
            }
        }
        else if(currNode != null)
        {
            if(e.getSource().equals(edit))
            {
                cardViewer.switchToChoiceEditor(currNode);
            }
            else if(e.getSource().equals(addNew))
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
        }
        
    }
    
}
