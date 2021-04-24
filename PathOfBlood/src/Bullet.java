import java.awt.*;

public class Bullet {
    public double x;
    public double y;
    public double vx;
    public double vy;
    public double speed;
    public int forсe;
    public String from = "";
    public Bullet(String f, double speed0){
        forсe = 20;
        from = f;
        speed = speed0;
    }
    public Bullet(String f, double speed0, int force0){
        this.forсe = force0;
        this.from = f;
        this.speed = speed0;
    }
    public void update(long dt) {
        x += dt * vx * speed;
        y += dt * vy * speed;
    }
    public void draw(Graphics2D g2d, Man man){
        int n = 6;
        if (from.equals("enemy")) {
            if (man.colorOfMan == Color.red){
                g2d.setColor(new Color(200,0,250));
            } else if (man.colorOfMan == Color.WHITE){
                g2d.setColor(new Color(0,255,0));
            } else if (man.colorOfMan == Color.BLUE){
                g2d.setColor(Color.YELLOW);
            } else {
                g2d.setColor(Color.RED);
            }
        }else {
            g2d.setColor(man.colorOfMan);
        }
        g2d.fillRect((int) (x - n/2), (int) (y - n/2), n, n);
        g2d.setColor(Color.BLACK);
        g2d.drawRect((int) (x - n/2), (int) (y - n/2), n, n);
    }
}
