package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.annotation.Target;

public class GamePanel extends JPanel implements Runnable{

    public static int width;
    public static int height;

    private Thread thread;
    private boolean running = false;
    private BufferedImage img;
    private Graphics2D graphics;

    public GamePanel(int width,int height) {
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width,height));
        setFocusable(true);
        requestFocus();
    }

    public void addNotify() {
        super.addNotify();
        if(thread==null) {
            thread = new Thread(this,"GameThread");
            thread.start();
        }
    }

    public void init() {
        running=true;
        img = new BufferedImage(width,height,BufferedImage.TYPE_INT_ARGB);
        graphics = (Graphics2D) img.getGraphics();
    }


    public void update() {

    }

    public void render() {
        if(graphics!=null) {
            graphics.setColor(new Color(66,134,244));
            graphics.fillRect(0,0,width,height);
        }
    }

    public void draw() {
        Graphics graphics2 = (Graphics) this.getGraphics();
        graphics2.drawImage(img,0,0,width,height,null);
        graphics2.dispose();
    }

    public void input() {

    }

    @Override
    public void run() {
        init();
        final double GAME_HERTZ = 60.0;
        final double TBU = 1000000000 / GAME_HERTZ; // Время обновления
        final int MUBR = 5; // Должен обновить после рендера
        double LastUpdateTime = System.nanoTime();
        double LastRenderTime;
        final double TARGET_FPS = 60;
        final double TOTAL_TIME_BEFORE_RENDER = 1000000000 / TARGET_FPS;
        int frameCount = 0;
        int lastSecondTime = (int) (LastUpdateTime/1000000000);
        int oldFrameCount = 0;

        while(running) {
            double now = System.nanoTime();
            int updateCount = 0;
            while(((now-LastUpdateTime)>TBU) && (updateCount<MUBR)) {
                update();
                input();
                LastUpdateTime += TBU;
                updateCount++;
            }
            if(now-LastUpdateTime > TBU) {
                LastUpdateTime = now - TBU;
            }

            input();
            render();
            draw();
            LastRenderTime = now;
            frameCount++;

            int thisSecond = (int) (LastUpdateTime / 1000000000);
            if(thisSecond > lastSecondTime) {
                if(frameCount != oldFrameCount) {
                    System.out.println("NewSecond " + thisSecond + " " + frameCount);
                    oldFrameCount = frameCount;
                }
                frameCount = 0;
                lastSecondTime  = thisSecond;
            }
            while(now - lastSecondTime < TOTAL_TIME_BEFORE_RENDER && now - LastUpdateTime < TBU) {
                Thread.yield();
                try {
                    Thread.sleep(1);
                }
                catch(Exception e ){
                    System.out.println("ERROR THREAD");
                }
                now = System.nanoTime();
            }
        }
    }
}
