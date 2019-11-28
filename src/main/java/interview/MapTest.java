package interview;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @version 1.0
 * @Author ningque
 * @Date 2019/11/28
 *
 * 测试有序与否
 *
 * 执行结果显示：HashMap不能保证存入和取出的顺序一致，但是LinkedHashMap可以保证！
 */
public class MapTest {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(5,"a");
        map.put(8,"b");
        map.put(3,"c");

        for(int key : map.keySet()){
            System.out.println(map.get(key));
        }

        Map<Integer, String> map1 = new LinkedHashMap<>();
        map1.put(5,"a");
        map1.put(8,"b");
        map1.put(3,"c");
        for(Map.Entry entry : map1.entrySet()){
            int key = (int) entry.getKey();
            String value = (String) entry.getValue();
            System.out.println(key + ": "+value);
        }
    }
}
