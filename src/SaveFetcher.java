import java.io.File;

public class SaveFetcher 
{
    File[] saves;

    public SaveFetcher()
    {
        String directoryPath = System.getProperty("user.dir") + File.separator + "saves";
        File folder = new File(directoryPath);
        saves = folder.listFiles();
    }
}
