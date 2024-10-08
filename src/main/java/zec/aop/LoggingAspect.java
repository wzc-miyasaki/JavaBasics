
package zec.aop;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LoggingAspect implements InvocationHandler {

    private Object target;

    public Object bind(Object realTrg) {
        target = realTrg;

        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(target.getClass().getName() + " : " + method.getName() + " is invoked");
        return method.invoke(target, args);
    }
}
