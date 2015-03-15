package nyc.c4q.ac21.signboard;

import java.io.*;

/**
 * Wrapper for a terminal that understands ANSI escape codes.
 *
 * See http://en.wikipedia.org/wiki/ANSI_escape_code .
 */
public class AnsiTerminal {

    private static final byte ESCAPE = 0x1b;

    private final FileOutputStream out;

    private void write(byte b) {
        try {
            out.write(b);
        } catch (IOException e) {
            // Ignore.
        }
    }

    public void write(String string) {
        try {
            out.write(string.getBytes());
        }
        catch (IOException e) {
            // Ignore.
        }
    }

    private void csi(String code) {
        write(ESCAPE);
        write((byte) '[');
        write(code);
    }

    private void csi(int arg, char code) {
        csi(Integer.toString(arg) + code);
    }

    private void escape(int arg0, int arg1, char code) {
        csi(Integer.toString(arg0) + ';' + arg1 + code);
    }

    private void sgr(int code) {
        assert 0 <= code && code < 107;
        csi(code, 'm');
    }

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

    public AnsiTerminal() {
        FileOutputStream out;
        try {
            out = new FileOutputStream(new File("/dev/tty"));
        } catch (FileNotFoundException e) {
            // Fall back to stdout.
            out = new FileOutputStream(java.io.FileDescriptor.out);
        }
        this.out = out;
    }

    public void reset() {
        sgr(0);
    }

    public void clear() {
        csi(2, 'J');
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
        csi("?25l");
    }

    public void showCursor() {
        csi("?25h");
    }

    public void scroll(int numLines) {
        csi(numLines, 'S');
    }

}
