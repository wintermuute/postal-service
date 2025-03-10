package ru.wintermute.postal_service.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.wintermute.postal_service.models.Postage;
import ru.wintermute.postal_service.models.Status;
import ru.wintermute.postal_service.models.Warehouse;
import ru.wintermute.postal_service.services.MailService;
import ru.wintermute.postal_service.services.WarehouseService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/warehouses")
public class WarehouseController {
    private final WarehouseService warehouseService;
    private final MailService mailService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService, MailService mailService) {
        this.warehouseService = warehouseService;
        this.mailService = mailService;
    }


    @GetMapping()
    public String mainPage(Model model) {
        List<Warehouse> warehouses = warehouseService.findAll();
        model.addAttribute("warehouses",warehouses);
        return "Warehouses/main";
    }

    //CRUD

    @GetMapping("/{id}")
    public String showOne(@PathVariable("id") int id, Model model) {
        Warehouse current = warehouseService.findOne(id);
        List<Postage> postagesIn = mailService.getPostagesInStore(id);
        model.addAttribute("postages", postagesIn);
        model.addAttribute("warehouse", current);
        return "Warehouses/show";

    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        Warehouse current = warehouseService.findOne(id);
        model.addAttribute("warehouse", current);
        return "Warehouses/edit";

    }
    @GetMapping("/instore/{id}")
    public String ship(@PathVariable("id") int id, Model model){

        Postage postage = mailService.findOne(id);
        model.addAttribute("postage", postage);

        Status[] values = Arrays.copyOfRange(Status.values(),1,Status.values().length);
        model.addAttribute("statuses", values);

        return "warehouses/ship";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("warehouse") Warehouse updatedWarehouse ){
        warehouseService.update(id,updatedWarehouse);
        return "redirect:/warehouses";
    }
    @PatchMapping("/instore/{id}")
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
        updatedPostage.setCurrentWarehouse(warehouseService.findOne(currentWarehouse));
        updatedPostage.setWeight(weight);
        updatedPostage.setPrice(price);
        updatedPostage.setStatus(Status.values()[statusId]);
        updatedPostage.resolveStatus(updatedPostage.getStatus());
        updatedPostage.detectTimeArrived();
        updatedPostage.setComment(comment);
        mailService.update(id,updatedPostage);

        return "redirect:/warehouses/" + currentWarehouse;
    }

    @GetMapping("/add")
    public String addPage(Model model) {
        Warehouse warehouse = new Warehouse();
        model.addAttribute("warehouse", warehouse);
        return "Warehouses/add";
    }

    @PostMapping()
    public String addWarehouse(@ModelAttribute("warehouse")Warehouse warehouse) {
        warehouseService.save(warehouse);
        return "redirect:warehouses";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        warehouseService.delete(id);
        return "redirect:/warehouses";
    }
}
