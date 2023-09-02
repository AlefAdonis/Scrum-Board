package com.ufcg.psoft.scrumboard.exception;

public class UserAlreadySubscribedException extends Exception{
    private static final long serialVersionUID = 7L;

    public UserAlreadySubscribedException(String msg) {
        super(msg);
    }
}
