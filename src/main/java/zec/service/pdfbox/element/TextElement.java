package zec.service.pdfbox.element;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import zec.service.pdfbox.PageUtil;
import zec.service.pdfbox.PageWrapper;

import java.io.IOException;

@Slf4j
public class TextElement extends PageElement implements Drawable {


    float fontSz;
    String content;
    PDFont fontStyle;

    /**
     *
     * @param content 文本内容
     * @param fontSz 字体大小
     * @param fontStyle 字体样式
     */
    public TextElement(String content, float fontSz, PDFont fontStyle) {
        super();
        this.fontSz = fontSz;
        this.content = content;
        this.fontStyle = fontStyle;
        this.setPosition(1f, 1f);
    }

    public boolean hasText() {
        if (content == null) return false;
        return !content.isEmpty();
    }


    @Override
    public float getWidth() {
        try{
            return PageUtil.getTextWidth(content, fontSz, fontStyle);
        } catch (IOException e) {
            log.error("Unable to get width: {}", e.getMessage(), e);
        }
        return 0f;
    }

    @Override
    public float getHeight() {
        return fontSz;
    }

    @Override
    public void draw(PageWrapper pageWrapper) throws IOException {
        PDPageContentStream contentStream = pageWrapper.getContentStream();
        contentStream.beginText();
        contentStream.newLineAtOffset(x, y);
        contentStream.setFont(fontStyle, fontSz);
        contentStream.showText(content);
        contentStream.endText();
    }
}
