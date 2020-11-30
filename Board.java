import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Board<K, V> {
    protected int weigh;
    protected int height;
    protected Map<K, V> board = new HashMap<>();

    public Board(int weigh, int height) {
        this.weigh = weigh;
        this.height = height;
    }

    public abstract void fillBoard(List<V> list);

    public abstract List<K> availableSpace();

    public abstract void addItem(K key, V value);

    public abstract K getKey(int i, int j);

    public abstract V getValue(K key);

    public abstract List<K> getColumn(int j);

    public abstract List<K> getRow(int i);

    public abstract boolean hasValue(V value);

    public abstract List<V> getValues(List<K> keys);
}