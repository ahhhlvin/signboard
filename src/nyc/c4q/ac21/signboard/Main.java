package nyc.c4q.ac21.signboard;

import java.util.Random;

public class Main {

    public static void ribbonScene(SignBoard board, int numCycles) {
        int width = board.getWidth();
        int height = board.getHeight();
        for (int i = 0; i < numCycles; ++i) {
            board.clear();
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
        board.setWhite();
        int width = board.getWidth();
        int y = board.getHeight() / 2;
        for (int x = -text.length(); x >= width; ++x) {
            board.clear();
            if (x >= width)
                break;

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
        int width = board.getWidth();
        int leftPosition = width / 4 - leftText.length() / 2;
        int rightPosition = 3 * width / 4 - rightText.length() / 2;
        int y = board.getHeight() / 2;

        for (int i = 0; i < cycles * 2; ++i) {
            board.clear();

            // Choose a color at random.
            int color = random.nextInt(4);
            if (color == 0)
                board.setGreen();
            else if (color == 1)
                board.setRed();
            else if (color == 2)
                board.setWhite();
            else
                board.setYellow();

            if (i % 2 == 0)
                board.write(leftPosition, y, leftText);
            else
                board.write(rightPosition, y, rightText);
            board.pause(0.25);
        }
    }

    public static void main(String[] args) {
        SignBoard signBoard = new SignBoard(8);
        while (true) {
            ribbonScene(signBoard, 48);
            scrollWordScene(signBoard, "F A L A F E L");
            ribbonScene(signBoard, 48);
            flash2Scene(signBoard, "FRESH", "HOT", 8);
        }
    }
}
