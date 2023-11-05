/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import main.GamePanel;

/**
 *
 * @author kanas
 */
abstract public class Monster extends Entity {
    
    private int ATK;
    private int delayTime;
    
    private int spriteCounter = 0;
    private int spriteNum = 1;
    private int actionLockCounter = 0;
    
    public Monster(GamePanel gp) {
        super(gp);
    }
    // GET-SET METHOD
    public int getATK(){
        return ATK;
    }
    public int getDelayTime(){
        return delayTime;
    }
    public void setATK(int ATK){
        this.ATK = ATK;
    }
    public void setDelayTime(int delayTime){
        this.delayTime = delayTime;
    }
    
    public void setAction(){
        actionLockCounter++;
        if(actionLockCounter == 60){
            Random random = new Random();
            int i = random.nextInt(100)+1; // from 1 to 100
            if(i<=25){
                direction = "up";
            }
            if(i>25 && i<=50){
                direction = "down";
            }
            if(i>50 && i<=75){
                direction = "left";
            }
            if(i>75 && i<=100){
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
    @Override
    public void update(){
        setAction();
        // CHECK COLLISION
        collisionOn = false;
        gp.cChecker.checkTile(this);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.monster);
        boolean contactPlayer = gp.cChecker.checkPlayer(this);
        
        if(contactPlayer == true){
            if(gp.player.invincible == false){
                switch(name){
                    case "Seth":
                        if(gp.ui.currentTime <= 10){
                            gp.player.setHP(gp.player.getHP() - ATK);
                            gp.player.invincible = true; 
                        }
                        else{
                            gp.player.setHP(gp.player.getHP() - ATK);
                            gp.ui.currentTime -= delayTime;
                            gp.player.invincible = true;
                        }
                        
                        break;
                    case "Sphinx": 
                        gp.player.setHP(gp.player.getHP() - ATK);
                        gp.ui.currentTime -= delayTime;
                        gp.player.invincible = true;
                        break;
                }
                gp.ui.showMessage("Attacked by "+name);
            }
        }
        
        if(collisionOn == false){
            switch(direction){
            case "up": y -= speed; break;
            case "down": y += speed; break;
            case "left": x -= speed; break;    
            case "right": x += speed; break;            
            }
        }
        // CHECK OBJECT COLLISION
        gp.cChecker.checkObject(this, false);
        
        spriteCounter++;
        if(spriteCounter > 10){
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
    @Override
    public void draw(Graphics2D g2){
        BufferedImage image = null;
        switch(direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }
        g2.drawImage(image,x,y,gp.tileSize,gp.tileSize,null);
    }
}
