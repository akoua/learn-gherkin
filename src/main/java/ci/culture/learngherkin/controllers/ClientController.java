package ci.culture.learngherkin.controllers;

import ci.culture.learngherkin.controllers.dto.ClientDto;
import ci.culture.learngherkin.entities.Client;
import ci.culture.learngherkin.services.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "v1/client", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ClientController {
    private final ClientService clientService;

    @PostMapping(value = "addClient")
    public ResponseEntity<Client> createNewClient(@RequestBody ClientDto clientDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clientService.createClient(clientDto));
    }
}
