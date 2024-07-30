package org.example.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CulinaryExperienceNotFound extends RuntimeException{
    public CulinaryExperienceNotFound(){ super("Culinary Experience not found!");}
}
