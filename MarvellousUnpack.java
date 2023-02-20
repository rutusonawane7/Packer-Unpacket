import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MarvellousUnpack
{
   FileOutputStream outstream = null;

   public MarvellousUnpack(String src) throws Exception
   {
    unpack(src);
   }

   public void unpack(String filepath) throws Exception
   {
    try
    {
        try (FileInputStream instream = new FileInputStream(filepath)) {
            byte header[] = new byte[100];
            byte Magic[] = new byte[12];
            instream.read(Magic,0,Magic.length);

            String Magicstr = new String(Magic);

            if(!Magicstr.equals("Marvellous11"))
            {
                throw new InvalidFileException("Invalid packed file format");
            }

            while((instream.read(header,0,100)) > 0)
            {
               String str = new String(header);

               String ext = str.substring(str.lastIndexOf("/"));
               ext = ext.substring(1);

               String[] words = ext.split("\\s");

               String filename = words[0];

               int size = Integer.parseInt(words[1]);

               byte arr[] = new byte[size];

               instream.read(arr,0,size);

               try (FileOutputStream fout = new FileOutputStream(filename)) {
                fout.write(arr,0,size);
            }

            }
        }

    }
    catch(InvalidFileException obj)
    {
        throw new InvalidFileException("Invalid packed file format");
    }
    catch(Exception e)
    {}
   }
}
   