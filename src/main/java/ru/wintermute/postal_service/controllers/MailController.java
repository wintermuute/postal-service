package ru.wintermute.postal_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.wintermute.postal_service.models.Postage;
import ru.wintermute.postal_service.models.PostageHistoryEntity;
import ru.wintermute.postal_service.models.Status;
import ru.wintermute.postal_service.models.Warehouse;
import ru.wintermute.postal_service.services.MailService;
import ru.wintermute.postal_service.services.WarehouseService;

import java.util.List;

@Controller
@RequestMapping("/mail")
public class MailController {

    private final MailService mailService;
    private final WarehouseService warehouseService;

    @Autowired
    public MailController(MailService mailService, WarehouseService warehouseService) {
        this.mailService = mailService;
        this.warehouseService = warehouseService;
    }

    @GetMapping()
    public String index(Model model){
        List<Postage> postages = mailService.findAll();
        model.addAttribute("postages", postages);
        return "postages/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model){
       List<PostageHistoryEntity> entities = mailService.getHistory(id);
       model.addAttribute("entities", entities);
       return "postages/show";
    }

    @GetMapping("/add")
    public String fill(Model model) {
        model.addAttribute("postage",new Postage());
        List<Warehouse> warehouses = warehouseService.findAll();
        model.addAttribute("warehouses", warehouses);
        return "Postages/add";
    }
    @PostMapping()
    public String addPostage(@ModelAttribute("postage") Postage postage) {

        postage.generateTrackNumber();
        postage.calculatePrice();
        postage.setStatus(Status.NEW);
        postage.detectCreationTime();
        // model.addAttribute("track",postage.getTrackNumber());
        mailService.save(postage);
        return "redirect:/mail";

    }

}
