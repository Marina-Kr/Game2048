import java.util.Collections;
import java.util.List;

public class Game2048 implements Game {
    public static final int GAME_SIZE = 4;
    private final Board<Key, Integer> gameBoard = new SquareBoard<>(GAME_SIZE);

    @Override
    public void init() {
        gameBoard.board.clear();
        for (int i = 0; i < GAME_SIZE; i++) {
            for (int j = 0; j < GAME_SIZE; j++) {
                gameBoard.addItem(new Key(i, j), null);
            }
        }
        this.addItem();
        this.addItem();
    }

    @Override
    public boolean canMove() {
        return !gameBoard.availableSpace().isEmpty();
    }

    @Override
    public void move(Direction direction) throws GameOverException {
        switch (direction) {
            case LEFT: {
                for (int j = 0; j < GAME_SIZE; j++) {
                    List<Key> keys = gameBoard.getRow(j);
                    moveLine(keys);
                }
                break;
            }
            case RIGHT: {
                for (int j = 0; j < GAME_SIZE; j++) {
                    List<Key> keys = gameBoard.getRow(j);
                    Collections.reverse(keys);
                    moveLine(keys);
                }

                break;
            }
            case UP: {
                for (int j = 0; j < GAME_SIZE; j++) {
                    List<Key> keys = gameBoard.getColumn(j);
                    moveLine(keys);
                }

                break;
            }
            case DOWN: {
                for (int j = 0; j < GAME_SIZE; j++) {
                    List<Key> keys = gameBoard.getColumn(j);
                    Collections.reverse(keys);
                    moveLine(keys);
                }

                break;
            }
        }
        if (!canMove()) {
            throw new GameOverException();
        } else {
            this.addItem();
        }
    }

    public void moveLine(List<Key> keys) {
        List<Integer> values = gameBoard.getValues(keys);
        List<Integer> integers = GameHelper.moveAndMergeEqual(values);
        for (int i = 0; i < keys.size(); i++) {
            gameBoard.board.replace(keys.get(i), integers.get(i));
        }
    }

    @Override
    public void addItem() {
        List<Key> keys = gameBoard.availableSpace();
        if (!keys.isEmpty()) {
            Key key = keys.get(getRandomNumber(0, keys.size()));
            gameBoard.addItem(key, 2);
        }
        else try {
            throw new GameOverException();
        } catch (GameOverException e) {
            e.printStackTrace();
        }
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @Override
    public Board<Key, Integer> getGameBoard() {
        return gameBoard;
    }

    @Override
    public boolean hasWin() {
        return (gameBoard.hasValue(2048));
    }
}