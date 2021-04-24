import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Frame extends JFrame implements KeyEventDispatcher, MouseListener, MouseMotionListener {

    public MouseEvent eDragged;
    public MouseEvent eClick;
    public int number = 0; // Сколько врагов убил игрок
    public int numberOfLevel = 1;
    public Man man;
    public int lastButton;
    private long previousWorldUpdateTime; // Храним здесь момент времени когда физика мира обновлялась в последний раз
    private long previousTapUpdateTime; // Храним здесь момент времени когда игрок нажимал на клавиатуру в последний раз

    ArrayList<Enemy> enemies;
    ArrayList<Boos> bosses;
    ArrayList<Block> blocks;
    ArrayList<Bullet> bullets;
    Portal portal;
    boolean isNewGame;
    boolean isGameWin;

    public ProgressBar barOfHealth;
    public ProgressBar barOfMana;
    public ProgressBar barOfShield;
    public ProgressBar barOfJerk;

    public Inventar inventar = new Inventar(1000, 330, man);
    public int maxXpOfBoss = -1;
    public int nowXpOfBoss = -1;


    public Frame() throws IOException {
        setSize(1000, 1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        newGame();

        addMouseListener(this);
        addMouseMotionListener(this);
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(this);
    }

    public void newGame() {
        if (numberOfLevel > 50){
            isGameWin = true;
        } else {
            isGameWin = false;
        }
        maxXpOfBoss = -1;
        nowXpOfBoss = -1;

        lastButton = 0;
        enemies = new ArrayList<>();
        blocks = new ArrayList<>();
        bullets = new ArrayList<>();
        bosses = new ArrayList<>();
        if (man == null) {
            man = new Man(100, 900, 20, 20);
        }
        man.x = 100;
        man.y = 900;
        man.newMan();
        man.isShieldOn = true;
        man.timeOfShield = 0;


        barOfHealth = new ProgressBar(32, 80, 150, 20, Color.red, man.maxXP, "XP");
        barOfMana = new ProgressBar(32, 105, 150, 20, Color.cyan, man.maxMana, "MN");
        barOfJerk = new ProgressBar(32, 130, 150, 20, Color.yellow, man.maxJerkTime, "Jrk");
        barOfShield = new ProgressBar(32, 155, 150, 20, Color.MAGENTA, man.maxTimeOfShield, "Sld");

        enemies.clear();
        blocks.clear();
        bullets.clear();
        bosses.clear();

        blocks.add(new Block(0, getHeight() - 50, getWidth() + 1000, 150, 0));
        blocks.add(new Block(-30, -10, getWidth() + 30, 110, 0));
        if (numberOfLevel % 5 == 0) {
            if (numberOfLevel % 10 == 5) {
                portal = new Portal(490, 920, 20, 30);
                portal.visiable = false;

                bosses.add(new FirstBoss(new Block(0, 950, 1000, 50, 0), numberOfLevel));
            }
            if (numberOfLevel % 100 == 10) {
                portal = new Portal(200, 490, 20, 30);
                portal.visiable = false;
                blocks.add(new Block(-20, 0, 40, getHeight(), 0));
                blocks.add(new Block(getWidth() - 20, 0, 40, getHeight(), 0));
                blocks.add(new Block(100, 700, 200, 50, 0 ));
                blocks.add(new Block(700, 700, 200, 50, 0 ));

                bosses.add(new SecondBoss(new Block(0, 0, 0, 0, 0), numberOfLevel));
            }
            if (numberOfLevel % 100 == 20){
                portal = new Portal(490, 920, 20, 30);
                portal.visiable = false;
                blocks.add(new Block(100, 600, 100,50,0 ));
                blocks.add(new Block(450, 600, 100,50,0 ));
                blocks.add(new Block(800, 600, 100,50,0 ));

                bosses.add(new ThirdBoss(new Block(0, 950, 1000, 50, 0), numberOfLevel));
            }
            if (numberOfLevel % 100 == 30){
                portal = new Portal(490, 920, 20, 30);
                portal.visiable = false;
                blocks.add(new Block(100, 650, 100,50,0 ));
                blocks.add(new Block(450, 650, 100,50,0 ));
                blocks.add(new Block(800, 650, 100,50,0 ));
                blocks.add(new Block(100, 400, 100,50,0 ));
                blocks.add(new Block(450, 400, 100,50,0 ));
                blocks.add(new Block(800, 400, 100,50,0 ));

                bosses.add(new FirstBoss(new Block(0, 950, 1000, 50, 0), numberOfLevel));
                bosses.add(new FirstBoss(new Block(0, 950, 1000, 50, 0), numberOfLevel));

            }
            if (numberOfLevel % 100 == 40){
                portal = new Portal(490, 920, 20, 30);
                portal.visiable = false;
                blocks.add(new Block(100, 600, 100,50,0 ));
                blocks.add(new Block(450, 600, 100,50,0 ));
                blocks.add(new Block(800, 600, 100,50,0 ));

                bosses.add(new ThirdBoss(new Block(0, 950, 1000, 50, 0), numberOfLevel));
                bosses.add(new ThirdBoss(new Block(0, 950, 1000, 50, 0), numberOfLevel));
            }

            if (numberOfLevel % 100 == 50){
                portal = new Portal(490, 920, 20, 30);
                portal.visiable = false;
                blocks.add(new Block(100, 600, 100,50,0 ));
                blocks.add(new Block(800, 600, 100,50,0 ));

                bosses.add(new SecondBoss(new Block(0, 950, 1000, 50, 0), numberOfLevel));
                bosses.add(new SecondBoss(new Block(0, 950, 1000, 50, 0), numberOfLevel));

            }

        } else {
            int kolOfLevels = 7;
            double d = Math.random();
            if (d * kolOfLevels < 1) {
                blocks.add(new Block(-50, 300, 80, 400, 0));
                blocks.add(new Block(100, 400, 400, 50, -1));
                blocks.add(new Block(100, 500, 200, 100, -1));
                blocks.add(new Block(150, 800, 200, 50, -1));
                blocks.add(new Block(300, 150, 20, 80, 1));
                blocks.add(new Block(320, 200, 180, 30, -1));
                blocks.add(new Block(500, 150, 20, 80, 1));
                blocks.add(new Block(500, 700, 100, 50, -1));
                blocks.add(new Block(700, 300, 100, 150, -1));
                blocks.add(new Block(800, 500, 20, 150, 1));

                portal = new Portal(900, 220, 20, 30);
                blocks.add(new Block(800, 250, 200, 50, 0));
                addEnemy(17);
            } else if (d * kolOfLevels < 2) {
                blocks.add(new Block(200, 800, 100, 40, -1));
                blocks.add(new Block(400, 700, 100, 40, -1));
                blocks.add(new Block(600, 600, 100, 40, -1));
                blocks.add(new Block(800, 500, 100, 40, -1));
                blocks.add(new Block(80, 600, 20, 20, 1));
                blocks.add(new Block(0, 400, 100, 40, -1));
                blocks.add(new Block(150, 300, 100, 40, -1));
                blocks.add(new Block(400, 200, 100, 40, -1));

                portal = new Portal(440, 170, 20, 30);
                addEnemy(17);
            } else if (d * kolOfLevels < 3) {
                blocks.add(new Block(100, 300, 20, 20, 1));
                blocks.add(new Block(100, 700, 20, 20, 1));
                blocks.add(new Block(800, 300, 20, 20, 1));
                blocks.add(new Block(800, 700, 20, 20, 1));
                blocks.add(new Block(50, 400, 20, 200, -1));
                blocks.add(new Block(850, 400, 20, 200, -1));
                blocks.add(new Block(300, 200, 400, 50, -1));
                blocks.add(new Block(300, 800, 400, 50, -1));

                portal = new Portal(430, 470, 60, 60);
                addEnemy(17);
            } else if (d * kolOfLevels < 4) {
                blocks.add(new Block(-50, 0, 100, 1000, 0));
                blocks.add(new Block(950, 0, 100, 1000, 0));
                blocks.add(new Block(100, 150, 850, 50, -1));
                blocks.add(new Block(100, 250, 800, 50, -1));
                blocks.add(new Block(100, 350, 850, 50, -1));
                blocks.add(new Block(50, 450, 850, 50, -1));
                blocks.add(new Block(100, 550, 800, 50, -1));
                blocks.add(new Block(50, 650, 850, 50, -1));
                blocks.add(new Block(100, 750, 800, 50, -1));
                blocks.add(new Block(100, 850, 850, 50, -1));

                portal = new Portal(900, 100, 30, 50);
                addEnemy(25);
            } else if (d * kolOfLevels < 5) {
                man.x = 490;
                man.y = 900;
                man.xitPoint = man.maxXP;
                man.isShieldOn = true;
                man.timeOfShield = 0;

                blocks.add(new Block(350, 300, 300, 40, -1));
                blocks.add(new Block(350, 450, 300, 40, -1));
                blocks.add(new Block(350, 600, 300, 40, -1));
                blocks.add(new Block(350, 750, 300, 40, -1));
                blocks.add(new Block(350, 850, 300, 30, -1));


                portal = new Portal(490, 120, 20, 30);
                addEnemy(10);
                for (int i = 0; i < 4; i = i + 1) {
                    enemies.add(new Enemy(new Block(20, 150 + i * 200, 20, 20, 0), numberOfLevel).makeTurrel());
                    enemies.add(new Enemy(new Block(970, 150 + i * 200, 20, 20, 0), numberOfLevel).makeTurrel());
                }
            } else if (d * kolOfLevels < 6) {
                blocks.add(new Block(200, 900, 150, 30, -1));
                blocks.add(new Block(450, 800, 150, 30, -1));
                blocks.add(new Block(700, 700, 150, 30, -1));
                blocks.add(new Block(950, 600, 70, 30, -1));
                blocks.add(new Block(50, 500, 100, 30, -1));
                blocks.add(new Block(300, 400, 100, 30, -1));
                blocks.add(new Block(550, 300, 100, 30, -1));
                blocks.add(new Block(700, 200, 300, 30, -1));

                portal = new Portal(800, 150, 30, 50);
                addEnemy(25);
            } else {
                man.x = 490;
                man.y = 680;
                man.xitPoint = man.maxXP;
                man.isShieldOn = true;
                man.timeOfShield = 0;

                blocks.add(new Block(0, 200, 200, 20, 0));
                blocks.add(new Block(800, 200, 200, 20, 0));
                for (int i = 0; i < 2; i = i + 1) {
                    for (int j = 0; j < 4; j = j + 1) {
                        blocks.add(new Block(100 + i * 600, 300 + j * 200, 200, 20, -1));
                    }
                }
                for (int i = 0; i < 2; i = i + 1) {
                    for (int j = 0; j < 3; j = j + 1) {
                        blocks.add(new Block(300 + i * 300, 400 + j * 200, 100, 20, -1));
                    }
                }
                for (int j = 0; j < 4; j = j + 1) {
                    blocks.add(new Block(400, 270 + j * 200, 200, 20, -1));
                }
                portal = new Portal(30, 170, 20, 30);
                addEnemy(17);
            }
        }
        isNewGame = false;
        this.previousWorldUpdateTime = System.currentTimeMillis();
        this.previousTapUpdateTime = System.currentTimeMillis();
    }

    public void paint(Graphics g) {
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if (bufferStrategy == null) {
            this.createBufferStrategy(2);
            bufferStrategy = this.getBufferStrategy();
        }
        g = bufferStrategy.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(23, 23, 23));
        g2d.fillRect(0, 0, getWidth(), getHeight());

        if (isGameWin){
            try {
                Image lastImage = ImageIO.read(Frame.class.getResourceAsStream("Pictures/YOU_WIN.jpg"));
                g2d.drawImage(lastImage, 0, 0,1000,1000,null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if ((man != null) && (man.death == true)) {
            try {
                Image lastImage = ImageIO.read(Frame.class.getResourceAsStream("Pictures/YOU_LOSE.jpg"));
                g2d.drawImage(lastImage, 0, 0,1000,1000,null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            if ((portal != null)&&(portal.visiable)) {
                portal.draw(g2d, man);
            }
            for (int i = 0; i < blocks.size(); i = i + 1) {
                blocks.get(i).draw(g2d, man);
            }

            // Статичные элементы
            barOfHealth.draw(g2d);
            barOfMana.draw(g2d);
            barOfJerk.draw(g2d);
            if (barOfShield.nowOfBar != 0) {
                barOfShield.draw(g2d);
            }

            for (int i = 0; i < bosses.size(); i = i + 1) {
                bosses.get(i).draw(g2d);
            }
            for (int i = 0; i < enemies.size(); i = i + 1) {
                enemies.get(i).draw(g2d, man);
            }
            man.draw(g2d, this.getWidth(), this.getHeight());
            for (int i = 0; i < bullets.size(); i = i + 1) {
                bullets.get(i).draw(g2d, man);
            }

            if (!inventar.isExpand) {
                inventar.drawIcon(g2d, man);
            } else {
                inventar.draw(g2d, man);
            }
            g2d.setColor(Color.white);
            g2d.drawString("Killed enemies: " + number + "    Level: " + numberOfLevel + "  Coins: " + man.coins + "   Experience: " + man.experience + "/" + man.minExperienceToUpdate, 120, 50);
            g2d.setColor(Color.black);
            g2d.drawString("Killed enemies: " + number + "    Level: " + numberOfLevel + "  Coins: " + man.coins + "   Experience: " + man.experience + "/" + man.minExperienceToUpdate, 121, 51);
        }


        g.dispose();
        bufferStrategy.show();
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent e) {
        if ((isGameWin == false) && (man.death == false)) {
            long currentTime = System.currentTimeMillis();
            long dt = currentTime - previousTapUpdateTime; // нашли сколько миллисекунд прошло с предыдущего обновления физики мира

            if (e.getID() == KeyEvent.KEY_PRESSED) { // Если кнопка была нажата (т.е. сейчас она зажата)
                if ((e.getKeyCode() == KeyEvent.VK_LEFT) || (e.getKeyCode() == KeyEvent.VK_A)) {
                    // Движение влево
                    man.startRunningLeft();
                    if ((lastButton == e.getKeyCode()) && (dt < 100)) {
                        if (man.timeFromJerk > man.minTimeToJerk) {
                            man.jerk();
                            man.timeFromJerk = man.timeFromJerk - man.minTimeToJerk;
                            if (isTouchLeft(man)) {
                                man.xRunningSpeed = man.xStartSpeed;
                            }
                        }
                    }
                }
                if ((e.getKeyCode() == KeyEvent.VK_RIGHT) || (e.getKeyCode() == KeyEvent.VK_D)) {
                    // Движение вправо
                    man.startRunningRight();
                    if ((lastButton == e.getKeyCode()) && (dt < 100)) {
                        if (man.timeFromJerk > man.minTimeToJerk) {
                            man.jerk();
                            man.timeFromJerk = man.timeFromJerk - man.minTimeToJerk;
                            if (isTouchRigth(man)) {
                                man.xRunningSpeed = man.xStartSpeed;
                            }
                        }
                    }
                }
                if ((e.getKeyCode() == KeyEvent.VK_DOWN) || (e.getKeyCode() == KeyEvent.VK_S)) {
                    // Резкий спуск вниз
                    man.yRunningSpeed = 23;
                }

                if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    // Скольжение по стене
                    if (isTouch2(man) && (man.yRunningSpeed > 0)) {
                        man.isShifting = true;
                    } else {
                        man.isShifting = false;
                    }
                }
                if ((e.getKeyCode() == KeyEvent.VK_SPACE)) {
                    // Сброс крюка
                    if (man.hook != null) {
                        if (man.hook.isSeize == true) {
                            man.noSpeeds();
                            man.jump();
                        }
                        man.hook = null;
                    }
                    // Прыжок
                    if (isTouch2(man)) {
                        man.isShifting = false;
                        man.jump();
                    }
                    // Заход в портал
                    if (portal.visiable) {
                        if (portal.checkManInPortal(man)) {
                            numberOfLevel = numberOfLevel + 1;
                            man.coins = man.coins + numberOfLevel * 100 * numberOfLevel;
                            isNewGame = true;
                        }
                    }
                }

                // Кнопки выбора оружия
                if (e.getKeyCode() == KeyEvent.VK_F) {
                    // Выбор оружия -- пистолет
                    if (man.gunPistol.isUnlocked() == true) {
                        man.gunIsNow = man.gunPistol;
                        new Thread(() -> {
                            new MakeSound().playSound("Sounds/Sounds_Gun.wav");
                        }).start();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_R) {
                    // Выбор оружия -- дробовик
                    if (man.gunShotGun.isUnlocked() == true) {
                        man.gunIsNow = man.gunShotGun;
                        new Thread(() -> {
                            new MakeSound().playSound("Sounds/Sounds_Gun.wav");
                        }).start();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_V) {
                    // Выбор оружия -- винтовка
                    if (man.gunRifle.isUnlocked() == true) {
                        man.gunIsNow = man.gunRifle;
                        new Thread(() -> {
                            new MakeSound().playSound("Sounds/Sounds_Gun.wav");
                        }).start();
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_T) {
                    // Выбор оружия -- пистолет
                    if (man.gunMachineGun.isUnlocked() == true) {
                        man.gunIsNow = man.gunMachineGun;
                        new Thread(() -> {
                            new MakeSound().playSound("Sounds/Sounds_Gun.wav");
                        }).start();
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_G) {
                    // Выбор оружия -- огнемет
                    if (man.gunFlamethrower.isUnlocked() == true) {
                        man.gunIsNow = man.gunFlamethrower;
                        new Thread(() -> {
                            new MakeSound().playSound("Sounds/Sounds_Gun.wav");
                        }).start();
                    }
                }

                if (e.getKeyCode() == KeyEvent.VK_E) {
                    // Срабатывание крюк-кошки
                    if (man.hook != null) {
                        if (man.hook.isSeize == true) {
                            man.noSpeeds();
                        }
                        man.hook = null;
                    } else {
                        man.hooking = true;
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_Q) {
                    // Включение божественного щита
                    if (man.coins >= 500) {
                        man.coins = man.coins - 500;
                        man.isShieldOn = true;
                        man.timeOfShield = 0;
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_X) {
                    // Добавка хп
                    if (man.xitPoint != man.maxXP) {
                        if (man.coins >= 150) {
                            man.coins = man.coins - 150;
                            man.xitPoint = man.maxXP;
                            new Thread(() -> {
                                new MakeSound().playSound("Sounds/Sound_Eat.wav");
                            }).start();
                        }
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_C) {
                    // Восстановление маны
                    if (man.mana != man.maxMana) {
                        if (man.coins >= 100) {
                            man.coins = man.coins - 100;
                            man.mana = man.maxMana;
                            new Thread(() -> {
                                new MakeSound().playSound("Sounds/Sound_Drink.wav");
                            }).start();
                        }
                    }
                }
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    inventar.isExpand = !inventar.isExpand;
                }
            }

            if (e.getID() == KeyEvent.KEY_RELEASED) {     // Если кнопка была отпущена - мы должны прекратить бег
                if (e.getKeyCode() == KeyEvent.VK_LEFT || (e.getKeyCode() == KeyEvent.VK_A)) { // но только бег в ту сторону, которой соответствует отпущенная кнопка
                    man.stopRunningLeft();
                } else if ((e.getKeyCode() == KeyEvent.VK_RIGHT) || (e.getKeyCode() == KeyEvent.VK_D)) {
                    man.stopRunningRight();
                } else if ((e.getKeyCode() == KeyEvent.VK_UP) || (e.getKeyCode() == KeyEvent.VK_W)) {
                    //man.stopRunningUp();
                } else if (e.getKeyCode() == KeyEvent.VK_DOWN || (e.getKeyCode() == KeyEvent.VK_S)) {
                    man.yRunningSpeed = 0;
                } else if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
                    man.isShifting = false;
                }
            }
            repaint();
            previousTapUpdateTime = currentTime;
            lastButton = e.getKeyCode();
        }
        return false;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        eDragged = e;
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if ((isGameWin == false) && (man.death == false)) {
            eDragged = null;

            if (inventar.checkIcon(e)) {
                inventar.isExpand = !inventar.isExpand;
            }
            if (inventar.isExpand) {
                inventar.checkClick(e);
            } else if (man.hooking == true) {
                man.makeHook(e);
                man.hooking = false;
            } else {
                eClick = e;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if ((isGameWin == false) && (man.death == false)) {
            eDragged = e;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if ((isGameWin == false) && (man.death == false)) {
            eDragged = null;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void updateWorldPhysics() {
        long currentTime = System.currentTimeMillis();
        long dt = currentTime - previousWorldUpdateTime; // нашли сколько миллисекунд прошло с предыдущего обновления физики мира

        man.update(dt);
        if ((eDragged != null) && (man.gunIsNow.isRapidFire)) {
            if (man.gunIsNow.timeFromShot > man.gunIsNow.minTimeToShot) {
                man.addBullet(bullets, eDragged);
            }
        } else if (eClick != null) {
            if (man.gunIsNow.timeFromShot > man.gunIsNow.minTimeToShot) {
                man.addBullet(bullets, eClick);
            }
            eClick = null;
        }
        barOfHealth.maxOfBar = man.maxXP;
        barOfMana.maxOfBar = man.maxMana;
        barOfShield.maxOfBar = man.maxTimeOfShield;
        barOfJerk.maxOfBar = man.maxJerkTime;

        barOfHealth.nowOfBar = man.xitPoint;
        barOfMana.nowOfBar = man.mana;
        barOfShield.nowOfBar = man.timeOfShield;
        barOfJerk.nowOfBar = man.timeFromJerk;

        for (int i = 0; i < bullets.size(); i = i + 1) {
            bullets.get(i).update(dt);
        }
        for (int i = 0; i < enemies.size(); i = i + 1) {
            enemies.get(i).update(dt);
            enemies.get(i).addBullet(bullets, man);
        }
        for (int i = 0; i < bosses.size(); i = i + 1) {
            bosses.get(i).update(dt, man, enemies);
            bosses.get(i).addBullet(bullets, man);
        }

        for (int i = 0; i < enemies.size(); i = i + 1) {
            if ((enemies.get(i).x == -100) && (enemies.get(i).y == -100)) {
                enemies.remove(i);
                i = i - 1;
            }
        }
        checkAllDeath();
        inventar.updateMan(man);
        previousWorldUpdateTime = currentTime;
    }

    public void checkAllDeath() {
        if (man.hook != null) {
            checkCollisionHook(man.hook);
        }
        checkCollision(man);
        if (bullets != null) {
            checkDeath(man);
        }
        for (int i = 0; i < bullets.size(); i = i + 1) {
            checkCollisionBullet(bullets.get(i));

            for (int j = 0; j < enemies.size(); j = j + 1) {
                if (enemies.get(j).checkDeath(bullets.get(i))) {
                    enemies.get(j).xitPoint = enemies.get(j).xitPoint - bullets.get(i).forсe;
                    if (Math.random() < man.luck){
                        enemies.get(j).xitPoint = enemies.get(j).xitPoint - bullets.get(i).forсe;
                    }
                    if (enemies.get(j).xitPoint <= 0) {
                        number = number + 1;
                        man.coins = man.coins + enemies.get(j).coinFromDeath;
                        man.experience = man.experience + enemies.get(j).experienceFromDeath;
                        if (Math.random() < man.luck){
                            man.coins = man.coins + enemies.get(j).coinFromDeath;
                        }
                        enemies.remove(j);
                    }
                    bullets.get(i).x = -100;
                    bullets.get(i).y = -100;
                    bullets.get(i).vx = 0;
                    bullets.get(i).vy = 0;
                }
            }
            for (int j = 0; j < bosses.size(); j = j + 1) {
                if (bosses.get(j).checkDeath(bullets.get(i))) {
                    bosses.get(j).xitPoint = bosses.get(j).xitPoint - bullets.get(i).forсe;
                    if (Math.random() < man.luck){
                        bosses.get(j).xitPoint = bosses.get(j).xitPoint - bullets.get(i).forсe;
                    }
                    maxXpOfBoss = bosses.get(j).maxXP;
                    nowXpOfBoss = bosses.get(j).xitPoint;

                    if (bosses.get(j).xitPoint <= 0) {
                        number = number + 1;
                        man.coins = man.coins + bosses.get(j).coinFromDeath;
                        man.experience = man.experience + bosses.get(j).experienceFromDeath;
                        bosses.remove(j);
                    }
                    bullets.get(i).x = -100;
                    bullets.get(i).y = -100;
                    bullets.get(i).vx = 0;
                    bullets.get(i).vy = 0;
                }
            }
            if (bosses.size() == 0){
                portal.visiable = true;
            }
        }
        int i = 0;
        while (true) {
            if (i == bullets.size()) {
                break;
            }
            if ((bullets.get(i).x == -100) && (bullets.get(i).y == -100)) {
                bullets.remove(i);
            } else {
                i = i + 1;
            }
        }
    }

    public void checkCollision(Man m) {
        if (m.xRunning > 0) {
            for (int i = 0; i < blocks.size(); i = i + 1) {
                if ((m.y > blocks.get(i).y) && (m.y < blocks.get(i).y + blocks.get(i).h) || (m.y + m.h > blocks.get(i).y) && (m.y + m.h < blocks.get(i).y + blocks.get(i).h)) {
                    if ((m.x + m.h > blocks.get(i).x) && (m.x < blocks.get(i).x)) {
                        m.x = blocks.get(i).x - m.w;
                    }
                }
            }
        }
        if (m.xRunning < 0) {
            for (int i = 0; i < blocks.size(); i = i + 1) {
                if ((m.y > blocks.get(i).y) && (m.y < blocks.get(i).y + blocks.get(i).h) || (m.y + m.h > blocks.get(i).y) && (m.y + m.h < blocks.get(i).y + blocks.get(i).h)) {
                    if ((m.x - 1 < blocks.get(i).x + blocks.get(i).w) && (m.x + m.w > blocks.get(i).x + blocks.get(i).w)) {
                        m.x = blocks.get(i).x + blocks.get(i).w;
                    }
                }
            }
        }
        if (m.yRunningSpeed > 0) {
            for (int i = 0; i < blocks.size(); i = i + 1) {
                if ((m.y + m.h > blocks.get(i).y) && (m.y < blocks.get(i).y + blocks.get(i).h)) {
                    if ((m.x + m.w > blocks.get(i).x) && (m.x < blocks.get(i).x + blocks.get(i).w)) {
                        m.y = blocks.get(i).y - m.h;
                        m.yRunningSpeed = 0;
                        m.yRunning = 0;
                    }
                }
            }
        }
        if ((m.yRunningSpeed < 0) || (m.xRunning < 0)) {
            for (int i = 0; i < blocks.size(); i = i + 1) {
                if ((m.y > blocks.get(i).y) && (m.y < blocks.get(i).y + blocks.get(i).h)) {
                    if ((m.x + m.w > blocks.get(i).x) && (m.x < blocks.get(i).x + blocks.get(i).w)) {
                        m.y = blocks.get(i).y + blocks.get(i).h;
                        m.yRunningSpeed = 0;
                        m.yRunning = 0;
                    }
                }
            }
        }
        for (int i = 0; i < blocks.size(); i = i + 1) {
            if ((m.y < blocks.get(i).y + blocks.get(i).h) && (m.y + m.h > blocks.get(i).y + blocks.get(i).h)) {
                if ((m.x + m.w > blocks.get(i).x) && (m.x < blocks.get(i).x + blocks.get(i).w)) {
                    m.y = blocks.get(i).y + blocks.get(i).h;
                    m.yRunningSpeed = 0;
                    m.yRunning = 0;
                }

            }
        }
    }

    public void checkDeath(Man m) {
        // Проверка смерти игрока
        boolean b = false;
        if ((numberOfLevel == 10)||(numberOfLevel == 50)) {
            if (bosses.size() > 0) {
                if (man.isShieldOn == false) {
                    bosses.get(0).touchMan(man);
                }
            }
        }
        for (int i = 0; i < enemies.size(); i = i + 1) {
            if (Math.sqrt((m.x - enemies.get(i).x) * (m.x - enemies.get(i).x) + (m.y - enemies.get(i).y) * (m.y - enemies.get(i).y)) < 23) {
                b = true;
                if (m.isShieldOn) {
                    //enemies.remove(i);
                }
            }
        }
        for (int i = 0; i < bullets.size(); i = i + 1) {
            String s = bullets.get(i).from;
            if (s.equals("enemy")) {
                if (m.checkDeath(bullets.get(i))) {
                    b = true;
                    bullets.get(i).x = -100;
                    bullets.get(i).y = -100;
                    bullets.get(i).vx = 0;
                    bullets.get(i).vy = 0;

                    if (m.isShieldOn) {

                    } else {
                        m.xitPoint = m.xitPoint - Math.max(1,(int) (bullets.get(i).forсe - m.armor));
                    }
                }
            }
        }
        if (b == true) {
            if (m.isShieldOn == true) {
                // Щит действует почти 5 секунд
            } else {
                if (m.xitPoint > 0) {

                } else {
                    m.death = true;
                }
            }
        }
    }

    public boolean checkCollisionBullet(Bullet b) {
        boolean ret = false;
        for (int i = 0; i < blocks.size(); i = i + 1) {
            if ((b.x > blocks.get(i).x) && (b.x < blocks.get(i).x + blocks.get(i).w) && (b.y > blocks.get(i).y) && (b.y < blocks.get(i).y + blocks.get(i).h)) {
                b.x = -100;
                b.y = -100;
                b.vx = 0;
                b.vy = 0;
                ret = true;
            }
        }

        for (int i = 0; i < bullets.size(); i = i + 1) {
            Bullet t = bullets.get(i);
            if (!b.from.equals(t.from)) {
                if (Math.sqrt((b.x - t.x) * (b.x - t.x) + (b.y - t.y) * (b.y - t.y)) < 6) {

                    b.x = -100;
                    b.y = -100;
                    b.vx = 0;
                    b.vy = 0;
                    bullets.get(i).x = -100;
                    bullets.get(i).y = -100;
                    bullets.get(i).vx = 0;
                    bullets.get(i).vy = 0;

                    ret = true;
                }
            }
        }
        return ret;

    }

    public void checkCollisionHook(Hook hook) {
        for (int i = 0; i < blocks.size(); i = i + 1) {
            if ((hook.x > blocks.get(i).x) && (hook.x < blocks.get(i).x + blocks.get(i).w) && (hook.y > blocks.get(i).y) && (hook.y < blocks.get(i).y + blocks.get(i).h)) {
                hook.vx = 0;
                hook.vy = 0;
                hook.isSeize = true;
            }
        }
    }

    public boolean isTouch2(Man m) {
        boolean touch = false;
        for (int i = 0; i < blocks.size(); i = i + 1) {
            touch = ((m.isTouch2(blocks.get(i))) || touch);
            if ((m.yRunningSpeed > 0) && (m.y + m.h >= blocks.get(i).y) && (m.y <= blocks.get(i).y + blocks.get(i).h) && ((m.x + m.w == blocks.get(i).x) || (m.x == blocks.get(i).x + blocks.get(i).w))) {
                touch = true;
            }
        }
        return touch;
    }

    public boolean isTouchLeft(Man m) {
        boolean touch = false;
        for (int i = 0; i < blocks.size(); i = i + 1) {
            if ((m.y + m.h > blocks.get(i).y) && (m.y < blocks.get(i).y + blocks.get(i).h) && (m.x == blocks.get(i).x + blocks.get(i).w)) {
                touch = true;
            }
        }
        return touch;
    }

    public boolean isTouchRigth(Man m) {
        boolean touch = false;
        for (int i = 0; i < blocks.size(); i = i + 1) {
            if ((m.y + m.h > blocks.get(i).y) && (m.y < blocks.get(i).y + blocks.get(i).h) && (m.x + m.w == blocks.get(i).x)) {
                touch = true;
            }
        }
        return touch;
    }

    public void addEnemy(int kolvo) {
        for (int i = 0; i < kolvo; i = i + 1) {
            int b = (int) (Math.random() * blocks.size());
            if (blocks.get(b).numberOfEnemeis == -1) {
                enemies.add(new Enemy(blocks.get(b), numberOfLevel));
            } else if (blocks.get(b).numberOfEnemeis == 1) {
                enemies.add(new Enemy(blocks.get(b), numberOfLevel));
                enemies.get(enemies.size() - 1).makeTurrel();
                blocks.get(b).numberOfEnemeis = 0;
            } else {
                i = i - 1;
            }
        }
    }

    public void updateWorldTime() {
        long currentTime = System.currentTimeMillis();

        previousWorldUpdateTime = currentTime;
    }
}
