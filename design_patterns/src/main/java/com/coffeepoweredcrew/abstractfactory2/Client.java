package com.coffeepoweredcrew.abstractfactory2;

import com.coffeepoweredcrew.abstractfactory2.aws.AwsResourceFactory;
import com.coffeepoweredcrew.abstractfactory2.gcp.GoogleResourceFactory;

public class Client {

    private ResourceFactory factory;

    public Client(ResourceFactory factory) {
        this.factory = factory;
    }

    public Instance createServer(Instance.Capacity capacity, int storageMib) {
        Instance instance = factory.createInstance(capacity);
        Storage storage = factory.createStorage(storageMib);
        instance.attachStorage(storage);

        return instance;
    }

    public static void main(String[] args) {
        System.out.println("ABSTRACT FACTORY OUTPUT >>");

        Client aws = new Client(new AwsResourceFactory());
        Instance i1 = aws.createServer(Instance.Capacity.micro, 20480);
        i1.start();
        i1.stop();

        System.out.println("ABSTRACT FACTORY 2 (GOOGLE) OUTPUT >>");
        Client gcp = new Client(new GoogleResourceFactory());
        Instance i2 = gcp.createServer(Instance.Capacity.micro, 20480);
        i2.start();
        i2.stop();
    }

}
