package com.hyf.tank.frame;

import com.hyf.tank.mode.GameMode;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @author hyf
 * @version [版本号, 2020/5/20]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class TankFrame extends Frame {


    public static final TankFrame INSTANCE = new TankFrame();

    private  GameMode gameMode = new GameMode();

    public static final int GAME_WIDTH = 800;

    public static final int GAME_HEIGHT = 600;

    private TankFrame() {
        this.setTitle("tank war");
        this.setLocation(400, 100);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.addKeyListener(new TankKeyListener());
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics graphics) {
        gameMode.paint(graphics);
    }

    public GameMode getGameMode() {
        return gameMode;
    }


    private class TankKeyListener extends KeyAdapter implements Serializable {

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            if (keyCode == KeyEvent.VK_S) {
                save();
            } else if (keyCode == KeyEvent.VK_L) {
                load();
            }
            gameMode.getMyTank().keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            gameMode.getMyTank().keyReleased(e);
        }

    }
    String fileName = "C:/Users/hyf/Desktop/tank.dat";

    private void save(){

        File file = new File(fileName);
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (
                OutputStream op = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(op);
        ){
            oos.writeObject(this.gameMode);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void load(){
        File file = new File(fileName);
        try (
                InputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fileInputStream);
        ){
            GameMode gameMode2 = (GameMode)ois.readObject();
            this.gameMode = gameMode2;
        }catch (Exception e){
            e.printStackTrace();
        }

    }



}
