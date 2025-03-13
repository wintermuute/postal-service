package ru.wintermute.postal_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.wintermute.postal_service.models.Postage;
import ru.wintermute.postal_service.services.MailService;

import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

    private static final int POSTAGES_PER_PAGE = 2;
    private final MailService mailService;

    @Autowired
    public SearchController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping()
    public String listByPage(@RequestParam(name = "pageNumber", defaultValue = "1") int pageNumber, @RequestParam(name = "trackNumber", required = false) String trackNumber, Model model) {
        Page<Postage> found = mailService.findAll(PageRequest.of(pageNumber - 1,POSTAGES_PER_PAGE),trackNumber);

            model.addAttribute("currentPage", pageNumber);
            model.addAttribute("trackNumber", trackNumber);
            model.addAttribute("postages", found.getContent());
            model.addAttribute("totalPages", found.getTotalPages());
            model.addAttribute("totalItems", found.getTotalElements());
       return "search/index";

    }


}
