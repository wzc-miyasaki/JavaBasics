package zec.aop.itext;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class PdfHelperAspect {

    @After("execution(* zec.service.pdfbox.PdfService.pdfTest(..))")
    public void logAfter(JoinPoint joinPoint) {
        log.info("pdfTest hsa been Executed: " + joinPoint.getSignature());
    }
}
