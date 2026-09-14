package com.rutaexpress.catalogo.controllers;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rutaexpress.catalogo.entities.Catalogo;
import com.rutaexpress.catalogo.services.CatalogoService;

@RestController
@RequestMapping("/api/catalog/services")
@CrossOrigin(origins = "${app.cors.allowed-origin}")
public class CatalogoController {

    private final CatalogoService catalogoService;

    public CatalogoController(CatalogoService catalogoService) {
        this.catalogoService = catalogoService;
    }

    @GetMapping
    public List<Catalogo> listarCatalogos() {
        return catalogoService.listar();
    }

    @GetMapping("/{id}")
    public Catalogo obtenerCatalogo(@PathVariable Long id) {
        return catalogoService.obtener(id);
    }

    @PostMapping
    public ResponseEntity<Catalogo> crearCatalogo(@Valid @RequestBody Catalogo catalogo) {
        Catalogo creado = catalogoService.guardar(catalogo);
        return ResponseEntity.status(201).body(creado);
    }

    @PutMapping("/{id}")
    public Catalogo actualizarCatalogo(
            @PathVariable Long id,
            @Valid @RequestBody Catalogo catalogo) {

        catalogo.setId(id);

        return catalogoService.guardar(catalogo);
    }

    @PutMapping("/{id}/capacity/decrease")
    public Catalogo disminuirCapacidad(@PathVariable Long id) {
        return catalogoService.disminuirCapacidad(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCatalogo(@PathVariable Long id) {
        catalogoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}
