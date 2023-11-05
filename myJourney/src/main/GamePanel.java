/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author kanas
 */
import entity.Entity;
import entity.Isis;
import entity.Monster;
import java.awt.*;
import javax.swing.*;
import tile.TileManager;

/**
 *
 * @author kanas
 */
public class GamePanel extends JPanel implements Runnable{
    // screen settings
    final int originalTileSize = 16; // 16*16 tile
    final int scale = 3;
    
    public final int tileSize = originalTileSize * scale; // 48*48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 768 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels
    
    //FPS
    int FPS = 60;
    
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;
    
    // ENTITY AND OBJECT
    public Isis player = new Isis(this,keyH);
    public Entity[] obj = new Entity[10];
    public Monster[] monster = new Monster[10];
   
    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int endState = 2;
    
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    
    public void setupGame(){
        aSetter.setObject();
        aSetter.setMonster();
        gameState = titleState;
        
    }
    
    public void restart(){
        player.setDefault();
        ui.setDefault();
        aSetter.setObject();
        aSetter.setMonster();
    }
    
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    
    @Override 
    public void run(){
        
        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        
        while(gameThread != null){
            currentTime = System.nanoTime();
            System.out.println(currentTime);
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if(delta >= 1){      
                // 1.UPDATE: update information ex. character positions
                update();
                // 2.DRAW: draw the screen with the updated information
                repaint();
                delta--;
            }
        }
    }
    
    public void update(){
        if(gameState == playState){
            player.update();
            for(int i = 0;i < monster.length; i++){
                if(monster[i] != null){
                    monster[i].update();
                }
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        // TITLE SCREEN
        if(gameState == titleState){
            ui.draw(g2);
        }
        // PLAY SCREEN
        else{
            // TILE
            tileM.draw(g2);
            
            // OBJECT
            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null){
                    obj[i].draw(g2);
                }
            }
            // MOSTER
            for(int i = 0; i < monster.length; i++){
                if(monster[i] != null){
                    monster[i].draw(g2);
                }
            }
            // PLAYER
            player.draw(g2);
            // UI
            ui.draw(g2);
        }
        g2.dispose();
    }
}
