package com.freetmp.investigate.algorithm;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LiuPin on 2015/5/12.
 */
@State(Scope.Thread)
public class TwoSum extends AlgorithmProfileSuite {

  int[] nums = new int[]{3, 6, 8, 4};
  int target = 11;

  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap();
    for (int i = 0; i < nums.length; i++) {
      int num = nums[i];
      if (null == map.get(target - num)) {
        map.put(num, i + 1);
      } else {
        return new int[]{map.get(target - num), i + 1};
      }
    }
    return null;
  }

  @Benchmark
  public int[] test() {
    return twoSum(nums, target);
  }

  public static void main(String[] args){
    new TwoSum().run();
  }
}
