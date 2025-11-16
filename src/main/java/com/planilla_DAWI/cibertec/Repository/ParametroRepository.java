package com.planilla_DAWI.cibertec.Repository;

import com.planilla_DAWI.cibertec.Entity.Parametro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParametroRepository extends JpaRepository<Parametro, Integer> {
    @Query(value = """
            SELECT * FROM Parametros WHERE Activo = 1 LIMIT 1
            """, nativeQuery = true)
    public Parametro findParametro ();
}
