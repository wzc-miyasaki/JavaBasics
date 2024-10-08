package zec.service.pdfbox;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import zec.service.pdfbox.element.PageLayout;
import zec.service.pdfbox.element.TextBoxElement;
import zec.service.pdfbox.element.TextElement;

import java.awt.*;

@Slf4j
@Service
public class PdfService {

    public void pdfTest() {

        //1. create an empty Docment
        try (PDDocument document = new PDDocument()) {

            //2. create a pdf Page:  A4 size
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            PageWrapper pageWrapper = new PageWrapper(page);
            pageWrapper.openStream(document);
            PDPageContentStream contentStream = pageWrapper.getContentStream();
            PDType0Font font = PageUtil.loadFont(document, "DroidSansFallback.ttf");
            PDType1Font enFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA);

            // page details
            float width = pageWrapper.getPageWidth();
            float height = pageWrapper.getPageHeight();
            log.info("page width:  {}", width);
            log.info("page height: {}", height);

            //3. >>>>>> start to edit page
            String sampleText = "测试文本框的边界和文本添加的顺序影响";
            contentStream.setFont(font, 12);
            PageLayout layout = new PageLayout();
            TextElement text = new TextElement(sampleText, 8, font);


            //a. set offset to the upper left corner of the page
            float x = 0f + PageUtil.countMM(55f);
            float y = height - PageUtil.countMM(55f);

            text.setPosition(x, y);
            layout.addDrawableElement(text);

            TextBoxElement box = new TextBoxElement(x, y);

            box.addText(text);
            box.setLeftBoarder(PageUtil.countMM(5f));
            box.setRightBoarder(PageUtil.countMM(5f));
            box.setTopBoarder(PageUtil.countMM(5f));
            box.setBottomBoarder(PageUtil.countMM(5f));
            layout.addDrawableElement(box);




            layout.drawElements(pageWrapper);
            contentStream.setStrokingColor(Color.BLACK);


            //3. <<<<<<< end to edit page
            contentStream.close();

            document.save("hello-1.pdf");
            System.out.println("PDF created successfully.");
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        PdfService pdfService = new PdfService();
        pdfService.pdfTest();

    }

}
