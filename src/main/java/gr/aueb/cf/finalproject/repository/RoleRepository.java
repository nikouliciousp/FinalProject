package gr.aueb.cf.finalproject.repository;

import gr.aueb.cf.finalproject.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Finds a role by its name.
     *
     * @param name The name of the role.
     * @return The found role or null if not found.
     */
    Role findByName(String name);
}
