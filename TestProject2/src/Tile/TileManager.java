package Tile;

import Main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
     public Tile[] tile;
     public int mapTileNum[][];


    public int j;


     public TileManager(GamePanel gp){
         this.gp = gp;



         tile = new Tile[10];

         mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
         getTileImage();
         loadMap();
     }
     public void loadMap(){
         try {
             InputStream is = getClass().getResourceAsStream("/Map/World.txt");
             BufferedReader br = new BufferedReader(new InputStreamReader(is));

             int col = 0;
             int row = 0;
             while (col<gp.maxWorldCol && row<gp.maxWorldRow){
                 String line = br.readLine();
                 while (col<gp.maxWorldCol){
                     String numbers[] = line.split(" ");
                     int num = Integer.parseInt(numbers[col]);
                     mapTileNum[col][row]=num;
                     col++;
                 }
                 if (col == gp.maxWorldCol){
                     col=0;
                     row++;
                 }


             }
             br.close();


         } catch (Exception e) {

         }
     }
     public void getTileImage(){
         try {
             tile[0]= new Tile();
             tile[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/pixil-frame-0(2).png"));
             tile[0].collision = true;

             tile[1]= new Tile();
             tile[1].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/pixil-frame-0(3).png"));
             tile[1].collision = true;

             tile[2]= new Tile();
             tile[2].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/pixil-frame-0(4).png"));
             tile[2].collision = true;

             tile[3]= new Tile();
             tile[3].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/pixil-frame-0(6).png"));


         } catch (IOException e) {
             e.printStackTrace();
         }
     }
     public void draw(Graphics2D g2){
         int worldCol=0;
         int worldRow=0;
         while (worldCol<gp.maxWorldCol && worldRow<gp.maxWorldRow){
             int tileNum = mapTileNum[worldCol][worldRow];
             int worldX = worldCol * gp.tileSize;
             int worldY = worldRow * gp.tileSize;
             int screenX = worldX - gp.player.worldX + gp.player.screenX;
             int screenY = worldY - gp.player.worldY + gp.player.screenY;
             g2.drawImage(tile[tileNum].image,screenX,screenY,gp.tileSize, gp.tileSize, null);
             worldCol++;

             if (worldCol == gp.maxWorldCol){
                 worldCol=0;
                 worldRow++;

             }


         }



     }
    public void update(){

    }
}
