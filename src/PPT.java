import org.apache.poi.hslf.usermodel.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class PPT
{
    static boolean to_image(String file_path, String image_path) throws Exception
    {

        FileInputStream is = new FileInputStream(file_path);
        HSLFSlideShow ppt = new HSLFSlideShow(is);
        is.close();

        Dimension page_size = ppt.getPageSize();

        for (HSLFSlide slide : ppt.getSlides())
        {
            for( HSLFShape shape : slide.getShapes() )
            {
                if (shape instanceof HSLFTextShape)
                {
                    HSLFTextShape text_shape = (HSLFTextShape) shape;

                    for (HSLFTextParagraph text_para : text_shape.getTextParagraphs())
                    {
                        for (HSLFTextRun text_run : text_para.getTextRuns())
                        {
                            text_run.setFontFamily("宋体");
                        }
                    }
                }
            }

            String title = slide.getTitle();
            System.out.println("Rendering slide " + slide.getSlideNumber() + (title == null?"":": " + title));

            BufferedImage img = new BufferedImage(page_size.width, page_size.height,BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = img.createGraphics();

            graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
            graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);

            graphics.setPaint(Color.white);

            graphics.fill(new java.awt.geom.Rectangle2D.Float(0, 0, page_size.width, page_size.height));

            slide.draw(graphics);

            String image_file_path = image_path + File.separator + slide.getSlideNumber() + ".png";
            FileOutputStream out = new FileOutputStream(image_file_path);
            ImageIO.write(img, "png", out);

            out.close();
        }
        return true;
    }
}
