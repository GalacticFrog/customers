/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intercorp.customers.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.intercorp.customers.entity.Cliente;
import com.intercorp.customers.service.ClienteService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author USUARIO
 */
@WebMvcTest
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClienteService clienteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCrearCliente() throws Exception {

        Cliente cliente = Cliente.builder()
                .nombres("Lida")
                .apellidos("PRADO NAJARRO")
                .email("emai8800@mail.com")
                .dni("75707777")
                .fechaCreacion(new Date())
                .fechaNacimiento(new Date())
                .build();
        given(clienteService.crear(any(Cliente.class)))
                .willAnswer((invocation) -> invocation.getArgument(0));

        ResultActions response = mockMvc.perform(post("/customer/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(cliente)));

        response.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombres", is(cliente.getNombres())))
                .andExpect(jsonPath("$.apellidos", is(cliente.getApellidos())))
                .andExpect(jsonPath("$.email", is(cliente.getEmail())))
                .andExpect(jsonPath("$.dni", is(cliente.getDni())));
    }

    @Test
    void testListarClientes() throws Exception {

        List<Cliente> listaEmpleados = new ArrayList<>();
        listaEmpleados.add(Cliente.builder().nombres("Christian").apellidos("Ramirez").email("c1@gmail.com").dni("00000001").build());
        listaEmpleados.add(Cliente.builder().nombres("Gabriel").apellidos("Ramirez").email("g1@gmail.com").dni("00000002").build());
        listaEmpleados.add(Cliente.builder().nombres("Julen").apellidos("Ramirez").email("cj@gmail.com").dni("00000003").build());
        listaEmpleados.add(Cliente.builder().nombres("Biaggio").apellidos("Ramirez").email("b1@gmail.com").dni("00000004").build());
        listaEmpleados.add(Cliente.builder().nombres("Adrian").apellidos("Ramirez").email("a@gmail.com").dni("00000005").build());
        given(clienteService.listar()).willReturn(listaEmpleados);

        ResultActions response = mockMvc.perform(get("/customer/listar"));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()", is(listaEmpleados.size())));
    }

    @Test
    void testGetClienteByDni() throws Exception {

        String dni = "73963112";
        Cliente cliente = Cliente.builder()
                .nombres("Dafne")
                .apellidos("RODRIGUEZ HUARANCCA")
                .email("mail009@mail.com")
                .dni("73963112")
                .build();
        given(clienteService.getClienteByDni(dni)).willReturn(Optional.of(cliente));

        ResultActions response = mockMvc.perform(get("/customer/dni/{dni}", dni));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.nombres", is(cliente.getNombres())))
                .andExpect(jsonPath("$.apellidos", is(cliente.getApellidos())))
                .andExpect(jsonPath("$.email", is(cliente.getEmail())))
                .andExpect(jsonPath("$.dni", is(cliente.getDni())));
    }

    @Test
    void testGetClienteByEmail() throws Exception {
    
        String email = "mail003@mail.com";
        Cliente cliente = Cliente.builder()
                .nombres("Ana Maria")
                .apellidos("HUAMANI HUAMAN")
                .email("mail009@mail.com")
                .dni("74568602")
                .build();
        given(clienteService.getClienteByEmail(email)).willReturn(Optional.of(cliente));

        ResultActions response = mockMvc.perform(get("/customer/email/{email}", email));

        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.nombres", is(cliente.getNombres())))
                .andExpect(jsonPath("$.apellidos", is(cliente.getApellidos())))
                .andExpect(jsonPath("$.email", is(cliente.getEmail())))
                .andExpect(jsonPath("$.dni", is(cliente.getDni())));
    }
}
