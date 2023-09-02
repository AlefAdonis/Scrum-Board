package com.ufcg.psoft.scrumboard.dto;

public class AssociationProjectDTO {

    private final String associationName;
    private final String role;

    public AssociationProjectDTO(String associationName, String role) {
        this.associationName = associationName;
        this.role = role;
    }

    public String getAssociationName() {
        return associationName;
    }

    public String getRole() {
        return role;
    }

}
