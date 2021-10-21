package com.example.supermarket.exception;

public class ResourceExistException extends RuntimeException{

    public ResourceExistException(String msg){
        super(msg);
    }
}
