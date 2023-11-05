/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.UtilityTool;

/**
 *
 * @author kanas
 */
abstract public class Entity {
    GamePanel gp;
    public int x,y;
    public int speed;
    public String direction = "down";
    public String name;
    public BufferedImage image,image2,up1,up2,down1,down2,left1,left2,right1,right2;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;
    public boolean collision = false;
    
    public Entity(GamePanel gp){
        this.gp = gp;
    }
    
    public BufferedImage setup(String imagePath){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;
        try{
            image = ImageIO.read(getClass().getResource(imagePath+".png"));
            image = uTool.scaledImage(image, gp.tileSize, gp.tileSize);
        }catch(IOException e){
            e.printStackTrace();
        }
        return image;
    }
    
    abstract public void update();
    abstract public void draw(Graphics2D g2);
    
}