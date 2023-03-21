/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.intercorp.customers.repository;

import com.intercorp.customers.entity.Cliente;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
/**
 *
 * @author USUARIO
 */
@DataJpaTest
public class ClienteRepositoryTest {
    
    @Autowired
    private ClienteRepository clienteRepository;
    
    private  Cliente cliente;
    
    @BeforeEach
    void setup(){
        cliente = Cliente.builder()
                .nombres("Lida")
                .apellidos("PRADO NAJARRO")
                .email("emai8800@mail.com")
                .dni("75707777")
                .fechaCreacion(new Date())
                .fechaNacimiento(new Date())
                .build();
    }
        
    @Test
    void testFindByEmail(){
        
        clienteRepository.save(cliente);        
        Cliente clienteBD = clienteRepository.findByEmail(cliente.getEmail()).get();        
        assertThat(clienteBD).isNotNull();
    }
    
    @Test
    void testFindByDni(){
        
        clienteRepository.save(cliente);        
        Cliente clienteBD = clienteRepository.findByDni(cliente.getDni()).get();        
        assertThat(clienteBD).isNotNull();
    }
    
    
    @Test
    public void testGuardarCliente() {      
      
        Cliente cliente2 = Cliente.builder()
                .nombres("Frank")
                .apellidos("SILVA DIPAS")
                .email("email100@mail.com")
                .dni("70707777")
                .fechaCreacion(new Date())
                .fechaNacimiento(new Date())
                .build();
        Cliente clienteGuardado = clienteRepository.save(cliente2);
        
        assertThat(clienteGuardado).isNotNull(); 
        assertThat(clienteGuardado.getId()).isGreaterThan(0);
    }
    @Test
    public void testListarClientes() {      
      
        Cliente cliente2 = Cliente.builder()
                .nombres("Juan")
                .apellidos("QUISPE QUISPE")
                .email("email2200@mail.com")
                .dni("70707788")
                .fechaCreacion(new Date())
                .fechaNacimiento(new Date())
                .build();                
        
        clienteRepository.save(cliente2);
        clienteRepository.save(cliente);
        
        List<Cliente> listaClientes = clienteRepository.findAll();
        
        assertThat(cliente2).isNotNull();
        assertThat(listaClientes.size()).isGreaterThan(2);
        
    }
    
}
