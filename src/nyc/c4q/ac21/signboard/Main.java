package nyc.c4q.ac21.signboard;

public class Main {

    public static void ribbon(SignBoard board, int numCycles) {
        for (int i = 0; i < numCycles; ++i) {
            board.clear();
            int width = board.getWidth();
            int height = board.getHeight();

            for (int x = 0; x < width - 2; ++x) {
                int y = (x + i) % (2 * height - 2);
                if (y >= height)
                    y = 2 * height - y - 2;
                board.setYellow();
                board.write(x, y, "*");
                board.setGreen();
                board.write(x + 1, y, "*");
                board.setRed();
                board.write(x + 2, y, "*");
            }

            board.pause(0.05);
        }
    }

    public static void main(String[] args) {
        SignBoard signBoard = new SignBoard(8);
        ribbon(signBoard, 100);
    }
}
