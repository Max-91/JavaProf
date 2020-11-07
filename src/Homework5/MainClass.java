package Homework5;
/*  ЗАДАНИЕ:
Все участники должны стартовать одновременно, несмотря на то, что на подготовку у каждого их них уходит разное время.
В тоннель не может заехать одновременно больше половины участников (условность).
Попробуйте все это синхронизировать.
Только после того, как все завершат гонку, нужно выдать объявление об окончании.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static CountDownLatch cdl1=new CountDownLatch(CARS_COUNT); // Счетчик потоков для старта гонки
    public static CountDownLatch cdl2=new CountDownLatch(CARS_COUNT); // Счетчик потоков для завершения гонки
    public static Semaphore smp= new Semaphore(CARS_COUNT/2); // Семафор показывает кол-во потоков которые можно выполнять одновременно, остальные в wait
    public static boolean wasWin = false;

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        try {
            cdl1.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
            cdl2.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

