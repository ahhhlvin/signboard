package nyc.c4q.ac21.signboard;

public class SignBoard {
    private final AnsiTerminal terminal;
    private final int height;

    private int numCols;
    private int numRows;
    private int xOffset = 1;
    private int yOffset;

    private String makeBorder(int width) {
        StringBuffer sb = new StringBuffer();
        for (int c = 0; c < numCols - 1; ++c)
            sb.append("=");
        return sb.toString();
    }

    public SignBoard(int height) {
        this.height = height;
        terminal = new AnsiTerminal(System.out);
        terminal.reset();
        terminal.hideCursor();
        terminal.setBackgroundColor(AnsiTerminal.Color.BLACK);
    }

    public void clear() {
        // Find out the size of the terminal currently.
        numCols = TerminalSize.getNumColumns();
        numRows = TerminalSize.getNumLines();
        if (height + 2 > numRows)
            throw new RuntimeException("terminal too short");
        yOffset = (numRows - (height + 2)) / 2 + 2;

        terminal.clear();

        String border = makeBorder(numCols);
        terminal.setTextColor(AnsiTerminal.Color.WHITE, false);
        terminal.moveTo(-1 + yOffset, 0 + xOffset);
        System.out.print(border);
        terminal.moveTo(height + yOffset, 0 + xOffset);
        System.out.print(border);

        terminal.setTextColor(AnsiTerminal.Color.WHITE, true);
    }

    public int getWidth() {
        return numCols;
    }

    public int getHeight() {
        return height;
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
        System.out.print(text);
    }

    public void pause(double time) {
        try {
            Thread.sleep((int) (time * 1000));
        } catch (InterruptedException e) {
            // Ignore.
        }
    }
}
