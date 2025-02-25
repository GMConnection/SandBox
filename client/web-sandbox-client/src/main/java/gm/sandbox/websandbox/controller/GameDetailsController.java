package gm.sandbox.websandbox.controller;

import gm.sandbox.websandbox.dto.GameDto;
import gm.sandbox.websandbox.entity.Game;
import gm.sandbox.websandbox.service.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/games")
public class GameDetailsController {

    private final GameService gameService;

    public GameDetailsController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/{id}")
    public String showGameDetails(@PathVariable Long id, Model model) {
        GameDto game = gameService.findById(id);
        model.addAttribute("game", game);
        return "gameDetails";
    }
}
