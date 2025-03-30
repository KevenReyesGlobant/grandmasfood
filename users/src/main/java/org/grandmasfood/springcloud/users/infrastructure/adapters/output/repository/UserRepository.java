package org.grandmasfood.springcloud.users.infrastructure.adapters.output.repository;

import org.grandmasfood.springcloud.users.infrastructure.adapters.output.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserDetails findByEmail(String email);

    Optional<UserEntity> findByVerification(String token);

}
