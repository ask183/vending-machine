/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.ProductInformation;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author abekoppal
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    private String VENDMACHINE_FILE;
    final String DELIMITER = "::";
    
    private Map<String, ProductInformation> inventory = new HashMap<>();

    public VendingMachineDaoFileImpl() {
        VENDMACHINE_FILE = "vending.txt";
    }

    public VendingMachineDaoFileImpl(String vendingTextFile) {
        VENDMACHINE_FILE = vendingTextFile;
    }

    @Override
    public ProductInformation addVendingProduct(String name, ProductInformation item) throws VendingMachinePersistenceException {
        loadFile();
        ProductInformation prevProduct = inventory.put(name, item);
        writeFile();
        return prevProduct;
    }

    @Override
    public List<ProductInformation> getAllVendingInventory() throws VendingMachinePersistenceException {
        loadFile();
        return new ArrayList<ProductInformation>(inventory.values()); //this constructor gives you a method that return a collection of products
    }

    @Override
    public ProductInformation getVendingProductByName(String vendingItemName) throws VendingMachinePersistenceException {
    /*    loadFile();
        return inventory.get(vendingItemName); */
      
        getAllVendingInventory();
        ProductInformation pi = inventory.get(vendingItemName);
        if (pi.getName().equals(vendingItemName)) {
            return pi;
        }else{

        return null;}   
    }

    @Override
    public ProductInformation removeVendingProduct(String vendingItemName) throws VendingMachinePersistenceException {
    /*    
        loadFile();
        ProductInformation removedProduct = inventory.remove(vendingItemName);
        writeFile();
        return removedProduct; */
       
        getAllVendingInventory(); 

        ProductInformation currentProductInfo = inventory.get(vendingItemName);
    //    int currentStock = Integer.parseInt(currentProductInfo.getInventoryLeft());
        int currentStock = currentProductInfo.getInventoryLeft();
        int newStock = currentStock - 1;
     //   currentProductInfo.setInventoryLeft(Integer.toString(newStock));
        currentProductInfo.setInventoryLeft(newStock);

        writeFile();
  //      return null;
        return currentProductInfo;
    }  
    
    private String marshallProductInfo(ProductInformation aProductInfo) {
        String productInfoAsText = aProductInfo.getName() + DELIMITER;
        productInfoAsText += aProductInfo.getCost() + DELIMITER;
        productInfoAsText += aProductInfo.getInventoryLeft();
        return productInfoAsText;
    }

    private ProductInformation unmarshallProductInfo(String productInfoAsText) {
        //productInfoAsText is expecting a String as a line read in from our file
        String[] productInfoTokens = productInfoAsText.split(DELIMITER);
        String name = productInfoTokens[0];

        ProductInformation vendingProductFromFile = new ProductInformation(name);

        vendingProductFromFile.setCost(productInfoTokens[1]);
     //   vendingProductFromFile.setInventoryLeft(productInfoTokens[2]);
        vendingProductFromFile.setInventoryLeft(Integer.parseInt(productInfoTokens[2]));

        return vendingProductFromFile;
    }

    private void loadFile() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(VENDMACHINE_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load vending file data into memory.", e);
        }

        String currentLine;
        ProductInformation currentProductInfo;

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();
            currentProductInfo = unmarshallProductInfo(currentLine);
            inventory.put(currentProductInfo.getName(), currentProductInfo);
        }
        scanner.close();
    }

    private void writeFile() throws VendingMachinePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(VENDMACHINE_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not save product information data.", e);
        }

        //What do we do with the file resource?
        String productInfoAsText;
        List<ProductInformation> productInfoList = this.getAllVendingInventory();

        for (ProductInformation currentProductInfo : productInfoList) {
            productInfoAsText = marshallProductInfo(currentProductInfo);
            out.println(productInfoAsText);
            out.flush(); //flush is to stream so whatever is in the buffer make sure that line gets printed to the stream
        }
        out.close();

    }

}
