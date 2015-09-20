package com.freetmp.investigate.qi4j.helloworld2;

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
      @Override
      public void assemble(ModuleAssembly moduleAssembly) throws AssemblyException {
        moduleAssembly.transients(HelloWorldComposite.class);
      }
    };

    HelloWorldComposite speaker = assembler.module().newTransient(HelloWorldComposite.class);
    speaker.phrase().set("Hello");
    speaker.name().set("World");
    System.out.println(speaker.say());
  }
}