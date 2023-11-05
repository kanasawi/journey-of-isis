/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package main;

import javax.swing.JFrame;

/**
 *
 * @author kanas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("The Journey of Isis");
        
        GamePanel gamePanel = new GamePanel();
        frame.add(gamePanel);
        
        frame.pack();
        
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        gamePanel.setupGame();
        gamePanel.startGameThread();
    }
    
}
