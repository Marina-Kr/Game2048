import java.util.Comparator;
import java.util.List;

public class GameHelper {

    static List<Integer> mergeEqual(List<Integer> values) {   // объединяем элементы
        for (int i = 0; i < values.size() - 1; i++) {
            if (values.get(i) != null && values.get(i).equals(values.get(i + 1))) {
                values.set(i, values.get(i) * 2);
                values.set(i + 1, null);
            }
        }
        return values;
    }

    public static List<Integer> moveItems(List<Integer> list) {   // перемещаем элементы в начало
        list.sort(Comparator.nullsLast(Comparator.comparing(o -> true)));
//        List<Integer> newList = list;
//        List<Integer> tempListWithNull = new ArrayList<>();
//        List<Integer> tempListWithoutNull = new ArrayList<>();
//
//        for (int i = 0; i < newList.size(); i++) {
//            if (newList.get(i) == null) {
//                tempListWithNull.add(null);
//            } else {
//                tempListWithoutNull.add(newList.get(i));
//            }
//        }
//
//        tempListWithoutNull.addAll(tempListWithNull);
        return mergeEqual(list);
    }

    public static List<Integer> moveAndMergeEqual(List<Integer> list) {
        List<Integer> movedList = moveItems(list);
        List<Integer> mergedList = mergeEqual(movedList);
        return moveItems(mergedList);
    }
}