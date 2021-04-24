import java.awt.event.MouseEvent;

public class Firearms {
    public boolean isBought;
    public boolean isRapidFire;
    public long minTimeToShot;
    public long timeFromShot;
    public int numberOfShots;
    public double spreadOfBullets; // Разброс пули;
    public double speedOfBullet;
    public String fromWho;
    public int force;
    public int minManaToUse;
    public int level;
    public String whichFirearms;
    public String whichAmmo;

    public Firearms(String whichFirearms, long minTimeToShot, int numberOfShots, double spreadOfBullets, double speedOfBullet, String fromWho, int force, boolean isRapidFire, int minManaToUse){
        this.isBought = false;
        this.minTimeToShot = minTimeToShot;
        this.timeFromShot = 99999;
        this.spreadOfBullets = spreadOfBullets;
        this.numberOfShots = numberOfShots;
        this.speedOfBullet = speedOfBullet;
        this.fromWho = fromWho;
        this.force = force;
        this.isRapidFire = isRapidFire;
        this.minManaToUse = minManaToUse;
        level = 0;
        this.whichFirearms = whichFirearms;
        whichAmmo = "Bullet";
    }

    public Bullet makeShot(MouseEvent e, int x, int y) {
        Bullet test = new Bullet(fromWho, speedOfBullet);
        test.x = x;
        test.y = y;
        int n = -7;
        int m = -7;
        double l = Math.sqrt((x - e.getX() + n) * (x - e.getX() + n) + (y - e.getY() + m) * (y - e.getY() + m));

        test.vx = (e.getX() - x + n) / l + (Math.random() - 0.5) / spreadOfBullets;
        test.vy = (e.getY() - y + m) / l + (Math.random() - 0.5) / spreadOfBullets;
        return test;
    }

    public Bullet makeShotBoss(int xTo,int yTo, int xFrom, int yFrom) {
        Bullet test = new Bullet(fromWho, speedOfBullet);
        test.x = xFrom;
        test.y = yFrom;
        int n = 0;
        int m = 0;
        double l = Math.sqrt((xFrom - xTo + n) * (xFrom - xTo + n) + (yFrom - yTo + m) * (yFrom - yTo + m));

        test.vx = (xTo - xFrom + n) / l + (Math.random() - 0.5) / spreadOfBullets;
        test.vy = (yTo - yFrom + m) / l + (Math.random() - 0.5) / spreadOfBullets;
        return test;
    }
    public boolean isUnlocked(){
        if (level == 0){
            return false;
        } else {
            return true;
        }
    }
    public void update(){
        if (whichFirearms.equals("Pst")){
            if (level == 2){
                force = 10;
                speedOfBullet = 0.17;
            }
            if (level == 3){
                force = 20;
                speedOfBullet = 0.17;
                spreadOfBullets = 5;
            }
            if (level == 4){
                force = 50;
                speedOfBullet = 0.2;
                spreadOfBullets = 5;
            }
            if (level == 5){
                force = 100;
                speedOfBullet = 0.2;
                spreadOfBullets = 4;
            }
        }
        if (whichFirearms.equals("Shtg")){
            if (level == 2){
                force = 20;
                speedOfBullet = 0.13;
            }
            if (level == 3){
                force = 50;
                speedOfBullet = 0.13;
                minTimeToShot = 600;
            }
            if (level == 4){
                force = 100;
                speedOfBullet = 0.15;
                minTimeToShot = 600;
            }
            if (level == 5){
                force = 500;
                speedOfBullet = 0.15;
                minTimeToShot = 600;
                numberOfShots = 5;
            }
        }
        if (whichFirearms.equals("Rfl")){
            if (level == 2){
                force = 50;
                speedOfBullet = 0.35;
            }
            if (level == 3){
                force = 100;
                minTimeToShot = 900;
                speedOfBullet = 0.4;
            }
            if (level == 4){
                force = 500;
                minTimeToShot = 900;
                speedOfBullet = 0.5;
            }
            if (level == 5){
                force = 1000;
                minTimeToShot = 900;
                minManaToUse = 2;
                speedOfBullet = 0.7;
            }
        }
        if (whichFirearms.equals("Mcng")){
            if (level == 2){
                force = 5;
                speedOfBullet = 0.21;
            }
            if (level == 3){
                force = 10;
                speedOfBullet = 0.21;
                minTimeToShot = 150;
            }
            if (level == 4){
                force = 20;
                speedOfBullet = 0.25;
                minTimeToShot = 150;
            }
            if (level == 5){
                force = 40;
                speedOfBullet = 0.25;
                minTimeToShot = 150;
                numberOfShots = 2;
            }
        }
        if (whichFirearms.equals("Flm")){
            if (level == 2){
                speedOfBullet = 0.2;
                force = 2;
            }
            if (level == 3){
                speedOfBullet = 0.23;
                force = 4;
                minTimeToShot = 25;
            }
            if (level == 4){
                speedOfBullet = 0.25;
                force = 8;
                minTimeToShot = 25;
            }
            if (level == 5){
                speedOfBullet = 0.27;
                force = 16;
                minTimeToShot = 20;
                minManaToUse = 1;
            }
        }
    }
}
