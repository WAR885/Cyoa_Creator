
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ChoiceEditor implements ActionListener
{
    JFrame frame;
    
    JTextField choiceName;
    JTextArea choiceText;

    JScrollPane pane;

    JButton acceptChanges;
    JButton goBack;

    EditorCard cardViewer;

    JPanel mainPanel;
    JPanel buttonPanel;

    CyoaNode currNode;


    public ChoiceEditor(JFrame frame, JPanel cardPanel, CyoaTree tree, CyoaNode currNode, EditorCard cardViewer)
    {
        this.cardViewer = cardViewer;
        this.frame = frame;
        this.currNode = currNode;


        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(50,50,50,50));

        choiceName = new JTextField();
        choiceName.setText(currNode.getChoice());
        choiceName.setFont(new Font("Times New Roman",Font.PLAIN,30));
        choiceName.setMaximumSize(new Dimension(1000,100));

        choiceText = new JTextArea();
        choiceText.setText(currNode.getText());
        choiceText.setFont(new Font("Times New Roman",Font.PLAIN,20));
        choiceText.setLineWrap(true);
        choiceText.setWrapStyleWord(true);

        pane = new JScrollPane(choiceText);
        pane.setPreferredSize(new Dimension(500,500));

        acceptChanges = new JButton("Accept Changes");
        acceptChanges.setFont(new Font("Times New Roman",Font.PLAIN,20));
        acceptChanges.setMaximumSize(new Dimension(200,50));
        acceptChanges.addActionListener(this);

        goBack = new JButton("Cancel Changes");
        goBack.setFont(new Font("Times New Roman",Font.PLAIN,20));
        goBack.setMaximumSize(new Dimension(200,50));
        goBack.addActionListener(this);

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT,50,0));

        mainPanel.add(choiceName);
        mainPanel.add(Box.createRigidArea(new Dimension(0,50)));
        mainPanel.add(pane);
        mainPanel.add(Box.createRigidArea(new Dimension(0,50)));
        buttonPanel.add(acceptChanges);
        if(!currNode.isRoot())
        {
            buttonPanel.add(goBack);
        }
        mainPanel.add(buttonPanel);
        cardPanel.add(mainPanel, "Choice Editor");

        frame.setVisible(true);
    }

    public void changeNode(CyoaNode newNode)
    {
        if(!buttonPanel.isAncestorOf(goBack))
            buttonPanel.add(goBack);
        currNode = newNode;
        choiceName.setText(currNode.getChoice());
        choiceText.setText(currNode.getText());
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource().equals(acceptChanges))
        {
            currNode.setChoice(choiceName.getText());
            currNode.setText(choiceText.getText());
            cardViewer.switchToTreeMenu();
        }
        else if(e.getSource().equals(goBack))
        {
            cardViewer.switchToTreeMenu();
        }
    }
    
}
