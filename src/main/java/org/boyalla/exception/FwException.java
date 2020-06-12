/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.boyalla.exception;

/**
 *
 * @author raveendraboyalla
 */
 public class FwException extends RuntimeException{

    public FwException(String message) {
        super(message);
    }
    
     public FwException(Throwable throwable) {
        super(throwable);
    }
    
}
