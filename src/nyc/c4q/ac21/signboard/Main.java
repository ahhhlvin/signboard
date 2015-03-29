package nyc.c4q.ac21.signboard;

import java.util.Random;

public class Main {
    /**
     * Draws a scene with a scrolling multicolor zig-zag ribbon.
     *
     * @param board     The board on which to draw.
     * @param numCycles The number of cycles to draw for.
     */
    public static void ribbonScene(SignBoard board, int numCycles) {
        Random random = new Random();
        int width = board.getWidth();
        int height = board.getHeight();
        for (int i = 0; i < numCycles; ++i) {
            SignBoard.Frame frame = board.newFrame();


            for (int x = -2; x < width; ++x) {
                int y = (2 * height - 2 + x + i) % (2 * height - 2);

                int color = random.nextInt(4);
                if (color == 0)
                    frame.setGreen();
                else if (color == 1)
                    frame.setWhite();
                else if (color == 2)
                    frame.setRed();
                else
                    frame.setYellow();

                if (y >= height)
                    y = 2 * height - y - 2;
                if (0 < x) {
                    //frame.setYellow();
                    frame.write(x, y, "#");
                }
                if (0 < x + 7 && x + 7 < width) {
                    //frame.setGreen();
                    frame.write(x + 6, y, "@");
                    frame.write(x + 7, y, "@");

                }
                if (x + 5 < width) {
                    //frame.setRed();
                    frame.write(x + 2, y, "$");
                    frame.write(x + 3, y, "$");
                    frame.write(x + 4, y, "$");
                    frame.write(x + 5, y, "$");


                }


            }

            frame.finish(0.20);
        }
    }

    /**
     * Draws a scene with text scrolling across the screen..
     *
     * @param board The board on which to draw.
     * @param text  The text to scroll.
     */
    public static void scrollTextScene(SignBoard board, String text) {

        int width = board.getWidth();
        int y = board.getHeight() / 2;

        for (int x = -text.length(); x <= width; ++x) {
            SignBoard.Frame frame = board.newFrame();
            frame.setGreen();

            if (x >= width)
                break;

            if (x < 0) {
                // Scrolling on to the left side.
                frame.write(0, y, text.substring(-x));
            }
            else if (x + text.length() <= width)
                // Fully on the board.
                frame.write(x, y, text);
            else
                // Scrolling off the board.
                frame.write(x, y, text.substring(0, width - x));

            frame.finish(0.02);   // how long we want each frame to LAST for when the text is moving !
        }
    }


    public static void scrollTextScene2(SignBoard board, String text, String text2, String text3, String text4) {
        Random random = new Random();
        int width = board.getWidth();
        int y = board.getHeight() / 2 - 2;
        for (int x = -text.length() + 2; x <= width; ++x) {
            SignBoard.Frame frame = board.newFrame();


            int color = random.nextInt(4);
            if (color == 0)
                frame.setGreen();
            else if (color == 1)
                frame.setWhite();
            else if (color == 2)
                frame.setRed();
            else
                frame.setYellow();


            if (x >= width)
                break;

            if (x < 0) {
                // Scrolling on to the left side.
                frame.write(0, y + 1, text.substring(-x));
                frame.write(0, y + 2, text2.substring(-x));
                frame.write(0, y + 3, text3.substring(-x));
                frame.write(0, y + 4, text4.substring(-x));


            } else if (x + text.length() <= width) {
                frame.write(0, y + 1, text);
                frame.write(0, y + 2, text2);
                frame.write(0, y + 3, text3);
                frame.write(0, y + 4, text4);


            } else {
                frame.write(0, y + 1, text.substring(0, width - x));
                frame.write(0, y + 2, text2.substring(0, width - x));
                frame.write(0, y + 3, text3.substring(0, width - x));
                frame.write(0, y + 4, text4.substring(0, width - x));


            }
            frame.finish(0.05);
        }
    }






    /**
     * Draws a scene which flashes the words "FRESH" and "HOT".
     *
     * @param board  The board on which to draw.
     * @param cycles The number of cycles to draw for.
     */
    public static void flashIceColdScene(SignBoard board, int cycles) {
        Random random = new Random();
        int width = board.getWidth();
        int leftPosition = width / 4 - 12;
        int rightPosition = 2 * width / 4 - 7;
        int y = board.getHeight() / 2;

        for (int i = 0; i < cycles * 4; ++i) {
            SignBoard.Frame frame = board.newFrame();

            // Choose a color at random.
            int color = random.nextInt(3);
            if (color == 0)
                frame.setGreen();
            else if (color == 1)
                frame.setWhite();
            else
                frame.setYellow();


            // Write a word.
            if (i % 2 == 0) {

                frame.write(leftPosition, y - 3, "::::::::::: ::::::::  ::::::::::");
                frame.write(leftPosition, y - 2, "    :+:    :+:    :+: :+:       ");
                frame.write(leftPosition, y - 1, "    +:+    +:+        +:+       ");
                frame.write(leftPosition, y    , "    +#+    +#+        +#++:++#  ");
                frame.write(leftPosition, y + 1, "    +#+    +#+        +#+       ");
                frame.write(leftPosition, y + 2, "    #+#    #+#    #+# #+#       ");
                frame.write(leftPosition, y + 3, "########### ########  ##########");
            }
            else {
                frame.write(rightPosition, y - 3, " ::::::::   ::::::::  :::        :::::::::  ");
                frame.write(rightPosition, y - 2, ":+:    :+: :+:    :+: :+:        :+:    :+: ");
                frame.write(rightPosition, y - 1, "+:+        +:+    +:+ +:+        +:+    +:+ ");
                frame.write(rightPosition, y    , "+#+        +#+    +:+ +#+        +#+    +:+ ");
                frame.write(rightPosition, y + 1, "+#+        +#+    +#+ +#+        +#+    +#+ ");
                frame.write(rightPosition, y + 2, "#+#    #+# #+#    #+# #+#        #+#    #+# ");
                frame.write(rightPosition, y + 3, " ########   ########  ########## #########  ");
            }

            frame.finish(0.10);
        }
    }

    public static void main(String[] args) {
        SignBoard signBoard = new SignBoard(8);


        String truckLine = "                      ( ;;;;;) ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ .--------.__    ";
        String truckLine2 = "                       \\; ; /  ICE CREAM ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~|::::::::|[_I___,";
        String truckLine3 = "                        \\  /   FOR ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~|_.-.____I__.-~;|";
        String truckLine4 = "                         \\/… !!YOU :)~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ ~ `(_)--------(_)\"";


        // Run the sign board forever.
        while (true) {
            ribbonScene(signBoard, 10);
            scrollTextScene2(signBoard, truckLine, truckLine2, truckLine3, truckLine4);
            scrollTextScene(signBoard, "*****<(''<)(^''^)(>'')>*****!!  I C E   C R E A M  !!*****<(''<)(^''^)(>'')>*****");
            scrollTextScene(signBoard, "*****<(''<)(^''^)(>'')>*****!! Y O U   S C R E A M !!*****<(''<)(^''^)(>'')>*****");
            scrollTextScene(signBoard, "****<>*$$OO(!!  W E  A L L  S C R E A M  F O R  I C E  C R E A M  !!)OO$$*<>*****");
            ribbonScene(signBoard, 10);
            flashIceColdScene(signBoard, 12);
        }
    }
}