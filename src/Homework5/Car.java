package Homework5;

import java.util.concurrent.TimeUnit;

public class Car implements Runnable {
    private static int CARS_COUNT;

    static {
        CARS_COUNT = 0;
    }

    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            MainClass.cdl1.countDown();
            Thread.sleep(100); // Добавил задержку, чтобы сначала снимался cdl1.await в MainClass, иначе появляется сообщение, что учасник начал этап
            MainClass.cdl1.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        MainClass.cdl2.countDown();
        if (!MainClass.wasWin) { // Проверка есть ли уже победитель
            System.out.println(this.name + " ПОБЕДИЛ !!");
            MainClass.wasWin=true;
        }
    }
}
