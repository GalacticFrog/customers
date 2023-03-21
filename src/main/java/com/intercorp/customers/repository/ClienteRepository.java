
package com.intercorp.customers.repository;

import com.intercorp.customers.entity.Cliente;
import com.intercorp.customers.entity.dto.NatalidadDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.intercorp.customers.entity.dto.MesAndAnioDTO;
import java.util.Optional;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author USUARIO
 */
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByDni(String dni);
    
    Optional<Cliente> findByEmail(String email);    
    
    @Query(value = "SELECT YEAR(fecha_nacimiento) anio, MONTH(fecha_nacimiento) mes, COUNT(*) cantidad FROM cliente GROUP BY YEAR(fecha_nacimiento), MONTH(fecha_nacimiento)", nativeQuery = true)
    List<MesAndAnioDTO> porMesAndAnio();    
    
    @Query(value = "SELECT f1.anio, f1.mes, f1.cantidad " +
                   "FROM ( SELECT YEAR(fecha_nacimiento) anio, MONTH(fecha_nacimiento) mes, COUNT(*) cantidad FROM cliente GROUP BY YEAR(fecha_nacimiento), MONTH(fecha_nacimiento) ) f1 " +
                   "JOIN ( SELECT t.anio, MAX(t.cantidad) AS cantidad " +
                   "       FROM ( SELECT YEAR(fecha_nacimiento) anio, MONTH(fecha_nacimiento) mes, COUNT(*) cantidad FROM cliente GROUP BY YEAR(fecha_nacimiento), MONTH(fecha_nacimiento) ) t " +
                   "       GROUP BY anio ) f2 " +
                   "ON f1.anio = f2.anio AND f1.cantidad = f2.cantidad " +
                   "ORDER BY f1.anio DESC", nativeQuery = true)
    List<MesAndAnioDTO> nacimientosMesAnioMayor();
    
    @Query(value = "SELECT f1.anio, f1.mes, f1.cantidad " +
                   "FROM ( SELECT YEAR(fecha_nacimiento) anio, MONTH(fecha_nacimiento) mes, COUNT(*) cantidad FROM cliente GROUP BY YEAR(fecha_nacimiento), MONTH(fecha_nacimiento) ) f1 " +
                   "JOIN ( SELECT t.anio, MIN(t.cantidad) AS cantidad " +
                   "       FROM ( SELECT YEAR(fecha_nacimiento) anio, MONTH(fecha_nacimiento) mes, COUNT(*) cantidad FROM cliente GROUP BY YEAR(fecha_nacimiento), MONTH(fecha_nacimiento) ) t " +
                   "       GROUP BY anio ) f2 " +
                   "ON f1.anio = f2.anio AND f1.cantidad = f2.cantidad " +
                   "ORDER BY f1.anio DESC;", nativeQuery = true)
    List<MesAndAnioDTO> nacimientosMesAnioMenor();
        
    @Query(value = "SELECT t2.mes, t2.cantidad, 1000 * t2.cantidad / (SELECT  COUNT(*) FROM cliente) tasa "
                 + "FROM ( SELECT MONTH(fecha_nacimiento) mes, COUNT(*) cantidad FROM cliente GROUP BY MONTH(fecha_nacimiento) ) t2;", nativeQuery = true)
    List<NatalidadDTO> tasaNatalidad();      
    
}
