
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class CyoaCreator implements ActionListener
{
    JFrame frame;
    JPanel mainPanel;
    JLabel title;
    JTextField nameField;
    JButton create;
    CyoaTree tree;


    public CyoaCreator(JFrame frame)
    {
        this.frame = frame;

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(50,50,50,50));

        title = new JLabel();
        title.setText("Create Project");
        title.setFont(new Font("Times New Roman",Font.PLAIN,50));
        title.setBorder(new EmptyBorder(0,65,0,0));

        nameField = new JTextField();
        nameField.setFont(new Font("Times New Roman",Font.PLAIN,30));
        nameField.setMaximumSize(new Dimension(1000,100));

        create = new JButton();
        create.setText("Create New CYOA");
        create.setFont(new Font("Times New Roman",Font.PLAIN,50));
        create.setMaximumSize(new Dimension(500,100));
        create.addActionListener(this);

        mainPanel.add(title);
        mainPanel.add(Box.createRigidArea(new Dimension(0,125)));
        mainPanel.add(nameField);
        mainPanel.add(Box.createRigidArea(new Dimension(0,125)));
        mainPanel.add(create);

        frame.add(mainPanel);

        frame.setVisible(true);
    } 

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource().equals(create))
        {
            String txt = nameField.getText();
            frame.setVisible(true);
            tree = new CyoaTree(txt);
            Gui.clear(frame);
            new EditorCard(frame,tree);
        }
    }
    
}
