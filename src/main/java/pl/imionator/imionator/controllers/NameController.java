package pl.imionator.imionator.controllers;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import pl.imionator.imionator.domain.Name;
import pl.imionator.imionator.domain.Sex;
import pl.imionator.imionator.repository.NamesManager;
import pl.imionator.imionator.services.NamesService;
import pl.imionator.imionator.utils.PdfGenerator;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;

@Controller
public class NameController {

    private NamesService namesService;

    private NamesManager namesManager;

    public NameController(NamesService namesService, NamesManager namesManager) {
        this.namesService = namesService;
        this.namesManager = namesManager;
    }

    @GetMapping
    public String namesList(Model model) {
        model.addAttribute("userInput", namesManager.getUserInput());
        model.addAttribute("namesDrawnFromUserInput", namesManager.getNamesDrawnFromUserInput());
        model.addAttribute("name", new Name());
        return "index";
    }

    @PostMapping
    public String saveName(@Valid Name name, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userInput", namesManager.getUserInput());
            return "index";
        }
        namesManager.saveUserInputName(name);
        return "redirect:/";
    }

    @GetMapping("/deleteLastName")
    public String deleteLastNameFromUserInput() {
        namesManager.deleteLastAddedName();
        return "redirect:/";
    }

    @GetMapping("/result")
    public String drawResult(Model model) {
        Name name = namesService.drawNameFromUserInput();
        model.addAttribute("name", name);
        namesManager.saveNameDrawnFromUserInput(name);
        return "drawnname";
    }

    @GetMapping("/randomResult")
    public String randomDrawResult(Model model) {
        model.addAttribute("namesDrawnFromPropositions", namesManager.getNamesDrawnFromPropositionList());
        model.addAttribute("drawnName", namesService.getLastNameDrawnFromPropositionList());
        model.addAttribute("randomName", new Name());
        return "drawnrandomname";
    }

    @PostMapping("/randomResult")
    public String drawRandomName(Name name) {
        name.setFirstName(namesService.getRandomNameFromGivenCategory(name.getNameCategory(), name.getSex()));
        namesManager.saveNameDrawnFromPropositionList(name);
        return "redirect:/randomResult";
    }

    @GetMapping("/stats")
    public String drawStats(Model model) {
        model.addAttribute("statsFromUserInputDraw", namesService.generateStatisticsFromUserInputDraw());
        model.addAttribute("boyStatsFromPropositionListDraw", namesService.generateStatisticsFromPropositionListDrawBySex(Sex.BOY));
        model.addAttribute("girlStatsFromPropositionListDraw", namesService.generateStatisticsFromPropositionListDrawBySex(Sex.GIRL));
        return "statistics";
    }

    @GetMapping(value = "/stats/drawResult", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> namesPdf() {
        return namesService.generatePdf();
    }

    @GetMapping("/clearUserStats")
    public String clearUserStats() {
        namesManager.removeNamesDrawnFromUserInput();
        return "redirect:/stats";
    }

    @GetMapping("/clearBoyNames")
    public String clearBoyNamesStatsDrawnFromPropositionList() {
        namesManager.filterNamesDrawnFromPropositionListBySex(Sex.BOY);
        return "redirect:/stats";
    }

    @GetMapping("/clearGirlNames")
    public String clearGirlNamesStatsDrawnFromPropositionList() {
        namesManager.filterNamesDrawnFromPropositionListBySex(Sex.GIRL);
        return "redirect:/stats";
    }

    @GetMapping("/deleteName/{firstName}")
    public String deleteNameDrawnFromPropositionList(@PathVariable(name = "firstName") String name) {
        namesManager.deleteNameDrawnFromPropositionList(name);
        return "redirect:/stats";
    }

}
