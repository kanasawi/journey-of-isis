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
public class Heart extends Entity{

    public Heart(GamePanel gp){
        super(gp);
        name = "Heart";
        image = setup("/image_object/heart_full");
        image2 = setup("/image_object/heart_empty");
    }

    @Override
    public void update(){}

    @Override
    public void draw(Graphics2D g2){}
    
    
}
