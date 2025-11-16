package com.planilla_DAWI.cibertec.Repository;

import com.planilla_DAWI.cibertec.Entity.TipoDocumento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoDocumentoRepository extends JpaRepository<TipoDocumento, Integer>, TipoDocumentoRepositoryCustom {

    @Query("SELECT t FROM TipoDocumento t WHERE " +
            "(:estado = 2 OR t.activo = CASE WHEN :estado = 1 THEN true ELSE false END) " +
            "AND (:texto IS NULL OR :texto = '' OR LOWER(t.nombre) LIKE LOWER(CONCAT('%', :texto, '%')))")
    Page<TipoDocumento> findByEstado(@Param("estado") int estado, @Param("texto") String texto, Pageable pageable);
}
