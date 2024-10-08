package zec.service.pdfbox.element;

import lombok.extern.slf4j.Slf4j;
import zec.service.pdfbox.PageWrapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PageLayout {
    List<Drawable> drawableElements = new ArrayList<>();

    public void addDrawableElement(Drawable element) {
        drawableElements.add(element);
    }

    public void drawElements(PageWrapper pageWrapper) {
        if (drawableElements.isEmpty()) {
            return;
        }

        drawableElements.forEach(e -> {
            try {
                e.draw(pageWrapper);
            } catch (IOException ex) {
                log.error("Error while drawing drawable element: {}", ex.getMessage(), ex);
            }
        });
    }
}
