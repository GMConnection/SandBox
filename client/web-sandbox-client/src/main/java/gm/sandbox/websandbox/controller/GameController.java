package gm.sandbox.websandbox.controller;

import gm.sandbox.websandbox.dto.GameDto;
import gm.sandbox.websandbox.entity.Game;
import gm.sandbox.websandbox.service.GameService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/api/games")
public class GameController {

    private final GameService gameService;
    private final MessageSource messageSource;

    public GameController(GameService gameService, MessageSource messageSource) {
        this.gameService = gameService;
        this.messageSource = messageSource;
    }

    @PostMapping("/new")
    public String createGame(@Valid @ModelAttribute("game") GameDto game, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("game", game);
            model.addAttribute("errors", result.getAllErrors().stream().map(ObjectError::getDefaultMessage).toList());
            return "saveGame";
        } else {
            System.err.println("game = " + game);
            gameService.saveOrUpdate(game);
            return "redirect:/api/games";
        }
    }

    @GetMapping
    public String selectAll(Model model) {
        List<GameDto> all = gameService.findAll();
//        if (all.isEmpty()) {
//            throw new NoSuchElementException("catalogue.errors.product.not_found");
//        }
        model.addAttribute("games", all);
        return "home";
    }

    @GetMapping("/new")
    public String newGame(Model model) {
        model.addAttribute("game", new Game());
        return "saveGame";
    }

    @GetMapping("/edit/{id}")
    public String editGame(@PathVariable("id") Long id, Model model) {
        GameDto game = gameService.findById(id);
        model.addAttribute("game", game);
        return "saveGame";
    }

    @PostMapping("/delete/{id}")
    public String deleteGame(@PathVariable Long id) {
        gameService.deleteById(id);
        return "redirect:/api/games";
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