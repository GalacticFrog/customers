package com.intercorp.customers.controller;

import com.intercorp.customers.entity.Cliente;
import com.intercorp.customers.entity.dto.NatalidadDTO;
import com.intercorp.customers.service.ClienteService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import com.intercorp.customers.entity.dto.MesAndAnioDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 *
 * @author USUARIO
 */
@RestController
@RequestMapping("/customer")
public class ClienteController {

    Logger logger = LoggerFactory.getLogger(ClienteController.class);
    
    @Autowired
    ClienteService clienteService;

    @GetMapping("/dni/{dni}")
    public ResponseEntity<Cliente> obtenerClientePorDni(@PathVariable("dni") String dni) {

        return clienteService.getClienteByDni(dni)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Cliente> obtenerClientePorEmail(@PathVariable("email") String email) {

        logger.info("email=>"+email);
        return clienteService.getClienteByEmail(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/listar")
    public List<Cliente> listar() {

        return clienteService.listar();
    }

    @GetMapping("/mesandanio")
    public ResponseEntity<Map<String, List<MesAndAnioDTO>>> getMesAndAnio() {

        Map<String, List<MesAndAnioDTO>> mesAnio = clienteService.getMesAndAnio();
        return ResponseEntity.ok(mesAnio);

    }

    @GetMapping("/nacimientos/mayor")
    public ResponseEntity<List<MesAndAnioDTO>> getNacimientosMayor() {

        List<MesAndAnioDTO> mesAndAnio = clienteService.getNacimientosMayor();
        return ResponseEntity.ok(mesAndAnio);

    }

    @GetMapping("/nacimientos/menor")
    public ResponseEntity<List<MesAndAnioDTO>> getNacimientosMenor() {

        List<MesAndAnioDTO> mesAndAnio = clienteService.getNacimientosMenor();
        return ResponseEntity.ok(mesAndAnio);

    }

    @GetMapping("/natalidad")
    public ResponseEntity<List<NatalidadDTO>> getNatalidad() {

        List<NatalidadDTO> tasaNatalidad = clienteService.getNatalidad();
        return ResponseEntity.ok(tasaNatalidad);

    }   

    @PostMapping("/crear")
    public ResponseEntity<Cliente> crear(@RequestBody Cliente Nuevocliente) {

        Cliente cliente = clienteService.crear(Nuevocliente);
        return ResponseEntity.ok(cliente);

    }

}
