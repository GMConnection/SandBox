package gm.sandbox.websandboxserver.controller;

import gm.sandbox.websandboxserver.dto.GameDto;
import gm.sandbox.websandboxserver.service.GameService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class GameRestController {

    private final GameService gameService;

    public GameRestController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping
    public List<GameDto> findAll() {
        return gameService.findAll();
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody GameDto game, BindingResult result,
                                          UriComponentsBuilder uri) throws BindException{

        if (result.hasErrors()) {
            if (result instanceof  BindException exception) {
                throw exception;
            } else {
                throw new BindException(result);
            }
        } else {
            GameDto gameDto = gameService.saveOrUpdate(game);
            return ResponseEntity.created(uri.replacePath("/api/games/{id}").build(gameDto.id())).body(gameDto);
        }
    }
}
