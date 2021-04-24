import java.awt.*;
import java.util.ArrayList;

public class Boos{
    public int xitPoint = 1;
    public int maxXP = 1;
    public int coinFromDeath;
    public int experienceFromDeath;
    public Boos(Block s, int level) {
        experienceFromDeath = (int) (100 * level * (Math.random() + 0.5) + 1000);
    }
    public void update(long dt, Man m, ArrayList<Enemy> enemies){}
    public boolean checkDeath(Bullet b) {
        return false;
    }
    public void addBullet(ArrayList<Bullet> bullets, Man man) {}
    public void draw(Graphics2D g2d){}
    public boolean touchMan(Man man){return false;}
}
