package com.company;

import javax.swing.*;

public class Window extends JFrame {

    public Window() {
        setTitle("2DGame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(new GamePanel(1280,720));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
