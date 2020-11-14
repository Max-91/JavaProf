package Homework7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
Создать класс, который может выполнять «тесты». В качестве тестов выступают классы с наборами методов
 с аннотациями @Test. Для этого у него должен быть статический метод start(), которому в качестве
  параметра передается или объект типа Class, или имя класса. Из «класса-теста» вначале должен быть
   запущен метод с аннотацией @BeforeSuite, если такой имеется. Далее запущены методы с
   аннотациями @Test, а по завершении всех тестов – метод с аннотацией @AfterSuite. К каждому тесту
    необходимо добавить приоритеты (int числа от 1 до 10), в соответствии с которыми будет выбираться
     порядок их выполнения. Если приоритет одинаковый, то порядок не имеет значения.
     Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном экземпляре,
      иначе необходимо бросить RuntimeException при запуске «тестирования».
 */
public class MainClass {
    public static void main(String[] args) {
        try {
            start(TestClass.class);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void start(Class cl) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = cl.getDeclaredMethods();
        // Проверка на кол-во BeforeSuite и AfterSuite
        // и еще проверка на корректность приоритетов от 0 до 10
        int countBefore = 0;
        int countAfter = 0;
        for (Method m : methods) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                countBefore++;
            }
            if (m.isAnnotationPresent(AfterSuite.class)) {
                countAfter++;
            }
            if (m.isAnnotationPresent(Test.class)) {
                if (m.getAnnotation(Test.class).priority() < 0 | m.getAnnotation(Test.class).priority() > 10) {
                    throw new RuntimeException("Неправильно указан приоритет у аннотации @Test (должен быть от 0 до 10)");
                }
            }
        }
        if (countBefore > 1) {
            throw new RuntimeException("Более одного @BeforeSuite ");
        }
        if (countAfter > 1) {
            throw new RuntimeException("Более одного @AfterSuite");
        }
        // Выполнение @BeforeSuite
        for (Method m : methods) {
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                m.invoke(null);
            }
        }

        // Выполнение тестов по приоритетам
        for (int i = 0; i <= 10; i++) {
            for (Method m : methods) {
                if (m.isAnnotationPresent(Test.class)) {
                    if (m.getAnnotation(Test.class).priority() == i) {
                        m.invoke(null);
                    }
                }
            }
        }
        // Выполнение @AfterSuite
        for (Method m : methods) {
            if (m.isAnnotationPresent(AfterSuite.class)) {
                m.invoke(null);
            }
        }

    }
}
