
package com.intercorp.customers.entity.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 *
 * @author USUARIO
 */
@JsonPropertyOrder({ "mes", "cantidad" })
public interface MesDTO {

    String getMes();

    Integer getCantidad();

}
