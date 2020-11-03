package Homework4;
/*
 Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС).
 Используйте wait/notify/notifyAll.
 */
public class MainClass {
    static final Object mon = new Object(); // монитор, объект которые показывает, что один из синхронных потоков используется (статик, чтобы у переменный была один и тот же указатель при изменение значения)
    static volatile int currentThread = 1; // переменная, которая не может быть закэширована (поэтому все ее значения будут видны в программе)
    static final int count = 5; // константа в программе - кол-во повторений

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis(); // Фиксация времени начала
        new Thread(() -> {  // Первый поток
            try {
                for (int i = 0; i < count; i++) {
                    synchronized (mon) {
                        while (currentThread != 1) { // выход из цикла когда currentThread будет 1
                            mon.wait();  // Ожидание когда освободится монитор, чтобы перейти к повторной проверки currentThread
                        }
                        System.out.print("A");
                        currentThread = 2;
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {  // Второй поток
            try {
                for (int i = 0; i < count; i++) {
                    synchronized (mon) {
                        while (currentThread != 2) { // выход из цикла когда currentThread будет 1
                            mon.wait();  // Ожидание когда освободится монитор, чтобы перейти к повторной проверки currentThread
                        }
                        System.out.print("B");
                        currentThread = 3;
                        mon.notifyAll();
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {  // Третий поток
            try {
                for (int i = 0; i < count; i++) {
                    synchronized (mon) {
                        while (currentThread != 3) { // выход из цикла когда currentThread будет 1
                            mon.wait();  // Ожидание когда освободится монитор, чтобы перейти к повторной проверки currentThread
                        }
                        System.out.print("C");
                        currentThread = 1;
                        mon.notifyAll();
                    }
                }
                System.out.println("\nВремя выполнения: " + (System.currentTimeMillis() - startTime) + " мсек"); // получилось 40-50 мсек
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
