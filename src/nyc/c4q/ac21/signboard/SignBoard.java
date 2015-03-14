package nyc.c4q.ac21.signboard;

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

    public SignBoard(int height) {
        // Find out the size of the terminal currently.
        numCols = TerminalSize.getNumColumns();
        numRows = TerminalSize.getNumLines();

        this.height = height;
        terminal = new AnsiTerminal();
        terminal.reset();
        terminal.hideCursor();
        terminal.setBackgroundColor(AnsiTerminal.Color.BLACK);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                reset();
            }
        });
    }

    public int getWidth() {
        return numCols;
    }

    public int getHeight() {
        return height;
    }

    public void clear() {
        // Figure out where in the terminal we'll draw the sign board.
        if (height + 2 > numRows)
            throw new RuntimeException("terminal too short");
        yOffset = (numRows - (height + 2)) / 2 + 2;

        // Clear the screen.
        terminal.clear();
        // Draw borders around the sign board.
        String border = makeBorder(numCols);
        terminal.setTextColor(AnsiTerminal.Color.WHITE, false);
        terminal.moveTo(-1 + yOffset, 0 + xOffset);
        terminal.write(border);
        terminal.moveTo(height + yOffset, 0 + xOffset);
        terminal.write(border);

        setWhite();
    }

    public void setWhite() {
        terminal.setTextColor(AnsiTerminal.Color.WHITE);
    }

    public void setGreen() {
        terminal.setTextColor(AnsiTerminal.Color.GREEN);
    }

    public void setYellow() {
        terminal.setTextColor(AnsiTerminal.Color.YELLOW);
    }

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

    public void pause(double time) {
        try {
            Thread.sleep((int) (time * 1000));
        } catch (InterruptedException e) {
            // Ignore.
        }
    }

    public void reset() {
        terminal.reset();
        terminal.scroll(1);
        terminal.moveTo(numRows, 0);
    }
}
