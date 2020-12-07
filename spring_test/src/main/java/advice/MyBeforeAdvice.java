package advice;

import aop.MyLogger;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
public class MyBeforeAdvice implements MethodBeforeAdvice {
    @Autowired
    private MyLogger myLogger;

    public MyLogger getMyLogger() {
        return myLogger;
    }

    public void setMyLogger(MyLogger myLogger) {
        this.myLogger = myLogger;
    }

    public void before(Method method, Object[] objects, Object o) throws Throwable {
        myLogger.log(method.getName()+" is invoke...");

    }
}
