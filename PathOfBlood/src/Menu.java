import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;

public class Menu extends JFrame implements MouseListener {
    public Image backgroundImage;
    public boolean isOpen = true; // Показывает открыто ли меню сейчас
    public Settings settings = new Settings();

    public Menu() throws IOException {
        setSize(1000, 1000);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);

        backgroundImage = ImageIO.read(Menu.class.getResourceAsStream("Pictures/Menu.jpg"));
        isOpen = true;
        settings = new Settings();
        addMouseListener(this);
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

        g2d.drawImage(backgroundImage, 0, 0, 1000, 1000, null);
        if (settings.isOpenSettings == true) {
            settings.draw(g2d);
        }
        g.dispose();
        bufferStrategy.show();
    }

    public void CheckClick(MouseEvent e) {
        if (settings.isOpenSettings == false) {
            if ((e.getX() > 325) && (e.getX() < 700) && (e.getY() > 400) && (e.getY() < 600)) {
                isOpen = false;
            }
            if ((e.getX() > 325) && (e.getX() < 700) && (e.getY() > 670) && (e.getY() < 850)) {
                settings.isOpenSettings = true;
            }
        } else {
            settings.CheckClick(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        CheckClick(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
