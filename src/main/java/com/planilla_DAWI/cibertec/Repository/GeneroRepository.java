package com.planilla_DAWI.cibertec.Repository;

import com.planilla_DAWI.cibertec.Entity.Genero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepository extends JpaRepository<Genero, Integer>, GeneroRepositoryCustom {

    @Query("SELECT g FROM Genero g WHERE " +
            "(:estado = 2 OR g.activo = CASE WHEN :estado = 1 THEN true ELSE false END) " +
            "AND (:texto IS NULL OR :texto = '' OR LOWER(g.nombre) LIKE LOWER(CONCAT('%', :texto, '%')))")
    Page<Genero> findByEstado(@Param("estado") int estado, @Param("texto") String texto, Pageable pageable);
}
