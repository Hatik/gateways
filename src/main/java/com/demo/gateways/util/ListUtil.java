package com.demo.gateways.util;

import java.util.List;
import java.util.stream.Collectors;

public class ListUtil{
    public static <CT, CF> List<CT> copy(List<CF> list, Copier<CT, CF> copier){
        return list.stream().map(copier::copy).collect(Collectors.toList());
    }


    public interface Copier<CT,CF> {
        CT copy(CF item);
    }
}
