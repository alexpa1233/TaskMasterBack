/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.Alejandro.TFG.util.exception;

/**
 *
 * @author Alex
 */
//Aunque todas las exceptions son iguales, prefiero diferenciarlos, 
//ya que así podre distinguirlos mejor a la hora de correccion de errores

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
