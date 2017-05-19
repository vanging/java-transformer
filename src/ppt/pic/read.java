package ppt.pic;

import org.apache.poi.hslf.HSLFSlideShow;
import org.apache.poi.hslf.model.Picture;
import org.apache.poi.hslf.usermodel.PictureData;
import org.apache.poi.hslf.usermodel.SlideShow;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class extractor {

    public static void getPicFromPPT(String filePath1) {
        SlideShow ppt = null;
        try {
            ppt = new SlideShow(new HSLFSlideShow(filePath1));
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        PictureData[] picDatas=ppt.getPictureData();

        for (int i=0;i<picDatas.length;i++) {
            if(picDatas[i].getType() == Picture.JPEG){
                FileOutputStream out = null;
                try {
                    out = new FileOutputStream("1,jpg");
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                try {
                    ppt.write(out);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                try {
                    out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

}