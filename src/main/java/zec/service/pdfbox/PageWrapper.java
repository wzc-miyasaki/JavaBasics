package zec.service.pdfbox;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;

import java.io.IOException;

/**
 * 为 PDPage 类的包装类
 */
@Slf4j
public class PageWrapper {

    private boolean isContentOpen;
    private PDPage page;
    @Getter
    private PDPageContentStream contentStream;

    public PageWrapper(PDPage page) {
        this.page = page;
        isContentOpen = false;
    }

    public void openStream(PDDocument document) throws IOException {
        try {
            contentStream =  new PDPageContentStream(document, page);
            isContentOpen = true;
        } catch (IOException e) {
            log.error("Unable to open content stream: {}", e.getMessage());
        }
    }

    public void closeStream() {
        try {
            contentStream.close();
        } catch (IOException e) {
            log.error("Unable to close content stream: {}", e.getMessage());
            contentStream = null;
        }
        isContentOpen = false;
    }

    /**
     * @return width of page
     */
    public float getPageWidth() {
        return page.getMediaBox().getWidth();
    }

    /**
     * @return height of page
     */
    public float getPageHeight() {
        return page.getMediaBox().getHeight();
    }

}
