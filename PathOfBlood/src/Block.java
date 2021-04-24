import java.awt.*;

public class Block {
    int x;
    int y;
    int h;
    int w;
    int numberOfEnemeis;

    public Block(int x ,int y, int w, int h, int number){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        numberOfEnemeis = number;
    }

    public void draw(Graphics2D g2d, Man man){
        g2d.setColor(Color.BLACK);
        g2d.fillRect(x,y,w,h);
        if (man.colorOfMan == Color.red){
            g2d.setColor(new Color(200,0,250));
        } else if (man.colorOfMan == Color.WHITE){
            g2d.setColor(new Color(0,255,0));
        } else if (man.colorOfMan == Color.BLUE){
            g2d.setColor(Color.YELLOW);
        } else {
            g2d.setColor(Color.RED);
        }
        g2d.drawRect(x,y,w,h);
    }

}