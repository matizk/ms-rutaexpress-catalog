package com.rutaexpress.catalogo.repositories;

import com.rutaexpress.catalogo.entities.Catalogo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogoRepository extends JpaRepository<Catalogo, Long> {
    
}
