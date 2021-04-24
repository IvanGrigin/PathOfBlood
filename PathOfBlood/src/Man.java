import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Man {
    public int w;
    public int h;
    public double x;
    public double preX = 0;
    public double xStartSpeed;
    public double xRunningSpeed;
    public int xRunning; // -1=НАЛЕВО БЕЖИМ, 0=СТОИМ НА МЕСТЕ, +1=НАПРАВО БЕЖИМ
    public double forсeOfRub;// Сила трения, когда персонаж делает рывок
    public long timeFromJerk; // Время после последнего рывка
    public long minTimeToJerk; // Минимальное время для рывка
    public long maxJerkTime;
    public double jerkSpeed;

    public double y;
    public double preY = 0;
    public double yRunningSpeed;
    public int yRunning; // -1=НАЛЕВО Вверх, 0=СТОИМ НА МЕСТЕ, +1=Вниз БЕЖИМ
    public double G;
    public boolean death = false;
    public boolean isShifting = false;
    public double jumpSpeed;

    public Hook hook;
    public boolean hooking = false;
    public boolean noMove = false;

    public int armor;
    public boolean isShieldOn;// Показывает включен ли щит
    public Color colorOfMan;

    public long timeOfShield;
    public int maxTimeOfShield;

    public int xitPoint;
    public int maxXP;
    public int mana;
    public int maxMana;
    public long timeOfStaying;
    public long minTimeToRegenerateMana;
    public double speedOfRegenerateMana;
    public int coins;

    public Firearms gunShotGun;
    public Firearms gunRifle;
    public Firearms gunPistol;
    public Firearms gunMachineGun;
    public Firearms gunFlamethrower;
    public Firearms gunIsNow;
    public int levelOfXP;
    public int levelOfMana;
    public int levelOfSpeed;
    public int levelOfShieldAndHook;
    public int levelOfLuck;
    public int levelOfHook;
    public double luck;

    public int statPoints;
    public int experience;
    public int minExperienceToUpdate;


    public Man(double x0, double y0, int w0, int h0) {
        this.x = x0;
        this.y = y0;
        this.w = w0;
        this.h = h0;
        hook = null;

        armor = 7;
        isShieldOn = true;
        timeOfShield = 0;
        maxTimeOfShield = 4000;
        colorOfMan = Color.red;

        timeFromJerk = 5000;
        minTimeToJerk = 2500;
        maxJerkTime = 12500;
        jerkSpeed = 0.6;
        jumpSpeed = 4;

        maxXP = 300;
        xitPoint = maxXP;
        maxMana = 100;
        mana = maxMana;
        timeOfStaying = 0;
        minTimeToRegenerateMana = 7500;
        speedOfRegenerateMana = 0.04;
        coins = 500;

        statPoints = 2;
        experience = 0;
        minExperienceToUpdate = 9;

        gunPistol = new Firearms("Pst", 100, 1, 6, 0.15, "player", 15, false, 0);
        gunPistol.level = 1;
        gunShotGun = new Firearms("Shtg",750, 4, 3, 0.1, "player", 10, false, 5);
        gunRifle = new Firearms("Rfl",1000, 1, 9999, 0.3, "player", 30, false, 3);
        gunMachineGun = new Firearms("Mcng", 200, 1, 4, 0.19, "player", 3, true, 1);
        gunFlamethrower = new Firearms("Flm",35, 1, 2, 0.2, "player", 1, true, 2);
        gunIsNow = gunPistol;

        levelOfXP = 0;
        levelOfMana = 0;
        levelOfSpeed = 0;
        levelOfShieldAndHook = 0;
        levelOfLuck = 0;
        luck = 0;

        xStartSpeed = 0.1;
        xRunningSpeed = xStartSpeed;
        forсeOfRub = 0.002;
        yRunningSpeed = 2;
        G = 0.002;
    }

    public void newMan() {
        hook = null;
        xitPoint = maxXP;
        mana = maxMana;
        xRunningSpeed = xStartSpeed;
        yRunningSpeed = 2;
    }


    public void draw(Graphics2D g2d, int panelWidth, int panelHeight) {
        x = (panelWidth + x) % panelWidth;
        y = y % panelHeight;
        g2d.setColor(Color.RED);
        g2d.fillRect((int) x, (int) y, w, h);
        drawPlayer(g2d);
        if (hook != null) {
            hook.draw(this, g2d);
        }
        if (isShieldOn == true) {
            drawShield(g2d);
        }
    }

    public void drawShield(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.drawRect((int) x, (int) y, w, h);
        g2d.drawRect((int) x + 1, (int) y + 1, w - 2, h - 2);

        g2d.drawRect((int) x + 1, (int) y + 1, w - 2, 1);
        g2d.drawRect((int) x + 3, (int) y + 2, w - 6, 1);
        g2d.drawRect((int) x + 5, (int) y + 3, w - 10, 1);
        g2d.drawRect((int) x + 7, (int) y + 4, w - 14, 1);
        g2d.drawRect((int) x + 9, (int) y + 5, w - 18, 1);
    }

    public void drawPlayer(Graphics2D g2d) {
        g2d.setColor(colorOfMan);
        if (Math.sqrt((x - preX) * (x - preX) + (y - preY) * (y - preY)) > 2) {
            g2d.fillRect((int) preX + 1, (int) preY + 1, w - 2, h - 2);
        }
        ///
        g2d.setColor(colorOfMan);
        g2d.fillRect((int) x, (int) y, w, h);
        g2d.setColor(Color.yellow);
        g2d.fillRect((int) x + 2, (int) y + 4, w - 4, 4);
        g2d.setColor(Color.black);
        g2d.drawRect((int) x, (int) y, w, h);
    }


    public void startRunningLeft() {
        xRunning = -1;
    }

    public void startRunningRight() {
        xRunning = 1;
    }

    public void stopRunningLeft() {
        if (xRunning == -1) {
            xRunning = 0;
        }
    }

    public void stopRunningRight() {
        if (xRunning == 1) {
            xRunning = 0;
        }
    }

    public void jump() {
        yRunningSpeed = -jumpSpeed;
    }

    public void jerk() {
        xRunningSpeed = jerkSpeed;
    }

    public void update(long dt) {
        updateExperience();
        updateStats();

        timeFromJerk = Math.min(timeFromJerk + dt, maxJerkTime);
        gunIsNow.timeFromShot = gunIsNow.timeFromShot + dt;
        updateGunsTime(dt);
        updateGuns();
        preX = x;
        preY = y;

        if (hook != null) {
            if (hook.isSeize == true) {
                hook.vx = 0;
                hook.vy = 0;
                double l = Math.sqrt((x + (w / 2) - hook.x) * (x + w / 2 - hook.x) + (y + h / 2 - hook.y) * (y + (h / 2) - hook.y));
                noMove = true;
                if (l > w / 2 + 3) {
                    noMove = false;
                }
                if ((l > w / 2 + 3) && (noMove == false)) {
                    this.x += dt * hook.speed * (hook.x - (x + (w / 2))) / l;
                    this.yRunningSpeed = hook.speed * (hook.y - (y + (h / 2))) / l;
                    this.y += dt * hook.speed * (hook.y - (y + (h / 2))) / l;
                    if (hook.x - (x + (w / 2)) > 0) {
                        this.xRunning = 1;
                    } else {
                        this.xRunning = -1;
                    }
                    if (hook.y - (y + (h / 2)) > 0) {
                        this.yRunning = 1;
                    } else {
                        this.yRunning = -1;
                    }
                }
            }
            hook.update(dt, this);
            if (hook.moveAway == false) {
                double l = Math.sqrt((x + (w / 2) - hook.x) * (x + w / 2 - hook.x) + (y + h / 2 - hook.y) * (y + (h / 2) - hook.y));
                if (l < 20) {
                    hook = null;
                    noMove = true;
                    hooking = false;
                    noSpeeds();
                }
            }
        }
        if ((hook == null) || (hook.isSeize != true)) {
            xRunningSpeed = Math.max(xStartSpeed, (xRunningSpeed - forсeOfRub * dt));
            x += dt * xRunningSpeed * xRunning;
            y = y + yRunningSpeed;
            if (isShifting) {
                yRunningSpeed = 0.2;
            } else {
                yRunningSpeed = yRunningSpeed + G * dt;
            }
        }
        if (isShieldOn == true) {
            timeOfShield = timeOfShield + dt;
            if (timeOfShield > maxTimeOfShield) {
                isShieldOn = false;
                timeOfShield = 0;
            }
        }
        if ((Math.abs(x - preX) < 0.1) && (Math.abs(y - preY)  <0.1)){
            timeOfStaying = timeOfStaying + dt;
        } else {
            timeOfStaying = 0;
        }
        if (timeOfStaying > minTimeToRegenerateMana){
            mana = Math.min(maxMana, (int) (mana + speedOfRegenerateMana * dt));
        }
    }

    public boolean isTouch2(Block block) {
        if (((x + w > block.x) && (x < block.x + block.w)) && (y + h == block.y)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkDeath(Bullet b) {
        if ((b.x > x) && (b.x < x + w) && (b.y > y) && (b.y < y + h)) {
            return true;
        } else {
            return false;
        }
    }

    public void makeHook(MouseEvent e) {
        hook = new Hook(this, levelOfHook);
        int n = -7;
        int m = -7;
        double l = Math.sqrt((x + (w / 2) - e.getX() + n) * (x + w / 2 - e.getX() + n) + (y + h / 2 - e.getY() + m) * (y + (h / 2) - e.getY() + m));
        hook.vx = (e.getX() - x + n) / l;
        hook.vy = (e.getY() - y + m) / l;
        hook.goToX = e.getX();
        hook.goToY = e.getY();
    }

    public void addBullet(ArrayList<Bullet> bullets, MouseEvent e) {
        if (gunIsNow.minManaToUse == 0) {
            bullets.add(gunIsNow.makeShot(e, (int) x + w / 2, (int) y + h / 2));
            if (gunIsNow.isRapidFire == false) {
                new Thread(() -> {
                    new MakeSound().playSound("Sounds/Sound_Bullet.wav");
                }).start();
            }
        } else {
            if (mana >= gunIsNow.minManaToUse) {
                for (int i = 0; i < gunIsNow.numberOfShots; i = i + 1) {
                    bullets.add(gunIsNow.makeShot(e, (int) x + w / 2, (int) y + h / 2));
                }
                if (Math.random() > luck) {
                    mana = mana - gunIsNow.minManaToUse;
                }
                if (gunIsNow.isRapidFire == false) {
                    new Thread(() -> {
                        new MakeSound().playSound("Sounds/Sound_Bullet.wav");
                    }).start();
                }
            }
            gunIsNow.timeFromShot = 0;
        }
    }

    public void updateGunsTime(long dt) {
        gunPistol.timeFromShot = gunPistol.timeFromShot + dt;
        gunRifle.timeFromShot = gunRifle.timeFromShot + dt;
        gunShotGun.timeFromShot = gunShotGun.timeFromShot + dt;
        gunMachineGun.timeFromShot = gunMachineGun.timeFromShot + dt;
        gunFlamethrower.timeFromShot = gunFlamethrower.timeFromShot + dt;
    }
    public void updateGuns(){
        gunPistol.update();
        gunShotGun.update();
        gunRifle.update();
        gunMachineGun.update();
        gunFlamethrower.update();
    }

    public void noSpeeds() {
        xRunning = 0;
        yRunningSpeed = 0;
    }

    public void updateExperience(){
        boolean b = false;
        while (experience >= minExperienceToUpdate){
            experience = experience - minExperienceToUpdate;
            minExperienceToUpdate = (int) (minExperienceToUpdate * 3.5);
            statPoints = statPoints + 2;
            b = true;
        }
        if (b == true) {
            new Thread(() -> {
                new MakeSound().playSound("Sounds/Sounds_Level_Up.wav");
            }).start();
        }
    }

    public void updateStats(){
        // Этот метод обновит уровни способностей и персонажа
        for(int i = 0; i <= 5; i = i + 1){
            // обновляет жизнь
            if (levelOfXP == i){
                maxXP = 300 + i * 20;
                armor = Math.max(armor, 7 + i);
            }
        }
        for(int i = 0; i <= 5; i = i + 1){
            // обновляет ману
            if (levelOfMana == i){
                maxMana = 100 + i * 50;
                speedOfRegenerateMana = 0.04 + 0.01 * i;
                minTimeToRegenerateMana = 7500 - 200 * i;
            }
        }

        for(int i = 0; i <= 5; i = i + 1){
            // обновляет удачу
            if (levelOfLuck == i){
                luck = i * 0.08;
            }
        }

        // Обновляет скорости перемещений
        if (levelOfSpeed == 1){
            xStartSpeed = 0.12;
        } else if (levelOfLuck == 2){
            jumpSpeed = 4.5;
        } else if (levelOfLuck == 3){
            xStartSpeed = 0.16;
        } else if (levelOfLuck == 4){
            jumpSpeed = 5;
        } else if (levelOfLuck == 5){
            jerkSpeed = 0.8;
        }

        // Обновляет шит, крюк и броню
        if (levelOfShieldAndHook == 1){
            maxTimeOfShield = 5000;
        } else if (levelOfShieldAndHook == 2){
            levelOfHook = 1;
        } else if (levelOfShieldAndHook == 3){
            maxTimeOfShield = 5500;
        } else if (levelOfShieldAndHook == 4){
            armor = 15;
        } else if (levelOfShieldAndHook == 5){
            levelOfHook = 2;
        }
    }
}
