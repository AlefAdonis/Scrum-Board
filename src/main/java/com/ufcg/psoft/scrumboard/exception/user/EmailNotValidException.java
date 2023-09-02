package com.ufcg.psoft.scrumboard.exception.user;

public class EmailNotValidException extends Exception{
    private static final long serialVersionUID = 4L;

    public EmailNotValidException(String msg) {
        super(msg);
    }
}
