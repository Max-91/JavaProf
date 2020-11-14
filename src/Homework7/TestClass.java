package Homework7;

public class TestClass {

    @BeforeSuite
    public static void startTest() {
        System.out.println("Начала тестирования");
    }

    @AfterSuite
    public static void endTest() {
        System.out.println("Конец тестирования");
    }

    @Test(priority = 1)
    public static void test1() {
        System.out.println("Тест 1");
    }

    @Test(priority = 2)
    public static void test2() {
        System.out.println("Тест 2");
    }

    @Test(priority = 3)
    public static void test31() {
        System.out.println("Тест 3.1");
    }

    @Test(priority = 3)
    public static void test32() {
        System.out.println("Тест 3.2");
    }

    @Test(priority = 4)
    public static void test4() {
        System.out.println("Тест 4");
    }

    @Test(priority = 10)
    public static void test10() {
        System.out.println("Тест 10");
    }
}
