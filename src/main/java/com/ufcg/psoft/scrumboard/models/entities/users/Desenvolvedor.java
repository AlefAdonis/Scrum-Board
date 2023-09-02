package com.ufcg.psoft.scrumboard.models.entities.users;

import com.ufcg.psoft.scrumboard.resource.enums.Role;

public class Desenvolvedor implements UserRole {

    @Override
    public String getRole() {
        return Role.DESENVOLVEDOR.getRole();
    }
}
