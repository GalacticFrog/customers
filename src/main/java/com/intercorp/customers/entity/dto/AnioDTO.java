
package com.intercorp.customers.entity.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 *
 * @author USUARIO
 */
@JsonPropertyOrder({ "anio", "cantidad" })
public interface AnioDTO {

    String getAnio();

    Integer getCantidad();

}
