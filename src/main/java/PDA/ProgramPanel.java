package PDA;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgramPanel extends JPanel implements ActionListener {
    int in_elements;
    int out_elements;
    FontMetrics metrics;
    String message;
    Color Z0Color = new Color(250, 179, 135);
    Color XColor = new Color(137, 180, 250);
    static Font font = new Font("Roboto", Font.BOLD,17);
    static final int WIDTH = 400, HEIGHT = 600;
    static final int PADDLE_WIDTH = 80, PADDLE_HEIGHT = 40;
    static final Dimension PANEL_SIZE = new Dimension(WIDTH,HEIGHT);
    final int speed = 8;

    boolean adding = true;

    int []xArr;
    int y;

    int [] xMovements;

    int no_objects = 1;


    Timer timer;
    private void moveBall(){
        if(adding){
            for(int i = 0; i < no_objects; i++){
                if(xArr[i] >= (WIDTH-50)/2){
                    xMovements[i] *= 0;

                    if(i == no_objects-1){
                        createNewObject(no_objects++);
//                        System.out.println("No of objects: " + no_objects);
                    }
                    if(no_objects == in_elements +1){
//                        System.out.println("Stopped: " + out_elements);
                        adding = !adding;
                        xMovements[no_objects-1] = -speed;
                    }
                } else if( xArr[i] <= 0){
                    xMovements[i] *= -1;
                }
                xArr[i] += xMovements[i];
            }
            //if were not adding, then now we are removing
        } else {
            for(int i = 0; i <= no_objects;i++){
                if(xArr[i] <= 0){
                    xMovements[i] *= 0;
                    if(i == no_objects-1){
                        no_objects--;
//                        System.out.println("Iterating No objects: " + no_objects);
                        if(i == (in_elements-out_elements)){
                            timer.stop();
                            this.repaint();
                            Main.showFinalDialogWindow();
                        }
                        try{
                            xMovements[i-1] = -speed;
                        } catch(IndexOutOfBoundsException ignored) { }
                    }
                }
                xArr[i] += xMovements[i];
            }
        }



    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        Graphics2D circles = (Graphics2D) g;
        metrics = g.getFontMetrics(font);
        circles.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        circles.setStroke(new BasicStroke(2.0f));
        //draw background manually
        circles.setColor(Color.BLACK);
        circles.fillRect(0,0,WIDTH,HEIGHT);

        for(int i = 0; i < no_objects; i++){
            drawStack(circles,xArr[i],y-(i*45),i);
        }
    }

    void drawStack(Graphics2D g,int xPos,int yPos, int n){
        if(n == 0){
            g.setColor(Z0Color);
            message = "Z0";
        } else{
            g.setColor(XColor);
            message = "X";
        }
        g.setFont(font);
        g.fillRoundRect(xPos,yPos,PADDLE_WIDTH,PADDLE_HEIGHT,5,5);
        g.setColor(Color.BLACK);
        g.drawString(message,xPos+metrics.stringWidth(message) + 10,yPos + metrics.getHeight());
    }

    void createNewObject(int element){
        xArr[element] = 1;
        xMovements[element] = speed;
    }

    ProgramPanel() {
        y = HEIGHT-PADDLE_HEIGHT;
        xArr = new int[20];
        xMovements = new int[20];
        //Initializing the first object Z0
        createNewObject(0);
        this.setFocusable(true);
        this.setPreferredSize(PANEL_SIZE);

        timer = new Timer(10,this);
        timer.start();
    }

    public void setInOutElements(int in, int out){
        if(out > in){
            out = in+1;
        }
        this.in_elements = in+1;
        this.out_elements = out;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.moveBall();
        this.repaint();
    }
}
