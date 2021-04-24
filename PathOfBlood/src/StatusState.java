import java.awt.*;
import java.awt.event.MouseEvent;

public class StatusState {
    public int x;
    public int y;
    public int w;
    public int h;
    public ClickObject nameObject;

    public ClickObject object_1;
    public ClickObject object_2;
    public ClickObject object_3;
    public ClickObject object_4;
    public ClickObject object_5;

    public StatusState(String name, int x, int y, int w, int h, String name01, String name02, String name03, String name04, String name05){
        nameObject = new ClickObject(0,x,y,40,h,Color.white,name);
        object_1 = new ClickObject(1,x + 40,y,40,h,Color.GREEN,name01);
        object_2 = new ClickObject(2,x + 80,y,40,h,Color.GREEN,name02);
        object_3 = new ClickObject(3,x + 120,y,40,h,Color.GREEN,name03);
        object_4 = new ClickObject(4,x + 160,y,40,h,Color.GREEN,name04);
        object_5 = new ClickObject(5,x + 200,y,40,h,Color.GREEN,name05);

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
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
        int pointsToUpdate = 1;

        ClickObject test = null;
        if (object_1.checkClick(e)){ test = object_1; pointsToUpdate = 1;}
        if (object_2.checkClick(e)){ test = object_2; pointsToUpdate = 1;}
        if (object_3.checkClick(e)){ test = object_3; pointsToUpdate = 1;}
        if (object_4.checkClick(e)){ test = object_4; pointsToUpdate = 1;}
        if (object_5.checkClick(e)){ test = object_5; pointsToUpdate = 1;}
        if (test != null){

            if (man.statPoints >= pointsToUpdate) {
                if (nameObject.string.equals("XP")) {
                    // Прибавка по уровню к количеству жизни
                    if (test.number == man.levelOfXP + 1) {
                        man.statPoints = man.statPoints - pointsToUpdate;
                        man.levelOfXP = man.levelOfXP + 1;
                        if (object_1 == test){ object_1.coloring = true;}
                        if (object_2 == test){ object_2.coloring = true;}
                        if (object_3 == test){ object_3.coloring = true;}
                        if (object_4 == test){ object_4.coloring = true;}
                        if (object_5 == test){ object_5.coloring = true;}
                        new Thread(() -> {
                            new MakeSound().playSound("Sounds/Sounds_Skill_Up.wav");
                        }).start();
                    }
                }
                if (nameObject.string.equals("Mana")) {
                    // Прибавка по уровню к количеству маны
                    if (test.number == man.levelOfMana + 1) {
                        man.statPoints = man.statPoints - pointsToUpdate;
                        man.levelOfMana = man.levelOfMana + 1;
                        if (object_1 == test){ object_1.coloring = true;}
                        if (object_2 == test){ object_2.coloring = true;}
                        if (object_3 == test){ object_3.coloring = true;}
                        if (object_4 == test){ object_4.coloring = true;}
                        if (object_5 == test){ object_5.coloring = true;}
                        new Thread(() -> {
                            new MakeSound().playSound("Sounds/Sounds_Skill_Up.wav");
                        }).start();
                    }
                }
                if (nameObject.string.equals("Spd")) {
                    // Прибавка по уровню к премещению
                    if (test.number == man.levelOfSpeed + 1) {
                        man.statPoints = man.statPoints - pointsToUpdate;
                        man.levelOfSpeed = man.levelOfSpeed + 1;
                        if (object_1 == test){ object_1.coloring = true;}
                        if (object_2 == test){ object_2.coloring = true;}
                        if (object_3 == test){ object_3.coloring = true;}
                        if (object_4 == test){ object_4.coloring = true;}
                        if (object_5 == test){ object_5.coloring = true;}
                        new Thread(() -> {
                            new MakeSound().playSound("Sounds/Sounds_Skill_Up.wav");
                        }).start();
                    }
                }
                if (nameObject.string.equals("Sld/Hk")) {
                    // Прибавка по уровню к времени шита и крюка
                    if (test.number == man.levelOfShieldAndHook + 1) {
                        man.statPoints = man.statPoints - pointsToUpdate;
                        man.levelOfShieldAndHook = man.levelOfShieldAndHook + 1;
                        if (object_1 == test){ object_1.coloring = true;}
                        if (object_2 == test){ object_2.coloring = true;}
                        if (object_3 == test){ object_3.coloring = true;}
                        if (object_4 == test){ object_4.coloring = true;}
                        if (object_5 == test){ object_5.coloring = true;}
                        new Thread(() -> {
                            new MakeSound().playSound("Sounds/Sounds_Skill_Up.wav");
                        }).start();
                    }
                }
                if (nameObject.string.equals("Luck")) {
                    // Прибавка по уровню к удаче...
                    if (test.number == man.levelOfLuck + 1) {
                        man.statPoints = man.statPoints - pointsToUpdate;
                        man.levelOfLuck = man.levelOfLuck + 1;
                        if (object_1 == test){ object_1.coloring = true;}
                        if (object_2 == test){ object_2.coloring = true;}
                        if (object_3 == test){ object_3.coloring = true;}
                        if (object_4 == test){ object_4.coloring = true;}
                        if (object_5 == test){ object_5.coloring = true;}
                        new Thread(() -> {
                            new MakeSound().playSound("Sounds/Sounds_Skill_Up.wav");
                        }).start();
                    }
                }
            }
        }
    }

}
