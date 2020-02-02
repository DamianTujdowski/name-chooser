package pl.imionator.imionator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        return "nameslist";
    }

    @PostMapping("/names")
    public String saveName(Name name) {
        namesService.saveNameToInputList(name);
        return "redirect:/names";
    }

    @GetMapping("/result")
    public String drawResult(Model model) {
        Name name = namesService.nameDrawer();
        model.addAttribute("name", name);
        namesService.saveNameToDrawingList(name);
        return "drawnname";
    }

//    @PostMapping("/result")
//    public String drawAnothernameFromUserList(Name name) {
//        namesService.saveNameToDrawingList(name);
//        return "drawnname";
//    }

    @GetMapping("/stats")
    public String drawStats(Model model) {
        model.addAttribute("statistics", namesService.namesDrawStats());
        return "statistics";
    }
}
