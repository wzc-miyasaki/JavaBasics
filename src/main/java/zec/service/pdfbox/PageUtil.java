package zec.service.pdfbox;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import zec.service.pdfbox.element.TextElement;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * PdfBox 的工具类
 */
@Slf4j
public class PageUtil {

    /**
     * 在（x，y）位置画一条长度为 length 的横线
     * @param pageWrapper 页面对象
     */
    public static void drawHorizontalLine(PageWrapper pageWrapper, float startX, float startY, float length) {
        drawLine(pageWrapper, startX, startY, startX + length, startY);
    }

    /**
     * 在（x，y）位置向下画一条长度为 length 的竖线
     * @param pageWrapper 页面对象
     */
    public static void drawVerticalLine(PageWrapper pageWrapper, float startX, float startY, float length) {
        drawLine(pageWrapper, startX, startY, startX, startY - length);
    }

    /**
     * 画一条从（x1，y1）到（x2，y2）直线
     */
    public static void drawLine(PageWrapper pageWrapper, float x1, float y1, float x2, float y2) {
        PDPageContentStream contentStream = pageWrapper.getContentStream();
        if (contentStream == null) {
            log.error("PDF content stream is null, check if page has active content stream");
            return;
        }

        try{
            contentStream.moveTo(x1, y1);
            contentStream.lineTo(x2, y2);
            contentStream.stroke();
        } catch(IOException e) {
            log.error("Error drawing line: {}", e.getMessage(), e);
        }

    }

    /**
     * 从 resources/font 目录下加载字体文件
     * @param doc pdf 文件对象
     * @param fileName font 目录下的 ttf 字体文件名称
     * @return PDType0Font 成功则返回字体对象，失败则返回 {@code null}
     * @Author zecheng wang
     */
    public static PDType0Font loadFont(PDDocument doc, String fileName){
        try {
            URL newPath = PageUtil.class.getClassLoader().getResource("font/" + fileName);
            if(newPath == null){
                log.error("Could not find font {}", fileName);
                return null;
            }
            File path = new File(newPath.getPath());
            String absolutePath = path.getAbsolutePath();
            PDType0Font font = PDType0Font.load(doc, path);
            log.info("Load font from {}", absolutePath);
            return font;
        } catch (IOException e){
            log.error(e.getMessage());
        }
        return null;
    }

    public static float getTextWidth(String text, float fontSize, PDFont fontStyle) throws IOException {
        float width = 0;
        width += fontStyle.getStringWidth(text) * fontSize / 1000;
        return width;
    }

    /**
     * 计算page中以 mm 为单位进行的位移量，可对每个元素的布局进行细微的位移操作
     * @param distance 位移的距离
     * @return 位移的距离（mm）
     */
    public static float countMM(float distance) {
        return (float) (distance * 72 / 25.4);
    }
}
