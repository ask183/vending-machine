/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.service.InsufficientFundsException;
import com.sg.vendingmachine.service.NoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;

/**
 *
 * @author abekoppal
 */
public class VendingMachineController {
    
    private VendingMachineView view;
    private VendingMachineServiceLayer service;
    
    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service){
        this.service = service;
        this.view = view;
    }
    
    public void run(){
        boolean keepGoing = true;
        
        view.printMenuAndGetSelection();
        view.showInsertFundsBanner();
        String initialBalance = view.showGetInitialBalanceBanner();
        
        while (keepGoing){
            String newBalance;
            String vendingItemName;
            
            int menuSelection = getMenuSelection();
            try{
                switch(menuSelection){
                    case 1:
                        vendingItemName = "Snickers";
                        vendTheProduct(vendingItemName, initialBalance);
                        if (!view.purchaseAnotherVendingProduct(getTheExactChangeAmountInBigDecimal(vendingItemName, initialBalance))){
                            view.changeInCoins(service.getChangeInDenomination(getTheExactChangeAmountInBigDecimal(vendingItemName, initialBalance)));
                            keepGoing = false;
                        }else{
                            newBalance = getTheExactChangeAmountInBigDecimal(vendingItemName, initialBalance).toString();
                            initialBalance = newBalance;
                        }
                        break;
                    case 2:
                        vendingItemName = "Doritos";
                        vendTheProduct(vendingItemName, initialBalance);
                        if (!view.purchaseAnotherVendingProduct(getTheExactChangeAmountInBigDecimal(vendingItemName, initialBalance))){
                            view.changeInCoins(service.getChangeInDenomination(getTheExactChangeAmountInBigDecimal(vendingItemName, initialBalance)));
                            keepGoing = false;
                        }else{
                            newBalance = getTheExactChangeAmountInBigDecimal(vendingItemName, initialBalance).toString();
                            initialBalance = newBalance;
                        }
                        break;
                    case 3:
                        vendingItemName = "Chips";
                        vendTheProduct(vendingItemName, initialBalance);
                        if (!view.purchaseAnotherVendingProduct(getTheExactChangeAmountInBigDecimal(vendingItemName, initialBalance))){
                            view.changeInCoins(service.getChangeInDenomination(getTheExactChangeAmountInBigDecimal(vendingItemName, initialBalance)));
                            keepGoing = false;
                        }else{
                            newBalance = getTheExactChangeAmountInBigDecimal(vendingItemName, initialBalance).toString();
                            initialBalance = newBalance;
                        }
                        break;
                    case 4:
                        vendingItemName = "Cookies";
                        vendTheProduct(vendingItemName, initialBalance);
                        if (!view.purchaseAnotherVendingProduct(getTheExactChangeAmountInBigDecimal(vendingItemName, initialBalance))) {
                            view.changeInCoins(service.getChangeInDenomination(getTheExactChangeAmountInBigDecimal(vendingItemName, initialBalance)));
                            keepGoing = false;
                        } else {
                            newBalance = getTheExactChangeAmountInBigDecimal(vendingItemName, initialBalance).toString();
                            initialBalance = newBalance;
                        }
                        break;
                    case 5:
                        vendingItemName = "Brownie";
                        vendTheProduct(vendingItemName, initialBalance);
                        if (!view.purchaseAnotherVendingProduct(getTheExactChangeAmountInBigDecimal(vendingItemName, initialBalance))) {
                            view.changeInCoins(service.getChangeInDenomination(getTheExactChangeAmountInBigDecimal(vendingItemName, initialBalance)));
                            keepGoing = false;
                        } else {
                            newBalance = getTheExactChangeAmountInBigDecimal(vendingItemName, initialBalance).toString();
                            initialBalance = newBalance;
                        }
                        break;
                    case 6:
                        view.displayRefundMoneyBanner();
                        view.changeInCoins(service.getRefundInCoins(initialBalance));
                        keepGoing = false;
                        break;
                    case 7:
                        view.displayAdminOptionsMenu();
                        break;
                    default:
                        view.showUnknownCommandBanner();     
                }
            } catch(VendingMachinePersistenceException | NoItemInventoryException | InsufficientFundsException ex){
                view.showErrorMessage(ex.getMessage());
            }
        }
    }
    
    private int getMenuSelection(){
        return view.getSelection();
    }
    
    private String vendTheProduct(String vendingItemName, String initialBalance) throws VendingMachinePersistenceException, NoItemInventoryException, InsufficientFundsException{
        service.removeVendingProduct(vendingItemName, initialBalance);
        view.takeYourVendingItem(vendingItemName);
        return service.vendingItemCostInString(vendingItemName);
    }
    
    private BigDecimal getTheExactChangeAmountInBigDecimal(String initialBalance, String vendingItemName) throws VendingMachinePersistenceException, NoItemInventoryException{
        BigDecimal exactChange = service.getTheExactChangeAmountInBigDecimal(initialBalance, vendingItemName);
        return exactChange;
    }
}
