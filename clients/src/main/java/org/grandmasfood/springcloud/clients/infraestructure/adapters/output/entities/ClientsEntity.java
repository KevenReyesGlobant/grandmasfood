package org.grandmasfood.springcloud.clients.infraestructure.adapters.output.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.grandmasfood.springcloud.clients.domain.model.Client;

import java.util.UUID;

@Entity
@Table(name = "client_entity")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID uuid;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Column(unique = true)
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;

    @Column(unique = true)
    @NotBlank(message = "Document cannot be empty")
    private String document;

    @NotBlank(message = "Phone cannot be empty")
    private String phone;

    @NotBlank(message = "Delivery address cannot be empty")
    private String deliveryAddress;
    @JsonIgnore
    private Boolean active;

//    public static ClientsEntity fromDomainModel(Client client) {
//        return new ClientsEntity(client.getId(), client.getUuid(), client.getName(), client.getEmail(), client.getDocument(), client.getPhone(), client.getDeliveryAddress(), client.getActive());
//
//    }
//
//    public Client toDomainModel() {
//        return new Client(id, uuid, name, email, document, phone, deliveryAddress, active);
//    }

}
