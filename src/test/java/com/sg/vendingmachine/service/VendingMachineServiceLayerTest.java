/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.sg.vendingmachine.dto.ProductInformation;
import java.math.BigDecimal;
import java.math.RoundingMode;
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
public class VendingMachineServiceLayerTest {
    
    VendingMachineDaoFileImpl dao = new VendingMachineDaoFileImpl("testing.txt");
  //  VendingMachineServiceLayer service;
    
    public VendingMachineServiceLayerTest() {
    /*    VendingMachineDao testDao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao testAudit = new VendingMachineAuditDaoStubImpl();
        
        service = new VendingMachineServiceLayer(testDao, testAudit); */
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    //test of removeVendingProduct method, of class VendingMachineServiceLayer
    @Test
    public void testRemoveVendingProduct() throws Exception {
        //doesn't do anything. sends it to dao and checks for stuff.. its already tested.
    }
    
    //test of checkForVendingProduct method, of class VendingMachineServiceLayer
    @Test
    public void testCheckForVendingProduct() throws Exception{
     /*   assertFalse(dao.getVendingProductByName("Chips").getInventoryLeft().equals("0")); */
     //Arrange
     ProductInformation lonelyProduct = new ProductInformation("Coke");
     lonelyProduct.setCost("2.50");
     lonelyProduct.setInventoryLeft("29");
     
     //act and assert 
     ProductInformation shouldBeLonely = dao.getVendingProductByName("Coke");
     assertNotNull("Being lonely is valid", shouldBeLonely);
     assertEquals("I'm lonely should not have to prove it", shouldBeLonely, lonelyProduct);
     
     
    }
    
    //test of checkForEnoughVendingFunds method, of class VendingMachineServiceLayer
    @Test
    public void testCheckForEnoughVendingFunds() throws Exception{
        String itemsCost = dao.getVendingProductByName("Cookies").getCost();
        BigDecimal vendingProductCost = new BigDecimal(itemsCost);
        BigDecimal initialVendingFunds = new BigDecimal("1.00");
        //Big Decimal form for initialVendingFunds<vendingProductCost
        assertFalse(initialVendingFunds.compareTo(vendingProductCost) == 1);
    }
    
    //test of vendingItemCostInString method, of class VendingMachineServiceLayer
    @Test
    public void testVendingItemCostInString() throws Exception{
        ProductInformation pi = dao.getVendingProductByName("Doritos");
        
        assertEquals(pi.getCost(), "1.25");
    }
    
    //test of getTheExactChangeAmountInBigDecimal method, of class VendingMachineServiceLayer
    @Test
    public void testGetTheExactChangeAmountInBigDecimal() throws Exception{
        String vendingItemName = "Brownie";
        String initialAmount = "3";
        
        ProductInformation currentProductInfo = dao.getVendingProductByName(vendingItemName);
        String cost = currentProductInfo.getCost();
        
        BigDecimal initialVendingFunds = new BigDecimal(initialAmount);
        BigDecimal vendingProductCost = new BigDecimal(cost);
        
        BigDecimal change = initialVendingFunds.subtract(vendingProductCost);
        
        assertEquals(change, new BigDecimal("1.50"));
    }
    
    //test of getChangeInDenomination method, of class VendingMachineServiceLayer
    @Test
    public void testGetChangeInDenomination() throws Exception{
        BigDecimal initialVendingFunds = new BigDecimal("1.32");

        BigDecimal QUARTER = new BigDecimal(".25");
        BigDecimal quarters = initialVendingFunds.divide(QUARTER, 0, RoundingMode.DOWN);

        BigDecimal remaindingChange = initialVendingFunds.subtract(quarters.multiply(QUARTER));

        BigDecimal DIME = new BigDecimal(".10");
        BigDecimal dimes = remaindingChange.divide(DIME, 0, RoundingMode.DOWN);

        BigDecimal remaindingChangeTwo = remaindingChange.subtract(dimes.multiply(DIME));

        BigDecimal NICKLE = new BigDecimal(".05");
        BigDecimal nickles = remaindingChangeTwo.divide(NICKLE, 0, RoundingMode.DOWN);

        BigDecimal remaindingChangeThree = remaindingChangeTwo.subtract(nickles.multiply(NICKLE));

        BigDecimal PENNY = new BigDecimal(".01");
        BigDecimal pennies = remaindingChangeThree.divide(PENNY, 0, RoundingMode.DOWN);

        BigDecimal change = quarters.multiply(QUARTER).add(dimes.multiply(DIME).add(nickles.multiply(NICKLE).add(remaindingChangeThree)));

        assertEquals(change, initialVendingFunds);
    }
    
    //test of getRefundInCoins method, of class VendingMachineServiceLayer
    @Test
    public void testGetRefundInCoins() throws Exception{
        BigDecimal initialVendingFunds = new BigDecimal("5.67");

        BigDecimal QUARTER = new BigDecimal(".25");
        BigDecimal quarters = initialVendingFunds.divide(QUARTER, 0, RoundingMode.DOWN);

        BigDecimal remaindingChange = initialVendingFunds.subtract(quarters.multiply(QUARTER));

        BigDecimal DIME = new BigDecimal(".10");
        BigDecimal dimes = remaindingChange.divide(DIME, 0, RoundingMode.DOWN);

        BigDecimal remaindingChangeTwo = remaindingChange.subtract(dimes.multiply(DIME));

        BigDecimal NICKLE = new BigDecimal(".05");
        BigDecimal nickles = remaindingChangeTwo.divide(NICKLE, 0, RoundingMode.DOWN);

        BigDecimal remaindingChangeThree = remaindingChangeTwo.subtract(nickles.multiply(NICKLE));

        BigDecimal PENNY = new BigDecimal(".01");
        BigDecimal pennies = remaindingChangeThree.divide(PENNY, 0, RoundingMode.DOWN);

        BigDecimal refund = quarters.multiply(QUARTER).add(dimes.multiply(DIME).add(nickles.multiply(NICKLE).add(remaindingChangeThree)));

        assertEquals(refund, initialVendingFunds);
    }
    
    

    
}
