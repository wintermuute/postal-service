package ru.wintermute.postal_service.controllers;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import ru.wintermute.postal_service.models.Postage;
import ru.wintermute.postal_service.models.Warehouse;
import ru.wintermute.postal_service.services.MailService;
import ru.wintermute.postal_service.services.WarehouseService;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class WarehouseControllerTest {

    @Mock
    private WarehouseService warehouseService;
    @Mock
    private MailService mailService;

    private static final int ID = 1;

    @InjectMocks
    private WarehouseController warehouseController;

    @Test
    void mainPageShouldCallService() {
        List<Warehouse> warehouses = new ArrayList<>();
        final Model model = Mockito.mock(Model.class);
        Mockito.when(warehouseService.findAll()).thenReturn(warehouses);

        String url = "Warehouses/main";
        String actual = warehouseController.mainPage(model);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(url,actual);
        Mockito.verify(warehouseService).findAll();

    }
    @Test
    void showOneShouldCallTwoServices() {

        final Model model = Mockito.mock(Model.class);
        Warehouse warehouse = Mockito.mock(Warehouse.class);
        List<Postage> postages = new ArrayList<>();
        Mockito.when(warehouseService.findOne(ID)).thenReturn(warehouse);
        Mockito.when(mailService.getPostagesInStore(ID)).thenReturn(postages);

        String url = "Warehouses/show";
        String actual = warehouseController.showOne(ID,model);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(url,actual);
        Mockito.verify(warehouseService).findOne(ID);
        Mockito.verify(mailService).getPostagesInStore(ID);

    }
}
