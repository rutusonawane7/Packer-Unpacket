import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;

public class MarvellousPacker
{
   FileOutputStream outstream = null;

   String ValidExt[] = {".txt",".c",".java",".cpp"};

   public MarvellousPacker(String src, String Dest)throws Exception
{
    String Magic = "Marvellous11";
    byte arr[] = Magic.getBytes();
    outstream = new FileOutputStream(Dest);
    outstream.write(arr,0,arr.length);

    new File(src);

    System.setProperty("user.dir",src);

    listAllFiles(src);
}
/**
 * @param path
 */
public void listAllFiles(String path)
{
    try
    (Stream<Path> paths = Files.walk(Paths.get(path)))
    {
        paths.forEach(filepath ->
        extracted(filepath));
    }
    catch(IOException e)
    {
        System.out.println(e);
    }
}
private void extracted(Path filepath) {
    if (Files.isRegularFile(filepath))
    {
        try
        {
            String name = filepath.getFileName().toString();
            String ext = name.substring(name.lastIndexOf("."));

            List<String> list = Arrays.asList(ValidExt);
            if(list.contains(ext))
            {
                File file = new File(filepath.getFileName().toString());
                Pack(file.getAbsolutePath());
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}

public void Pack(String filepath)
{
    FileInputStream instream = null;
    try
    {
        byte[] buffer = new byte[1024];
        int length;
        byte temp[] = new byte[100];
        File fobj = new File(filepath);
        String Header = filepath+" "+fobj.length();
        for(int i = Header.length();i<100;i++)
        Header += " ";
        temp=Header.getBytes();
        instream = new FileInputStream(filepath);
        outstream.write(temp,0,temp.length);
        while((length = instream.read(buffer)) > 0)
        {
            outstream.write(buffer,0,length);
        }
        instream.close();
    }
    catch(Exception e)
    {
        System.out.println(e);
    }
}

}