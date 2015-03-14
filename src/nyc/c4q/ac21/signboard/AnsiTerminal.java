package nyc.c4q.ac21.signboard;

import java.io.PrintStream;

public class AnsiTerminal {

    private static final char ESCAPE = 0x1b;
    private static final String CSI = ESCAPE + "[";

    private final PrintStream out;

    private void escape(char code) {
        out.print(CSI + code);
    }

    private void escape(String code) {
        out.print(CSI + code);
    }

    private void escape(int arg, char code) {
        out.print(CSI + arg + code);
    }

    private void escape(int arg0, int arg1, char code) {
        out.print(CSI + arg0 + ';' + arg1 + code);
    }

    private void sgr(int code) {
        assert 0 <= code && code < 107;
        escape(code, 'm');
    }

    /**************************************************************************
    * Public interface.
    **************************************************************************/

    public static enum Color {
        BLACK,
        RED,
        GREEN,
        YELLOW,
        BLUE,
        MAGENTA,
        CYAN,
        WHITE;

        public int getCode(boolean foreground, boolean intense) {
            return (foreground ? (intense ? 90 : 30) : (intense ? 100 : 40)) + ordinal();
        }
    }

    public AnsiTerminal(PrintStream out) {
        this.out = out;
    }

    public void reset() {
        sgr(0);
    }

    public void clear() {
        escape(2, 'J');
    }

    public void setTextColor(Color color, boolean intense) {
        sgr(color.getCode(true, intense));
    }

    public void setTextColor(Color color) {
        setTextColor(color, true);
    }

    public void setBackgroundColor(Color color, boolean intense) {
        sgr(color.getCode(false, intense));
    }

    public void setBackgroundColor(Color color) {
        setBackgroundColor(color, false);
    }

    public void moveTo(int line, int col) {
        escape(line, col, 'H');
    }

    public void hideCursor() {
        escape("?25l");
    }

    public void showCursor() {
        escape("?25h");
    }

}
