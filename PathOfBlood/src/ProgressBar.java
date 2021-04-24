import java.awt.*;

public class ProgressBar {
    public int x;
    public int y;
    public int w;
    public int h;
    public Color c;
    public double maxOfBar;
    public double nowOfBar;
    public String text;

    public ProgressBar(int x, int y, int w, int h, Color c, double max, String s0){
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.c = c;
        this.maxOfBar = max;
        this.text = s0;
    }
    public void draw(Graphics2D g2d){
        g2d.setColor(Color.WHITE);
        g2d.fillRect(x,y,w,h);
        g2d.setColor(c);
        g2d.fillRect(x,y,Math.min((int)(w * nowOfBar / maxOfBar),(int) w), h);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(x,y,w,h);
        g2d.drawString("  " + nowOfBar, x + 2, y+h-2);
        g2d.setColor(Color.BLACK);
        g2d.drawString(text,x-19, y+h-1);
        g2d.setColor(Color.white);
        g2d.drawString(text,x-20, y+h-2);

    }

}