import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SaveInterfacer 
{
    private final File currFile;
    private CyoaTree tree;
    private String randomAddress;
    
    public SaveInterfacer(String directory)
    {
        currFile = new File(directory);
        convertToCyoa();
    }

    public SaveInterfacer(File file)
    {
        currFile = file;
        convertToCyoa();
    }

    public SaveInterfacer(CyoaTree tree)
    {
        this.tree = tree;
        randomAddress = addressGenerator();
        currFile = saveCreator();
    }

    public CyoaTree convertToCyoa()
    {
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(currFile));
            String name = reader.readLine();
            tree = new CyoaTree(name);
            CyoaNode rootNode = tree.getRoot();
            createChildren(rootNode, reader);
            reader.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return tree;
    }

    private String addressGenerator()
    {
        String address = "";
        char[] availableChars = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
        for(int i = 0; i < 8; i++)
        {
            address += availableChars[(int)(Math.random()*availableChars.length)];
        }
        return String.valueOf(address);
    }

    private String[] readFileLine(String line)
    {
        return line.split("\\|::");
    }

    private void createChildren(CyoaNode currNode, BufferedReader reader)
    {
        try 
        {
            String line = reader.readLine();
            if(line != null)
            {
                String[] arr = readFileLine(line);
                int numChildren = Integer.parseInt(arr[0]);
                for(int i = 0; i < numChildren; i++)
                {
                    currNode.setText(arr[1]);
                    currNode.setChoice(arr[0]);
                    createChildren(currNode.addNextNode(),reader);
                }
            }
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
    }

    public void saveEditor()
    {
        try 
        {
            FileWriter write = new FileWriter(currFile);
            write.write(tree.toString());
            write.close();
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }

    }

    private File saveCreator()
    {
        String directoryPath = System.getProperty("user.dir") + File.separator + "saves";
        File newFile = new File(directoryPath,(randomAddress + ".txt"));
        try
        {
            newFile.createNewFile();
            FileWriter write = new FileWriter(newFile);
            write.write(tree.toString());
            write.close();
        }
        catch(IOException io)
        {
            io.printStackTrace();
        }
        return newFile;
    }
    
    public void saveDeletor()
    {
        boolean deleted = currFile.delete();
        if(!deleted)
        {
            System.out.println("Couldn't successfully delete");
        }
    }

    public File getFile() 
    {
        return currFile;
    }

    public CyoaTree getTree()
    {
        return tree;
    }

}
