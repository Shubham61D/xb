package com.wishfy.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException (String resourceName){
super(resourceName);

    }
}