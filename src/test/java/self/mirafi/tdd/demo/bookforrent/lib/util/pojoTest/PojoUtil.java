package self.mirafi.tdd.demo.bookforrent.lib.util.pojoTest;

import self.mirafi.tdd.demo.bookforrent.constant.ENUMS;
import self.mirafi.tdd.demo.bookforrent.persistence.entity.Book;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class PojoUtil {
    public static <T> PojoInf getMethod(Class<T> tClass) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {

        PojoInf pojoInf = new PojoInf(tClass);
        Method[]  methods = tClass.getDeclaredMethods();

        for(Method method:methods){
            String methodName = method.getName();
            if(!methodName.startsWith("set"))continue;
            Method getMethod  = tClass.getMethod(methodName.replaceFirst("set","get"));

            Map<ENUMS.POJO_METHODS,Method> methodMap = new HashMap<>();

            methodMap.put(ENUMS.POJO_METHODS.SETTER,method);
            methodMap.put(ENUMS.POJO_METHODS.GETTER,getMethod);

            pojoInf.addMethod(methodMap);

        }
        for(Method method:methods){
            String methodName = method.getName();

            if(!methodName.startsWith("hashCode") &
                    !methodName.startsWith("equals"))continue;


            if(methodName.startsWith("hashCode"))pojoInf.setHashCodeMethod(method);
            if(methodName.startsWith("equals"))pojoInf.setEqualsMethod(method);


        }


        return pojoInf;

    }

    public static void main(String[] args) {



        try {
           PojoInf pojoInf =  getMethod(Book.class);
            System.out.println(pojoInf);
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
