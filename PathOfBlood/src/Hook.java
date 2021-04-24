import java.awt.*;

public class Hook {
    public double goToX;
    public double x;
    public double goToY;
    public double y;
    public double vx;
    public double vy;
    public double speed;
    public boolean moveAway;
    public boolean isSeize;
    public int maxLength;
    public int level;

    public Hook(Man m, int level){
        this.x = m.x + m.w/2;
        this.y = m.y + m.h/2;
        speed = 0.23 + 0.02 * level;
        moveAway = true;
        maxLength = 130 + level * 30;
        this.level = level;
    }
    public void update(long dt, Man m) {
        double l = Math.sqrt((m.x + m.w / 2 - this.x) * (m.x + m.w / 2 - this.x) + (m.y + m.h / 2 - this.y) * (m.y + m.h / 2 - this.y));
        moveAway = (true && moveAway);
        if(l > maxLength){
            moveAway = false;
        }
        if (moveAway == true) {
            x += dt * vx * speed;
            y += dt * vy * speed;
        }
        if (moveAway == false) {
            vx = (m.x + (m.w / 2) - this.x) / l;
            vy = (m.y + (m.h / 2) - this.y) / l;
            x += dt * vx * speed;
            y += dt * vy * speed;
        }
    }
    public void draw(Man m, Graphics2D g2d){
        g2d.setColor(m.colorOfMan);
        g2d.fillRect((int) this.x-2, (int) this.y-2, 4,4);
        g2d.drawLine((int)m.x + m.w/2, (int) m.y + m.h/2, (int) this.x, (int) this.y);
    }
}
