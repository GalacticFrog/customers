/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intercorp.customers.controller;

import com.intercorp.customers.entity.Cliente;
import java.util.List;
import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 *
 * @author USUARIO
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClienteControllerWebClientTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @Order(1)
    void testGuardarEmpleado() {
        //Emily Kaory', 'MENDOZA ZAMORA
        Cliente cliente = Cliente.builder()
                .nombres("Emily")
                .apellidos("MENDOZA ZAMORA")
                .email("mail066@mail.com")
                .dni("74437357")
                .build();

        webTestClient.post().uri("http://localhost:8080/customer/crear")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(cliente)
                .exchange() //envia el request

                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.nombres").isEqualTo(cliente.getNombres())
                .jsonPath("$.apellidos").isEqualTo(cliente.getApellidos())
                .jsonPath("$.email").isEqualTo(cliente.getEmail())
                .jsonPath("$.dni").isEqualTo(cliente.getDni());
    }
    
    @Test
    @Order(2)
    void testGetClienteByEmail() {
        webTestClient.get().uri("http://localhost:8080/customer/email/mail003@mail.com").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isEqualTo(3)
                .jsonPath("$.nombres").isEqualTo("Ana Maria")
                .jsonPath("$.apellidos").isEqualTo("HUAMANI HUAMAN")
                .jsonPath("$.dni").isEqualTo("74568602")
                .jsonPath("$.email").isEqualTo("mail003@mail.com");
    }

    @Test
    @Order(3)
    void testGetClienteByDni() {
        webTestClient.get().uri("http://localhost:8080/customer/dni/73963112").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.id").isEqualTo(9)
                .jsonPath("$.nombres").isEqualTo("Dafne")
                .jsonPath("$.apellidos").isEqualTo("RODRIGUEZ HUARANCCA")
                .jsonPath("$.dni").isEqualTo("73963112")
                .jsonPath("$.email").isEqualTo("mail009@mail.com");
    }
    
    /*    {
        "id": 1,
        "nombres": "Nilda Fabiola",
        "apellidos": "NUNEZ JAYO",
        "email": "mail001@mail.com",
        "dni": "74293701",
        "fechaCreacion": "2018-12-17",
        "fechaNacimiento": "2002-02-20"
    },*/
    @Test
    @Order(4)
    void testListarClientes(){
        webTestClient.get().uri("localhost:8080/customer/listar").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$[0].id").isEqualTo(1)
                .jsonPath("$[0].nombres").isEqualTo("Nilda Fabiola")
                .jsonPath("$[0].apellidos").isEqualTo("NUNEZ JAYO")
                .jsonPath("$[0].email").isEqualTo("mail001@mail.com")
                .jsonPath("$[0].dni").isEqualTo("74293701")
                .jsonPath("$").isArray()
                .jsonPath("$").value(hasSize(11));
    }

    @Test
    @Order(5)
    void testListarClientesTamanio(){
        webTestClient.get().uri("http://localhost:8080/customer/listar").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(Cliente.class)
                .consumeWith(response -> {
                    List<Cliente> empleados = response.getResponseBody();
                    Assertions.assertEquals(11,empleados.size());
                    Assertions.assertNotNull(empleados);
                });
    }
}
