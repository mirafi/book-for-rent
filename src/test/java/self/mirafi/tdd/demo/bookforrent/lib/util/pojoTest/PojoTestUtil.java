package self.mirafi.tdd.demo.bookforrent.lib.util.pojoTest;

import org.mockito.Mockito;
import self.mirafi.tdd.demo.bookforrent.constant.ENUMS;
import self.mirafi.tdd.demo.bookforrent.persistence.entity.Book;
import self.mirafi.tdd.demo.bookforrent.service.SearchService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class PojoTestUtil {
    public static <T> void getMethod(Class<T> tClass) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {

        PojoInf pojoInf = new PojoInf(tClass);
        Method[]  methods = tClass.getDeclaredMethods();
        T obj =  tClass.newInstance();



        for(Method method:methods){
            String methodName = method.getName();
            if(!methodName.startsWith("set"))continue;
            System.out.println(method.getName());
            Method getMethod  = tClass.getMethod(methodName.replaceFirst("set","get"));

            Map<ENUMS.POJO_METHODS,Method> methodMap = new HashMap<>();

            methodMap.put(ENUMS.POJO_METHODS.SETTER,method);
            methodMap.put(ENUMS.POJO_METHODS.GETTER,getMethod);

            pojoInf.addMethod(methodMap);

        }
        for(Method method:methods){
            String methodName = method.getName();

            if(!methodName.startsWith("hashCode") &
                    !methodName.startsWith("equals")     )continue;


            System.out.println(method.getName());


        }

    }

    public static void main(String[] args) {
        System.out.println(Mockito.mock(Integer.class));
        System.out.println(Mockito.mock(Integer.class));



        try {
            getMethod(Book.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
