package com.shyaragas.app.helpers.exceptions;

public class ClientNotFoundException extends Exception
{
    public ClientNotFoundException(String errorMsg)
    {
        super(errorMsg);
    }
}
