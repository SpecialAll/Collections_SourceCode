package interview;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @version 1.0
 * @Author ningque
 * @Date 2019/11/28
 *
 * list测试是否有序
 *
 * ArrayList和LinkedList都是有序的；
 */
public class ListTest {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(34);
        list.add(19);
        list.add(1);
        for(Integer list1 : list){
            System.out.println(list1);
        }

        List<Integer> linkedList = new LinkedList<>();
        linkedList.add(3);
        linkedList.add(48);
        linkedList.add(19);
        linkedList.add(1);
        for(Integer list1 : linkedList){
            System.out.println(list1);
        }
    }
}
