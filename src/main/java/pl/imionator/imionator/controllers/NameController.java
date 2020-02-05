package pl.imionator.imionator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.imionator.imionator.domain.Name;
import pl.imionator.imionator.services.NamesService;

@Controller
public class NameController {

    private NamesService namesService;

    @Autowired
    public NameController(NamesService namesService) {
        this.namesService = namesService;
    }


    @GetMapping("/names")
    public String namesList(Model model) {
        model.addAttribute("namesGivenByUser", namesService.namesGivenByUser());
        model.addAttribute("name", new Name());
//        model.addAttribute("nameCategory", new Name)
        return "main";
    }

    @PostMapping("/names")
    public String saveName(Name name) {
        namesService.saveNameGivenByUser(name);
        return "redirect:/names";
    }

    @GetMapping("/result")
    public String drawResult(Model model) {
        Name name = namesService.nameDrawer();
        model.addAttribute("name", name);
        namesService.saveNameDrawnByUser(name);
        return "drawnname";
    }

    @GetMapping("/randomResult")
    public String randomDrawResult(Model model) {
        model.addAttribute("randomName", new Name());
        return "drawnrandomname";
    }

    @PostMapping("/randomResult")
    public String drawRandomName(Name name) {
        //TODO implement logic responsible for drawing name based on name category
        namesService.saveNameDrawnByUser(name);
        return "redirect:/drawnrandomname";
    }

    @GetMapping("/stats")
    public String drawStats(Model model) {
        model.addAttribute("statistics", namesService.drawnNamesStatsMaker());
        return "statistics";
    }
}
