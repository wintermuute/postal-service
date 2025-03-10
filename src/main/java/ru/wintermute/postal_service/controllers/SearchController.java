package ru.wintermute.postal_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.wintermute.postal_service.models.Postage;
import ru.wintermute.postal_service.services.MailService;

@Controller
@RequestMapping("/search")
public class SearchController {
    private final MailService mailService;

    @Autowired
    public SearchController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping()
    public String index() {
        return "search/index";
    }

    @PostMapping()
    public String result(@RequestParam("trackNumber") String trackNumber, Model model) {
        Postage found = mailService.findByTrackNumber(trackNumber);
        model.addAttribute("found", found);
        return "search/result";
    }
}
