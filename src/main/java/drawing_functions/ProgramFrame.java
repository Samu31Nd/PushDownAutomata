package drawing_functions;

import com.catppuccin.Flavour;
import com.catppuccin.Palette;
import org.example.Personal_utils;

import javax.swing.*;

public class ProgramFrame extends JFrame {

    Flavour colours = Palette.MOCHA;
    ProgramPanel panel;


    public ProgramFrame(){
        Personal_utils.createColors();
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
        this.setBackground(colours.getBase());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
