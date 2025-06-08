import java.awt.FlowLayout;

import javax.swing.JFrame;

public class Gui 
{
    public Gui()
    {
        JFrame frame = new JFrame();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);
        frame.setTitle("CYOA Creator");
        frame.setLayout(new FlowLayout(FlowLayout.CENTER));
        TitlePage page1 = new TitlePage(frame);
    }
}
