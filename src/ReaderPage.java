import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ReaderPage implements MouseListener, ActionListener
{
    CyoaTree tree;
    CyoaNode currNode;
    JFrame frame;
    JTextArea choiceText;
    JScrollPane textPane;
    JScrollPane choicePane;
    JPanel mainPanel;
    JPanel choicePanel;
    JPanel buttonPanel;
    JLabel[] choices;
    JLabel title;
    JButton goBack;
    JButton previousChoice;

    public ReaderPage(JFrame frame, CyoaTree tree)
    {
        this.frame = frame;
        this.tree = tree;
        currNode = tree.getRoot();

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        mainPanel.setMinimumSize(new Dimension(1000,500));

        choicePanel = new JPanel();
        choicePanel.setLayout(new BoxLayout(choicePanel,BoxLayout.Y_AXIS));
        
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT,100,0));
        buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        title = new JLabel();
        title.setText(currNode.getChoice());
        title.setFont(new Font("Times New Roman",Font.PLAIN,50));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        choiceText = new JTextArea();
        choiceText.setText(currNode.getText());
        choiceText.setFont(new Font("Times New Roman",Font.PLAIN,20));
        choiceText.setLineWrap(true);
        choiceText.setWrapStyleWord(true);
        choiceText.setEditable(false);

        choicePane = new JScrollPane(choicePanel);
        choicePane.setAlignmentX(Component.CENTER_ALIGNMENT);
        choicePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        textPane = new JScrollPane(choiceText);
        textPane.setPreferredSize(new Dimension(600,500));
        textPane.setAlignmentX(Component.CENTER_ALIGNMENT);

        choices = new JLabel[currNode.getNextNodeLength()];
        for(int i = 0; i < choices.length; i++)
        {
            choices[i] = new JLabel();
            choices[i].setText(currNode.getNextNode(i).getChoice());
            choices[i].setFont(new Font("Times New Roman",Font.PLAIN,20));
            choices[i].setForeground(Color.BLUE);
            choices[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            choicePanel.add(choices[i]);
            choices[i].addMouseListener(this);
        }
        choicePane.setMaximumSize(new Dimension(500,150));
        choicePane.revalidate();


        goBack = new JButton("Exit");
        goBack.setFont(new Font("Times New Roman",Font.PLAIN,20));
        goBack.setMaximumSize(new Dimension(200,50));
        goBack.addActionListener(this);

        previousChoice = new JButton("Go to Previous Choice");
        previousChoice.setFont(new Font("Times New Roman",Font.PLAIN,20));
        previousChoice.setMaximumSize(new Dimension(200,50));
        previousChoice.addActionListener(this);

        buttonPanel.add(goBack);
        buttonPanel.add(previousChoice);

        mainPanel.add(title);
        mainPanel.add(textPane);
        mainPanel.add(choicePane);
        mainPanel.add(buttonPanel);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void changeCurrChoice(CyoaNode node)
    {
        currNode = node;
        choiceText.setText(currNode.getText());
        title.setText(currNode.getChoice());
        for(JLabel choice : choices)
        {
            choice.removeMouseListener(this);
        }
        choicePanel.removeAll();
        choices = new JLabel[currNode.getNextNodeLength()];
        for(int i = 0; i < choices.length; i++)
        {
            choices[i] = new JLabel();
            choices[i].setText(currNode.getNextNode(i).getChoice());
            choices[i].setFont(new Font("Times New Roman",Font.PLAIN,20));
            choices[i].setForeground(Color.BLUE);
            choices[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            choicePanel.add(choices[i]);
            choices[i].addMouseListener(this);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        for(int i = 0; i < choices.length; i++)
        {
            if(e.getComponent().equals(choices[i]))
            {
                changeCurrChoice(currNode.getNextNode(i));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) 
    {
        for(JLabel choice : choices)
        {
            if(e.getComponent().equals(choice))
            {
                choice.setForeground(Color.GRAY);
            }
        }
    }
    @Override
    public void mouseExited(MouseEvent e) 
    {
        for(JLabel choice : choices)
        {
            if(e.getComponent().equals(choice))
            {
                choice.setForeground(Color.BLUE);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource().equals(goBack))
        {
            Gui.clear(frame);
            new TitlePage(frame);
        }
        else if(e.getSource().equals(previousChoice))
        {
            if(currNode != tree.getRoot())
                changeCurrChoice(currNode.getLastNode());
        }
    }
}


    
