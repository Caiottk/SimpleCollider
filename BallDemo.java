import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import java.util.Iterator;

/**
 * Class BallDemo - provides two short demonstrations showing how to use the 
 * Canvas class. 
 *
 * @author Michael Kolling and David J. Barnes
 * @version 2008.03.30
 */

public class BallDemo   
{
    private Canvas myCanvas;
    private Random random;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 601, 501);
        myCanvas.setVisible(true);
        random = new Random();
    }

    /**
     * Demonstrate some of the drawing operations that are
     * available on a Canvas object.
     */
    public void drawDemo()
    {
        myCanvas.setFont(new Font("helvetica", Font.BOLD, 14));
        myCanvas.setForegroundColor(Color.red);

        myCanvas.drawString("We can draw text, ...", 20, 30);
        myCanvas.wait(1000);

        myCanvas.setForegroundColor(Color.black);
        myCanvas.drawString("...draw lines...", 60, 60);
        myCanvas.wait(500);
        myCanvas.setForegroundColor(Color.gray);
        myCanvas.drawLine(200, 20, 300, 50);
        myCanvas.wait(500);
        myCanvas.setForegroundColor(Color.blue);
        myCanvas.drawLine(220, 100, 370, 40);
        myCanvas.wait(500);
        myCanvas.setForegroundColor(Color.green);
        myCanvas.drawLine(290, 10, 320, 120);
        myCanvas.wait(1000);

        myCanvas.setForegroundColor(Color.gray);
        myCanvas.drawString("...and shapes!", 110, 90);

        myCanvas.setForegroundColor(Color.red);

        // the shape to draw and move
        int xPos = 10;
        Rectangle rect = new Rectangle(xPos, 150, 30, 20);

        // move the rectangle across the screen
        for(int i = 0; i < 200; i ++) {
            myCanvas.fill(rect);
            myCanvas.wait(10);
            myCanvas.erase(rect);
            xPos++;
            rect.setLocation(xPos, 150);
        }
        // at the end of the move, draw once more so that it remains visible
        myCanvas.fill(rect);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.drawLine(50, ground, 550, ground);

        // crate and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.blue, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.red, ground, myCanvas);
        ball2.draw();
        BouncingBall ball3 = new BouncingBall(100, 30, 30, Color.green, ground, myCanvas);
        ball3.draw();
        // make them bounce
        boolean finished =  false;
        while(!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();

            ball3.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 && ball3.getXPosition() >= 550) {
                finished = true;
            }
        }
        ball.erase();
        ball2.erase();
        ball3.erase();
    }

    public Rectangle drawFrame(){
        Dimension dimension = myCanvas.getSize();
        Rectangle rect = new Rectangle(20, 20, (int)dimension.getWidth()-40, (int)dimension.getHeight()-40);
        myCanvas.draw(rect);
        return rect;

    }

    public void boxBounce(){
        Rectangle rect = drawFrame();   //o retangulo apaga em algumas bordas quando algumas bolinhas colidem
        ArrayList<BoxBall> boxBall = new ArrayList();
        Scanner sc = new Scanner(System.in);
        System.out.print("Write the balls number: ");
        int ballsNumber = sc.nextInt();
        while(ballsNumber!=0){
            int i=0;
            boxBall.add(new BoxBall(random.nextInt((int)rect.getWidth())+20, random.nextInt((int)rect.getHeight())+20, 20, gerarCorAleatoriamente() , rect, myCanvas, random.nextInt(51)+5, random.nextInt(51)+5));
            boxBall.get(i).draw();
            i++;
            ballsNumber--;
        }
        
        boolean finished =  false;
        while(!finished) {    //ainda não implementei uma saída desse laço, então o programa funciona indefinadamente 
            myCanvas.wait(50);                  // small delay
            Iterator<BoxBall> it = boxBall.iterator();
            while(it.hasNext())
                it.next().move();
        }
        sc.close();
    }

    private Color gerarCorAleatoriamente(){  
        Random randColor = new Random();   
        int r = randColor.nextInt(256);  
        int g = randColor.nextInt(256);  
        int b = randColor.nextInt(256);  
        return new Color(r, g, b);  
    }  
}
