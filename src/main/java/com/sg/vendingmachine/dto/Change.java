/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author abekoppal
 */
public class Change {

    BigDecimal QUARTER = new BigDecimal(".25");
    BigDecimal DIME = new BigDecimal(".10");
    BigDecimal NICKLE = new BigDecimal(".05");
    BigDecimal PENNY = new BigDecimal(".01");

    private BigDecimal quarters;
    private BigDecimal dimes;
    private BigDecimal nickles;
    private BigDecimal pennies;

    public BigDecimal getQuarters(BigDecimal initialAmount) {
        quarters = initialAmount.divide(QUARTER, 0, RoundingMode.DOWN);
        return quarters;
    }

    public void setQuerters(BigDecimal quarters) {
        this.quarters = quarters;
    }

    public BigDecimal getDimes(BigDecimal remaindingChange) {
        dimes = remaindingChange.divide(DIME, 0, RoundingMode.DOWN);
        return dimes;
    }

    public void setDimes(BigDecimal dimes) {
        this.dimes = dimes;
    }

    public BigDecimal getNickles(BigDecimal remaindingAmount) {
        nickles = remaindingAmount.divide(NICKLE, 0, RoundingMode.DOWN);
        return nickles;
    }

    public void setNickles(BigDecimal nickles) {
        this.nickles = nickles;
    }

    public BigDecimal getPennies(BigDecimal remaindingAmount) {
        pennies = remaindingAmount.divide(PENNY, 0, RoundingMode.DOWN);
        return pennies;

    }

    public void setPennies(BigDecimal pennies) {
        this.pennies = pennies;
    }

}
