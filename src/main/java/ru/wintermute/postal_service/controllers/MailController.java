package ru.wintermute.postal_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.wintermute.postal_service.models.Postage;
import ru.wintermute.postal_service.models.PostageHistoryEntity;
import ru.wintermute.postal_service.models.Status;
import ru.wintermute.postal_service.models.Warehouse;
import ru.wintermute.postal_service.services.MailService;
import ru.wintermute.postal_service.services.WarehouseService;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@RestController
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
    public ResponseEntity<List<Postage>> index(){
        List<Postage> postages = mailService.findAll();
        return new ResponseEntity<>(postages, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<PostageHistoryEntity>> show(@PathVariable("id") int id){
       List<PostageHistoryEntity> entities = mailService.getHistory(id);
       return new ResponseEntity<>(entities,HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Postage> addPostage(@RequestBody Postage postage) {

        postage.generateTrackNumber();
        postage.calculatePrice();
        postage.setStatus(Status.NEW);
        postage.detectCreationTime();

        return new ResponseEntity<>(mailService.save(postage),HttpStatus.OK);

    }


}
