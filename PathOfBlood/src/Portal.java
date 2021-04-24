import java.awt.*;

public class Portal {
    public int x;
    public int y;
    public int h;
    public int w;
    public boolean visiable;

    public Portal(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        visiable = true;
    }
    public boolean checkManInPortal(Man m){
        if((m.x + m.w > x)&&(m.x < x + w)&&(m.y + m.h > y)&&(m.y < y + h)){
            return true;
        }else {
            return false;
        }
    }
    public void draw(Graphics2D g2d, Man man) {
        if (man.colorOfMan == Color.red){
            g2d.setColor(new Color(200,0,250));
        } else if (man.colorOfMan == Color.WHITE){
            g2d.setColor(new Color(0,255,0));
        } else if (man.colorOfMan == Color.BLUE){
            g2d.setColor(Color.YELLOW);
        } else {
            g2d.setColor(Color.RED);
        }
        g2d.fillRect(x,y,w,h);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x,y,w,h);
        g2d.drawRect(x + 1,y + 1,w - 2,h - 2);
        g2d.drawRect(x + 2,y + 2,w - 4,h - 4);
    }

}
