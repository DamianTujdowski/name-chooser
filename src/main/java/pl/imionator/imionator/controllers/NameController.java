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
import pl.imionator.imionator.repository.NamesRepository;
import pl.imionator.imionator.services.NamesService;
import pl.imionator.imionator.utils.PdfGenerator;

import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.util.Objects;

@Controller
public class NameController {

    private NamesService namesService;

    private NamesRepository namesRepository;

    private PdfGenerator pdfGenerator;

    public NameController(NamesService namesService, NamesRepository namesRepository, PdfGenerator pdfGenerator) {
        this.namesService = namesService;
        this.namesRepository = namesRepository;
        this.pdfGenerator = pdfGenerator;
    }

    @GetMapping("/names")
    public String namesList(Model model) {
        model.addAttribute("namesGivenByUser", namesRepository.getUserInput());
        model.addAttribute("namesDrawnFromUserInput", namesRepository.getNamesDrawnFromUserInput());
        model.addAttribute("name", new pl.imionator.imionator.domain.Name());
        return "index";
    }

    @PostMapping("/names")
    public String saveName(@Valid pl.imionator.imionator.domain.Name name, BindingResult bindingResult, Model model) {
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
        pl.imionator.imionator.domain.Name name = namesService.drawNameFromUserInput();
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
        model.addAttribute("boyStatsFromPropositionListDraw",
                namesService.generateStatisticsFromPropositionListDraw(name -> name != null && name.getSex() == Sex.BOY));
        model.addAttribute("girlStatsFromPropositionListDraw",
                namesService.generateStatisticsFromPropositionListDraw(name -> name != null && name.getSex() == Sex.GIRL));
        return "statistics";
    }

    @GetMapping("/clearUserStats")
    public String clearUserStats() {
        namesRepository.clearNamesDrawnFromUserInput();
        return "redirect:/stats";
    }

    @GetMapping("/clearBoyNames")
    public String clearBoyNamesStatsDrawnFromPropositionList() {
        namesRepository.clearBoyNamesDrawnFromPropositionList();
        return "redirect:/stats";
    }

    @GetMapping("/clearGirlNames")
    public String clearGirlNamesStatsDrawnFromPropositionList() {
        namesRepository.clearGirlNamesDrawnFromPropositionList();
        return "redirect:/stats";
    }

    @GetMapping("/deleteName/{firstName}")
    public String deleteNameDrawnFromPropositionList(@PathVariable(name = "firstName") String name) {
        namesRepository.deleteNameDrawnFromPropositionList(name);
        return "redirect:/stats";
    }

    @GetMapping(value = "/stats/drawResult", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> namesPdf() {
        ByteArrayInputStream byteArrayInputStream = pdfGenerator.generatePdf(namesService.generateStatisticsFromUserInputDraw(),
                namesService.generateStatisticsFromPropositionListDraw(Objects::nonNull));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=drawnnames.pdf");
        return ResponseEntity.ok()
                .header(String.valueOf(headers))
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(byteArrayInputStream));
    }

}
