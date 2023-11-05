/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package object;

import entity.Entity;
import java.awt.Graphics2D;
import main.GamePanel;

/**
 *
 * @author kanas
 */
public class Osiris extends Entity {
    
    public Osiris(GamePanel gp){
        super(gp);
        name = "Osiris";
        image = setup("/image_object/osiris");
        collision = true;
    }

    @Override
    public void update(){}
    
    @Override
    public void draw(Graphics2D g2){
        g2.drawImage(image, x, y,48, 48, null);
    }

}