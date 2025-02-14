package ru.wintermute.postal_service.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.wintermute.postal_service.models.Postage;

@Controller
@RequestMapping("/mail")
public class MailController {

    @GetMapping()
    public String mailPage() {
        return "mail/add";
    }
    @PostMapping()
    public String addPostage(@ModelAttribute("postage") Postage postage) {

        return "redirect:/mail";

    }

}
