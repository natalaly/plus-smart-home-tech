package ru.yandex.practicum.commerce.cart.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SandBox {

  public static void main(String[] args) {

    Map<String, Integer> mapOne = new HashMap<>();
    mapOne.put("1", 2);
    mapOne.put("2", 3);
    mapOne.put("3", 1);
    mapOne.put("4", 5);


    Set<String> set = new HashSet<>();
    set.add("1");
    set.add("5");


mapOne.keySet().retainAll(set);

//    mapOne.putAll(mapTwo);
    // Merge mapTwo into mapOne
//    mapTwo.forEach((key, value) -> mapOne.merge(key, value, Integer::sum));

    System.out.println(mapOne);

  }

}
