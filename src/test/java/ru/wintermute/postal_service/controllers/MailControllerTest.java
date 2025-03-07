package ru.wintermute.postal_service.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.wintermute.postal_service.models.Postage;
import ru.wintermute.postal_service.services.MailService;
import ru.wintermute.postal_service.services.WarehouseService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MailControllerTest {

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
    @Test
    void showTest() throws Exception {
        mockMvc.perform(get("/mail/add")).andExpect(status().isOk());
    }
    @Test
    void addPostageTest() throws Exception {
        Postage postage = new Postage();
        String postageSerialized = objectMapper.writeValueAsString(postage);
        mockMvc.perform(post("/mail")
                .contentType(MediaType.ALL)
                .content(postageSerialized))
                .andExpect(status().is3xxRedirection());

    }

}
