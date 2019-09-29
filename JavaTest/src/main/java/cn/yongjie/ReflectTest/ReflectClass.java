package cn.yongjie.ReflectTest;


import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectClass {

    private final static String TAG = "peter.log.ReflectClass";


    // 创建对象
    public static void reflectNewInstance() {

        try{
            Class<?> classBook = Class.forName("cn.yongjie.javaTest.Book");
            Object objectBook = classBook.newInstance();
            Book book = (Book) objectBook;
            book.setName("Android进阶之光");
            book.setAuthor("刘望舒");
            System.out.println(TAG + "reflectNewInstance book = " + book.toString());
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    // 反射私有的构造方法
    public static void reflectPrivateConstructor() {

        try {
            Class<?> classBook = Class.forName("cn.yongjie.javaTest.Book");
            Constructor<?> declaredConstructorBook = classBook.getDeclaredConstructor(String.class,String.class);
            declaredConstructorBook.setAccessible(true);
            Object objectBook = declaredConstructorBook.newInstance("Android开发艺术探索","任玉刚");
            Book book = (Book) objectBook;
            System.out.println(TAG + "reflectPrivateConstructor book = " + book.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 反射私有属性
    public static void reflectPrivateField() {
        try {
            Class<?> classBook = Class.forName("cn.yongjie.javaTest.Book");
            Object objectBook = classBook.newInstance();
            Field fieldTag = classBook.getDeclaredField("TAG");
            fieldTag.setAccessible(true);
            String tag = (String) fieldTag.get(objectBook);
            System.out.println(TAG + "reflectPrivateField tag = " + tag);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // 反射私有方法
    public static void reflectPrivateMethod() {
        try {
            Class<?> classBook = Class.forName("cn.yongjie.javaTest.Book");
            Method methodBook = classBook.getDeclaredMethod("declaredMethod",int.class);
            methodBook.setAccessible(true);
            Object objectBook = classBook.newInstance();
            String string = (String) methodBook.invoke(objectBook,0);
            System.out.println(TAG + "reflectPrivateMethod string = " + string);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
