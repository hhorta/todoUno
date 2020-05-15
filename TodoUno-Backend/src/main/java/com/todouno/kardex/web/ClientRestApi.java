package com.todouno.kardex.web;

import com.todouno.kardex.core.exceptions.base.ServiceException;
import com.todouno.kardex.domain.dto.ClientDTO;
import com.todouno.kardex.services.client.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api")
@CrossOrigin({"*"})
public class ClientRestApi {

    private final ClientService service;

    @Autowired
    public ClientRestApi(ClientService clientService) {
        this.service = clientService;
    }

    @GetMapping(value = "/client", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClientDTO> getAllClient() throws ServiceException {
        return service.getAllClient();
    }

    @GetMapping(value = "/client/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDTO> getClientById(@PathVariable("id") Integer id)
            throws ServiceException {
        ClientDTO entity = service.getClientById(id);
        return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping(value = "/client/{document}/info", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClientDTO> getClientByDocument(@PathVariable("document") String document)
            throws ServiceException {
        return service.findClientByDocument(document);
    }


    @PostMapping(value = "/client", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDTO> create(@RequestBody ClientDTO clientDTO) throws ServiceException {
        ClientDTO created = service.createClient(clientDTO);
        return new ResponseEntity<>(created, new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping(value = "/client", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientDTO> updateClient(@RequestBody ClientDTO clientDTO) throws ServiceException {
        ClientDTO update = service.updateClient(clientDTO);
        return new ResponseEntity<>(update, new HttpHeaders(), HttpStatus.OK);
    }
}
