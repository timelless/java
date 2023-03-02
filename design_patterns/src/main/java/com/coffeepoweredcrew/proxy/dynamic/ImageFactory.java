package com.coffeepoweredcrew.proxy.dynamic;

import java.lang.reflect.Proxy;

import com.coffeepoweredcrew.proxy.Image;

//Factory to get image objects. 
public class ImageFactory {
	//We'll provide proxy to caller instead of real object
	public static Image getImage() {
        return (Image) Proxy.newProxyInstance(ImageFactory.class.getClassLoader(), new Class[] {Image.class}, new ImageInvocationHandler());
    }
}