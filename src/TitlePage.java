
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
import javax.swing.border.EmptyBorder;

public class TitlePage implements ActionListener
{
    JFrame frame;
    JPanel panel;
    JLabel title;
    JButton createNew;
    JButton editOld;
    JButton readOld;
    JButton settings;

    public TitlePage(JFrame frame)
    {
        this.frame = frame;

        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        panel.setBorder(new EmptyBorder(50,50,50,50));


        title = new JLabel();
        title.setText("CYOA Creator");
        title.setFont(new Font("Times New Roman",Font.PLAIN,50));
        title.setBorder(new EmptyBorder(0,55,0,0));

        createNew = new JButton();
        createNew.setText("Create New CYOA");
        createNew.setFont(new Font("Times New Roman",Font.PLAIN,50));
        createNew.setMaximumSize(new Dimension(500,100));
        createNew.addActionListener(this);

        readOld = new JButton();
        readOld.setText("View Old CYOA");
        readOld.setFont(new Font("Times New Roman",Font.PLAIN,50));
        readOld.setMaximumSize(new Dimension(500,100));
        readOld.addActionListener(this);

        editOld = new JButton();
        editOld.setText("Edit Old CYOA");
        editOld.setFont(new Font("Times New Roman",Font.PLAIN,50));
        editOld.setMaximumSize(new Dimension(500,100));
        editOld.addActionListener(this);

        settings = new JButton();
        settings.setText("Settings");
        settings.setFont(new Font("Times New Roman",Font.PLAIN,50));
        settings.setMaximumSize(new Dimension(500,100));
        settings.addActionListener(this);

        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0,125)));
        panel.add(createNew);
        panel.add(Box.createRigidArea(new Dimension(0,75)));
        panel.add(readOld);
        panel.add(Box.createRigidArea(new Dimension(0,75)));
        panel.add(editOld);
        panel.add(Box.createRigidArea(new Dimension(0,75)));
        panel.add(settings);
        frame.add(panel);


        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource().equals(createNew))
        {
            Gui.clear(frame);
            new CyoaCreator(frame);
        }
        else if(e.getSource().equals(editOld))
        {
            Gui.clear(frame);
            new SaveFetcher(frame);
        }
    }
}
