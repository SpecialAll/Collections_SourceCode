package interview;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * @version 1.0
 * @Author ningque
 * @Date 2019/11/28
 *
 * set测试
 */
public class SetTest {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(67);
        set.add(12);
        set.add(3);
        for(Integer n : set){
            System.out.println(n);
        }
        System.out.println("-------------------------------------------");
        Set<Integer> set2 = new TreeSet<>();
        set2.add(67);
        set2.add(12);
        set2.add(3);
        set2.add(13);
        for(Integer n : set2){
            System.out.println(n);
        }
        System.out.println("-------------------------------------------");
        Set<Integer> set3 = new LinkedHashSet<>();
        set3.add(67);
        set3.add(12);
        set3.add(3);
        set3.add(13);
        for(Integer n : set3){
            System.out.println(n);
        }
    }
}
