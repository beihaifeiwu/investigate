package com.freetmp.investigate.qi4j.helloworld;

import org.qi4j.api.activation.ActivationException;
import org.qi4j.bootstrap.AssemblyException;
import org.qi4j.bootstrap.ModuleAssembly;
import org.qi4j.bootstrap.SingletonAssembler;

/**
 * Created by LiuPin on 2015/4/28.
 */
public class Main {
    public static void main(String[] args) throws ActivationException, AssemblyException {

        SingletonAssembler assembler = new SingletonAssembler() {
            @Override public void assemble(ModuleAssembly moduleAssembly) throws AssemblyException {
                moduleAssembly.transients(Speaker.class);
            }
        };

        Speaker speaker = assembler.module().newTransient(Speaker.class);
        System.out.println(speaker.sayHello());
    }
}
