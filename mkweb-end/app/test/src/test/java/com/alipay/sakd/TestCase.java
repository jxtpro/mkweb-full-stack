package com.alipay.sakd;


import com.alibaba.fastjson.JSON;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author xujie
 * @since 2020/4/9 8:48
 */
public class TestCase {

  static int[] candidates = {1, 2, 3, 9, 8, 4, 6, 5, 7};


  public static List<Set<Integer>> combinationSum(int[] candidates, int target) {
    List<Set<Integer>> result = new ArrayList<>();
    Set<Integer> list = new HashSet<>();
    Arrays.sort(candidates);
    combinationHelper(result, list, candidates, target, 0);
    return result;
  }

  private static void combinationHelper(List<Set<Integer>> result, Set<Integer> list, int[] candidates, int target, int i) {
    if (target < 0) {
      return;
    } else if (target == 0) {
      result.add(list);
      return;
    } else {
      for (int j = i; j < candidates.length && candidates[j] <= target; j++) {
        Set<Integer> tmp = new HashSet<>(list);
        tmp.add(candidates[j]);
        combinationHelper(result, tmp, candidates, target - candidates[j], j + 1);
      }
    }
  }

  public static void main(String[] args) {
    List<Set<Integer>> list = combinationSum(candidates, 12);
    for (Set<Integer> candidate : list) {
      System.out.println(JSON.toJSONString(candidate));
    }
    //    List list = new ArrayList();
    //    List list2 = new LinkedList();
    //    CountDownLatch latch = new CountDownLatch(10);
    //    latch.await();
    //    System.out.println(DigestUtils.md5DigestAsHex("我要进入系统".getBytes(Charset.forName("UTF-8"))));
    //    System.out.println(DigestUtils.md5DigestAsHex("我要进入系统".getBytes(Charset.forName("UTF-8"))));
    //    System.out.println(DigestUtils.md5DigestAsHex("(abc.123D)".getBytes(Charset.forName("UTF-8"))));
    //    System.out.println("eb91de42e26d36baaf571dd9383c6fd7".length());
  }


}
