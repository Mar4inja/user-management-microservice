package de.ait.usermanagment.service.implementation;

import de.ait.usermanagment.model.Role;
import de.ait.usermanagment.repository.RoleRepository;
import de.ait.usermanagment.service.RoleServiceInterface;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RoleServiceImplementation implements RoleServiceInterface {

    private RoleRepository roleRepository;

    @Override
    public Role getRole() {
        Role role = roleRepository.findByTitle("ROLE_USER");
        if (role == null) {
            throw new RuntimeException("Role not found");
        }
        return role;
    }
}
