/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.ProductInformation;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author abekoppal
 */

public class VendingMachineDaoStubImpl implements VendingMachineDao {
    
    public ProductInformation lonelyProduct;
    
    public VendingMachineDaoStubImpl(ProductInformation lonelyProduct){
        this.lonelyProduct = lonelyProduct;
    }
    
    public VendingMachineDaoStubImpl(){
        lonelyProduct = new ProductInformation("Coke");
      //  lonelyProduct.setName("Coke");
        lonelyProduct.setCost("2.00");
        lonelyProduct.setInventoryLeft(100);
    }

    @Override
    public ProductInformation addVendingProduct(String name, ProductInformation item) throws VendingMachinePersistenceException {
        if(name.equals(lonelyProduct.getName())){
            return lonelyProduct;
        }else{
            return null;
        }
    }

    @Override
    public List<ProductInformation> getAllVendingInventory() throws VendingMachinePersistenceException {
        List vList = new ArrayList<ProductInformation>();
        vList.add(lonelyProduct);
        return vList;
    }

    @Override
    public ProductInformation getVendingProductByName(String name) throws VendingMachinePersistenceException {
        if(name.equals(lonelyProduct.getName())){
            return lonelyProduct;
        }else{
            return null;
        }
    }

    @Override
    public ProductInformation removeVendingProduct(String name) throws VendingMachinePersistenceException {
        if(name.equals(lonelyProduct.getName())){
            return lonelyProduct;
        }else{
            return null;
        }
    } 
    
}
