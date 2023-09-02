package com.ufcg.psoft.scrumboard.exception.user;

public class UserNotInProjectException extends Exception{
    private static final long serialVersionUID = 1L;
    public UserNotInProjectException() {
        super("Usuário não está cadastrado no projeto");
    }
}