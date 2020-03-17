package pl.imionator.imionator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.imionator.imionator.domain.Name;
import pl.imionator.imionator.repository.NamesRepository;
import pl.imionator.imionator.services.NamesService;

import javax.validation.Valid;

@Controller
public class NameController {

    private NamesService namesService;

    private NamesRepository namesRepository;

    public NameController(NamesService namesService, NamesRepository namesRepository) {
        this.namesService = namesService;
        this.namesRepository = namesRepository;
    }

    @GetMapping("/names")
    public String namesList(Model model) {
        model.addAttribute("namesGivenByUser", namesRepository.getUserInput());
        model.addAttribute("name", new Name());
        return "index";
    }

    @PostMapping("/names")
    public String saveName(@Valid Name name, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("namesGivenByUser", namesRepository.getUserInput());
            return "index";
        }
        namesRepository.saveUserInputName(name);
        return "redirect:/names";
    }

    @GetMapping("/deleteLastName")
    public String deleteLastNameFromUserInput() {
        namesRepository.deleteLastAddedName();
        return "redirect:/names";
    }

    @GetMapping("/result")
    public String drawResult(Model model) {
        Name name = namesService.drawNameFromUserInput();
        model.addAttribute("name", name);
        namesRepository.saveNameDrawnFromUserInput(name);
        return "drawnname";
    }

    @GetMapping("/randomResult")
    public String randomDrawResult(Model model) {
        model.addAttribute("drawnName", namesService.getLastNameDrawnFromPropositionList());
        model.addAttribute("randomName", new Name());
        return "drawnrandomname";
    }

    @PostMapping("/randomResult")
    public String drawRandomName(Name name) {
        name.setFirstName(namesService.getRandomNameFromGivenCategory(name.getNameCategory(), name.getSex()));
        namesRepository.saveNameDrawnFromPropositionList(name);
        return "redirect:/randomResult";
    }

    @GetMapping("/stats")
    public String drawStats(Model model) {
        model.addAttribute("statsFromUserInputDraw", namesService.generateStatisticsFromUserInputDraw());
        model.addAttribute("statsFromPropositionListDraw", namesService.generateStatisticsFromPropositionListDraw());
        return "statistics";
    }

    @GetMapping("/deleteName/{firstName}")
    public String deleteNameDrawnFromPropositionList(@PathVariable(name = "firstName") String name) {
        namesRepository.deleteNameDrawnFromPropositionList(name);
        return "redirect:/stats";
    }

}
