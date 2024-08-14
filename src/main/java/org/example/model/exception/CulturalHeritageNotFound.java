package org.example.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CulturalHeritageNotFound extends RuntimeException{
    public CulturalHeritageNotFound(){ super("Cultural Heritage not found!");}
}
