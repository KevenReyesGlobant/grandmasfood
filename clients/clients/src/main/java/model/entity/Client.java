package model.entity;

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
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String uuid;
    @NotEmpty(message = "This field is not empty")
    private String name;
    @Column(unique = true)
    @Email
    @NotEmpty
    private String email;
    @NotBlank
    private String document;
    @NotBlank
    private String phone;
    @NotBlank
    private String deliveryAddress;

}
