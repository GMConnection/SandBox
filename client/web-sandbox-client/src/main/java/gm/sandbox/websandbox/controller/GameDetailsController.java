package gm.sandbox.websandbox.controller;

import gm.sandbox.websandbox.client.GameRestClient;
import gm.sandbox.websandbox.dto.GameDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/games/{id}")
public class GameDetailsController {

    private final GameRestClient gameRestClient;

    @GetMapping
    public String showGameDetails(@PathVariable Long id, Model model) {
        GameDto game = gameRestClient.findById(id);
        model.addAttribute("game", game);
        return "gameDetails";
    }

    @GetMapping("edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        GameDto game = gameRestClient.findById(id);
        model.addAttribute("game", game);
        return "saveGame";
    }

    @DeleteMapping("delete")
    public String delete(@PathVariable Long id) {
        gameRestClient.deleteById(id);
        return "redirect:/api/games";
    }
}
