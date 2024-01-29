package com.baraki.librarymanagement.service;

import com.baraki.librarymanagement.model.BorrowingRecord;
import com.baraki.librarymanagement.repository.BorrowingRecordRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BorrowingRecordServiceTest {

    @Mock
    private BorrowingRecordRepository borrowingRecordRepository;

    @InjectMocks
    private BorrowingRecordService borrowingRecordService;

    private BorrowingRecord borrowingRecord;

    @BeforeEach
    public void setUp() {
        borrowingRecord = new BorrowingRecord();
    }

    @Test
    public void whenFindAllBorrowingRecords_thenBorrowingRecordListShouldBeReturned() {
        when(borrowingRecordRepository.findAll()).thenReturn(Arrays.asList(borrowingRecord));

        List<BorrowingRecord> result = borrowingRecordService.findAllBorrowingRecords();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(borrowingRecord, result.get(0));
    }

    @Test
    public void whenFindBorrowingRecordById_thenBorrowingRecordShouldBeReturned() {
        when(borrowingRecordRepository.findById(anyLong())).thenReturn(Optional.of(borrowingRecord));

        Optional<BorrowingRecord> result = borrowingRecordService.findBorrowingRecordById(1L);

        assertTrue(result.isPresent());
        assertEquals(borrowingRecord, result.get());
    }

    @Test
    public void whenSaveBorrowingRecord_thenBorrowingRecordShouldBeSaved() {
        when(borrowingRecordRepository.save(any(BorrowingRecord.class))).thenReturn(borrowingRecord);

        BorrowingRecord result = borrowingRecordService.saveBorrowingRecord(borrowingRecord);

        assertNotNull(result);
        assertEquals(borrowingRecord, result);
    }

    @Test
    public void whenDeleteBorrowingRecord_thenBorrowingRecordShouldBeDeleted() {
        doNothing().when(borrowingRecordRepository).deleteById(anyLong());

        borrowingRecordService.deleteBorrowingRecord(1L);

        verify(borrowingRecordRepository, times(1)).deleteById(1L);
    }
}