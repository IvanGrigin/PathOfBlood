import java.util.ArrayList;

public class Levels {

    public int numberOfLevel;
    public int kolOfLevels;
    public ArrayList<Enemy> enemies;
    public ArrayList<Block> blocks;
    public ArrayList<Boos> booses;
    public Portal portal;
    public Man man;

    public Levels(ArrayList<Enemy> enemies, ArrayList<Block> blocks,ArrayList<Boos> booses, Portal portal, Man man, int numberOfLevel){
        this.enemies = enemies;
        this.blocks = blocks;
        this.booses = booses;
        this.portal = portal;
        this.man = man;
        this.numberOfLevel = numberOfLevel;
        this.kolOfLevels = 5;
    }
    public void addLevel(ArrayList<Enemy> enemies, ArrayList<Block> blocks,ArrayList<Boos> booses, Portal portal, Man man){
        double d = Math.random() * (Math.min(numberOfLevel, kolOfLevels));
        int i = 0;
        while (i < kolOfLevels){
            i = i + 1;
            if (d < i / kolOfLevels){
                Level(i);
                i = kolOfLevels + 1;
                break;
            }
        }
        enemies = this.enemies;
        blocks = this.blocks;
        booses = this.booses;
        portal = this.portal;
        man = this.man;
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
    public void Level(int i){
        if (i == 1){
            Level_01();
        }
        if (i == 2){
            Level_02();
        }
        if (i == 3){
            Level_03();
        }
        if (i == 4){
            Level_04();
        }
        if (i == 5){
            Level_05();
        }

    }
    public void Level_01(){
        man.x = 100;
        man.y = 900;
        man.xitPoint = man.maxXP;
        man.isShieldOn = true;
        man.timeOfShield = 0;

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
    }
    public void Level_02(){
        man.x = 100;
        man.y = 900;
        man.xitPoint = man.maxXP;
        man.isShieldOn = true;
        man.timeOfShield = 0;

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
    }
    public void Level_03(){
        man.x = 100;
        man.y = 900;
        man.xitPoint = man.maxXP;
        man.isShieldOn = true;
        man.timeOfShield = 0;

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
    }
    public void Level_04(){
        man.x = 490;
        man.y = 900;
        man.xitPoint = man.maxXP;
        man.isShieldOn = true;
        man.timeOfShield = 0;

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
    }
    public void Level_05(){
        man.x = 100;
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
        for (int i = 0; i < 4; i = i + 1){
            enemies.add(new Enemy(new Block(20, 150 + i * 200, 20, 20, 0),numberOfLevel).makeTurrel());
            enemies.add(new Enemy(new Block(970, 150 + i * 200, 20, 20, 0),numberOfLevel).makeTurrel());
        }
    }

}
