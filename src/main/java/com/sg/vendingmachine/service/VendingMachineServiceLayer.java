/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.ProductInformation;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author abekoppal
 */
public class VendingMachineServiceLayer {
    
    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;
    
    public VendingMachineServiceLayer(VendingMachineDao dao, VendingMachineAuditDao auditDao){
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    public void removeVendingProduct(String vendingItemName, String initialAmount) throws VendingMachinePersistenceException, NoItemInventoryException, InsufficientFundsException{
        
        checkForVendingProduct(vendingItemName);
        checkForEnoughVendingFunds(vendingItemName, initialAmount);
        dao.removeVendingProduct(vendingItemName);
        
        //write to audit log (audit.txt)
        auditDao.writeAuditEntry("Item " + vendingItemName + " REMOVED.");
    }
    
    public void checkForVendingProduct(String vendingItemName) throws VendingMachinePersistenceException, NoItemInventoryException{
        
        if (dao.getVendingProductByName(vendingItemName).getInventoryLeft().equals("0")){
            throw new NoItemInventoryException(vendingItemName + " is all sold out");
        }   
     //   return dao.getVendingProductByName(vendingItemName);
    }
    
    public void checkForEnoughVendingFunds(String vendingItemName, String initialAmount) throws VendingMachinePersistenceException, InsufficientFundsException{
        String cost = dao.getVendingProductByName(vendingItemName).getCost();
        BigDecimal vendingProductCost = new BigDecimal(cost);
        BigDecimal initialVendingFunds = new BigDecimal(initialAmount);
        //Big Decimal Form for initialVendingFunds < vendingProductCost
        if(initialVendingFunds.compareTo(vendingProductCost) == -1){
            throw new InsufficientFundsException("Not enough funds for purchasing this item.");
        }
    }
    
    public String vendingItemCostInString(String vendingItemName) throws VendingMachinePersistenceException, NoItemInventoryException{
        ProductInformation pi = dao.getVendingProductByName(vendingItemName);
        String vendingItemCostInString = pi.getCost();
        return vendingItemCostInString;
    }
    
    public BigDecimal getTheExactChangeAmountInBigDecimal(String vendingItemName, String initialAmount) throws VendingMachinePersistenceException, NoItemInventoryException, UnsupportedOperationException{
        ProductInformation currentProductInfo = dao.getVendingProductByName(vendingItemName);
        String cost = currentProductInfo.getCost();
        
        BigDecimal initialVendingFunds = new BigDecimal(initialAmount);
        BigDecimal vendingProductCost = new BigDecimal(cost);
        
        BigDecimal change = initialVendingFunds.subtract(vendingProductCost);
        return change;
    }
    
    public String getCoins(BigDecimal exactChange) throws VendingMachinePersistenceException, NoItemInventoryException, UnsupportedOperationException{
        String getChangeInDenomination = getChangeInDenomination(exactChange);
        return getChangeInDenomination;
    } 

    public String getChangeInDenomination(BigDecimal exactChange) {
        String quartersString = "";
        String dimesString = "";
        String nicklesString = "";
        String penniesString = "";
        
        BigDecimal QUARTER = new BigDecimal(".25");
        BigDecimal quarters = exactChange.divide(QUARTER, 0, RoundingMode.DOWN);
        quartersString = quarters.toString();
        
        BigDecimal remaindingChange = exactChange.subtract(quarters.multiply(QUARTER));
        
        BigDecimal DIME = new BigDecimal(".10");
        BigDecimal dimes = remaindingChange.divide(DIME, 0, RoundingMode.DOWN);
        dimesString = dimes.toString();
        
        BigDecimal remaindingChangeTwo = remaindingChange.subtract(dimes.multiply(DIME));
        
        BigDecimal NICKLE = new  BigDecimal(".05");
        BigDecimal nickles = remaindingChangeTwo.divide(NICKLE, 0, RoundingMode.DOWN);
        nicklesString = nickles.toString();
        
        BigDecimal remaindingChangeThree = remaindingChangeTwo.subtract(nickles.multiply(NICKLE));
        
        BigDecimal PENNY = new  BigDecimal(".01");
        BigDecimal pennies = remaindingChangeThree.divide(PENNY, 0, RoundingMode.DOWN);
        penniesString = pennies.toString();
        
        String coins = "Your change is: \n" + quartersString + "-Quarters \n"
                + dimesString + "-Dimes \n" + nicklesString + "-Nickles \n"
                + penniesString + "-Pennies";
        
        return coins;
    }
    
    public String getRefundInCoins(String initialAmount){ 
        BigDecimal initialVendingFunds = new BigDecimal(initialAmount);
        
        String quartersString = "";
        String dimesString = "";
        String nicklesString = "";
        String penniesString = "";
        
        BigDecimal QUARTER = new BigDecimal(".25");
        BigDecimal quarters = initialVendingFunds.divide(QUARTER, 0, RoundingMode.DOWN);
        quartersString = quarters.toString();

        BigDecimal remaindingChange = initialVendingFunds.subtract(quarters.multiply(QUARTER));

        BigDecimal DIME = new BigDecimal(".10");
        BigDecimal dimes = remaindingChange.divide(DIME, 0, RoundingMode.DOWN);
        dimesString = dimes.toString();

        BigDecimal remaindingChangeTwo = remaindingChange.subtract(dimes.multiply(DIME));

        BigDecimal NICKLE = new BigDecimal(".05");
        BigDecimal nickles = remaindingChangeTwo.divide(NICKLE, 0, RoundingMode.DOWN);
        nicklesString = nickles.toString();

        BigDecimal remaindingChangeThree = remaindingChangeTwo.subtract(nickles.multiply(NICKLE));

        BigDecimal PENNY = new BigDecimal(".01");
        BigDecimal pennies = remaindingChangeThree.divide(PENNY, 0, RoundingMode.DOWN);
        penniesString = pennies.toString();

        String coins = "Your change is: \n" + quartersString + "-Quarters \n"
                + dimesString + "-Dimes \n" + nicklesString
                + "-Nickles \n" + penniesString + "-Pennies";

        return coins;
        
    }
    
}
