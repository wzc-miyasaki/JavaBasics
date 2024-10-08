package zec.service.pdfbox.element;

public abstract class PageElement {
    float x;
    float y;

    public PageElement() {}
    public PageElement(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {return x;}
    public float getY() {return y;}
    public void setX(float x) {this.x = x;}
    public void setY(float y) {this.y = y;}
    public void setPosition(float x, float y) {
        setX(x);
        setY(y);
    }

    public abstract float getWidth();

    public abstract float getHeight();
}
