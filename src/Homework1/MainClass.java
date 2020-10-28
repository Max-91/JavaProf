package Homework1;

import java.util.ArrayList;

/*
Задание:
1. Написать метод, который меняет два элемента массива местами (массив может быть любого
ссылочного типа);
2. Написать метод, который преобразует массив в ArrayList;
3. Задача:
a. Даны классы Fruit, Apple extends Fruit, Orange extends Fruit;
b. Класс Box, в который можно складывать фрукты. Коробки условно сортируются по
типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
c. Для хранения фруктов внутри коробки можно использовать ArrayList;
d. Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного фрукта
и их количество: вес яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
e. Внутри класса Box сделать метод compare(), который позволяет сравнить текущую
коробку с той, которую подадут в compare() в качестве параметра. true – если их массы
равны, false в противоположном случае. Можно сравнивать коробки с яблоками и
апельсинами;
f. Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую.
Помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами.
Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются
объекты, которые были в первой;
g. Не забываем про метод добавления фрукта в коробку.
 */
public class MainClass {
    public static void main(String[] args) {
        // Задание 1
        Integer[] arrInt = {10, 15, 20, 25, 30, 35, 40};
        Float[] arrFloat = {1.0f, 1.5f, 2.0f, 2.5f, 3.0f, 3.5f, 4.0f, 4.5f};
        String[] arrString = {"word1", "word2", "word3", "word4"};
        changeElements(arrString, 2, 3);
        showArr(arrString);

        // Задание 2
        System.out.println(transferToArrayList(arrFloat));

        // Задание 3
        Box<Apple> box1 = new Box<>(new Apple(), 5);
        Box<Apple> box2 = new Box<>(new Apple(), 9);
        Box<Orange> box3 = new Box<>(new Orange(), 6);
        Box<Orange> box4 = new Box<>(new Orange(), 12);
        // Отображение всех ящиков
        System.out.println(" В 1 ящике  " + box1 + ", вес " + box1.getWeight());
        System.out.println(" Во 2 ящике " + box2 + ", вес " + box2.getWeight());
        System.out.println(" В 3 ящике  " + box3 + ", вес " + box3.getWeight());
        System.out.println(" В 4 ящике  " + box4 + ", вес " + box4.getWeight());

        System.out.println(box2.compare(box3)); // Проверка одинаковый ли вес
        box3.moveTo(box4); // Передача из 3 в 4 ящик
        // Отображение 3 и 4 ящиков
        System.out.println(" В 3 ящике  " + box3 + ", вес " + box3.getWeight());
        System.out.println(" В 4 ящике  " + box4 + ", вес " + box4.getWeight());

    }

    // Метод для изменения массива (первое задание)
    public static <T> void changeElements(T[] arr, int First, int Second) {
        T buffer = arr[First];
        arr[First] = arr[Second];
        arr[Second] = buffer;
    }

    // Метод для отбражения массива любого типа (для первого задания)
    public static <T> void showArr(T[] arr) {
        for (T element : arr) {
            System.out.print(element + "  ");
        }
        System.out.println(); // Переход на следующую строку
    }

    // Метод для преобразование массива в ArrayList (второе задание)
    public static <T> ArrayList<T> transferToArrayList(T[] arr) {
        ArrayList<T> arrayList = new ArrayList<>();
        for (T element : arr) {
            arrayList.add(element);
        }
        return arrayList;
    }

}