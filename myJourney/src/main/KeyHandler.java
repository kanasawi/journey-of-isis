/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author kanas
 */
public class KeyHandler implements KeyListener{
    
    GamePanel gp;
    public boolean upPressed, downPressed, leftPressed, rightPressed;
    
    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        // TITLE STATE
        if(gp.gameState == gp.titleState){
            if(code==KeyEvent.VK_W || code==KeyEvent.VK_UP){
                gp.ui.CommandNum--;
                if(gp.ui.CommandNum < 0){
                    gp.ui.CommandNum = 1;
                }
            }
            if(code==KeyEvent.VK_S || code==KeyEvent.VK_DOWN){
                gp.ui.CommandNum++;
                if(gp.ui.CommandNum > 1){
                    gp.ui.CommandNum = 0;
                }
            }
            if(code==KeyEvent.VK_ENTER){
                if(gp.ui.CommandNum == 0){
                    gp.gameState = gp.playState;
                }
                if(gp.ui.CommandNum == 1){
                    System.exit(0);
                }
            }
        }

        // PLAY STATE
        if(gp.gameState == gp.playState){
            if(code==KeyEvent.VK_W || code==KeyEvent.VK_UP){
                upPressed = true;
            }
            if(code==KeyEvent.VK_S || code==KeyEvent.VK_DOWN){
                downPressed = true;
            }
            if(code==KeyEvent.VK_A || code==KeyEvent.VK_LEFT){
                leftPressed = true;
            }
            if(code==KeyEvent.VK_D || code==KeyEvent.VK_RIGHT){
                rightPressed = true;
            }
        }
        
        // END STATE
        if(gp.gameState == gp.endState){
            if(code == KeyEvent.VK_W || code==KeyEvent.VK_UP){
                gp.ui.CommandNum--;
                if(gp.ui.CommandNum < 0){
                    gp.ui.CommandNum = 1;
                }
            }
            if(code == KeyEvent.VK_S || code==KeyEvent.VK_DOWN){
                gp.ui.CommandNum++;
                if(gp.ui.CommandNum > 1){
                    gp.ui.CommandNum = 0;
                }
            }
            if(code == KeyEvent.VK_ENTER){
                if(gp.ui.CommandNum == 0){
                    gp.gameState = gp.playState;
                    gp.restart();
                }
                else if(gp.ui.CommandNum == 1){
                    gp.gameState = gp.titleState;
                    gp.restart();
                    gp.ui.CommandNum = 0;
                }
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        
        if(code==KeyEvent.VK_W || code==KeyEvent.VK_UP){
            upPressed = false;
        }
        if(code==KeyEvent.VK_S || code==KeyEvent.VK_DOWN){
            downPressed = false;
        }
        if(code==KeyEvent.VK_A || code==KeyEvent.VK_LEFT){
            leftPressed = false;
        }
        if(code==KeyEvent.VK_D || code==KeyEvent.VK_RIGHT){
            rightPressed = false;
        }
    }
    
}
