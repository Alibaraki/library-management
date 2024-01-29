package com.baraki.librarymanagement.controllers;

import com.baraki.librarymanagement.model.Patron;
import com.baraki.librarymanagement.service.PatronService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
public class PatronControllerTest {

    private MockMvc mockMvc;

    @Mock
    private PatronService patronService;

    @InjectMocks
    private PatronController patronController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(patronController).build();
    }

    @Test
    public void getAllPatronsTest() throws Exception {
        Patron patron = new Patron(); // Set properties accordingly
        when(patronService.findAllPatrons()).thenReturn(Arrays.asList(patron));

        mockMvc.perform(get("/api/patrons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").isNotEmpty()); // Add more JSON path assertions as needed

        verify(patronService).findAllPatrons();
    }

    @Test
    public void getPatronByIdTest() throws Exception {
        Patron patron = new Patron(); // Set properties accordingly
        when(patronService.findPatronById(1L)).thenReturn(Optional.of(patron));

        mockMvc.perform(get("/api/patrons/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty()); // Add more JSON path assertions as needed

        verify(patronService).findPatronById(1L);
    }

    @Test
    public void createPatronTest() throws Exception {
        Patron patron = new Patron(); // Set properties accordingly
        when(patronService.savePatron(any(Patron.class))).thenReturn(patron);

        mockMvc.perform(post("/api/patrons")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patron)))
                .andExpect(status().isCreated());

        verify(patronService).savePatron(any(Patron.class));
    }

    @Test
    public void updatePatronTest() throws Exception {
        Patron patron = new Patron(); // Set properties accordingly
        when(patronService.updatePatron(eq(1L), any(Patron.class))).thenReturn(Optional.of(patron));

        mockMvc.perform(put("/api/patrons/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patron)))
                .andExpect(status().isOk());

        verify(patronService).updatePatron(eq(1L), any(Patron.class));
    }

    @Test
    public void deletePatronTest() throws Exception {
        mockMvc.perform(delete("/api/patrons/{id}", 1))
                .andExpect(status().isOk());

        verify(patronService).deletePatron(1L);
    }
}
