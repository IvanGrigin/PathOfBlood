import java.awt.*;
import java.awt.event.MouseEvent;

public class StatusGun {
    public ClickObject nameObject;

    public ClickObject object_1;
    public ClickObject object_2;
    public ClickObject object_3;
    public ClickObject object_4;
    public ClickObject object_5;

    public StatusGun(String name, int x, int y, int w, int h, String name01, String name02, String name03, String name04, String name05){
        nameObject = new ClickObject(0,x,y,50,h,Color.white,name);
        object_1 = new ClickObject(1,x + 50,y,50,h,Color.GREEN,name01);
        object_2 = new ClickObject(2,x + 100,y,50,h,Color.GREEN,name02);
        object_3 = new ClickObject(3,x + 150,y,50,h,Color.GREEN,name03);
        object_4 = new ClickObject(4,x + 200,y,50,h,Color.GREEN,name04);
        object_5 = new ClickObject(5,x + 250,y,50,h,Color.GREEN,name05);

    }

    public void draw(Graphics2D g2d, Man man){
        nameObject.draw(g2d);
        object_1.draw(g2d);
        object_2.draw(g2d);
        object_3.draw(g2d);
        object_4.draw(g2d);
        object_5.draw(g2d);
    }
    public void checkClick(MouseEvent e, Man man){
        int moneyToUpdate = 0;

        ClickObject test = null;
        if (object_1.checkClick(e)){ test = object_1;}
        if (object_2.checkClick(e)){ test = object_2;}
        if (object_3.checkClick(e)){ test = object_3;}
        if (object_4.checkClick(e)){ test = object_4;}
        if (object_5.checkClick(e)){ test = object_5;}
        if (test != null){
            if (test.string.equals("0k")){ moneyToUpdate = 0;}
            if (test.string.equals("1k")){ moneyToUpdate = 1000;}
            if (test.string.equals("2k")){ moneyToUpdate = 2000;}
            if (test.string.equals("5k")){ moneyToUpdate = 5000;}
            if (test.string.equals("10k")){ moneyToUpdate = 10000;}
            if (test.string.equals("20k")){ moneyToUpdate = 20000;}
            if (test.string.equals("50k")){ moneyToUpdate = 50000;}
            if (test.string.equals("100k")){ moneyToUpdate = 100000;}
            if (test.string.equals("200k")){ moneyToUpdate = 200000;}
            if (test.string.equals("500k")){ moneyToUpdate = 500000;}
            if (test.string.equals("1M")){ moneyToUpdate = 1000000;}
            if (test.string.equals("2M")){ moneyToUpdate = 2000000;}
            if (test.string.equals("5M")){ moneyToUpdate = 5000000;}
            if (test.string.equals("10M")){ moneyToUpdate = 10000000;}
            if (test.string.equals("50M")){ moneyToUpdate = 50000000;}
            if (test.string.equals("100M")){ moneyToUpdate = 100000000;}
            if (man.coins >= moneyToUpdate) {
                if (nameObject.string.equals("Pst")) {
                    if (test.number == man.gunPistol.level + 1) {
                        man.gunPistol.level = man.gunPistol.level + 1;
                        man.coins = man.coins - moneyToUpdate;
                        if (object_1 == test){ object_1.coloring = true;}
                        if (object_2 == test){ object_2.coloring = true;}
                        if (object_3 == test){ object_3.coloring = true;}
                        if (object_4 == test){ object_4.coloring = true;}
                        if (object_5 == test){ object_5.coloring = true;}
                        new Thread(() -> {
                            new MakeSound().playSound("Sounds/Sound_Coins.wav");
                        }).start();

                    }
                }
                if (nameObject.string.equals("Shtg")) {
                    if (test.number == man.gunShotGun.level + 1) {
                        man.gunShotGun.level = man.gunShotGun.level + 1;
                        man.coins = man.coins - moneyToUpdate;
                        if (object_1 == test){ object_1.coloring = true;}
                        if (object_2 == test){ object_2.coloring = true;}
                        if (object_3 == test){ object_3.coloring = true;}
                        if (object_4 == test){ object_4.coloring = true;}
                        if (object_5 == test){ object_5.coloring = true;}
                        new Thread(() -> {
                            new MakeSound().playSound("Sounds/Sound_Coins.wav");
                        }).start();
                    }
                }
                if (nameObject.string.equals("Rfl")) {
                    if (test.number == man.gunRifle.level + 1) {
                        man.gunRifle.level = man.gunRifle.level + 1;
                        man.coins = man.coins - moneyToUpdate;
                        if (object_1 == test){ object_1.coloring = true;}
                        if (object_2 == test){ object_2.coloring = true;}
                        if (object_3 == test){ object_3.coloring = true;}
                        if (object_4 == test){ object_4.coloring = true;}
                        if (object_5 == test){ object_5.coloring = true;}
                        new Thread(() -> {
                            new MakeSound().playSound("Sounds/Sound_Coins.wav");
                        }).start();
                    }
                }
                if (nameObject.string.equals("Mchg")) {
                    if (test.number == man.gunMachineGun.level + 1) {
                        man.gunMachineGun.level = man.gunMachineGun.level + 1;
                        man.coins = man.coins - moneyToUpdate;
                        if (object_1 == test){ object_1.coloring = true;}
                        if (object_2 == test){ object_2.coloring = true;}
                        if (object_3 == test){ object_3.coloring = true;}
                        if (object_4 == test){ object_4.coloring = true;}
                        if (object_5 == test){ object_5.coloring = true;}
                        new Thread(() -> {
                            new MakeSound().playSound("Sounds/Sound_Coins.wav");
                        }).start();
                    }
                }
                if (nameObject.string.equals("Flm")) {
                    if (test.number == man.gunFlamethrower.level + 1) {
                        man.gunFlamethrower.level = man.gunFlamethrower.level + 1;
                        man.coins = man.coins - moneyToUpdate;
                        if (object_1 == test){ object_1.coloring = true;}
                        if (object_2 == test){ object_2.coloring = true;}
                        if (object_3 == test){ object_3.coloring = true;}
                        if (object_4 == test){ object_4.coloring = true;}
                        if (object_5 == test){ object_5.coloring = true;}
                        new Thread(() -> {
                             new MakeSound().playSound("Sounds/Sound_Coins.wav");
                        }).start();
                    }
                }
            }
        }
    }

}
