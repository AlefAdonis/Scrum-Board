package com.ufcg.psoft.scrumboard.exception.user;

public class UserNameNotValidException extends Exception{
    private static final long serialVersionUID = 9L;

    public UserNameNotValidException(String msg) {
        super(msg);
    }
}
