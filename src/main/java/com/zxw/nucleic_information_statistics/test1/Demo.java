package com.zxw.nucleic_information_statistics.test1;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Demo {

    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("hello");
        words.add("world");
        System.out.println(test2(words));
    }

    public static char[] reverse(char[] data) {
        if (data==null||data.length-1<1){
            return null;
        }
        int left = 0;
        int right = data.length-1;
        while (left < right) {
            char temp = data[left];
            data[left] = data[right];
            data[right] = temp;
            left++;right--;
        }
        return data;
    }

    public static List<Character> test2(List<String> words) {
        List<Character> list = new ArrayList<>();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                char strs = word.charAt(i);
                if(!list.contains(strs)){
                    list.add(strs);
                }
            }
        }
        return list;
    }

    public Set<Character> test3(List<String> words) {
        Set<Character> set = new HashSet<>();
        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                set.add(word.charAt(i));
            }
        }
        return set;
    }
}
