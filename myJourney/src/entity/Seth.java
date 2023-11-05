/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import main.GamePanel;

/**
 *
 * @author kanas
 */
public class Seth extends Monster{
    
    public Seth(GamePanel gp){
        super(gp);
        name = "Seth";
        speed = 2;
        setATK(1);
        setDelayTime(5);
        solidArea.x = 5;
        solidArea.y = 16;
        solidArea.width = 38;
        solidArea.height = 32;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;     
        getImage();
    }
    public void getImage(){     
        up1 = setup("/image_entity/seth_down1");
        up2 = setup("/image_entity/seth_down2");
        down1 = setup("/image_entity/seth_down1");
        down2 = setup("/image_entity/seth_down2");
        left1 = setup("/image_entity/seth_down1");
        left2 = setup("/image_entity/seth_down2");
        right1 = setup("/image_entity/seth_down1");
        right2 = setup("/image_entity/seth_down2");
    }
}
