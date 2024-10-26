import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class ArrayListExample {

    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();

        list1.add(1);
        list1.add(3);
        list1.add(2);
        System.out.println("After addition:" + list1);

        list1.remove(1);
        System.out.println("After removal:" + list1);

        list1.set(0, 5);
        System.out.println("After update:" + list1);

//        Vector v = new Vector();
//        v.add(1);

        Iterator<Integer> iteratorObj = list1.iterator();
        while (iteratorObj.hasNext()){
            System.out.println(iteratorObj.next());
            iteratorObj.remove();
//            iteratorObj.forEachRemaining();
        }

        for(Integer i : list1){
            System.out.println(i);
        }
    }
}
