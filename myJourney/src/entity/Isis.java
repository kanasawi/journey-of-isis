/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import main.GamePanel;
import main.KeyHandler;

/**
 *
 * @author kanas
 */
public class Isis extends Entity{
    
    KeyHandler keyH;
    private int score;
    private int maxHP;
    private int HP;    
    
    private int spriteCounter = 0;
    private int spriteNum = 1;
    public boolean invincible = false;
    public int invincibleCounter = 0;
    
    public Isis(GamePanel gp, KeyHandler keyH){
        super(gp);
        this.keyH = keyH;
        name = "Isis";
        setDefault();
        // RECTANGLE
        solidArea.x = 16;
        solidArea.y = 16;
        solidArea.width = 16;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }
    
    // SET DEFAULT
    public void setDefault(){
        score = 0;
        maxHP = 3;
        HP = maxHP;
        speed = 3;
        x = gp.tileSize * 13;
        y = gp.tileSize * 9;
        direction = "down";
        invincible = false;
        
    }
    
    // GET-SET METHOD
    public int getScore(){
        return score;
    }
    public int getmaxHP(){
        return maxHP;
    }
    public int getHP(){
        return HP;
    }

    public void setScore(int score){
        this.score = score;
    }
    public void setmaxHP(int maxHP){
        this.maxHP = maxHP;
    }
    public void setHP(int HP){
        this.HP = HP;
    }
    
    public void getImage(){
        up1 = setup("/image_entity/isis_up1");
        up2 = setup("/image_entity/isis_up2");
        down1 = setup("/image_entity/isis_down1");
        down2 = setup("/image_entity/isis_down2");
        left1 = setup("/image_entity/isis_left1");
        left2 = setup("/image_entity/isis_left2");
        right1 = setup("/image_entity/isis_right1");
        right2 = setup("/image_entity/isis_right2");
    }
    
    public void pickUpObject(int i){
        if(i != 999){
            String objectName = gp.obj[i].name;
            switch(objectName){
                case "Osiris":
                    score++;
                    gp.obj[i] = null; // REMOVE OBJECT
                    gp.ui.showMessage("You obtained "+objectName+"!");
                    break;
                case "Bridge":
                    if(score < 7){
                        gp.ui.showMessage("I can't leave now!");
                    }
                    else{
                        gp.gameState = gp.endState;
                        gp.ui.endText = "CONGRATULATION!";
                    }
                    break;
            }
        }
    }
    
    public void contactMonster(int i){
        if(i != 999){
            String monsterName = gp.monster[i].name;
            if(invincible == false){
                switch(monsterName){
                    case "Seth":
                        if(gp.ui.currentTime <= 10){
                            HP -= gp.monster[i].getATK();
                            invincible = true;
                        }
                        else{
                            HP -= gp.monster[i].getATK();
                            gp.ui.currentTime -= gp.monster[i].getDelayTime();
                            invincible = true;
                        }
                        break;
                    case "Sphinx": 
                        HP -= 1;
                        gp.ui.currentTime -= gp.monster[i].getDelayTime();
                        invincible = true;
                        break;
                }
                gp.ui.showMessage("Attacked by "+monsterName+" !");
            }
            
        }
    }
    
    @Override
    public void update(){
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){
            if(keyH.upPressed == true){
            direction = "up";           
            }
            else if(keyH.downPressed == true){
                direction = "down";           
            }
            else if(keyH.leftPressed == true){
                direction = "left";           
            }
            else if(keyH.rightPressed == true){
                direction = "right";            
            }

            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);
            
            // CHECK OBJECT COLLISION
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            // CHECK MONSTER COLLISION
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);
            
            // IF COLLISION IS FALSE, ISIS CAN MOVE
            if(collisionOn == false){
                switch(direction){
                case "up": y -= speed; break;
                case "down": y += speed; break;
                case "left": x -= speed; break;    
                case "right": x += speed; break;
                }
            }

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
        // SHADOW WHEN ATTACKED BY MONSTER
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
        
        if(HP <= 0){
            gp.gameState = gp.endState;
            gp.ui.endText = "GAME OVER";
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
        
        // SHADOW WHEN ATTACKED BY MONSTER
        if(invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.3f));
        }
        g2.drawImage(image, x, y, null);
        // RESET SETTING
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
    }
}
