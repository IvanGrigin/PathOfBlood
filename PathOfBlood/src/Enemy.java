import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Enemy {
    public int w;
    public int h;
    public double x;
    public double xRunningSpeed;
    public int xRunning; // -1=НАЛЕВО БЕЖИМ, 0=СТОИМ НА МЕСТЕ, +1=НАПРАВО БЕЖИМ
    public double y;
    public double yRunningSpeed;
    public int yRunning; // -1=НАЛЕВО Вверх, 0=СТОИМ НА МЕСТЕ, +1=Вниз БЕЖИМ
    public double G;

    public Block myBlock;
    public long timeFromJump;
    public long minTimeToJump;

    public long minTimeToShot;
    public long timeFromShot;
    public int kolOfDrob;
    public int force;
    public int lengthForDrob;
    public int lengthForFast;

    public int xitPoint;
    public int maxXP;

    public Color c;
    public int coinFromDeath;
    public int experienceFromDeath;
    public int level;

    public Enemy(Block s, int level) {
        double d = Math.random();
        if (d < 0.33){
            c = new Color(70,40,40);
        } else if (d < 0.67) {
            c = new Color(70,70,70);
        } else {
            c = new Color(50,40,40);
        }

        int z = (int) (18 + Math.random() * 4);
        this.w = z;
        this.h = z;
        if (s.w == 20){
            w = 20;
            h = 20;
        }
        this.myBlock = s;
        this.minTimeToJump = 3000;
        this.minTimeToShot = 1200 - level * 20;
        this.x = s.x + Math.random()*(s.w - this.w) ;
        this.y = s.y - h;
        xRunning = 1;
        xRunningSpeed = Math.random()/10 + 0.05;
        yRunningSpeed = 0;
        G = 0.002;

        kolOfDrob = 6;
        this.level = level;

        if (level < 10) {
            maxXP = 30;
            coinFromDeath = 7;
            experienceFromDeath = (int)  (Math.random()*5 + 30);
        } else if(level < 20){
            maxXP = 100;
            coinFromDeath = 27;
            experienceFromDeath = (int)  (Math.random()*10 + 100);
        } else if(level < 30){
            maxXP = 300;
            coinFromDeath = 57;
            experienceFromDeath = (int)  (Math.random()*30 + 300);
        } else if(level < 40){
            maxXP = 900;
            coinFromDeath = 107;
            experienceFromDeath = (int)  (Math.random()*100 + 900);
        } else if(level < 50){
            maxXP = 1500;
            coinFromDeath = 257;
            experienceFromDeath = (int)  (Math.random()*300 + 1500);
        }
        xitPoint = maxXP;

        lengthForDrob = 170;
        lengthForFast = 400;
        timeFromJump = 0;
        timeFromShot = 0;
        this.force = 20;

        if (Math.random() > 0.95){
            makeGold();
        }
    }
    public Enemy makeGold(){
        coinFromDeath = (int) (coinFromDeath * 3.5);
        maxXP =  (int) (maxXP * 2);
        xitPoint = maxXP;
        c = new Color(255,215,0); // цвет золота
        minTimeToShot = 1000;
        xRunningSpeed = xRunningSpeed - 0.03;
        force = 37;
        return this;
    }
    public Enemy makeTurrel(){
        xRunningSpeed = 0;
        yRunningSpeed = 0;
        minTimeToJump = 99999999;
        maxXP = 150 + level;
        xitPoint = maxXP;
        kolOfDrob = 7;
        c = new Color(150,150,150);
        force = 12 + (int) (0.1 * level);
        lengthForDrob = 30;
        lengthForFast = 600;
        minTimeToShot = 450;
        return this;
    }

    public Enemy makeServant(){
        minTimeToJump = 1000;
        maxXP = 375 + level;
        xitPoint = maxXP;
        kolOfDrob = 0;
        coinFromDeath = 500;
        force = 0;
        lengthForDrob = 50;
        lengthForFast = 300;
        minTimeToShot = 9999999;
        return this;
    }


    public void update(long dt) {
        x += dt * xRunningSpeed * xRunning;
        if (x < myBlock.x) {
            x = myBlock.x;
            xRunning = 1;
        }
        if (x + w > myBlock.x + myBlock.w) {
            x = myBlock.x + myBlock.w - w;
            xRunning = -1;
        }
        if (Math.random() < 1/100){
            xRunning = (-1) * xRunning;
        }
        y = y + yRunningSpeed;
        yRunningSpeed = yRunningSpeed + G * dt;
        if (y + h > myBlock.y) {
            y = myBlock.y - h;
            yRunningSpeed = 0;
        }
        if ((timeFromJump > minTimeToJump)&&(y == myBlock.y - h)){
            if(Math.random() > 0.6){
                jump();
            }
            timeFromJump = 0;
        }
        timeFromShot = timeFromShot + dt;
        timeFromJump = timeFromJump + dt;
    }

    public void jump() {
        yRunningSpeed = -1.3;
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
    public Bullet fastShot(Man m){
        Bullet test = new Bullet("enemy", 0.3, force);
        test.x = x + w / 2;
        test.y = y + h / 2;
        double l = Math.sqrt((x + (w / 2) - m.x)*(x + w / 2 - m.x) + (y + h / 2 - m.y)*(y + (h / 2) - m.y));
        test.vx = (m.x - x) / l;
        test.vy = (m.y - y) / l;
        return test;
    }
    public Bullet drobShot(Man m){
        Bullet test = new Bullet("enemy", 0.1, force);
        test.x = x + w / 2;
        test.y = y + h / 2;
        double l = Math.sqrt((x + (w / 2) - m.x)*(x + w / 2 - m.x) + (y + h / 2 - m.y)*(y + (h / 2) - m.y));

        int q = 4;
        test.vx = (m.x - x) / l + (Math.random() - 0.5)/q;
        test.vy = (m.y - y) / l + (Math.random() - 0.5)/q;
        return test;
    }
    public Bullet slowShot(Man m){
        Bullet test = new Bullet("enemy", 0.15, force);
        test.x = x + w / 2;
        test.y = y + h / 2;
        double l = Math.sqrt((x + (w / 2) - m.x)*(x + w / 2 - m.x) + (y + h / 2 - m.y)*(y + (h / 2) - m.y));
        test.vx = (m.x - x) / l;
        test.vy = (m.y - y) / l;
        return test;
    }

    public void draw(Graphics2D g2d, Man man) {
        g2d.setColor(c);
        g2d.fillRect((int) x, (int) y, w, h);
        if (man.colorOfMan == Color.red){
            g2d.setColor(new Color(200,0,250));
        } else if (man.colorOfMan == Color.WHITE){
            g2d.setColor(new Color(0,255,0));
        } else if (man.colorOfMan == Color.BLUE){
            g2d.setColor(Color.YELLOW);
        } else {
            g2d.setColor(Color.RED);
        }

        g2d.drawRect((int) x, (int) y, w, h);
        g2d.fillRect((int)x,(int)y,(int)(w * xitPoint/maxXP), 3);
    }

    public boolean checkDeath(Bullet b) {
        if(b.from.equals("player")) {
            if ((b.x > x) && (b.x < x + w) && (b.y > y) && (b.y < y + h)) {
                return true;
            } else {
                return false;
            }
        } else{
            return false;
        }
    }
    public void addBullet(ArrayList<Bullet> bullets, Man man){
        if (timeFromShot > minTimeToShot) {
            if (Math.random() > 0.15) {
                if (whichShot(man).equals("fast")) {
                    bullets.add(fastShot(man));
                }
                if (whichShot(man).equals("slow")) {
                    bullets.add(slowShot(man));
                }
                if (whichShot(man).equals("drob")) {
                    for (int j = 0; j < kolOfDrob; j = j + 1) {
                        bullets.add(drobShot(man));
                    }
                }
            }
            timeFromShot = 0;
        }
    }
}
