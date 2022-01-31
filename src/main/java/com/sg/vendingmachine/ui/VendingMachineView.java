/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.ProductInformation;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author abekoppal
 */
public class VendingMachineView {
    
    public VendingMachineView(UserIO io) {
        this.io = io;
    }
    
    private UserIO io;
    
    public void printMenuAndGetSelection(){
        io.print("Vending selections");
        io.print("1. Snickers - $1.25");
        io.print("2. Doritos - $1.25");
        io.print("3. Chips - $1.00");
        io.print("4. Cookies - $1.50");
        io.print("5. Brownie - $1.50");
        io.print("6. Return Money");
    }
    
    public int getSelection(){
        return io.readInt("Please make a selection from the choices", 1, 6, 100);
    }
    
    public void displayVendingInventory(List<ProductInformation> inventory){
        for (ProductInformation currentVendingProduct: inventory){
            String vendingProductInfo = String.format("%s : #%s %s ", 
                    currentVendingProduct.getName(),
                    currentVendingProduct.getCost(),
                    currentVendingProduct.getInventoryLeft());
            io.print(vendingProductInfo);
        }
        io.readString("Please hit enter to continue");
    }
    
    public String showGetInitialBalanceBanner(){
        String initialBalance = io.readString("$");
        return initialBalance;
    }
    
    public String takeYourVendingItem(String vendingItemName){
        String takeYourVendingItem = io.readString("Please take your " + vendingItemName + ": press Enter to continue");
        return takeYourVendingItem;
    }
    
    public void changeInCoins(String exactChange){
        io.print(exactChange);
    }
    
    public boolean purchaseAnotherVendingProduct(BigDecimal exactChange){
        boolean keepGoing = true;
        
        io.readString("Your current balance is $" + exactChange.toString());
        
        io.print("Would you like to purchase another product");
        int userResponse = io.readInt("1: Yes \n2: No", 1, 2, 888);
        
        if (userResponse == 2){
            keepGoing = false;
            io.print("Thanks For Shopping at The Vending Machine");
        }
        return keepGoing;
    }
   
    //BANNERS
    
    public void showThanksForUsingVendingMachine(){
        io.print("Thanks for Buying from The Vending Machine");
    }
    
    public void showUnknownCommandBanner(){
        io.print("***NOT A VALID COMMAND***");
    }
    
    public void displayRefundMoneyBanner(){
        io.print("Refunding Money");
    }
    
    public void displayAdminOptionsMenu(){
        io.print("ADMIN OPTIONS");
    }
    
    public void showInsertFundsBanner(){
        io.print("Please insert Funds");
    }
    
    public void showErrorMessage(String errorMessage){
        io.print("===ERROR===");
        io.print(errorMessage);
    }
    
}
