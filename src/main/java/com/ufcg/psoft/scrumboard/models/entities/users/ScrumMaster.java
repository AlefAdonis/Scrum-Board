package com.ufcg.psoft.scrumboard.models.entities.users;

import com.ufcg.psoft.scrumboard.resource.enums.Role;

public class ScrumMaster implements UserRole {

    @Override
    public String getRole() {
        return Role.SCRUM_MASTER.getRole();
    }
}
