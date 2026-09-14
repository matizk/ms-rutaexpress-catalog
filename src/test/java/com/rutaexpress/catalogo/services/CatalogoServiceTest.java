package com.rutaexpress.catalogo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rutaexpress.catalogo.entities.Catalogo;
import com.rutaexpress.catalogo.repositories.CatalogoRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
class CatalogoServiceTest {

    @Mock
    private CatalogoRepository catalogoRepository;

    private CatalogoService catalogoService;

    @BeforeEach
    void setUp() {
        catalogoService = new CatalogoService(catalogoRepository);
    }

    @Test
    void decreasesAvailableCapacityWhenServiceIsAccepted() {
        Catalogo catalogo = new Catalogo(1L, "Express", "Entrega prioritaria", 4990, 2);
        when(catalogoRepository.findById(1L)).thenReturn(Optional.of(catalogo));
        when(catalogoRepository.save(catalogo)).thenReturn(catalogo);

        Catalogo updated = catalogoService.disminuirCapacidad(1L);

        assertEquals(1, updated.getCantidadDisponible());
        verify(catalogoRepository).save(catalogo);
    }

    @Test
    void rejectsDecreaseWhenThereIsNoAvailableCapacity() {
        Catalogo catalogo = new Catalogo(1L, "Express", "Entrega prioritaria", 4990, 0);
        when(catalogoRepository.findById(1L)).thenReturn(Optional.of(catalogo));

        assertThrows(ResponseStatusException.class, () -> catalogoService.disminuirCapacidad(1L));
    }
}
