package zec.basics.services;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@Slf4j
public class TemplateLoaderService {

    private final TemplateEngine templateEngine;
    @Setter
    private String dirPath; //e.g:  /Usr/test/

    public TemplateLoaderService(){
        StringTemplateResolver templateResolver = new StringTemplateResolver();
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);
        this.templateEngine = new TemplateEngine();
        this.templateEngine.setTemplateResolver(templateResolver);
    }

    public String loadHTMLTemplate(String htmlContent, Context context)
    {
        String result = templateEngine.process(htmlContent, context);
        return result;
    }


}
