package gm.sandbox.websandbox.controller;

import gm.sandbox.websandbox.client.BadRequestExp;
import gm.sandbox.websandbox.client.GameRestClient;
import gm.sandbox.websandbox.dto.GameDto;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/games")
public class GameController {

    private final GameRestClient gameRestClient;

    private final MessageSource messageSource;

    @GetMapping
    public String selectAll(Model model) {
        List<GameDto> all = gameRestClient.findAll();
        model.addAttribute("games", all);
        return "home";
    }

    @GetMapping("create")
    public String create(Model model) {
        model.addAttribute("game", null);
        return "saveGame";
    }

    @PostMapping("create")
    public String create(@ModelAttribute("game") GameDto game,  Model model) {
        try {
            gameRestClient.create(game);
            return "redirect:/api/games";
        } catch (BadRequestExp exception) {
            model.addAttribute("game", game);
            model.addAttribute("errors", exception.getErrors());
            return "catalogue/products/new_product";
        }
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(NoSuchElementException exception, Model model,
                                               HttpServletResponse response, Locale locale) {
        response.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("error",
                this.messageSource.getMessage(exception.getMessage(), new Object[0],
                        exception.getMessage(), locale));
        return "errors/404";
    }
}