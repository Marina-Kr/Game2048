import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SquareBoard<V> extends Board<Key, V> {

    public SquareBoard(int size) {
        super(size, size);
    }

    @Override
    public void fillBoard(List<V> list) {
        if (list.size() != weigh * height) throw new RuntimeException();
        for (int i = 0; i < weigh; i++) {
            for (int j = 0; j < height; j++) {
                board.put(new Key(i, j), list.get(i * height + j));
            }
        }
    }

    @Override
    public List<Key> availableSpace() {
        List<Key> keys = new ArrayList<>();
        for (Map.Entry<Key, V> pair : board.entrySet()) {
            if (pair.getValue() == null) {
                keys.add(pair.getKey());
            }
        }

        return keys;
    }

    @Override
    public void addItem(Key key, V value) {
        this.board.put(key, value);
    }

    @Override
    public Key getKey(int i, int j) {
        for (Key key : board.keySet()) {
            if (key.getI() == i && key.getJ() == j)
                return key;
        }
        return null;
    }

    @Override
    public V getValue(Key key) {
        for (Map.Entry<Key, V> pair : board.entrySet()) {
            if (pair.getKey() == key) {
                return pair.getValue();
            }
        }
        return null;
    }

    @Override
    public List<Key> getColumn(int j) {
        List<Key> list = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            list.add(getKey(i, j));
        }
        return list;
    }

    @Override
    public List<Key> getRow(int i) {
        List<Key> list = new ArrayList<>();
        for (int j = 0; j < this.weigh; j++) {
            list.add(getKey(i, j));
        }
        return list;
    }

    @Override
    public boolean hasValue(V value) {
        return this.board.containsValue(value);
    }

    @Override
    public List<V> getValues(List<Key> keys) {
        List<V> list = new ArrayList<>();
        for (Key key : keys) {

            for (Map.Entry<Key, V> pair : board.entrySet()) {
                if (key == pair.getKey()) {
                    list.add(pair.getValue());
                }
            }

        }
        return list;
    }
}