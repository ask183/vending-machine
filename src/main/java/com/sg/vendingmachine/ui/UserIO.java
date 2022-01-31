/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.vendingmachine.ui;

import java.math.BigDecimal;

/**
 *
 * @author abekoppal
 */
public interface UserIO {
    
    void print(String msg);

    double readDouble(String prompt);

    double readDouble(String prompt, double min, double max);

    float readFloat(String prompt);

    float readFloat(String prompt, float min, float max);

    int readInt(String prompt);

    int readInt(String prompt, int min, int max, int backDoor);

    long readLong(String prompt);

    long readLong(String prompt, long min, long max);

    String readString(String prompt);

    public void print(BigDecimal exactChange);

    
}
