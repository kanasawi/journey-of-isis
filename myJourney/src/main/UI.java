/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import entity.Entity;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import object.Heart;
import object.Osiris;

/**
 *
 * @author kanas
 */
public class UI {
    
    GamePanel gp;
    Graphics2D g2;
    Font font;
    BufferedImage osirisImage, heart_full, heart_empty;
    public String message = "";
    public boolean messageOn = false;
    int messageCounter = 0;
    public String endText;
    public int CommandNum = 0;
    public double maxTime;
    public double currentTime;
    public double finalTime;
    
    public UI(GamePanel gp){
        this.gp = gp;
        setDefault();
        Osiris osiris = new Osiris(gp);
        osirisImage = osiris.image;
        Entity heart = new Heart(gp);
        heart_full = heart.image;
        heart_empty = heart.image2;
        try {
            InputStream is = getClass().getResourceAsStream("/font/DalelandsUncial.ttf");
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(UI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setDefault(){
        maxTime = 60;
        currentTime = maxTime;
        finalTime = currentTime;
        messageOn = false;
        messageCounter = 0;
        
    }
    public void draw(Graphics2D g2){
        this.g2 = g2;
        g2.setFont(font);
        g2.setColor(Color.WHITE);
        
        // TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }
        
        // PLAY STATE
        if(gp.gameState == gp.playState){
            drawPlayScreen();
            if(currentTime <= 0){
                gp.gameState = gp.endState;
                endText = "TIME'S UP!";
            }
        }
        
        // END STATE
        if(gp.gameState == gp.endState){
            drawEndScreen(endText);
        }
    }
    
    public void showMessage(String message){
        this.message = message;
        messageOn =true;
    }
    
    public void drawMessage(){
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,30));
        drawCenterScreen(message);
        messageCounter++;
        if(messageCounter > 60){
            messageCounter = 0;
            messageOn = false;
        }
    }
    
    public void drawScore(){
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30));
        g2.drawImage(osirisImage, gp.tileSize*12,0,null);
        g2.drawString("x "+gp.player.getScore(), gp.tileSize * 13, 35);
    }
    
    public void drawPlayerLife(){
        int x = gp.tileSize;
        int y = 0;
        int i = 0;
        // DRAW MAXHP
        while(i < gp.player.getmaxHP()){
            g2.drawImage(heart_empty, x, y, null);
            i++;
            x += gp.tileSize;
        }
        // RESET
        x = gp.tileSize;
        y = 0;
        i = 0;
            //DRAW CURRENT LIFE
        while(i < gp.player.getHP()){
            g2.drawImage(heart_full, x, y, null);
            i++;
            x += gp.tileSize;
        }
    }
    
    public void drawTime(){
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,30));
        if(gp.gameState == gp.playState){
            currentTime -= (double)1/60;
            finalTime = currentTime;
            g2.drawString("Time: "+String.format("%.0f",currentTime), gp.screenWidth/2-72 , 35);
        }
        if(gp.gameState == gp.endState){
            g2.drawString("Time: "+String.format("%.0f",finalTime), gp.screenWidth/2-72 , 35);
        }
        
    }
    
    public void drawTitleScreen(){
        // TITLE SCREEN
        g2.setColor(new Color(66,140,252,230));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        
        // TITLE NAME
        g2.setColor(Color.ORANGE);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,50));
        String text = "THE JOURNEY OF ISIS";
        int x = gp.screenWidth/2 - gp.tileSize*6;
        int y = gp.screenHeight/2 - gp.tileSize*3;
        g2.drawString(text, x, y);
        
        // ENTITY IMAGE
        x = gp.screenWidth/2 - gp.tileSize;
        y = gp.screenHeight/2 - gp.tileSize;
        g2.drawImage(gp.player.down1,x,y,gp.tileSize*2,gp.tileSize*2,null);
        x = gp.screenWidth/2 - gp.tileSize*4;
        y = gp.screenHeight/2 - gp.tileSize;
        g2.drawImage(gp.player.left1,x,y,gp.tileSize*2,gp.tileSize*2,null);
        x = gp.screenWidth/2 + gp.tileSize*2;
        y = gp.screenHeight/2 - gp.tileSize;
        g2.drawImage(gp.player.right1,x,y,gp.tileSize*2,gp.tileSize*2,null);
        
        // TITLE OPTION
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25));
        
        g2.setColor(Color.WHITE);
        text = "START GAME";
        x = gp.screenWidth/2 - 96;
        y = gp.screenHeight/2 + gp.tileSize*3;
        if(CommandNum == 0){
            g2.setColor(Color.ORANGE);
            g2.drawString(">", x-gp.tileSize, y);
        }
        g2.drawString(text, x, y);
        
        g2.setColor(Color.WHITE);
        text = "QUIT";
        x = gp.screenWidth/2-40;
        y = gp.screenHeight/2 + gp.tileSize*4;
        if(CommandNum == 1){
            g2.setColor(Color.ORANGE);
            g2.drawString(">", x-gp.tileSize, y);
        }
        g2.drawString(text, x, y);
    }
    
    public void drawPlayScreen(){
        drawScore();
        drawPlayerLife();
        drawTime();
        // MESSAGE
        if(messageOn == true){
            drawMessage();
        }
    }
    
    public void drawEndScreen(String endText){
        drawScore();
        drawPlayerLife();
        drawTime();
        g2.setColor(new Color(0,0,0,50));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,60));
        drawCenterScreen(endText);
        String text;
        int x;
        int y;
        // END OPTION
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 25));
        text = "RETRY";
        x = gp.screenWidth/2-35;
        y = gp.screenHeight/2 + gp.tileSize*2;
        if(CommandNum == 0){
            g2.setColor(Color.ORANGE);
            g2.drawString(">", x-gp.tileSize, y);
        }
        g2.drawString(text, x, y);
        
        g2.setColor(Color.WHITE);
        text = "BACK";
        x = gp.screenWidth/2-25;
        y = gp.screenHeight/2 + gp.tileSize*3;
        if(CommandNum == 1){
            g2.setColor(Color.ORANGE);
            g2.drawString(">", x-gp.tileSize, y);
        }
        g2.drawString(text, x, y);
    }
    
    public void drawCenterScreen(String text){
        int textLenght;
        int x;
        int y;
        textLenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth/2 - textLenght/2;
        y = gp.screenHeight/2 - gp.tileSize;
        g2.drawString(text, x, y);
    }
}
