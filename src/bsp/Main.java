/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsp;

import bsp.Controller.BSPController;

/**
 *
 * @author Afzal
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        BSPController BSPController = new BSPController();
        BSPController.loginControl();//Calls the logincontrol in the controller
        
    }
    
}
