package ru.wintermute.postal_service.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.web.context.WebApplicationContext;
import ru.wintermute.postal_service.models.Postage;
import ru.wintermute.postal_service.models.PostageHistoryEntity;
import ru.wintermute.postal_service.services.MailService;
import ru.wintermute.postal_service.services.WarehouseService;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class MailControllerIT {

    private static final int ID = 1;
    @Mock
    private MailService mailService;
    @Mock
    private WarehouseService warehouseService;

    @InjectMocks
    private MailController mailController;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
       // mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc = MockMvcBuilders.standaloneSetup(mailController).build();
    }
    @Test
    void shouldCreateMockMvc() {
        Assertions.assertNotNull(mockMvc);
    }
//    @Test
//    void shouldReturnListOfPostages() throws Exception {
//        List<Postage> postages = new ArrayList<>();
//        final Model model = Mockito.mock(Model.class);
//        Mockito.when(mailService.findAll()).thenReturn(postages);
//
//        this.mockMvc.perform(MockMvcRequestBuilders.get("/mail"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.view().name("postages/index"))
//                .andExpect(MockMvcResultMatchers.model().attributeExists("postages"));
//    }
    @Test
    void shouldCreatePostage() throws Exception {
       // final Postage postage = Mockito.mock(Postage.class);

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/mail"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }
    @Test
    void shouldReturnTrackHistory() throws Exception {
        List<PostageHistoryEntity> entities = new ArrayList<>();
        Mockito.when(mailService.getHistory(ID)).thenReturn(entities);

        this.mockMvc.perform(MockMvcRequestBuilders.get("/mail/" + ID))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("postages/show"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("entities"));

    }

}
