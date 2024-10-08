package zec.service.pdfbox.element;
import zec.service.pdfbox.PageWrapper;

import java.io.IOException;

public interface Drawable {
    void draw(PageWrapper contentStream) throws IOException;
}
