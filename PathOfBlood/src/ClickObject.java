import java.awt.*;
import java.awt.event.MouseEvent;

public class ClickObject {
    public int x;
    public int y;
    public int w;
    public int h;
    public Color c;
    public boolean coloring;
    public String string;
    public int number;

    public ClickObject(int number, int x, int y, int w, int h, Color c, String s){
        this.number = number;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.c = c;
        this.coloring = false;
        this.string = s;
    }
    public boolean checkClick(MouseEvent e){
        if ((this.x < e.getX())&&(this.x + this.w > e.getX())&&(this.y < e.getY())&&(this.y + this.h > e.getY())){
            return true;
        } else {
            return false;
        }
    }
    public void draw(Graphics2D g2d){
        if (coloring) {
            g2d.setColor(c);
        } else {
            g2d.setColor(Color.white);
        }
        g2d.fillRect(x,y,w,h);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x,y,w,h);
        g2d.drawString(string, x+2, (int) y + h/2);
    }
}
