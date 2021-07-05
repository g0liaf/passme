package org.example.passme.component;

import org.example.passme.entity.Role;
import org.example.passme.entity.User;
import org.example.passme.entity.Vault;
import org.example.passme.repository.RoleRepository;
import org.example.passme.repository.UserRepository;
import org.example.passme.repository.VaultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VaultRepository vaultRepository;
    @Autowired
    PasswordEncoder encoder;

    @Override
    public void run(ApplicationArguments args) {
        roleRepository.save(Role.User());
        roleRepository.save(Role.Admin());
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(encoder.encode("qwer1234"));
        admin.setRoles(Collections.singleton(Role.Admin()));
        userRepository.save(admin);
        Vault vault = new Vault();
        vault.setId(admin.getId());
        vaultRepository.save(vault);
    }
}
