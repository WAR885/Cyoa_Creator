import java.awt.CardLayout;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class EditorCard 
{

    JPanel cardPanel;
    JFrame frame;
    CardLayout cardLayout;
    ChoiceEditor choice;
    TreeMenu tree;
    SaveInterfacer interfacer;

    public EditorCard(JFrame frame, CyoaTree tree)
    {
        this.frame = frame;
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        this.choice = new ChoiceEditor(frame,cardPanel,tree,tree.getRoot(),this);
        this.tree = new TreeMenu(frame,tree,cardPanel,this);
        this.interfacer = null;

        frame.add(cardPanel);
        frame.setVisible(true);
    }

    public EditorCard(JFrame frame, CyoaTree tree, File currFile)
    {
        this.frame = frame;
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        this.choice = new ChoiceEditor(frame,cardPanel,tree,tree.getRoot(),this);
        this.tree = new TreeMenu(frame,tree,cardPanel,this);
        this.interfacer = new SaveInterfacer(currFile);

        frame.add(cardPanel);
        frame.setVisible(true);
    }

    public EditorCard(JFrame frame, SaveInterfacer interfacer)
    {
        this.frame = frame;
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        this.choice = new ChoiceEditor(frame,cardPanel,interfacer.getTree(),interfacer.getTree().getRoot(),this);
        this.tree = new TreeMenu(frame,interfacer.getTree(),cardPanel,this);
        this.interfacer = interfacer;

        frame.add(cardPanel);
        frame.setVisible(true);
        switchToTreeMenu();
    }

    public void switchToTreeMenu()
    {
        cardLayout.show(cardPanel, "Tree Menu");
    }

    public void switchToChoiceEditor(CyoaNode currNode)
    {
        choice.changeNode(currNode);
        cardLayout.show(cardPanel,"Choice Editor");
        frame.setVisible(true);
    }

    public void saveWork(CyoaTree tree)
    {
        if(interfacer == null)
        {
            interfacer = new SaveInterfacer(tree);
        }
        else
        {
            interfacer.saveEditor();
        }
    }


}
