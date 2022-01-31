/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.vendingmachine.service;

/**
 *
 * @author abekoppal
 */
public class NoItemInventoryException extends Exception {
    
    public NoItemInventoryException(String message){
        super(message);
    }
    
    public NoItemInventoryException(String message, Throwable cause){
        super(message, cause);
    }
}
