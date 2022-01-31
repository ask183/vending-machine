/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.vendingmachine.dao;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author abekoppal
 */
public class VendingMachineAuditDaoFileImpl implements VendingMachineAuditDao {
    
        public static final String AUDIT_FILE = "audit.txt";
        
    
        @Override
        public void writeAuditEntry(String entry) throws VendingMachinePersistenceException {
        PrintWriter out;
        
        try{
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        }catch(IOException e){
            throw new VendingMachinePersistenceException("Couldn't Audit");
        }
        
        //We've now created file connection
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }
}
    

