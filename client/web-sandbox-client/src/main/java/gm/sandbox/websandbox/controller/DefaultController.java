package gm.sandbox.websandbox.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.hibernate.validator.spi.messageinterpolation.LocaleResolver;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class DefaultController {

   private final MessageSource messageSource;

    @GetMapping
    public String index(HttpServletRequest request) {
        return "index";
    }
}
