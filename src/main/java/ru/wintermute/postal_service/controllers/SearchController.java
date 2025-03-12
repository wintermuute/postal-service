package ru.wintermute.postal_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.wintermute.postal_service.models.Postage;
import ru.wintermute.postal_service.services.MailService;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {
    private final MailService mailService;

    @Autowired
    public SearchController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping()
    public String index(Model model) {
        return listByPage(1,null,model);
    }

    @GetMapping("/page/{pageNumber}")
    public String listByPage(@PathVariable("pageNumber") int pageNumber, @RequestParam(name = "trackNumber", required = false) String trackNumber, Model model) {
        Page<Postage> found = mailService.findAll(pageNumber,trackNumber);

            model.addAttribute("currentPage", pageNumber);
            model.addAttribute("trackNumber", trackNumber);
            model.addAttribute("postages", found.getContent());
            model.addAttribute("totalPages", found.getTotalPages());
            model.addAttribute("totalItems", found.getTotalElements());
        return "search/index";
    }


}
