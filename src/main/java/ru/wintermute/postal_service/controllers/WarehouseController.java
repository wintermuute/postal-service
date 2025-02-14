package ru.wintermute.postal_service.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.wintermute.postal_service.models.Warehouse;

@Controller
@RequestMapping("/warehouses")
public class WarehouseController {


    @GetMapping()
    public String mainPage() {
        return "warehouses/main";
    }

    @GetMapping("/add")
    public String addPage() {
        return "warehouses/add";
    }

    @PostMapping("/add")
    public String addWarehouse(@ModelAttribute("warehouse")Warehouse warehouse) {
        return "redirect:warehouses/main";
    }
}
