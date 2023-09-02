package com.ufcg.psoft.scrumboard.models.entities.users;

import com.ufcg.psoft.scrumboard.resource.enums.Role;

public class Pesquisador implements UserRole {

    @Override
    public String getRole() {
        return Role.PESQUISADOR.getRole();
    }
}
