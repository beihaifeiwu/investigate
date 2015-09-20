package com.freetmp.investigate.qi4j.helloworld3;

import org.qi4j.api.activation.ActivationException;
import org.qi4j.api.composite.TransientBuilder;
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
    TransientBuilder<HelloWorldComposite> builder = assembler.module().newTransientBuilder(HelloWorldComposite.class);

    HelloWorldState state = builder.prototypeFor(HelloWorldState.class);
    state.phrase().set("Hello");
    state.name().set("World");

    HelloWorldComposite composite = builder.newInstance();
    System.out.println(composite.say());
  }
}