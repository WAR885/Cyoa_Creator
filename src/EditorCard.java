import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EditorCard 
{

    CyoaTree tree;
    JPanel cardPanel;
    JFrame frame;
    CardLayout cardLayout;
    ChoiceEditor choice;
    TreeMenu treeMenu;

    public EditorCard(JFrame frame, CyoaTree tree)
    {
        this.tree = tree;
        this.frame = frame;
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        this.choice = new ChoiceEditor(frame,cardPanel,tree,tree.getRoot(),cardLayout,this);
        this.treeMenu = new TreeMenu(frame,tree,cardPanel,cardLayout,this);

        frame.add(cardPanel);
        frame.setVisible(true);
    }

    public void switchToTreeMenu()
    {
        cardLayout.next(cardPanel);
    }

    public void switchToChoiceEditor(CyoaNode currNode)
    {
        choice.changeNode(currNode);
        cardLayout.next(cardPanel);
        frame.setVisible(true);
    }


}
