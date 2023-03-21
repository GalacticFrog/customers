
package com.intercorp.customers.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 *
 * @author USUARIO
 */
@JsonPropertyOrder({"anio", "mes", "cantidad" })
public interface MesAndAnioDTO {
    
    //@JsonIgnore
    String getAnio();

    String getMes();

    Integer getCantidad();
    
}
