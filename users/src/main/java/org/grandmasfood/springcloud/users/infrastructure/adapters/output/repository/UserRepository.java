package org.grandmasfood.springcloud.users.infrastructure.adapters.output.repository;
import java.util.Optional;

import org.grandmasfood.springcloud.users.infrastructure.adapters.output.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

@EnableJpaRepositories
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Query("SELECT u.emailVerified FROM UserEntity u WHERE u.id = :idUser")
    Boolean findEmailVerifiedById(@Param("idUser") Long idUser);

    @Query("SELECT u.id FROM UserEntity u WHERE u.email = :email")
    Long getNextUserId(@Param("email") String email);

    UserDetails findByEmail(String email);

    Optional<UserEntity> findByVerification(String token);

    Optional<UserEntity> findUserEntityByEmail(String email);

}
