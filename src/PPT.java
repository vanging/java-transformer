import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

public class PPT
{
    static boolean to_image(String file_path, String image_path) throws Exception
    {
        int slidenum = -1;

        FileInputStream is = new FileInputStream(file_path);
        HSLFSlideShow ppt = new HSLFSlideShow(is);
        is.close();
        Dimension pgsize = ppt.getPageSize();
        int width = (int)((float)pgsize.width);
        int height = (int)((float)pgsize.height);
        Iterator i$ = ppt.getSlides().iterator();

        while(true)
        {
            HSLFSlide slide;
            do
            {
                if(!i$.hasNext())
                {
                    return true;
                }

                slide = (HSLFSlide)i$.next();
            } while(slidenum != -1 && slidenum != slide.getSlideNumber());

            String title = slide.getTitle();
            System.out.println("Rendering slide " + slide.getSlideNumber() + (title == null?"":": " + title));
            BufferedImage img = new BufferedImage(width, height, 1);
            Graphics2D graphics = img.createGraphics();
            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            graphics.setPaint(Color.white);
            graphics.fill(new java.awt.geom.Rectangle2D.Float(0.0F, 0.0F, (float)width, (float)height));
            graphics.scale((double)width / (double)pgsize.width, (double)height / (double)pgsize.height);
            slide.draw(graphics);
            String fname = image_path + File.separator + slide.getSlideNumber() + ".png";
            FileOutputStream out = new FileOutputStream(fname);
            ImageIO.write(img, "png", out);
            out.close();
        }

        return true;
    }
}
