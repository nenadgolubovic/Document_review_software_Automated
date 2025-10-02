package com.example.document_review.repository;

import com.example.document_review.entity.User;
import com.example.document_review.repository.impl.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.util.ReflectionTestUtils;


@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {

    private UserRepository userRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
        ReflectionTestUtils.setField(userRepository, "entityManager", entityManager);
    }

    @Test
    public void userRepositorySave() {
        User user = User.builder()
                .username("UsernameTest")
                .password("PasswordTest")
                .firstName("FirstNameTest")
                .lastName("LastNameTest")
                .email("EmailTest@EmailTest")
                .build();

        // Save će sada raditi jer je @Transactional aktivan kroz Spring proxy
        userRepository.save(user);

        // Provera da li je entitet sačuvan
        User savedUser = entityManager.find(User.class, user.getUserId());

        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getUsername()).isEqualTo(user.getUsername());
        Assertions.assertThat(savedUser.getEmail()).isEqualTo(user.getEmail());
    }
    @Test
    public void UserRepositoryFindByIdReturnUser() {

    }


    @Test
    public void UserRepositoryFindByUsernameReturnUser() {

        User user = User.builder()
                .username("UsernameTest")
                .password("PasswordTest")
                .firstName("FirstNameTest")
                .lastName("LastNameTest")
                .email("EmailTest@EmailTest")
                .build();

        userRepository.save(user);

        User foundUser = userRepository.findByUsername(user.getUsername());

        Assertions.assertThat(foundUser).isNotNull();
        Assertions.assertThat(foundUser).isEqualTo(user);

    }
}

