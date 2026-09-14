package com.rutaexpress.catalogo.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.rutaexpress.catalogo.entities.Catalogo;
import com.rutaexpress.catalogo.repositories.CatalogoRepository;

@Service
public class CatalogoService {

    private final CatalogoRepository catalogoRepository;

    public CatalogoService(CatalogoRepository catalogoRepository) {
        this.catalogoRepository = catalogoRepository;
    }

    public List<Catalogo> listar() {
        return catalogoRepository.findAll();
    }

    public Catalogo obtener(Long id) {
        return catalogoRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Servicio de catalogo no encontrado"
                        ));
    }

    public Catalogo guardar(Catalogo catalogo) {
        return catalogoRepository.save(catalogo);
    }

    public void eliminar(Long id) {

        if (!catalogoRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Servicio de catalogo no encontrado"
            );
        }

        catalogoRepository.deleteById(id);
    }

    public Catalogo disminuirCapacidad(Long id) {

        Catalogo catalogo = obtener(id);

        if (catalogo.getCantidadDisponible() == null
                || catalogo.getCantidadDisponible() <= 0) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "No hay capacidad disponible para este servicio"
            );
        }

        catalogo.setCantidadDisponible(
                catalogo.getCantidadDisponible() - 1
        );

        return catalogoRepository.save(catalogo);
    }
}