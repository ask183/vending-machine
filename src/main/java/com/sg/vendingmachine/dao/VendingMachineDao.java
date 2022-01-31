/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.ProductInformation;
import java.util.List;

/**
 *
 * @author abekoppal
 */
public interface VendingMachineDao {
    
    ProductInformation addVendingProduct(String name, ProductInformation item) throws VendingMachinePersistenceException;
    
    List<ProductInformation> getAllVendingInventory() throws VendingMachinePersistenceException;
    
    ProductInformation getVendingProductByName(String name) throws VendingMachinePersistenceException;
    
    ProductInformation removeVendingProduct(String name) throws VendingMachinePersistenceException;
    
}
