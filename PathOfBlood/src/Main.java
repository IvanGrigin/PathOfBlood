import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Menu menu = new Menu();
        while (menu.isOpen){
            menu.repaint();
            Thread.sleep(20);
        }
        menu.setVisible(false);
        Frame world = new Frame();
        while (true) {
            world.repaint();
            if(!world.inventar.isExpand) {
                world.updateWorldPhysics(); // вызываем чтобы обновить состояние физики мира (движение персонажа)
                if (world.isNewGame) {
                    world.newGame();
                }
                Thread.sleep(20);
            } else {
                world.updateWorldTime();
                Thread.sleep(20);
            }
        }
    }
}
