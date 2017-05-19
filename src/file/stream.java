package file;

import java.io.*;

public class stream
{
    public static InputStream create(String filepath)
    {
        InputStream is=null;
        try
        {
            is=new FileInputStream(filepath);
        }
        catch (FileNotFoundException e)
        {
            System.out.println("file not found :"+filepath);
            return null;
        }
        return is;
    }
}
