/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendingMachineController;
import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.sg.vendingmachine.service.VendingMachineServiceLayer;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendingMachineView;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author abekoppal
 */
public class App {
    public static void main(String[] args){
    UserIO myIo = new UserIOConsoleImpl();
    VendingMachineView myView = new VendingMachineView(myIo);
    VendingMachineDao myDao = new VendingMachineDaoFileImpl();
    
    VendingMachineAuditDao myAudit = new VendingMachineAuditDaoFileImpl();
    VendingMachineServiceLayer myService = new VendingMachineServiceLayer(myDao, myAudit);
    VendingMachineController controller = new VendingMachineController(myView, myService);
        controller.run(); 
   /*
        ApplicationContext appContext
                = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        VendingMachineController controller = appContext.getBean("controller", VendingMachineController.class);
        controller.run();   */
    }
}   
