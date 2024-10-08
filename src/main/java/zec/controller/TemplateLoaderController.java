package zec.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.Context;
import zec.basics.services.TemplateLoaderService;

@Controller
@RequestMapping("/api")
@Slf4j
public class TemplateLoaderController {


    private final TemplateLoaderService templateLoaderService = new TemplateLoaderService();

    @GetMapping("/template")
    @ResponseBody
    public String getHTMLTemplate()
    {
        Context context = new Context();
        context.setVariable("something", "test");
        // return name of the HTML template
        String htmlContent = "<!DOCTYPE html> <html xmlns:th=\"https://www.thymeleaf.org\" lang=\"en\">"
                + "<head><meta charset=\"UTF-8\"><title>Title</title></head>"
                + "<body><h1 th:text=\"${something}\"></h1></body></html>";

        return templateLoaderService.loadHTMLTemplate(htmlContent, context);
    }
}
