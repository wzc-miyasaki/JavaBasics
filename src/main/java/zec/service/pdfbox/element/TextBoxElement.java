package zec.service.pdfbox.element;

import lombok.Getter;
import lombok.Setter;
import zec.service.pdfbox.PageUtil;
import zec.service.pdfbox.PageWrapper;

import java.io.IOException;

public class TextBoxElement extends PageElement implements Drawable{

    TextElement textContent;
    @Setter
    float width;
    @Setter
    float height;

    float leftBoarder;

    float rightBoarder;

    float topBoarder;

    float bottomBoarder;


    public TextBoxElement(float x, float y) {
        super(x, y);

        //默认边界距离
        leftBoarder = 0f;
        rightBoarder =0f;
        topBoarder = 0f;
        bottomBoarder = 0f;

        //默认大小
        width = 0f;
        height = 0f;

    }

    public void addText(TextElement textContent) {
        this.textContent = textContent;
        // resize box width and height to fit with text
        width = textContent.getWidth() + leftBoarder + rightBoarder;
        height = textContent.getHeight() + topBoarder + bottomBoarder;
    }


    /** TODO: 实现 draw 方法
     * @param pageWrapper
     * @throws IOException
     */
    @Override
    public void draw(PageWrapper pageWrapper) throws IOException {
        drawOutline(pageWrapper);
        pageWrapper.getContentStream().stroke();
    }

    //TODO：根据四个角的坐标进行绘图
    private void drawOutline(PageWrapper pageWrapper) {
        float lowerLeftX = x - leftBoarder;
        float lowerRightX = lowerLeftX + width;
        float lowerLeftY = y - bottomBoarder;
        float upperLeftY = lowerLeftY + height;
        PageUtil.drawHorizontalLine(pageWrapper, lowerLeftX, lowerLeftY, width);
        PageUtil.drawHorizontalLine(pageWrapper, lowerLeftX, upperLeftY, width);
        PageUtil.drawVerticalLine(pageWrapper, lowerLeftX, upperLeftY, height);
        PageUtil.drawVerticalLine(pageWrapper, lowerRightX, upperLeftY, height);
    }


    @Override
    public float getWidth() {
        return width;
    }


    @Override
    public float getHeight() {
        return height;
    }

    public void setRightBoarder(float length) {
        width = width - rightBoarder + length;
        this.rightBoarder = length;
    }

    public void setLeftBoarder(float length) {
        width = width - leftBoarder + length;
        this.leftBoarder = length;
    }

    public void setTopBoarder(float length) {
        height = height - topBoarder + length;
        this.topBoarder = length;
    }

    public void setBottomBoarder(float length) {
        height = height - bottomBoarder + length;
        this.bottomBoarder = length;
    }
}
