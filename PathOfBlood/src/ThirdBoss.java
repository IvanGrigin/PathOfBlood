import java.awt.*;
import java.util.ArrayList;

public class ThirdBoss extends Boos{
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

    public Firearms gunIsNow;

    public long timeToRegenerate;
    public long minTimeToRegenerate;
    public int maxXP;

    public Color c;
    public int level;

    public ThirdBoss(Block s, int level) {
        super(s, level);
        this.level = level;
        c = Color.YELLOW;
        this.w = 150;
        this.h = 150;
        this.myBlock = s;
        this.minTimeToJump = 600 - 10 * level;
        this.x = s.x + s.w * 0.6;
        this.y = s.y - h;
        xRunningSpeed = 0;
        yRunningSpeed = -1;
        jumpSpeed = 3;
        G = 0.002;

        timeToRegenerate = 0;
        minTimeToRegenerate = 20000 - 100 * level;
        maxXP = 2000 + level * 700;
        xitPoint = maxXP;

        timeToSummonServant = 0;
        minTimeToSummonServant = 4000;
        numberOfServant = 7;

        gunIsNow = new Firearms("Gun", 800,4,10,0.2, "enemy", 10, false, 0);
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
        gunIsNow.timeFromShot = gunIsNow.timeFromShot + dt;
        timeFromJump = timeFromJump + dt;
        timeToSummonServant = timeToSummonServant + dt;
        //timeToRegenerate = timeToRegenerate + dt;
    }

    public void jump(Man m) {
        yRunningSpeed = -jumpSpeed;
        xRunningSpeed = -((this.x + this.w / 2) - (m.x + m.w / 2)) * G / (2 * (jumpSpeed));
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
        if (gunIsNow.timeFromShot > gunIsNow.minTimeToShot) {
            if (Math.random() > 0.6) {
                for (int i = 0; i < gunIsNow.numberOfShots; i = i + 1){
                    bullets.add(gunIsNow.makeShotBoss((int) man.x,(int) man.y, (int)x + (w/2),(int)( y + h/2)));
                }
                gunIsNow.timeFromShot = 0;
            }

        }
    }
}
