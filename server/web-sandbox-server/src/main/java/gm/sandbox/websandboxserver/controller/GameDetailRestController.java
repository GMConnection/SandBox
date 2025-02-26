package gm.sandbox.websandboxserver.controller;

import gm.sandbox.websandboxserver.dto.GameDto;
import gm.sandbox.websandboxserver.service.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Objects;

@RestController
@RequestMapping("/api/games")
public class GameDetailRestController {

    private final GameService gameService;

    private final MessageSource messageSource;

    public GameDetailRestController(GameService gameService, MessageSource messageSource) {
        this.gameService = gameService;
        this.messageSource = messageSource;
    }

    @ModelAttribute("game")
    public GameDto addAttributes(@PathVariable("id") Long id) {
        GameDto game = gameService.findById(id);
        if (Objects.isNull(game)) {
            throw new NoSuchElementException("errors.game.not_found");
        }
        return game;
    }


    @GetMapping("/{id}")
    public GameDto showGameDetails(@PathVariable("id") Long id) {
        GameDto game = gameService.findById(id);
        if (Objects.isNull(game)) {
            throw new NoSuchElementException("errors.game.not_found");
        }
        return game;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Long id, @Valid @RequestBody GameDto game,
                                       BindingResult result)  throws BindException {
        if (result.hasErrors()) {
            if (result instanceof  BindException exception) {
                throw exception;
            } else {
                throw new BindException(result);
            }
        } else {
            gameService.saveOrUpdate(game);
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("id") Long id) {
        gameService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ProblemDetail> handlerNoSuchException(NoSuchElementException e, Locale locale) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body( ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                messageSource.getMessage(e.getMessage(), new Object[0], locale)));
    }
}
