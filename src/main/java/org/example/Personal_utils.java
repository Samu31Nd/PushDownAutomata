package org.example;
import com.catppuccin.Color;
import com.catppuccin.Pair;
import com.catppuccin.Palette;

import java.util.*;

public class Personal_utils {
    static Queue<Color> colors;

    public String generateBinary() {
        return this.generateBinary(16,2);
    }

    public String generateBinary(int bound) {
        return this.generateBinary(bound,2);
    }

    public String generateBinary(int bound, int interval) {
        StringBuilder binary = new StringBuilder();
        int n = new Random().nextInt(bound);
        binary.append("0".repeat(n));
        //it adds or removes in an interval of [-2,2]
        n += new Random().nextInt(interval*2)-interval;
        if(n < 0) n*=-1;
        binary.append("1".repeat(n));
        return binary.toString();
    }
    public static void createColors(){
        colors = new ArrayDeque<>();
        List<Pair<String, Color>> l = Palette.MOCHA.toList();
        Color colorActual;
        Collections.shuffle(l);
        String []colorsOut = {
                "mantle", "crust", "surface2", "overlay2",
                "text", "subtext0", "surface0", "base",
                "subtext1", "overlay1", "surface1"
        };
        //get out mantle, crust, text, subtext0, surface0, base,subtext1
        for(Pair<String, Color> palette : l){
            colorActual = palette.getValue();
            colors.add(colorActual);
//            System.out.print(palette.getKey() + " - ");
//            System.out.println(colorActual.getHex());
            for(String s : colorsOut) {
                if (palette.getKey().equals(s)) {
//                    System.out.println("Removing: " + palette.getKey() + " - " + colorActual.getHex());
                    colors.remove(colorActual);
                }
            }
        }
    }
    public static Color getRandomColor(){
        Color c =  colors.remove();
        colors.offer(c);
        return c;
    }
}
