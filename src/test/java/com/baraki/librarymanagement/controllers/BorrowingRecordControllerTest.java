package com.baraki.librarymanagement.controllers;

import com.baraki.librarymanagement.model.BorrowingRecord;
import com.baraki.librarymanagement.service.BorrowingRecordService;
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
public class BorrowingRecordControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BorrowingRecordService borrowingRecordService;

    @InjectMocks
    private BorrowingRecordController borrowingRecordController;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(borrowingRecordController).build();
    }

    @Test
    public void getAllBorrowingRecordsTest() throws Exception {
        BorrowingRecord record = new BorrowingRecord(); // Set properties accordingly
        when(borrowingRecordService.findAllBorrowingRecords()).thenReturn(Arrays.asList(record));

        mockMvc.perform(get("/api/borrowing-records"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]").isNotEmpty()); // Add more JSON path assertions as needed

        verify(borrowingRecordService).findAllBorrowingRecords();
    }

    @Test
    public void getBorrowingRecordByIdTest() throws Exception {
        BorrowingRecord record = new BorrowingRecord(); // Set properties accordingly
        when(borrowingRecordService.findBorrowingRecordById(1L)).thenReturn(Optional.of(record));

        mockMvc.perform(get("/api/borrowing-records/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty()); // Add more JSON path assertions as needed

        verify(borrowingRecordService).findBorrowingRecordById(1L);
    }

    @Test
    public void createBorrowingRecordTest() throws Exception {
        BorrowingRecord record = new BorrowingRecord(); // Set properties accordingly
        when(borrowingRecordService.saveBorrowingRecord(any(BorrowingRecord.class))).thenReturn(record);

        mockMvc.perform(post("/api/borrowing-records")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(record)))
                .andExpect(status().isCreated());

        verify(borrowingRecordService).saveBorrowingRecord(any(BorrowingRecord.class));
    }

    @Test
    public void updateBorrowingRecordTest() throws Exception {
        BorrowingRecord record = new BorrowingRecord(); // Set properties accordingly
        when(borrowingRecordService.saveBorrowingRecord(any(BorrowingRecord.class))).thenReturn(record);

        mockMvc.perform(put("/api/borrowing-records/{id}", record.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(record)))
                .andExpect(status().isOk());

        verify(borrowingRecordService).saveBorrowingRecord(any(BorrowingRecord.class));
    }

    @Test
    public void deleteBorrowingRecordTest() throws Exception {
        mockMvc.perform(delete("/api/borrowing-records/{id}", 1))
                .andExpect(status().isOk());

        verify(borrowingRecordService).deleteBorrowingRecord(1L);
    }
}
