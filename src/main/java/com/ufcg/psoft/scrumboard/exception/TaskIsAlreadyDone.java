package com.ufcg.psoft.scrumboard.exception;

public class TaskIsAlreadyDone extends Exception{
    private static final long serialVersionUID = 8L;

    public TaskIsAlreadyDone(String msg) {
        super(msg);
    }
}
