import java.awt.*;
import java.util.ArrayList;

public class FirstBoss extends Boos {
    public int w;
    public int h;
    public double x;
    public double xRunningSpeed;
    public double y;
    public double yRunningSpeed;
    public int jumpSpeed;
    public double G;

    public Block myBlock;
    public long timeFromJump;
    public long minTimeToJump;

    public long timeToSummonServant;
    public long minTimeToSummonServant;
    public int numberOfServant;

    public long minTimeToShot;
    public long timeFromShot;
    public int kolOfDrob;
    public int force;
    public int lengthForDrob;
    public int lengthForFast;

    public long timeToRegenerate;
    public long minTimeToRegenerate;
    public int maxXP;

    public Color c;
    public int level;

    public FirstBoss(Block s, int level) {
        super(s, level);
        this.level = level;
        c = Color.YELLOW;
        this.w = 150;
        this.h = 150;
        this.myBlock = s;
        this.minTimeToJump = 600 - 10 * level;
        this.minTimeToShot = 900 - 5 * level;
        this.x = s.x + s.w * 0.7 - (int) (300 * Math.random());
        this.y = s.y - h;
        xRunningSpeed = 0;
        yRunningSpeed = -1;
        jumpSpeed = 3;
        G = 0.002;


        kolOfDrob = 3 + (int) (level * 0.45);

        timeToRegenerate = 0;
        minTimeToRegenerate = 20000 - 200 * level;
        maxXP = 1500 + level * 500;
        xitPoint = maxXP;

        timeToSummonServant = 0;
        minTimeToSummonServant = 4000;
        numberOfServant = 7;

        lengthForDrob = 200 + level * 5;
        lengthForFast = 600 + level * 10;
        timeFromJump = 0;
        timeFromShot = 0;
        this.force = 15 + level;
        coinFromDeath = level * level * 40;
    }

    public void update(long dt, Man m, ArrayList<Enemy> enemies) {
        if ((enemies.size() < numberOfServant) && (timeToSummonServant > minTimeToSummonServant)) {
            enemies.add(new Enemy(myBlock, level).makeServant());
            timeToSummonServant = 0;
        }
        if ((timeToRegenerate > minTimeToRegenerate)) {
            xitPoint = Math.min((int) xitPoint + maxXP / 20, maxXP);
            timeToRegenerate = 0;
        }


        x += dt * xRunningSpeed;
        if (x < myBlock.x) {
            x = myBlock.x;
            xRunningSpeed = -xRunningSpeed;
        }
        if (x + w > myBlock.x + myBlock.w) {
            x = myBlock.x + myBlock.w - w;
            xRunningSpeed = -xRunningSpeed;
        }
        yRunningSpeed = yRunningSpeed + G * dt;
        y = y + yRunningSpeed;
        if (y + h > myBlock.y) {
            y = myBlock.y - h;
            yRunningSpeed = 0;
        }


        if ((timeFromJump > minTimeToJump) && (y == myBlock.y - h)) {
            if (Math.random() > 0.6) {
                jump(m);
            }
            timeFromJump = 0;
        }
        timeFromShot = timeFromShot + dt;
        timeFromJump = timeFromJump + dt;
        timeToSummonServant = timeToSummonServant + dt;
        timeToRegenerate = timeToRegenerate + dt;
    }

    public void jump(Man m) {
        yRunningSpeed = -jumpSpeed;
        xRunningSpeed = -((this.x + this.w / 2) - (m.x + m.w / 2)) * G / (2 * (jumpSpeed));
    }

    public Bullet fastShot(Man m, int x0, int y0) {
        Bullet test = new Bullet("enemy", 0.3, force);
        test.x = x0;
        test.y = y0;
        double l = Math.sqrt((test.x - m.x) * (test.x - m.x) + (test.y - m.y) * (test.y - m.y));
        test.vx = (m.x - x) / l;
        test.vy = (m.y - y) / l;
        return test;
    }

    public Bullet drobShot(Man m, int x0, int y0) {
        Bullet test = new Bullet("enemy", 0.1, force);

        test.x = x0;
        test.y = y0;
        double l = Math.sqrt((test.x - m.x) * (test.x - m.x) + (test.y - m.y) * (test.y - m.y));

        int q = 4;
        test.vx = (m.x - x) / l + (Math.random() - 0.5) / q;
        test.vy = (m.y - y) / l + (Math.random() - 0.5) / q;
        return test;
    }

    public Bullet slowShot(Man m, int x0, int y0) {
        Bullet test = new Bullet("enemy", 0.15);

        test.x = x0;
        test.y = y0;
        double l = Math.sqrt((test.x - m.x) * (test.x - m.x) + (test.y - m.y) * (test.y - m.y));
        test.vx = (m.x - x) / l;

        test.vy = (m.y - y) / l;
        return test;
    }

    public void draw(Graphics2D g2d) {
        g2d.setColor(c);
        g2d.fillRect((int) x, (int) y, w, h);
        g2d.setColor(Color.BLACK);
        g2d.drawRect((int) x, (int) y, w, h);
        g2d.setColor(Color.MAGENTA);
        g2d.fillRect((int) x, (int) y, (int) (w * xitPoint / maxXP), 3);
    }

    public boolean checkDeath(Bullet b) {
        if (b.from.equals("player")) {
            if ((b.x > x) && (b.x < x + w) && (b.y > y) && (b.y < y + h)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public void addBullet(ArrayList<Bullet> bullets, Man man) {
        if (timeFromShot > minTimeToShot) {
            if (Math.random() > 0.15) {
                if (whichShot(man).equals("fast")) {
                    bullets.add(fastShot(man, (int) x + 1 * w / 4, (int) y + 1 * h / 4));
                    bullets.add(fastShot(man, (int) x + 3 * w / 4, (int) y + 1 * h / 4));
                    bullets.add(fastShot(man, (int) x + 1 * w / 4, (int) y + 3 * h / 4));
                    bullets.add(fastShot(man, (int) x + 3 * w / 4, (int) y + 3 * h / 4));

                }
                if (whichShot(man).equals("slow")) {
                    bullets.add(slowShot(man, (int) x + 1 * w / 4, (int) y + 1 * h / 4));
                    bullets.add(slowShot(man, (int) x + 3 * w / 4, (int) y + 1 * h / 4));
                    bullets.add(slowShot(man, (int) x + 1 * w / 4, (int) y + 3 * h / 4));
                    bullets.add(slowShot(man, (int) x + 3 * w / 4, (int) y + 3 * h / 4));

                }
                if (whichShot(man).equals("drob")) {
                    for (int j = 0; j < kolOfDrob; j = j + 1) {
                        bullets.add(drobShot(man, (int) x + 1 * w / 4, (int) y + 1 * h / 4));
                        bullets.add(drobShot(man, (int) x + 3 * w / 4, (int) y + 1 * h / 4));
                        bullets.add(drobShot(man, (int) x + 1 * w / 4, (int) y + 3 * h / 4));
                        bullets.add(drobShot(man, (int) x + 3 * w / 4, (int) y + 3 * h / 4));
                    }
                }
            }
            timeFromShot = 0;
        }
    }
    public String whichShot(Man m){
        double l = Math.sqrt((m.x + (m.w / 2) - x - w / 2) * (m.x + (m.w / 2) - x - w / 2) + (m.y + (m.h / 2) - y - h / 2) * (m.y + (m.h / 2) - y - h / 2));
        if (l < lengthForFast) {
            if (l < lengthForDrob) {
                return "drob";
            } else {
                return "fast";
            }
        }else {
            return "noBullet";
        }
    }
}
