package zec.controller;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import zec.pojo.SimpleUser;
import zec.service.pdfbox.PdfService;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Resource
    PdfService pdfService;

//    @RequestMapping(value = "/restful", method = RequestMethod.GET)
    @GetMapping("/restful")
    public String restTest(@RequestBody SimpleUser usr) {
        log.info("restful test enter");

        return usr.toString();
    }

    @GetMapping("/pdf")
    public String restTest() {
        pdfService.pdfTest();
        return "Test Finished";
    }
}
