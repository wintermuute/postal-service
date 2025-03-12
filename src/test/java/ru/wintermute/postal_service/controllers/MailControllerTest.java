package ru.wintermute.postal_service.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import ru.wintermute.postal_service.models.Postage;
import ru.wintermute.postal_service.models.PostageHistoryEntity;
import ru.wintermute.postal_service.models.Status;
import ru.wintermute.postal_service.models.Warehouse;
import ru.wintermute.postal_service.services.MailService;
import ru.wintermute.postal_service.services.WarehouseService;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MailControllerTest {

    private static final int ID = 1;
    private static final Status status = Status.NEW;

    @Mock
    private MailService mailService;
    @Mock
    private WarehouseService warehouseService;

    @InjectMocks
    private MailController mailController;

    private ObjectMapper objectMapper;

    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(mailController).build();
    }
    @Test
    void indexTest() throws Exception {
        mockMvc.perform(get("/mail")).andExpect(status().isOk());
    }
//    @Test
//    void indexShouldCallService() {
//        Page<Postage> postages = null;
//        final Model model = Mockito.mock(Model.class);
//        Mockito.when(mailService.findAll(1,"1")).thenReturn(postages);
//        //Mockito.when(model.addAttribute("postages",postages)) -????????
//        String url = "postages/index";
//
//        String actual = mailController.index(model);
//
//        Assertions.assertNotNull(actual);
//        Assertions.assertEquals(url,actual);
//        Mockito.verify(mailService).findAll();
//
//    }
    @Test
    void showTest() throws Exception {
        mockMvc.perform(get("/mail/add")).andExpect(status().isOk());
    }
    @Test
    void showShouldCallService() {
        List<PostageHistoryEntity> entities = new ArrayList<>();
        final Model model = Mockito.mock(Model.class);
        Mockito.when(mailService.getHistory(ID)).thenReturn(entities);
        String url = "postages/show";

        String actual = mailController.show(ID,model);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(url,actual);
        Mockito.verify(mailService).getHistory(ID);
    }
    @Test
    void fillShouldCallService() {
        String url = "Postages/add";
        final Model model = Mockito.mock(Model.class);
        List<Warehouse> warehouses = new ArrayList<>();
        Mockito.when(warehouseService.findAll()).thenReturn(warehouses);

        String actual = mailController.fill(model);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(url,actual);
        Mockito.verify(warehouseService).findAll();
    }

    @Test
    void addPostageShouldCallALot() {
        final Postage postage = Mockito.mock(Postage.class);
        String url = "redirect:/mail";

        String actual = mailController.addPostage(postage);

        Assertions.assertNotNull(actual);
        Assertions.assertEquals(url,actual);
        Mockito.verify(postage).generateTrackNumber();
        Mockito.verify(postage).calculatePrice();
        Mockito.verify(postage).setStatus(status);
        Mockito.verify(postage).detectCreationTime();

        Mockito.verify(mailService).save(postage);
    }

}
