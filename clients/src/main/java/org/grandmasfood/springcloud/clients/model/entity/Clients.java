package org.grandmasfood.springcloud.clients.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "client")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Clients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "UUID cannot be empty")
    private String uuid;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @Column(unique = true)
    @Email(message = "Email should be valid")
    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "Document cannot be empty")
    private String document;

    @NotBlank(message = "Phone cannot be empty")
    private String phone;

    @NotBlank(message = "Delivery address cannot be empty")
    private String deliveryAddress;

}
