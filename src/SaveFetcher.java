import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class SaveFetcher implements ActionListener,MouseListener
{
    ArrayList<File> saves;
    File selectedSave;

    JFrame frame;
    JPanel mainPanel;
    JLabel title;
    JPanel savePanel;
    JPanel buttonPanel;
    JScrollPane pane;
    ArrayList<JPanel> savePanels;
    ArrayList<JLabel> saveNames;
    ArrayList<JLabel> saveDates;
    JButton useSave;
    JButton goBack;
    JButton delete;


    public SaveFetcher(JFrame frame)
    {
        this.frame = frame;

        String directoryPath = System.getProperty("user.dir") + File.separator + "saves";
        File folder = new File(directoryPath);
        saves = new ArrayList<>();
        saves.addAll(Arrays.asList(folder.listFiles()));

        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        title = new JLabel();
        title.setText("Choose the save file");
        title.setFont(new Font("Times New Roman",Font.PLAIN,50));
        title.setBorder(new EmptyBorder(0,120,0,0));

        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT,50,0));

        savePanel = new JPanel();
        savePanel.setLayout(new BoxLayout(savePanel,BoxLayout.Y_AXIS));

        savePanels = new ArrayList<>();
        saveNames = new ArrayList<>();
        saveDates = new ArrayList<>();

        selectedSave = null;

        pane = new JScrollPane(savePanel);
        pane.setBorder(new EmptyBorder(50,50,50,50));

        addAllSaves();

        useSave = new JButton("Select Save");
        useSave.setFont(new Font("Times New Roman",Font.PLAIN,20));
        useSave.setMaximumSize(new Dimension(200,50));
        useSave.addActionListener(this);
        
        goBack = new JButton("Exit to Main Menu");
        goBack.setFont(new Font("Times New Roman",Font.PLAIN,20));
        goBack.setMaximumSize(new Dimension(200,50));
        goBack.addActionListener(this);

        delete = new JButton("Delete CYOA");
        delete.setFont(new Font("Times New Roman",Font.PLAIN,20));
        delete.setMaximumSize(new Dimension(200,50));
        delete.addActionListener(this);
        

        mainPanel.add(pane,BorderLayout.CENTER);
        mainPanel.add(title,BorderLayout.NORTH);
        mainPanel.add(buttonPanel,BorderLayout.SOUTH);

        buttonPanel.add(useSave);
        buttonPanel.add(delete);
        buttonPanel.add(goBack);

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private void addAllSaves()
    {
        for(int i = 0; i < saves.size(); i++)
        {
            savePanels.add(new JPanel());
            saveNames.add(new JLabel());
            saveDates.add(new JLabel());
            savePanels.get(i).setBorder(BorderFactory.createLineBorder(Color.BLACK));
            savePanels.get(i).setLayout(new FlowLayout(FlowLayout.LEFT,25,0));
            savePanels.get(i).setPreferredSize(new Dimension(50,20));
            saveNames.get(i).setFont(new Font("Times New Roman",Font.PLAIN,10));
            saveDates.get(i).setFont(new Font("Times New Roman",Font.PLAIN,10));
            try 
            {
                BufferedReader reader = new BufferedReader(new FileReader(saves.get(i)));
                saveNames.get(i).setText(reader.readLine());
                reader.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
            long lastModified = saves.get(i).lastModified();
            saveDates.get(i).setText("Last Modified: " + new Date(lastModified).toString());
            
            savePanels.get(i).add(saveNames.get(i));
            savePanels.get(i).add(saveDates.get(i));
            savePanels.get(i).addMouseListener(this);

            savePanel.add(savePanels.get(i));
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent e) 
    {
        int index = 0;
        for(int i = 0; i < savePanels.size(); i++)
        {
            savePanels.get(i).setBackground(Color.WHITE);
            if(savePanels.get(i).equals(e.getSource()))
            {
                index = i;
            }
        }
        selectedSave = saves.get(index);
        savePanels.get(index).setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {

    }

    @Override
    public void mouseReleased(MouseEvent e) 
    {

    }

    @Override
    public void mouseEntered(MouseEvent e) 
    {

    }

    @Override
    public void mouseExited(MouseEvent e) 
    {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(useSave))
        {
            SaveInterfacer interfacer = new SaveInterfacer(selectedSave);
            Gui.clear(frame);
            new EditorCard(frame, interfacer);
        }
        else if(e.getSource().equals(goBack))
        {
            Gui.clear(frame);
            new TitlePage(frame);
        }
        else if(e.getSource().equals(delete))
        {
            for(int i = 0; i < saves.size(); i++)
            {
                if(selectedSave.equals(saves.get(i)))
                {
                    savePanel.remove(savePanels.get(i));
                    savePanels.remove(i);
                    saveDates.remove(i);
                    saveNames.remove(i);
                    saves.remove(i);
                }
            }
            SaveInterfacer.saveDeletor(selectedSave);
            frame.setVisible(true);
        }
    }
}
