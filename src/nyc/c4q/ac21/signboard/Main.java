package nyc.c4q.ac21.signboard;

import java.util.Random;

public class Main {

    public static void ribbonScene(SignBoard board, int numCycles) {
        for (int i = 0; i < numCycles; ++i) {
            board.clear();
            int width = board.getWidth();
            int height = board.getHeight();

            for (int x = -2; x < width; ++x) {
                int y = (2 * height - 2 + x + i) % (2 * height - 2);
                if (y >= height)
                    y = 2 * height - y - 2;
                if (0 < x) {
                    board.setYellow();
                    board.write(x, y, "*");
                }
                if (0 < x + 1 && x + 1 < width) {
                    board.setGreen();
                    board.write(x + 1, y, "*");
                }
                if (x + 2 < width) {
                    board.setRed();
                    board.write(x + 2, y, "*");
                }
            }

            board.pause(0.02);
        }
    }

    public static void scrollWordScene(SignBoard board, String text) {
        int x = -text.length() + 1;
        board.setWhite();
        while (true) {
            board.clear();
            int width = board.getWidth();
            if (x >= width)
                break;
            int y = board.getHeight() / 2;

            if (x < 0)
                // Scrolling on to the left side.
                board.write(0, y, text.substring(-x));
            else if (x + text.length() <= width)
                // Fully on the board.
                board.write(x, y, text);
            else
                // Scrolling off the board.
                board.write(x, y, text.substring(0, width - x));

            x += 1;
            board.pause(0.02);
        }
    }

    public static void flash2Scene(SignBoard board, String leftText, String rightText, int cycles) {
        Random random = new Random();
        for (int i = 0; i < cycles * 2; ++i) {
            board.clear();
            int width = board.getWidth();
            int leftPosition = width / 4 - leftText.length() / 2;
            int rightPosition = 3 * width / 4 - rightText.length() / 2;
            int y = board.getHeight() / 2;

            int color = random.nextInt(4);
            if (color == 0) board.setGreen();
            else if (color == 1) board.setRed();
            else if (color == 2) board.setWhite();
            else board.setYellow();

            if (i % 2 == 0)
                board.write(leftPosition, y, leftText);
            else
                board.write(rightPosition, y, rightText);
            board.pause(0.25);
        }
    }

    public static void main(String[] args) {
        SignBoard signBoard = new SignBoard(8);
        ribbonScene(signBoard, 32);
        scrollWordScene(signBoard, "F A L A F E L");
        ribbonScene(signBoard, 32);
        flash2Scene(signBoard, "FRESH", "HOT", 12);
        ribbonScene(signBoard, 32);
    }
}
