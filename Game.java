public interface Game {

    void init();

    boolean canMove();

    void move(Direction direction) throws GameOverException;

    void addItem();

    Board<Key, Integer> getGameBoard();

    boolean hasWin();
}