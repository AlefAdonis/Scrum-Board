package com.ufcg.psoft.scrumboard.exception;

public class UserStoryNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public UserStoryNotFoundException(String msg) {
        super(msg);
    }

}
