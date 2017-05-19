import java.io.*;

public class Main
{
    private static String affix(String filename)
    {
        String[] snippets = filename.split("\\.");
        return snippets[snippets.length - 1];
    }
    public static void main(String[] args)
    {
        //绝对路径
        String input_file_path = args[0];
        String image_file_path = args[1];

        File image_file_dir = new File(image_file_path);
        if (!image_file_dir.exists())
        {
            System.out.println("image file path doesn't exist");
            System.exit(-1);
        }
        else
        {
            String affix = affix(input_file_path);
            boolean success = false;
            if(affix.equals("ppt"))
            {
                try
                {
                    success = PPT.to_image(input_file_path, image_file_path);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                if(affix.equals("pptx"))
                {
                    success = PPTX.to_image(input_file_path);
                }
                else
                {
                    System.out.println("input file is not ppt or pptx");
                    System.exit(-1);
                }
            }
            if(success)
            {
                System.exit(0);
            }
            else
            {
                System.exit(-1);
            }
        }
    }
}
