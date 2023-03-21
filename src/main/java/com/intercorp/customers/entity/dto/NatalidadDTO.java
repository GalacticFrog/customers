
package com.intercorp.customers.entity.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 *
 * @author USUARIO
 */

@JsonPropertyOrder({"mes", "cantidad", "tasa" })
public interface NatalidadDTO {
    
    String getMes();

    Integer getCantidad();
    
    Double getTasa();
    
}
