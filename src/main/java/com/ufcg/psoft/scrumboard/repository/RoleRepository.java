package com.ufcg.psoft.scrumboard.repository;

import com.ufcg.psoft.scrumboard.models.entities.users.*;
import com.ufcg.psoft.scrumboard.resource.enums.Role;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class RoleRepository {

    private final Map<String, UserRole> roleUsuario = new HashMap<>();

    private void setRoleUsuario() {
        roleUsuario.put(Role.SCRUM_MASTER.getRole(), new ScrumMaster());
        roleUsuario.put(Role.DESENVOLVEDOR.getRole(), new Desenvolvedor());
        roleUsuario.put(Role.PESQUISADOR.getRole(), new Pesquisador());
        roleUsuario.put(Role.PRODUCT_OWNER.getRole(), new ProductOwner());
        roleUsuario.put(Role.ESTAGIARIO.getRole(), new Estagiario());
    }

    public UserRole getUserByRole(String role) {
        setRoleUsuario();
        return roleUsuario.get(role);
    }
}
