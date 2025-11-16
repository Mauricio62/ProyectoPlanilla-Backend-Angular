
package com.planilla_DAWI.cibertec.Repository;

import com.planilla_DAWI.cibertec.Entity.Trabajador;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador, Integer> {

    @Query("""
       SELECT t FROM Trabajador t
       WHERE (:busqueda IS NULL OR :busqueda = '' OR
              t.apellidoPaterno LIKE %:busqueda% OR
              t.apellidoMaterno LIKE %:busqueda% OR
              t.nombres LIKE %:busqueda% OR
              t.documento LIKE %:busqueda%)
         AND (:estado = 2 OR (:estado = 1 AND t.activo = true) OR (:estado = 0 AND t.activo = false))
       """)
    Page<Trabajador> buscarTrabajadores(@Param("busqueda") String busqueda,
                                        @Param("estado") int estado, Pageable pageable);

    @Modifying
    @Transactional
    @Query("""
       UPDATE Trabajador t
       SET t.activo = CASE WHEN t.activo = true THEN false ELSE true END,
           t.fecUltimaModificacion = CURRENT_TIMESTAMP
       WHERE t.idTrabajador = :id
       """)
    int cambiarEstado(@Param("id") Integer id);

    @Query(value = """
            SELECT * FROM Trabajadores WHERE Activo = true
            """,nativeQuery = true)
    public List<Trabajador> listarTrabajadoresActivos ();

}
