package ci.culture.learngherkin.services;

import ci.culture.learngherkin.controllers.dto.ClientDto;
import ci.culture.learngherkin.entities.Client;
import ci.culture.learngherkin.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public Client createClient(ClientDto clientDto) {
        return clientRepository.save(Client.builder()
                .nom(clientDto.name())
                .prenom(clientDto.firstname())
                .build());
    }
}
