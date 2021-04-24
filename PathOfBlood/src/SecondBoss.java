import java.awt.*;
import java.util.ArrayList;

public class SecondBoss extends Boos {
    public int startRadius;
    public double radius;
    public double x;
    public double prex;
    public double xRunningSpeed;
    public double y;
    public double prey;
    public double yRunningSpeed;
    public double forсeOfRub;// Сила трения, когда персонаж делает рывок
    public double jerkSpeed;
    public double speed;

    public Block myBlock;

    public long timeFromJerk = 0;
    public long minTimeToJerk;


    public long timeToRegenerate;
    public long minTimeToRegenerate;
    public int maxXP;
    public int force;

    public Color c;
    public int level;

    public SecondBoss(Block s, int level) {
        super(s, level);

        c = Color.YELLOW;
        this.x = 100 + (int) (800 * Math.random());
        this.y = 100 + (int) (600 * Math.random());

        this.startRadius = 50;
        this.radius = startRadius;
        this.myBlock = s;
        this.minTimeToJerk = 2700;

        xRunningSpeed = 0;
        yRunningSpeed = -1;
        jerkSpeed = 0.4;
        speed = jerkSpeed;
        forсeOfRub = jerkSpeed / (minTimeToJerk - 700);

        this.level = level;

        timeToRegenerate = 0;
        minTimeToRegenerate = 17000;
        maxXP = 500 * level;
        xitPoint = maxXP;
        force = 10;

        coinFromDeath = 1500 + 500 * level;
    }

    public void update(long dt, Man m, ArrayList<Enemy> enemies) {
        prex = x;
        prey = y;
        if ((timeToRegenerate > minTimeToRegenerate)) {
            xitPoint = Math.min((int) (xitPoint + 0.02 * maxXP), maxXP);
            timeToRegenerate = 0;
        }
        if (timeFromJerk > minTimeToJerk) {
            double l = Math.sqrt((x - m.x) * (x - m.x) + (y - m.y) * (y - m.y));
            xRunningSpeed = (m.x - x) / l;
            yRunningSpeed = (m.y - y) / l;
            speed = jerkSpeed;
            timeFromJerk = 0;
        }
        x += dt * xRunningSpeed * speed;
        y += dt * yRunningSpeed * speed;
        speed = Math.max(0.05, speed - forсeOfRub * dt);

        timeToRegenerate = timeToRegenerate + dt;
        timeFromJerk = timeFromJerk + dt;
    }

    public void draw(Graphics2D g2d) {
        radius = Math.max(startRadius * xitPoint / maxXP, 10);
        g2d.setColor(c);
        g2d.fillOval((int) (prex - radius + 3), (int) (prey - radius + 3), (int) (2 * radius - 6), (int) (2 * radius - 6));

        g2d.setColor(c);
        g2d.fillOval((int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius));
        g2d.setColor(Color.WHITE);
        g2d.drawOval((int) (x - radius), (int) (y - radius), (int) (2 * radius), (int) (2 * radius));
        g2d.setColor(Color.BLACK);
        g2d.drawOval((int) (x - radius + 1), (int) (y - radius + 1), (int) (2 * radius - 2), (int) (2 * radius - 2));

    }

    public boolean checkDeath(Bullet b) {
        if (b.from.equals("player")) {
            double l = Math.sqrt((x - b.x) * (x - b.x) + (y - b.y) * (y - b.y));
            if (l <= radius) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean touchMan(Man man) {
        double l = Math.sqrt((x - (man.x + man.w/2)) * (x - (man.x + man.w/2)) + (y - (man.y + man.h/2)) * (y - (man.y + man.h/2)));
        if (l <= radius + 10) {
            man.xitPoint = man.xitPoint - force;
            return true;
        } else {
            return false;
        }
    }

}
