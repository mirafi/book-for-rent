package self.mirafi.tdd.demo.bookforrent.test.pojoTest;


import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import self.mirafi.tdd.demo.bookforrent.constant.ENUMS;
import self.mirafi.tdd.demo.bookforrent.lib.util.pojoTest.PojoInf;
import self.mirafi.tdd.demo.bookforrent.lib.util.pojoTest.PojoUtil;
import self.mirafi.tdd.demo.bookforrent.persistence.entity.Book;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

import static org.fest.assertions.Assertions.assertThat;

@SpringBootTest
public class PojoTest {


    @Test
    public void setterGetterTest() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
       PojoInf pojoInf =  PojoUtil.getMethod(Book.class);
      Object obj =   Book.class.newInstance();
       Set<Map<ENUMS.POJO_METHODS,Method>> methods = pojoInf.getMethods();

        for (Map<ENUMS.POJO_METHODS,Method> methodMap: methods ) {

            Method setterMethod =  methodMap.get(ENUMS.POJO_METHODS.SETTER);
            Method getterMethod =  methodMap.get(ENUMS.POJO_METHODS.GETTER);
            System.out.println(String.class.getName());
            String parameterClassType = setterMethod.getParameters()[0].getType().getName();
            if(!parameterClassType.equals(String.class.getName()))continue;
            String arg = "A";
            setterMethod.invoke(obj,arg);
            Object getReturned = getterMethod.invoke(obj);

            assertThat(getReturned)
                    .as("GETTER SETTER FAILED FOR arg : "+arg)
                    .isEqualTo(arg);

        }

    }
}
