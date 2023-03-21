package com.intercorp.customers.service.impl;

import com.intercorp.customers.entity.Cliente;
import com.intercorp.customers.entity.dto.NatalidadDTO;
import com.intercorp.customers.repository.ClienteRepository;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import com.intercorp.customers.entity.dto.MesAndAnioDTO;
import com.intercorp.customers.exception.ResourceNotFoundException;
import com.intercorp.customers.service.ClienteService;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 *
 * @author USUARIO
 */
@Service
public class ClienteServiceImpl implements ClienteService{

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public Optional<Cliente> getClienteByDni(String dni) {
        return clienteRepository.findByDni(dni);
    }

    @Override
    public Optional<Cliente> getClienteByEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    @Override
    public List<Cliente> listar() {

        return clienteRepository.findAll();

    }

    @Override
    public Map<String, List<MesAndAnioDTO>> getMesAndAnio() {
        List<MesAndAnioDTO> mesAndAnio = clienteRepository.porMesAndAnio();

        Map<String, List<MesAndAnioDTO>> studlistGrouped = mesAndAnio.stream().collect(Collectors.groupingBy(w -> w.getAnio()));

        return studlistGrouped;
    }

    @Override
    public List<MesAndAnioDTO> getNacimientosMayor() {

        return clienteRepository.nacimientosMesAnioMayor();

    }

    @Override
    public List<MesAndAnioDTO> getNacimientosMenor() {

        return clienteRepository.nacimientosMesAnioMenor();

    }

    @Override
    public List<NatalidadDTO> getNatalidad() {

        return clienteRepository.tasaNatalidad();

    }

    @Override
    public Cliente crear(Cliente cliente) {
        Optional<Cliente> emailCliente = clienteRepository.findByEmail(cliente.getEmail());
        Optional<Cliente> dniCliente = clienteRepository.findByDni(cliente.getDni());
        if (emailCliente.isPresent()) {
            throw new ResourceNotFoundException("El cliente con ese email ya existe : " + cliente.getEmail());
        }
        if (dniCliente.isPresent()) {
            throw new ResourceNotFoundException("El cliente con ese dni ya existe : " + cliente.getEmail());
        }
        return clienteRepository.save(cliente);
    }
}
