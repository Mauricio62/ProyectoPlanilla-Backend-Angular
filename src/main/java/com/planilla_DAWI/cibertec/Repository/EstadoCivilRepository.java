package com.planilla_DAWI.cibertec.Repository;

import com.planilla_DAWI.cibertec.Entity.EstadoCivil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoCivilRepository extends JpaRepository<EstadoCivil, Integer>, EstadoCivilRepositoryCustom {

    @Query("SELECT e FROM EstadoCivil e WHERE " +
            "(:estado = 2 OR e.activo = CASE WHEN :estado = 1 THEN true ELSE false END) " +
            "AND (:texto IS NULL OR :texto = '' OR LOWER(e.nombre) LIKE LOWER(CONCAT('%', :texto, '%')))")
    Page<EstadoCivil> findByEstado(@Param("estado") int estado, @Param("texto") String texto, Pageable pageable);
}
