package com.coffeepoweredcrew.proxy.dynamic;

import com.coffeepoweredcrew.proxy.Image;
import javafx.geometry.Point2D;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

//Implement invocation handler. Your "proxy" code goes here.
public class ImageInvocationHandler implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Method setLocationMethod = Image.class.getMethod("setLocation", new Class[] {Point2D.class});

        if(setLocationMethod.equals(method)) {
            Point2D point2d = (Point2D) args[0];
            System.out.println("FIC: " + point2d);
        }
        return null;
    }
}
