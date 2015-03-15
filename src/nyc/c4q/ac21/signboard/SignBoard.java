package nyc.c4q.ac21.signboard;

/**
 * Emulates an LED sign board.
 */
public class SignBoard {
    private final AnsiTerminal terminal;
    private final int height;

    private int numCols;
    private int numRows;
    private int xOffset = 1;
    private int yOffset;

    private String makeBorder(int width) {
        StringBuffer builder = new StringBuffer();
        for (int c = 0; c < numCols; ++c)
            builder.append("=");
        return builder.toString();
    }

    /**
     * Creates a new sign board.
     * @param height
     *   The vertical number of lines on the board.
     */
    public SignBoard(int height) {
        // Find out the size of the terminal currently.
        numCols = TerminalSize.getNumColumns();
        numRows = TerminalSize.getNumLines();

        // Figure out where in the terminal we'll draw the sign board.
        if (height + 2 > numRows)
            throw new RuntimeException("board too tall for terminal");
        yOffset = (numRows - (height + 2)) / 2 + 2;

        this.height = height;
        terminal = new AnsiTerminal();
        terminal.reset();
        terminal.hideCursor();
        terminal.setBackgroundColor(AnsiTerminal.Color.BLACK);

        // When the program shuts down, reset the terminal to its original state.
        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                reset();
            }
        });
    }

    /**
     * @return
     *   The width in columns of the board.
     */
    public int getWidth() {
        return numCols;
    }

    /**
     * @return
     *   The height in lines of the board.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Clears the screen and draws the borders.
     */
    public void clear() {
        // Clear the screen.
        terminal.clear();
        // Draw borders around the sign board.
        String border = makeBorder(numCols);
        terminal.setTextColor(AnsiTerminal.Color.WHITE, false);
        terminal.moveTo(-1 + yOffset, 0 + xOffset);
        terminal.write(border);
        terminal.moveTo(height + yOffset, 0 + xOffset);
        terminal.write(border);
        // Default to white text.
        setWhite();
    }

    /**
     * Selects white text.  Subsequent writes will be in white.
     */
    public void setWhite() {
        terminal.setTextColor(AnsiTerminal.Color.WHITE);
    }

    /**
     * Selects green text.  Subsequent writes will be in green.
     */
    public void setGreen() {
        terminal.setTextColor(AnsiTerminal.Color.GREEN);
    }

    /**
     * Selects yellow text.  Subsequent writes will be in yellow.
     */
    public void setYellow() {
        terminal.setTextColor(AnsiTerminal.Color.YELLOW);
    }

    /**
     * Selects red text.  Subsequent writes will be in red.
     */
    public void setRed() {
        terminal.setTextColor(AnsiTerminal.Color.RED);
    }

    public void write(int x, int y, String text) {
        if (!(x >= 0 && x + text.length() <= numCols))
            throw new IllegalArgumentException("x = " + x);
        if (!(y >= 0 && y < height))
            throw new IllegalArgumentException("y = " + y);

        terminal.moveTo(y + yOffset, x + xOffset);
        terminal.write(text);
    }

    /**
     * Pauses execution.
     * @param time
     *   Time in seconds for which to pause.
     */
    public void pause(double time) {
        try {
            Thread.sleep((int) (time * 1000));
        } catch (InterruptedException e) {
            // Ignore.
        }
    }

    /**
     * Restores the terminal to its original state.
     */
    public void reset() {
        terminal.reset();
        terminal.scroll(1);
        terminal.moveTo(numRows, 0);
    }
}
