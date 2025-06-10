
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ChoiceEditor implements ActionListener
{
    JFrame frame;
    
    JTextField choiceName;
    JTextArea choiceText;

    JButton acceptChanges;
    JButton goBack;

    JPanel mainPanel;

    CyoaTree tree;
    CyoaNode currNode;

    public ChoiceEditor(JFrame frame, CyoaTree tree, CyoaNode currNode)
    {
        this.frame = frame;
        this.tree = tree;
        this.currNode = currNode;

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(50,50,50,50));

        choiceName = new JTextField();
        choiceName.setFont(new Font("Times New Roman",Font.PLAIN,30));
        choiceName.setMaximumSize(new Dimension(1000,100));

        choiceText = new JTextArea();
        choiceText.setFont(new Font("Times New Roman",Font.PLAIN,10));
        choiceText.setMaximumSize(new Dimension(1000,1000));

        acceptChanges = new JButton("Accept Changes");
        acceptChanges.setFont(new Font("Times New Roman",Font.PLAIN,50));
        acceptChanges.setMaximumSize(new Dimension(500,100));
        acceptChanges.addActionListener(this);

        goBack = new JButton("Cancel Changes");
        goBack.setFont(new Font("Times New Roman",Font.PLAIN,50));
        goBack.setMaximumSize(new Dimension(500,100));
        goBack.addActionListener(this);

        mainPanel.add(choiceName);
        mainPanel.add(choiceText);
        mainPanel.add(acceptChanges);
        if(!currNode.isRoot())
        {
            mainPanel.add(goBack);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        
    }
    
}
