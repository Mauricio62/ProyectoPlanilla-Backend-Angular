package com.planilla_DAWI.cibertec.Repository;

import com.planilla_DAWI.cibertec.Entity.Cargo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Integer>, CargoRepositoryCustom {

    @Query("SELECT c FROM Cargo c " +
            "WHERE (:estado = 2 OR c.activo = CASE WHEN :estado = 1 THEN true ELSE false END) " +
            "AND (:texto IS NULL OR :texto = '' OR LOWER(c.nombre) LIKE LOWER(CONCAT('%', :texto, '%')))")
    Page<Cargo> findByEstado(@Param("estado") int estado,@Param("texto") String Texto, Pageable pageable);
    
    @Query("SELECT c FROM Cargo c WHERE (:estado = 2 OR c.activo = CASE WHEN :estado = 1 THEN true ELSE false END)")
    List<Cargo> findByEstado(@Param("estado") int estado);
}