package org.example.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoutesAndTransportNotFound extends RuntimeException{
    public RoutesAndTransportNotFound(){ super("Route and/or Transport not found!");}
}
