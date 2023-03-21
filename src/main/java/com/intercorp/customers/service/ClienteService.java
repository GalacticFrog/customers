package com.intercorp.customers.service;

import com.intercorp.customers.entity.Cliente;
import com.intercorp.customers.entity.dto.NatalidadDTO;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.intercorp.customers.entity.dto.MesAndAnioDTO;
import java.util.Optional;

/**
 *
 * @author USUARIO
 */
public interface ClienteService {

    Optional<Cliente> getClienteByDni(String dni);

    Optional<Cliente> getClienteByEmail(String email);

    List<Cliente> listar();

    Map<String, List<MesAndAnioDTO>> getMesAndAnio();

    List<MesAndAnioDTO> getNacimientosMayor();

    List<MesAndAnioDTO> getNacimientosMenor();

    List<NatalidadDTO> getNatalidad();

    Cliente crear(Cliente cliente);
}
