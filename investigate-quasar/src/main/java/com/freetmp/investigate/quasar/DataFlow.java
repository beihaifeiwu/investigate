package com.freetmp.investigate.quasar;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.Strand;
import co.paralleluniverse.strands.dataflow.Val;
import co.paralleluniverse.strands.dataflow.Var;

import java.util.concurrent.ExecutionException;

/**
 * Created by LiuPin on 2015/7/29.
 */
public class DataFlow {

  public static void main(String[] args) throws ExecutionException, InterruptedException, SuspendExecution {
    Val<Integer> a = new Val<>();
    Var<Integer> x = new Var<>();
    Var<Integer> y = new Var<>(() -> a.get() * x.get());
    Var<Integer> z = new Var<>(() -> a.get() + x.get());
    Var<Integer> r = new Var<>(() -> {
      int res = y.get() + z.get();
      System.out.println("res: " + res);
      return res;
    });

    Fiber<?> f = new Fiber<Void>(() -> {
      for (int i = 0; i < 200; i++) {
        x.set(i);
        Strand.sleep(100);
      }
    }).start();

    Strand.sleep(2000);
    a.set(3); // this will trigger everything
    f.join();
  }
}
