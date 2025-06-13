import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class EditorCard 
{

    JPanel cardPanel;
    JFrame frame;
    CardLayout cardLayout;
    ChoiceEditor choice;
    TreeMenu tree;

    public EditorCard(JFrame frame, CyoaTree tree)
    {
        this.frame = frame;
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        this.choice = new ChoiceEditor(frame,cardPanel,tree,tree.getRoot(),this);
        this.tree = new TreeMenu(frame,tree,cardPanel,this);

        frame.add(cardPanel);
        frame.setVisible(true);
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


}
