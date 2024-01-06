package PDA;

import javax.swing.*;
import java.awt.*;

public class ProgramFrame extends JFrame {

    Color base = new Color(36, 39, 58);
    ProgramPanel panel;


    public ProgramFrame(){
    }

    public void start(int in, int out){
        panel = new ProgramPanel();

        this.setTitle("PDA");
        panel.setInOutElements(in,out);
        //this.setIconImage(new ImageIcon("src/main/resources/logo2.png").getImage());
        this.add(panel);
        this.setVisible(true);
        this.setResizable(false);
        this.pack();
        this.setBackground(base);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
