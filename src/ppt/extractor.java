package ppt;

import java.io.*;

import org.apache.poi.hslf.extractor.*;

public class extractor {

    public static PowerPointExtractor create(InputStream is)
    {
        PowerPointExtractor extractor;
        try
        {
            extractor = new PowerPointExtractor(is);
        }
        catch(IOException e)
        {
            System.out.println("error when reading file");
            e.printStackTrace();
            extractor=null;
        }
        return extractor;
    }

}