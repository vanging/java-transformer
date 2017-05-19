package ppt.text;

import org.apache.poi.hslf.extractor.PowerPointExtractor;

import java.io.IOException;
import java.io.InputStream;

public class get
{
    public static String content(String type,String filePath)
    {
        InputStream is = file.stream.create(filePath);
        PowerPointExtractor extractor = ppt.extractor.create(is);
        String ret;
        try
        {
            if(is==null)
            {
                return "";
            }
            switch(type)
            {
                case "notes" : ret=extractor.getNotes();break;
                case "text" : ret=extractor.getText();break;
                default     : ret="";break;
            }
            extractor.close();
        }
        catch(IOException e)
        {
            System.out.println("error when reading file");
            e.printStackTrace();
            ret="";
        }
        return ret;
    }
}
