package com.shyaragas.app.helpers.exceptions;

public class ClientNotFoundException extends Exception
{
    public ClientNotFoundException()
    {
        super("Cliente no encontrado, intente nuevamente.");
    }

    public ClientNotFoundException(String errorMsg)
    {
        super(errorMsg);
    }
}

