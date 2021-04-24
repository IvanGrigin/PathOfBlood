import java.awt.*;
import java.awt.event.MouseEvent;

public class ColorsOfMan {
    public ColorObject object_red;
    public ColorObject object_blue;
    public ColorObject object_white;
    public ColorObject object_black;
    public int x;
    public int y;
    public int w;
    public int h;


    public ColorsOfMan(int x, int y, int w, int h){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;

        int n = 33;
        object_red = new ColorObject(1,x + n,y + h - 20,30,30, Color.red);
        object_blue = new ColorObject(2,x + n + 40,y + h - 20,30,30, Color.blue);
        object_white = new ColorObject(3,x + n + 80,y + h - 20,30,30, Color.white);
        object_black = new ColorObject(4,x + n + 120,y + h - 20,30,30, Color.GREEN);
    }
    public void draw(Graphics2D g2d, Man man){
        drawBigMan(g2d, man);
        object_red.draw(g2d);
        object_blue.draw(g2d);
        object_white.draw(g2d);
        object_black.draw(g2d);
    }
    public void drawBigMan(Graphics2D g2d, Man man){
        int n = 32;
        g2d.setColor(man.colorOfMan);
        g2d.fillRect((int) x + n, (int) y + n , w - 2 * n, h - 2 * n);

        g2d.setColor(Color.yellow);
        int m = (int) (h/10);
        g2d.fillRect((int) x + n + m, (int) y + n + m, w - 2 * m - 2 * n, 2 * m);
        g2d.setColor(Color.black);
        for (int i = 0; i < 3; i = i + 1){
            g2d.drawRect((int) x + n + i, (int) y + n + i, w - 2 * (i + n), h - 2 * (i + n));
        }
    }
    public void CheckClick(MouseEvent e, Man man){
        if(object_red.checkClick(e)){
            man.colorOfMan = object_red.c;
            new Thread(() -> {
                new MakeSound().playSound("Sounds/Sounds_ChangeColor.wav");
            }).start();
        }
        if(object_blue.checkClick(e)){
            man.colorOfMan = object_blue.c;
            new Thread(() -> {
                new MakeSound().playSound("Sounds/Sounds_ChangeColor.wav");
            }).start();
        }
        if(object_white.checkClick(e)){
            man.colorOfMan = object_white.c;
            new Thread(() -> {
                new MakeSound().playSound("Sounds/Sounds_ChangeColor.wav");
            }).start();
        }
        if(object_black.checkClick(e)){
            man.colorOfMan = object_black.c;
            new Thread(() -> {
                new MakeSound().playSound("Sounds/Sounds_ChangeColor.wav");
            }).start();
        }
    }
}
