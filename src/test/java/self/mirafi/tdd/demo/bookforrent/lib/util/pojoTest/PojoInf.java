package self.mirafi.tdd.demo.bookforrent.lib.util.pojoTest;

import self.mirafi.tdd.demo.bookforrent.constant.ENUMS;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PojoInf {
    private Class<?> genericClass;
    private Set<Map<ENUMS.POJO_METHODS,Method>> methods = new HashSet<>();
    private Method hashCodeMethod;
    private Method equalsMethod;

    public PojoInf(Class<?> genericClass) {
        this.genericClass = genericClass;
    }

    public Class<?> getGenericClass() {
        return genericClass;
    }

    public Set<Map<ENUMS.POJO_METHODS,Method>> getMethods() {
        return methods;
    }

    public Method getHashCodeMethod() {
        return hashCodeMethod;
    }

    public Method getEqualsMethod() {
        return equalsMethod;
    }


    void setHashCodeMethod(Method hashCodeMethod) {
        this.hashCodeMethod = hashCodeMethod;
    }

    void setEqualsMethod(Method equalsMethod) {
        this.equalsMethod = equalsMethod;
    }

    void setGenericClass(Class<?> genericClass) {
        this.genericClass = genericClass;
    }





    void addMethod(Map<ENUMS.POJO_METHODS,Method> methodMap) {
        this.methods.add(methodMap);
    }

    @Override
    public String toString() {
        return "PojoInf{" +
                "genericClass=" + genericClass +
                ", methods=" + methods +
                ", hashCodeMethod=" + hashCodeMethod +
                ", equalsMethod=" + equalsMethod +
                '}';
    }
}
