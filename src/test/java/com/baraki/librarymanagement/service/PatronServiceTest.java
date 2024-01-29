package com.baraki.librarymanagement.service;

import com.baraki.librarymanagement.model.Patron;
import com.baraki.librarymanagement.repository.PatronRepository;
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
public class PatronServiceTest {

    @Mock
    private PatronRepository patronRepository;

    @InjectMocks
    private PatronService patronService;

    private Patron patron;

    @BeforeEach
    public void setUp() {
        // Initialize your Patron object here
        patron = new Patron(); // set properties as needed
    }

    @Test
    public void whenFindAllPatrons_thenPatronListShouldBeReturned() {
        when(patronRepository.findAll()).thenReturn(Arrays.asList(patron));

        List<Patron> result = patronService.findAllPatrons();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(patron, result.get(0));
    }

    @Test
    public void whenFindPatronById_thenPatronShouldBeReturned() {
        when(patronRepository.findById(anyLong())).thenReturn(Optional.of(patron));

        Optional<Patron> result = patronService.findPatronById(1L);

        assertTrue(result.isPresent());
        assertEquals(patron, result.get());
    }

    @Test
    public void whenSavePatron_thenPatronShouldBeSaved() {
        when(patronRepository.save(any(Patron.class))).thenReturn(patron);

        Patron result = patronService.savePatron(patron);

        assertNotNull(result);
        assertEquals(patron, result);
    }

    @Test
    public void whenUpdatePatron_thenPatronShouldBeUpdated() {
        when(patronRepository.findById(anyLong())).thenReturn(Optional.of(patron));
        when(patronRepository.save(any(Patron.class))).thenReturn(patron);

        Optional<Patron> result = patronService.updatePatron(1L, patron);

        assertTrue(result.isPresent());
        assertEquals(patron, result.get());
    }

    @Test
    public void whenDeletePatron_thenPatronShouldBeDeleted() {
        doNothing().when(patronRepository).deleteById(anyLong());

        patronService.deletePatron(1L);

        verify(patronRepository, times(1)).deleteById(1L);
    }
}