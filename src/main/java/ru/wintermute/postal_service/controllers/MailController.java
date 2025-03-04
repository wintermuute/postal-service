package ru.wintermute.postal_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.wintermute.postal_service.models.Postage;
import ru.wintermute.postal_service.services.MailService;

@Controller
@RequestMapping("/mail")
public class MailController {

    private final MailService mailService;

    @Autowired
    public MailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/add")
    public String mailPage() {
        return "Postages/add";
    }
    @PostMapping()
    public String addPostage(@ModelAttribute("postage") Postage postage) {
        postage.generateTrackNumber();
        postage.calculatePrice();
        mailService.save(postage);
        return "redirect:/mail";

    }

}
