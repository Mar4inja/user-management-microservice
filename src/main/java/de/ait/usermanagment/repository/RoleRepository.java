package de.ait.usermanagment.repository;

import de.ait.usermanagment.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByTitle(String title);
}
