/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import entity.Seth;
import entity.Sphinx;
import object.Bridge;
import object.Osiris;

/**
 *
 * @author kanas
 */
public class AssetSetter {
    
    GamePanel gp;
    
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }
    
    public void setObject(){
        gp.obj[0] = new Bridge(gp);
        gp.obj[0].x = gp.tileSize * 15;
        gp.obj[0].y = gp.tileSize * 5;
        
        gp.obj[1] = new Osiris(gp);
        gp.obj[1].x = gp.tileSize * 11;
        gp.obj[1].y = gp.tileSize * 6;
        
        gp.obj[2] = new Osiris(gp);
        gp.obj[2].x = gp.tileSize * 11;
        gp.obj[2].y = gp.tileSize * 10;
        
        gp.obj[3] = new Osiris(gp);
        gp.obj[3].x = gp.tileSize * 9;
        gp.obj[3].y = gp.tileSize * 3;
        
        gp.obj[4] = new Osiris(gp);
        gp.obj[4].x = gp.tileSize * 5;
        gp.obj[4].y = gp.tileSize * 3;
        
        gp.obj[5] = new Osiris(gp);
        gp.obj[5].x = gp.tileSize * 1;
        gp.obj[5].y = gp.tileSize * 2;
        
        gp.obj[6] = new Osiris(gp);
        gp.obj[6].x = gp.tileSize * 1;
        gp.obj[6].y = gp.tileSize * 8;
        
        gp.obj[7] = new Osiris(gp);
        gp.obj[7].x = gp.tileSize * 2;
        gp.obj[7].y = gp.tileSize * 9;
        
    }
    
    public void setMonster(){
        gp.monster[0] = new Seth(gp);
        gp.monster[0].x = gp.tileSize * 8;
        gp.monster[0].y = gp.tileSize * 7;
        
        gp.monster[1] = new Seth(gp);
        gp.monster[1].x = gp.tileSize * 3;
        gp.monster[1].y = gp.tileSize * 1;
        
        gp.monster[2] = new Seth(gp);
        gp.monster[2].x = gp.tileSize * 4;
        gp.monster[2].y = gp.tileSize * 1;
        
        gp.monster[3] = new Sphinx(gp);
        gp.monster[3].x = gp.tileSize * 13;
        gp.monster[3].y = gp.tileSize * 3;
        
        gp.monster[4] = new Sphinx(gp);
        gp.monster[4].x = gp.tileSize * 2;
        gp.monster[4].y = gp.tileSize * 7;
        
    }
}
