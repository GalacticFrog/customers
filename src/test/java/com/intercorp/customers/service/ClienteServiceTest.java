/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intercorp.customers.service;

import com.intercorp.customers.entity.Cliente;
import com.intercorp.customers.exception.ResourceNotFoundException;
import com.intercorp.customers.repository.ClienteRepository;
import com.intercorp.customers.service.impl.ClienteServiceImpl;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

/**
 *
 * @author USUARIO
 */
@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    private ProjectionFactory factory = new SpelAwareProxyProjectionFactory();

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    private Cliente cliente;

    @BeforeEach
    void setup() {
        cliente = Cliente.builder()
                .nombres("Gustavo")
                .apellidos("GOMEZ LAGOS")
                .email("email00280@mail.com")
                .dni("71524571")
                .fechaCreacion(new Date())
                .fechaNacimiento(new Date())
                .build();
    }

    @Test
    void testGuardarCliente() {

        given(clienteRepository.findByDni(cliente.getDni()))
                .willReturn(Optional.empty());
        given(clienteRepository.save(cliente)).willReturn(cliente);

        Cliente guardado = clienteService.crear(cliente);

        assertThat(guardado).isNotNull();
    }

    @Test
    void testGuardarClienteConThrowException() {

        given(clienteRepository.findByDni(cliente.getDni()))
                .willReturn(Optional.of(cliente));

        assertThrows(ResourceNotFoundException.class, () -> {
            clienteService.crear(cliente);
        });

        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    @Test
    void testListarClientes() {

        Cliente cliente2 = Cliente.builder()
                .nombres("Frank")
                .apellidos("SILVA DIPAS")
                .email("email100@mail.com")
                .dni("70707777")
                .fechaCreacion(new Date())
                .fechaNacimiento(new Date())
                .build();
        given(clienteRepository.findAll()).willReturn(List.of(cliente, cliente2));

        List<Cliente> clientes = clienteService.listar();

        assertThat(clientes).isNotNull();
        assertThat(clientes.size()).isEqualTo(2);
    }

    @Test
    void testListarColeccionVacia() {

        given(clienteRepository.findAll()).willReturn(Collections.emptyList());

        List<Cliente> listaClientes = clienteService.listar();

        assertThat(listaClientes).isEmpty();
        assertThat(listaClientes.size()).isEqualTo(0);
    }

    @Test
    void testGetClienteByDni() {

        given(clienteRepository.findByDni("71524571")).willReturn(Optional.of(cliente));

        Cliente guardado = clienteService.getClienteByDni(cliente.getDni()).get();

        assertThat(guardado).isNotNull();
    }

    @Test
    void testGetClienteByEmail() {

        given(clienteRepository.findByEmail("email00280@mail.com")).willReturn(Optional.of(cliente));

        Cliente guardado = clienteService.getClienteByEmail(cliente.getEmail()).get();

        assertThat(guardado).isNotNull();
    }
}
