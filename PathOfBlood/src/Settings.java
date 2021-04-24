import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;

public class Settings{
    public Image backgroundSettingsImage;
    public Image illustrationSettingsImage;
    public Image illustrationSettingsMoveImage;
    public Image illustrationSettingsShootingImage;
    public Image illustrationSettingsUpgradeGunsImage;
    public Image illustrationSettingsUpgradeCharacterImage;
    public Image illustrationSettingsHotkeysImage;
    public Image illustrationSettingsOtherImage;

    public Image iconSettingsImage;

    public boolean isOpenSettings = false;

    public Settings() throws IOException {

        backgroundSettingsImage = ImageIO.read(Settings.class.getResourceAsStream("Pictures/Settings/Start.jpg"));
        illustrationSettingsMoveImage = ImageIO.read(Settings.class.getResourceAsStream("Pictures/Settings/Illustration_Move.jpg"));
        illustrationSettingsShootingImage = ImageIO.read(Settings.class.getResourceAsStream("Pictures/Settings/Illustration_Shooting.jpg"));
         illustrationSettingsUpgradeGunsImage = ImageIO.read(Settings.class.getResourceAsStream("Pictures/Settings/Illustration_UpgradeGuns.jpg"));
        illustrationSettingsUpgradeCharacterImage = ImageIO.read(Settings.class.getResourceAsStream("Pictures/Settings/Illustration_UpgradeCharacter.jpg"));
        illustrationSettingsHotkeysImage = ImageIO.read(Settings.class.getResourceAsStream("Pictures/Settings/Illustration_Hotkeys.jpg"));
        illustrationSettingsOtherImage = ImageIO.read(Settings.class.getResourceAsStream("Pictures/Settings/Illustration_Other.jpg"));

        illustrationSettingsImage = illustrationSettingsMoveImage;

        iconSettingsImage = ImageIO.read(Settings.class.getResourceAsStream("Pictures/Settings.jpg"));
    }
    public void draw(Graphics2D g2d){
        g2d.setColor(new Color(23, 23, 23));
        g2d.fillRect(0, 0, 1000, 1000);

        g2d.drawImage(backgroundSettingsImage, 0, 0, 1000, 1000, null);
        g2d.drawImage(illustrationSettingsImage, 400, 100, null);
    }
    public void CheckClick(MouseEvent e){
        if ((e.getX() > 0) && (e.getX() < 250) && (e.getY() > 50) && (e.getY() < 150)) {
            isOpenSettings = false;
        }
        if ((e.getX() > 110) && (e.getX() < 250) && (e.getY() > 180) && (e.getY() < 240)) {
            illustrationSettingsImage = illustrationSettingsMoveImage;
        }
        if ((e.getX() > 110) && (e.getX() < 350) && (e.getY() > 300) && (e.getY() < 370)) {
            illustrationSettingsImage = illustrationSettingsShootingImage;
        }
        if ((e.getX() > 110) && (e.getX() < 350) && (e.getY() > 420) && (e.getY() < 540)) {
            illustrationSettingsImage = illustrationSettingsUpgradeGunsImage;
        }
        if ((e.getX() > 110) && (e.getX() < 400) && (e.getY() > 600) && (e.getY() < 720)) {
            illustrationSettingsImage = illustrationSettingsUpgradeCharacterImage;
        }
        if ((e.getX() > 110) && (e.getX() < 300) && (e.getY() > 780) && (e.getY() < 850)) {
            illustrationSettingsImage = illustrationSettingsHotkeysImage;
        }
        if ((e.getX() > 110) && (e.getX() < 300) && (e.getY() > 910) && (e.getY() < 970)) {
            illustrationSettingsImage = illustrationSettingsOtherImage;
        }
    }

    public void drawIcon(Graphics2D g2d, int x, int y){
        g2d.drawImage(iconSettingsImage,  x, y, null);
    }
}
