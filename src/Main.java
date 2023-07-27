import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence;
    public static int[] heroesHealth = {270, 280, 250, 300, 240, 300, 450, 250};
    public static int[] heroesDamage = {10, 15, 20, 0, 10, 15, 5, 10};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic", "Lucky", "Thor", "Golem", "Berserk"};
    public static int roundNumber;
    public static void main(String[] args) {

        printStatistics();
        while (!isGameOver()) {
            playRound();
        }

    }
    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        golemTakesDamage();
        berserkBlock();
        bossHits();
        luckyEscapeHit();
        thorStunnedBoss();
        healing();
        heroesHit();
        printStatistics();

    }

    public static void healing (){
        int healing = 80;
        boolean oneTimeHealing = false;
        for (int i = 0; i < heroesHealth.length; i++){
            if (bossHealth >=0 && heroesHealth[3] >= 0 && heroesHealth[i] < 100  && heroesHealth[i] > 0) {
                if (!oneTimeHealing) {
                    if(heroesAttackType[i] != "Healer"){
                        oneTimeHealing=true;
                        heroesHealth[i] += healing;
                        System.out.println( heroesAttackType[3] + " just healed hero " + heroesAttackType[i]);
                    }
                }
            }
        }
    }
    public static void luckyEscapeHit(){
        Random escape = new Random();
        boolean randomEscape = escape.nextBoolean();
        if (randomEscape){
            if (heroesHealth[4]>0){
                heroesHealth[4] += bossDamage;
                System.out.println("Hero " + heroesAttackType[4] + " just escaped attack!!!");
            }
        }
    }

    public static void thorStunnedBoss(){
        Random thor = new Random();
        boolean stun = thor.nextBoolean();
        if (stun){
            int damage = bossDamage;
            damage = 0;
            System.out.println(heroesAttackType[5] + " stunned Boss");
        }
    }
    public static void golemTakesDamage(){
        if (heroesHealth[6] > 0 && bossHealth > 0) {
            int damage = bossDamage/5;
            heroesHealth[6] -= damage;
            bossDamage -= damage;
            System.out.println(heroesAttackType[6] + " took 20% of damage from his allies!");
        }
    }

    public static void berserkBlock (){
        int block = bossDamage/10;
        heroesHealth[7] += block;
        heroesDamage[7] += block;
    }
    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefence = heroesAttackType[randomIndex];
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                heroesHealth[i] -= bossDamage; // => heroesHealth[i] = heroesHealth[i] - bossDamage;
                if (heroesHealth[i] < 0) {
                    heroesHealth[i] = 0;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                int damage = heroesDamage[i];
                if (bossDefence == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                    damage = heroesDamage[i] * coeff;
                    System.out.println("Critical damage: " + damage);
                }
                bossHealth -= damage; // => bossHealth = bossHealth - heroesDamage[i];
                if (bossHealth < 0) {
                    bossHealth = 0;
                }
            }
        }
    }

    public static boolean isGameOver() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
         /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;


        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        System.out.println("ROUND " + roundNumber + " ------------");
        /*String defence;
        if (bossDefence == null) {
            defence = "No defence";
        } else {
            defence = bossDefence;
        }*/
        System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage + " defence: " +
                (bossDefence == null ? "No defence" : bossDefence));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] +
                    " damage: " + heroesDamage[i]);
        }
    }
}