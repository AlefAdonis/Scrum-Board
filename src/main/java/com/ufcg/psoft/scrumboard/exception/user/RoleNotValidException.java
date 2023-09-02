package com.ufcg.psoft.scrumboard.exception.user;

public class RoleNotValidException extends Exception{
    private static final long serialVersionUID = 4L;

    public RoleNotValidException(String msg) {
        super(msg);
    }
}
