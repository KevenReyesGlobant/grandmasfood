package org.grandmasfood.springcloud.clients.infraestructure.adapters.output.repository;

import org.grandmasfood.springcloud.clients.infraestructure.adapters.output.entities.ClientsEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class ClientsReposityIntegrationTest {

    @Autowired
    private ClientsReposity clientsReposity;

    @Autowired
    private TestEntityManager entityManager;

    private Long activeClientId;
    private Long inactiveClientId;

    @BeforeEach
    public void createInsertData() {
        ClientsEntity activeClient = new ClientsEntity();
        activeClient.setDocument("CC-1234567890");
        activeClient.setName("Test Active Client");
        activeClient.setEmail("active.client@example.com");
        activeClient.setPhone("3001234567");
        activeClient.setDeliveryAddress("123 Main St");
        activeClient.setActive(true);
        activeClient = entityManager.persistAndFlush(activeClient);
        activeClientId = activeClient.getIdClient();

        ClientsEntity inactiveClient = new ClientsEntity();
        inactiveClient.setDocument("CC-9876543210");
        inactiveClient.setName("Test Inactive Client");
        inactiveClient.setEmail("inactive.client@example.com");
        inactiveClient.setPhone("3109876543");
        inactiveClient.setDeliveryAddress("456 Oak Ave");
        inactiveClient.setActive(false);
        inactiveClient = entityManager.persistAndFlush(inactiveClient);
        inactiveClientId = inactiveClient.getIdClient();
    }

    @Test
    public void testFindClientsActiveByIdWhenClientExists() {
        ClientsEntity foundClient = clientsReposity.findClientsActiveById(activeClientId);

        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getIdClient()).isEqualTo(activeClientId);
        assertThat(foundClient.getActive()).isTrue();
    }

    @Test
    public void testFindClientsActiveByIdWhenClientNotActive() {
        ClientsEntity foundClient = clientsReposity.findClientsActiveById(inactiveClientId);

        assertThat(foundClient).isNull();
    }

    @Test
    public void testFindClientsActiveByDocumentWhenClientExists() {
        ClientsEntity activeClient = entityManager.find(ClientsEntity.class, activeClientId);
        ClientsEntity foundClient = clientsReposity.findClientsActiveByDocument(activeClient.getDocument());

        assertThat(foundClient).isNotNull();
        assertThat(foundClient.getDocument()).isEqualTo(activeClient.getDocument());
        assertThat(foundClient.getActive()).isTrue();
    }

    @Test
    public void testFindClientsActiveByDocumentWhenClientNotActive() {
        ClientsEntity inactiveClient = entityManager.find(ClientsEntity.class, inactiveClientId);
        ClientsEntity foundClient = clientsReposity.findClientsActiveByDocument(inactiveClient.getDocument());

        assertThat(foundClient).isNull();
    }

    @Test
    public void testFindAllWithSort() {
        // Create another active client for sorting test
        ClientsEntity anotherActiveClient = new ClientsEntity();
        anotherActiveClient.setDocument("CC-1111111111");
        anotherActiveClient.setName("Another Test Client");
        anotherActiveClient.setEmail("another.client@example.com");
        anotherActiveClient.setPhone("3211111111");
        anotherActiveClient.setDeliveryAddress("789 Test Ave");
        anotherActiveClient.setActive(true);
        entityManager.persistAndFlush(anotherActiveClient);

        Sort sortDescending = Sort.by(Sort.Direction.DESC, "idClient");
        var sortedClients = clientsReposity.findAll(sortDescending);

        assertThat(sortedClients).hasSize(3);
        assertThat(sortedClients.get(0).getDocument()).isEqualTo("CC-1111111111");
        assertThat(sortedClients.get(1).getDocument()).isEqualTo("CC-9876543210");
        assertThat(sortedClients.get(2).getDocument()).isEqualTo("CC-1234567890");
    }
}