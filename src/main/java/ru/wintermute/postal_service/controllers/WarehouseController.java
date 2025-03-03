package ru.wintermute.postal_service.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.wintermute.postal_service.models.Warehouse;
import ru.wintermute.postal_service.services.WarehouseService;

import java.util.List;

@Controller
@RequestMapping("/warehouses")
public class WarehouseController {
    private final WarehouseService warehouseService;

    @Autowired
    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }


    @GetMapping()
    public String mainPage(Model model) {
        List<Warehouse> warehouses = warehouseService.findAll();
        model.addAttribute("warehouses",warehouses);
        return "Warehouses/main";
    }
    @GetMapping("/{id}")
    public String showOne(@PathVariable("id") int id, Model model) {
        Warehouse current = warehouseService.findOne(id);
        model.addAttribute("warehouse", current);
        return "Warehouses/show";

    }

    @GetMapping("/add")
    public String addPage() {
        return "Warehouses/add";
    }

    @PostMapping("/add")
    public String addWarehouse(@ModelAttribute("warehouse")Warehouse warehouse) {
        return "redirect:warehouses/main";
    }
}
