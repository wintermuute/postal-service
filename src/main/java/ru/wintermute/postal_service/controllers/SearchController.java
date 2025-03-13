package ru.wintermute.postal_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.wintermute.postal_service.models.Postage;
import ru.wintermute.postal_service.models.Status;
import ru.wintermute.postal_service.services.MailService;
import ru.wintermute.postal_service.services.WarehouseService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

//TODO добавить фильтрацию по складу, сортировку по дате
//сначала сделаю, чтобы все работало, потом DTO добавлю

@Controller
@RequestMapping("/search")
public class SearchController {

    private static final int POSTAGES_PER_PAGE = 2;
    private final MailService mailService;
    private final WarehouseService warehouseService;

    @Autowired
    public SearchController(MailService mailService, WarehouseService warehouseService) {
        this.mailService = mailService;
        this.warehouseService = warehouseService;
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

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {

        Postage postage = mailService.findOne(id);
        model.addAttribute("postage", postage);

        Status[] values = Arrays.copyOfRange(Status.values(),1,Status.values().length);
        model.addAttribute("statuses", values);

        return "search/edit";
    }

    @PatchMapping("/{id}/ship")
    public String ship(@PathVariable("id") int id, @RequestParam("trackNumber") String trackNumber,
                       @RequestParam("timeOfCreation") LocalDateTime timeOfCreation,
                       @RequestParam("currentWarehouse") int currentWarehouse,
                       @RequestParam("weight") double weight,
                       @RequestParam("price") double price,
                       @RequestParam("status") int statusId,
                       @RequestParam("comment") String comment) {
        Postage updatedPostage = new Postage();
        updatedPostage.setTrackNumber(trackNumber);
        updatedPostage.setTimeOfCreation(timeOfCreation);
        //тут баг с нулловым складом
        updatedPostage.setCurrentWarehouse(warehouseService.findOne(currentWarehouse));
        updatedPostage.setWeight(weight);
        updatedPostage.setPrice(price);
        updatedPostage.setStatus(Status.values()[statusId]);
        updatedPostage.resolveStatus(updatedPostage.getStatus());
        updatedPostage.detectTimeArrived();
        updatedPostage.setComment(comment);
        mailService.update(id,updatedPostage);

        return "redirect:/search/{id}";
    }


}
