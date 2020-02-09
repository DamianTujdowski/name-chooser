package pl.imionator.imionator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.imionator.imionator.domain.Name;
import pl.imionator.imionator.repository.NamesRepository;
import pl.imionator.imionator.services.NamesService;

@Controller
public class NameController {

    private NamesService namesService;

    private NamesRepository namesRepository;

    @Autowired
    public NameController(NamesService namesService, NamesRepository namesRepository) {
        this.namesService = namesService;
        this.namesRepository = namesRepository;
    }

    @GetMapping("/names")
    public String namesList(Model model) {
        model.addAttribute("namesGivenByUser", namesRepository.getNamesGivenByUser());
        model.addAttribute("name", new Name());
        return "main";
    }

    @PostMapping("/names")
    public String saveName(Name name) {
        namesRepository.saveNameGivenByUser(name);
        return "redirect:/names";
    }

    @GetMapping("/result")
    public String drawResult(Model model) {
        Name name = namesService.drawFromNamesGivenByUser();
        model.addAttribute("name", name);
        namesRepository.saveNameDrawnByUser(name);
        return "drawnname";
    }

    @GetMapping("/randomResult")
    public String randomDrawResult(Model model) {
        model.addAttribute("drawnName", namesService.drawNameFromGivenCategory());
        model.addAttribute("randomName", new Name());
        return "drawnrandomname";
    }

    @PostMapping("/randomResult")
    public String drawRandomName(Name name) {
        namesRepository.saveNameDrawnByUser(name);
        return "redirect:/randomResult";
    }

    @GetMapping("/stats")
    public String drawStats(Model model) {
        model.addAttribute("statistics", namesService.generateStatistics());
        return "statistics";
    }
}
