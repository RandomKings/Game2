package game2d;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.jar.JarEntry;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font arial_40;
    BufferedImage  Image;
    JButton attackButton, Switch, Run;
    BufferedImage ballImage = getBallImage();
    public boolean messageOn = false;
    public String message = "";
    int messageCount = 0;
    public int spotCol = 0;
    public int spotRow = 0;


    public UI(GamePanel gp) {
        this.gp = gp;

        arial_40 = new Font("Arial", Font.PLAIN, 40);


    }


    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }


    public void draw(Graphics2D g2) {
        this.g2 = g2;

        g2.setFont(arial_40);
        g2.setColor(Color.white);

        //title state
        if (gp.gameState == gp.titleState){

            //title image
            g2.drawImage(getTitleImage(),0,0,gp.screenWidth,gp.screenHeight,null);

            //title name
            g2.setFont(new Font("Comic Sans MS",Font.BOLD,85));
            String text = "Pokemon Rip Off";
            int x = getXCentreText(text);
            int y = gp.screenHeight/4;
            g2.setColor(Color.darkGray);
            g2.drawString(text,x,y);

            //start text
            g2.setFont(g2.getFont().deriveFont(Font.ITALIC, 40));
            String text1 = "PRESS ENTER TO START";
            int x1 = getXCentreText(text1);
            int y1 = (gp.screenHeight/2) - 20;
            g2.setColor(Color.darkGray);
            g2.drawString(text1,x1,y1);


        }

        //play state
       if(gp.gameState == gp.playState) {
           //resize


           // Draw the resized image and the text
           g2.drawImage(ballImage, 0, 0,gp.tileSize*2,gp.tileSize*2,null);
           g2.drawString("x " + gp.player.numBalls, 74, 70);

       }

        //battle
        if (gp.gameState == gp.battleState) {
           battleScreen();
        }

        //bag state
        if (gp.gameState == gp.bagState){
            openBag();
        }

        //timer for message
        if (messageOn) {
            g2.setFont(g2.getFont().deriveFont(30F));
            g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);

            messageCount++;

            if (messageCount > 120) {
                messageCount = 0;
                messageOn = false;
            }
        }
    }

    public void battleScreen() {

        //BATTLE TITLE
        String text = "BATTLE";
        int x = getXCentreText(text);
        int y = gp.screenHeight/10;
        g2.setColor(Color.white);
        g2.drawString(text,x,y);

        //Battle Image
        g2.drawImage(getBackground(),0,0,gp.screenWidth + 30, gp.screenHeight - 150, null);

       // g2.drawImage(gp.player.playerPokemons.get(0).image, 30,30,100,100,null);



        //battle buttons
        // drawSubWindow(0,430,gp.screenWidth,150);
        Container con = new Container();
        JPanel choicePanel = new JPanel();
        choicePanel.setBounds(0,430,gp.screenWidth,150);
        choicePanel.setBackground(Color.red);
        con.add(choicePanel);
        attackButton = new JButton("Attack");
        attackButton.setBackground(Color.blue);
        attackButton.setActionCommand("attack");
        choicePanel.add(attackButton);
        attackButton.setVisible(true);
        choicePanel.setVisible(true);
        gp.add(con);





    }

    public void openBag(){
        int frameX = gp.tileSize*9;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize *6;
        int frameHeight = gp.tileSize*5;

        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        //spots
        final int spotXstart = frameX + 20;
        final int spotYstart= frameY + 20;
        int spotX = spotXstart;
        int spotY = spotYstart;

        //draw Pokemons
        for (int i =0; i<gp.player.playerPokemons.size(); i++){
            g2.drawImage(gp.player.playerPokemons.get(i).image, spotX,spotY,null);

            spotX += gp.tileSize;

            if (i == 4 || i == 9 || i== 14){
                spotX = spotXstart;
                spotY += gp.tileSize;
            }
        }

        //cursor
        int cursorX = spotXstart + (gp.tileSize * spotCol);
        int cursorY = spotYstart + (gp.tileSize * spotRow);
        int cursorWidth = gp.tileSize;
        int cursorHeight = gp.tileSize;

        //draw cursor
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(cursorX,cursorY,cursorWidth,cursorHeight,10,10);

        // Description
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameeHeight = gp.tileSize *3;
        drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameeHeight);

        // Description text
        int textX = dFrameX +20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(20F));

        int itemSpot = getItemSpot();

        if (itemSpot < gp.player.playerPokemons.size()){

            for (String line : gp.player.playerPokemons.get(itemSpot).description.split("\n")){
                g2.drawString(line,textX,textY);
                textY += 32;
            }

        }


    }

    public int getItemSpot(){
        int itemSpot = spotCol + (spotRow*5);
        return itemSpot;
    }
    public void drawSubWindow(int x, int y, int width, int height){

        Color c = new Color(0,0,0);
        g2.setColor(c);
        g2.fillRoundRect(x,y,width,height,35,35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5 ,width-10 ,height-10,25,25);
    }

    public BufferedImage getTitleImage(){
        try {
            Image = ImageIO.read(getClass().getResourceAsStream("/Maps/titlescreen.png"));


        }catch (IOException e){
            e.printStackTrace();
        } return Image;
    }
    public BufferedImage getBackground(){
        try {
            Image = ImageIO.read(getClass().getResourceAsStream("/Maps/background battle.png"));


        }catch (IOException e){
            e.printStackTrace();
        } return Image;
    }

    public BufferedImage getBallImage() {
        try {
            Image = ImageIO.read(getClass().getResourceAsStream("/Items/pokeball.png"));
        }catch (IOException e){
            e.printStackTrace();
        }return Image;
    }
    public BufferedImage resizeImage(BufferedImage image){
        image = getBallImage();
        image = new BufferedImage(gp.tileSize, gp.tileSize,BufferedImage.SCALE_DEFAULT);
        return image;
    }


    public int getXCentreText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
}
