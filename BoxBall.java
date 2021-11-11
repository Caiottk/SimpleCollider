import java.awt.*;
import java.awt.geom.*;

public class BoxBall
{
    private int ballDegradation = 2;
    private Ellipse2D.Double circle;
    private Color color;
    private int diameter;
    private int xPosition;
    private int yPosition;
    private Rectangle rect;      
    private Canvas canvas;
    private int ySpeed = 1;
    private int xSpeed = 1;

    public BoxBall(int xPos, int yPos, int ballDiameter, Color ballColor,
                        Rectangle rect, Canvas drawingCanvas, int xSpeed,
                        int ySpeed)
    {
        xPosition = xPos;
        yPosition = yPos;
        color = ballColor;
        diameter = ballDiameter;
        this.rect=rect;
        canvas = drawingCanvas;
        this.xSpeed=xSpeed;
        this.ySpeed=ySpeed;
    }

    /**
     * Draw this ball at its current position onto the canvas.
     **/
    public void draw()
    {
        canvas.setForegroundColor(color);
        canvas.fillCircle(xPosition, yPosition, diameter);
    }

    /**
     * Erase this ball at its current position.
     **/
    public void erase()
    {
        canvas.eraseCircle(xPosition, yPosition, diameter);
    }    

    /**
     * Move this ball according to its position and speed and redraw.
     **/
    public void move()  //um colisor apenas entre as dimensões do retangulo, implementar colisão entre as bolinhas é o próximo passo
    {
        // remove from canvas at the current position
        erase();
            
        // compute new position
        
        yPosition += ySpeed;
        xPosition += xSpeed;
        if(yPosition >= (rect.getHeight() - diameter) && ySpeed > 0) {
            yPosition = (int)(rect.getHeight()-2);
            ySpeed = -ySpeed ; 
        }
        else if(yPosition <= (rect.getY() + diameter) && ySpeed < 0) {
            yPosition = (int)(rect.getY()+2);
            ySpeed = -1*ySpeed ; 
        }
        else if(xPosition >= (rect.getWidth() - diameter) && xSpeed > 0) {
            xPosition = (int)(rect.getWidth()-2);
            xSpeed = -xSpeed ; 
        }
        else if(xPosition <= (rect.getX() + diameter) && xSpeed < 0) {
            xPosition = (int)(rect.getX()+2);
            xSpeed = -1*xSpeed ; 
        }
        // draw again at new position
    draw();
    }    

    /**
     * return the horizontal position of this ball
     */
    public int getXPosition()
    {
        return xPosition;
    }

    /**
     * return the vertical position of this ball
     */
    public int getYPosition()
    {
        return yPosition;
    }
}
