package Homework1;

import java.util.ArrayList;

public class Box<T extends Fruit> {
    private ArrayList<T> fruits;

    // Конструкторы
    public Box() {
        fruits=new ArrayList<>();
    }

    public Box(T fruit, int count ) {
        fruits=new ArrayList<>();
        this.addFruit(fruit,count);
    }

    public Box(ArrayList<T> fruits) {
        this.fruits = fruits;
    }

    // Расчет общего веса
    public float getWeight() {
        float sum = 0f;
        for (T element : fruits) {
            sum += element.getWeight();
        }
        return sum;
    }

    // Сравнение веса с другой коробкой
    public boolean compare(Box otherBox) {
        boolean sameWeight;
        if (Math.abs(this.getWeight() - otherBox.getWeight()) < 0.01) {
            sameWeight = true;
        } else {
            sameWeight = false;
        }
        return sameWeight;
    }

    // Перекладываение всех фруктов в другую коробку
    public void moveTo(Box<? super T> otherBox) {
        if (otherBox.equals(this)) {
             return;
        }
        for (T element: fruits){
            otherBox.addFruit(element,1);
        }
        this.fruits.clear();
    }

    // Добаление нового фрукта
    public void addFruit(T fruit,int count) {
        for (int i = 0; i < count; i++) {
            this.fruits.add(fruit);
        }
    }

    // Отображение содержимого коробки
    @Override
    public String toString() {
        if (fruits.toArray().length==0) return "пусто";
        if (fruits.get(0) instanceof Apple) return fruits.toArray().length+" яблок";
        return  fruits.toArray().length+" апельсинов";
    }
}
