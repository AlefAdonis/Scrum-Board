package com.ufcg.psoft.scrumboard.models.entities.users;

import com.ufcg.psoft.scrumboard.resource.enums.Role;

public class ProductOwner implements UserRole {

    @Override
    public String getRole() {
        return Role.PRODUCT_OWNER.getRole();
    }
}
