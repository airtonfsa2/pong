/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br;

import br.display.Display;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author airto
 */
public class Game implements Runnable {
    
    private Display display;
    private Thread thread;
    private boolean running = false;

    public Game() {
        display = new Display("Pong", 400, 300);
    }

    @Override
    public void run() {
        init();
        int FPS = 60;
        double timePerTick = 1000000000 / 60;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
                
        while(running){
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            lastTime = now;
            
            if(delta >= 1){
               update();
               render();
               delta--;
            }
                       
        }
        stop();
    }
    
        private void init() {
        
    }

    private void update() {
      
    }

    private void render() {
        BufferStrategy bs = display.getBufferStrategy();
        if(bs == null){
            display.createBufferStrategy();
            bs = display.getBufferStrategy();
        }
        
        Graphics g = bs.getDrawGraphics();
        
        g.setColor(Color.RED);
        g.fillRect(10, 10, 50, 50);
        
        g.dispose();
        bs.show();
        

    }
    
    public synchronized void start(){
        if(thread != null) return;
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    
    public synchronized void stop(){
        if(thread == null) return;
        try {
            thread.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
