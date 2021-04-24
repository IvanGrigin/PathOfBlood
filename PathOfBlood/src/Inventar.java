import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Inventar extends JPanel {
    public int w;
    public int h;
    public int xIcon;
    public int yIcon;
    public int wIcon;
    public int hIcon;
    public boolean isExpand;
    public Man man;

    public StatusGun gunPistolStatusBar;
    public StatusGun gunShotGunStatusBar;
    public StatusGun gunRifleStatusBar;
    public StatusGun gunMachineGunStatusBar;
    public StatusGun gunFlamethrowerStatusBar;

    public StatusState stateOfXP;
    public StatusState stateOfMana;
    public StatusState stateOfSpeed;
    public StatusState stateOfShieldAndHook;
    public StatusState stateOfLuck;
    public ColorsOfMan stateOfColorMan;

    public Settings settings = new Settings();

    public Inventar(int w, int h, Man man) throws IOException {
        this.man = man;
        this.w = w;
        this.h = h;
        this.xIcon = 950;
        this.yIcon = 35;
        this.wIcon = 30;
        this.hIcon = 30;

        isExpand = false;

        this.gunPistolStatusBar = new StatusGun("Pst", 40, 100, 300, 30, "0k", "5k", "10k", "50k", "100k");
        gunPistolStatusBar.object_1.coloring = true;
        this.gunShotGunStatusBar = new StatusGun("Shtg", 40, 140, 300, 30, "2k", "5k", "10k", "50k", "100k");
        this.gunRifleStatusBar = new StatusGun("Rfl", 40, 180, 300, 30, "5k", "10k", "50k", "100k", "500k");
        this.gunMachineGunStatusBar = new StatusGun("Mchg", 40, 220, 300, 30, "50k", "100k", "200k", "500k", "1M");
        this.gunFlamethrowerStatusBar = new StatusGun("Flm", 40, 260, 300, 30, "100k", "100k", "1M", "10M", "100M");

        this.stateOfXP = new StatusState("XP", 660, 100, 300, 30, "lvl 1", "lvl 2", "lvl 3", "lvl 4", "lvl 5");
        this.stateOfMana = new StatusState("Mana", 660, 140, 300, 30, "lvl 1", "lvl 2", "lvl 3", "lvl 4", "lvl 5");
        this.stateOfSpeed = new StatusState("Spd", 660, 180, 300, 30, "lvl 1", "lvl 2", "lvl 3", "lvl 4", "lvl 5");
        this.stateOfShieldAndHook = new StatusState("Sld/Hk", 660, 220, 300, 30, "lvl 1", "lvl 2", "lvl 3", "lvl 4", "lvl 5");
        this.stateOfLuck = new StatusState("Luck", 660, 260, 300, 30, "lvl 1", "lvl 2", "lvl 3", "lvl 4", "lvl 5");

        this.stateOfColorMan = new ColorsOfMan(375, 65, 220, 220);
    }

    public void updateMan(Man man) {
        this.man = man;
    }

    public void draw(Graphics2D g2d, Man man) {
        if (settings.isOpenSettings == true) {
            settings.draw(g2d);
        } else {
            g2d.setColor(new Color(23, 23, 23));
            g2d.fillRect(0, 0, w, h);

            if (man.colorOfMan == Color.BLUE){
                g2d.setColor(Color.yellow);
            } else {
                g2d.setColor(man.colorOfMan);
            }
            g2d.drawRect(30, 90, 320, 210);
            g2d.drawString("Gun is now: " + man.gunIsNow.whichFirearms, 50, 70);

            gunPistolStatusBar.draw(g2d, man);
            gunShotGunStatusBar.draw(g2d, man);
            gunRifleStatusBar.draw(g2d, man);
            gunMachineGunStatusBar.draw(g2d, man);
            gunFlamethrowerStatusBar.draw(g2d, man);

            if (man.colorOfMan == Color.BLUE){
                g2d.setColor(Color.yellow);
            } else {
                g2d.setColor(man.colorOfMan);
            }
            g2d.drawRect(650, 90, 320, 210);
            g2d.drawString("Stat points: " + man.statPoints, 750, 70);

            stateOfXP.draw(g2d, man);
            stateOfMana.draw(g2d, man);
            stateOfSpeed.draw(g2d, man);
            stateOfShieldAndHook.draw(g2d, man);
            stateOfLuck.draw(g2d, man);

            stateOfColorMan.draw(g2d, man);
            drawIcon(g2d, man);

            settings.drawIcon(g2d, 500, 20);

        }
    }

    public void checkClick(MouseEvent e) {
        if (settings.isOpenSettings == false) {
            gunPistolStatusBar.checkClick(e, man);
            gunShotGunStatusBar.checkClick(e, man);
            gunRifleStatusBar.checkClick(e, man);
            gunMachineGunStatusBar.checkClick(e, man);
            gunFlamethrowerStatusBar.checkClick(e, man);

            stateOfXP.checkClick(e, man);
            stateOfMana.checkClick(e, man);
            stateOfSpeed.checkClick(e, man);
            stateOfShieldAndHook.checkClick(e, man);
            stateOfLuck.checkClick(e, man);

            stateOfColorMan.CheckClick(e, man);
            if ((e.getX() > 500)&&(e.getX() < 500 + 360) && (e.getY() > 20)&&(e.getY() < 20 + 66)){
                settings.isOpenSettings = true;
            }
        } else {
            settings.CheckClick(e);
        }
    }

    public void drawIcon(Graphics2D g2d, Man man) {
        g2d.setColor(new Color(23,23,23));
        g2d.fillRect(xIcon, yIcon, wIcon, hIcon);
        g2d.setColor(Color.white);
        g2d.fillOval(xIcon, yIcon, wIcon, hIcon);
        g2d.setColor(Color.black);
        g2d.drawOval(xIcon, yIcon, wIcon, hIcon);
        int n = 5;
        g2d.drawOval(xIcon + n, yIcon + n, wIcon - 2 * n, hIcon - 2 * n);
    }

    public boolean checkIcon(MouseEvent e) {
        if ((this.xIcon < e.getX()) && (this.xIcon + this.wIcon > e.getX()) && (this.yIcon < e.getY()) && (this.yIcon + this.hIcon > e.getY())) {
            return true;
        } else {
            return false;
        }
    }

}
