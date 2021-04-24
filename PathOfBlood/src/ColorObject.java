import java.awt.*;
import java.awt.event.MouseEvent;

public class ColorObject {
    public int x;
    public int y;
    public int w;
    public int h;
    public Color c;
    public int number;

    public ColorObject(int number, int x, int y, int w, int h, Color c){
        this.number = number;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.c = c;
    }
    public boolean checkClick(MouseEvent e){
        if ((this.x < e.getX())&&(this.x + this.w > e.getX())&&(this.y < e.getY())&&(this.y + this.h > e.getY())){
            return true;
        } else {
            return false;
        }
    }
    public void draw(Graphics2D g2d){
        g2d.setColor(c);
        g2d.fillRect(x,y,w,h);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x,y,w,h);
    }
}
