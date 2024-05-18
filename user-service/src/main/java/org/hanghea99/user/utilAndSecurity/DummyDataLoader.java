package org.hanghea99.user.utilAndSecurity;

import lombok.RequiredArgsConstructor;
import org.hanghea99.user.domain.KeyRole;
import org.hanghea99.user.repository.KeyRoleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class DummyDataLoader implements CommandLineRunner {

    private final KeyRoleRepository keyRoleRepository;

    @Override
    public void run(String... args) {
        // Create KeyRole
        KeyRole normalOrderType = KeyRole.builder().userRole("user").build();
        KeyRole reservationOrderType = KeyRole.builder().userRole("admin").build();
        keyRoleRepository.save(normalOrderType);
        keyRoleRepository.save(reservationOrderType);
    }
}

