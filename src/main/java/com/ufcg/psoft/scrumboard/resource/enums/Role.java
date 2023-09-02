package com.ufcg.psoft.scrumboard.resource.enums;

public enum Role {
    SCRUM_MASTER("Scrum Master"),
    DESENVOLVEDOR("Desenvolvedor"),
    PESQUISADOR("Pesquisador"),
    PRODUCT_OWNER("Product Owner"),
    ESTAGIARIO("Estagiario");

    private final String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
