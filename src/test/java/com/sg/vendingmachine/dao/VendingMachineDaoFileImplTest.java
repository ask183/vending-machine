/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.ProductInformation;
import java.io.FileWriter;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author abekoppal
 */
public class VendingMachineDaoFileImplTest {
    
    //VendingMachineDaoFileImpl dao = new VendingMachineDaoFileImpl("testing.txt");
    VendingMachineDao testDao;
    public VendingMachineDaoFileImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception{
        String testFile = "testing.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new VendingMachineDaoFileImpl(testFile);
    }
    
    @After
    public void tearDown() {
    }
 /* 
    //test of removeVendingProduct method, of class VendingMachineDaoFileImpl
    @Test
    public void testRemoveVendingProduct() throws Exception {
        ProductInformation Brownie = testDao.getVendingProductByName("Brownie");
        int startStock = Integer.parseInt(Brownie.getInventoryLeft());
        testDao.removeVendingProduct("Brownie");
        
        int newStock = Integer.parseInt(Brownie.getInventoryLeft());
        assertTrue(newStock == startStock);
    }
   
    //test of getAllVendingInventory method, of class VendingMachineDaoFileImpl
    @Test
    public void testGetAllVendingInventory() throws Exception{
        List<ProductInformation> inventory = testDao.getAllVendingInventory();
        
        assertTrue(inventory.size() > 0);
    }
    
    //test of getVendingProductByName method, of class VendingMachineDaoFileImpl
    @Test
    public void testGetVendingProductByName() throws Exception{
        ProductInformation pi = testDao.getVendingProductByName("Snickers");
        
        assertEquals(pi.getName(), "Snickers");
    }
 */
    
    @Test
    public void testAddGetVendingProduct() throws Exception{
        //create our method test inputs
        String name = "Apple";
        ProductInformation product = new ProductInformation(name);
        product.setCost("10.00");
        product.setInventoryLeft("100");
        
        //Add the product to the DAO
        testDao.addVendingProduct(name, product);
        //Get the product from the DAO
        ProductInformation retrievedProduct = testDao.getVendingProductByName(name);
        
        //check the data is equal
        assertEquals("Checking name.", product.getName(), retrievedProduct.getName());
        assertEquals("Checking product cost.", product.getCost(), retrievedProduct.getCost());
        assertEquals("Checking product inventory left.", product.getInventoryLeft(), retrievedProduct.getInventoryLeft());
    }
    
    @Test
    public void testAddGetAllVendingInventory() throws Exception{
        //create our first product 
        ProductInformation product1 = new ProductInformation("gum");
        product1.setCost("5.00");
        product1.setInventoryLeft("50");
        
        //create our second product
        ProductInformation product2 = new ProductInformation("skittles");
        product2.setCost("10.00");
        product2.setInventoryLeft("20");
        
        //add both our products to the DAO
        testDao.addVendingProduct(product1.getName(), product1);
        testDao.addVendingProduct(product2.getName(), product2);
        
        //Retrieve the list of all products within the DAO
        List<ProductInformation> allProducts = testDao.getAllVendingInventory();
        
        //First check the general contents of the list
        assertNotNull("The list of products must not be null", allProducts);
        assertEquals("List of products should have 2 products", 2, allProducts.size());
   
        //then the speciffics
        assertTrue("The list of products should include gum.", testDao.getAllVendingInventory().contains(product1));
        assertTrue("The list of products should include skittles.", testDao.getAllVendingInventory().contains(product2));
    }
   
   
    @Test
    public void testRemoveVendingProuduct() throws Exception{
        //create two new products
        ProductInformation product1 = new ProductInformation("gum");
        product1.setCost("5.00");
        product1.setInventoryLeft("50");
        
        ProductInformation product2 = new ProductInformation("skittles");
        product2.setCost("10.00");
        product2.setInventoryLeft("20");
        
        //add both to the dao
        testDao.addVendingProduct(product1.getName(), product1);
        testDao.addVendingProduct(product2.getName(), product2);
        
        //remove the first product -gum
        ProductInformation removedProduct = testDao.removeVendingProduct(product1.getName());  
        //check that the correct object was removed.
        assertEquals("The removed product should be gum.", removedProduct, product1);
        
        //get all the products
        List<ProductInformation> allProducts = testDao.getAllVendingInventory();
        
        //First check the general contents of the list
        assertNotNull("All products list should not be null", allProducts);
        assertEquals("All products should only have 1 products", 1, allProducts.size());
        
        //then the specifics
        assertFalse("The list of products should NOT include gum.", allProducts.contains(product1));
        assertTrue("The list of products should NOT include skittles.", allProducts.contains(product2));
        
        //removed the second product
        removedProduct = testDao.removeVendingProduct(product2.getName());
        //check that the correct object was removed.
        assertEquals("The removed product should be skittles.", removedProduct, product2);
        
        //retrieve all of the products again, and check the list.
        allProducts = testDao.getAllVendingInventory();
        
        //check the contents of the list - it should be empty
        assertTrue("The retrieved list of products should be empty", allProducts.isEmpty());
        
        //try to 'get' both products by their old name - they should be nulll
        ProductInformation retrievedProduct = testDao.getVendingProductByName(product1.getName());
        assertNull("gum was removed, should be null.", retrievedProduct);
        
        retrievedProduct = testDao.getVendingProductByName(product2.getName());
        assertNull("skittles was removed, should be null.", retrievedProduct);
    }

    
   
    
}
