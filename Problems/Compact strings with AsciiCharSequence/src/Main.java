import java.util.Arrays;

class AsciiCharSequence implements java.lang.CharSequence {
    byte[] bytes;

    AsciiCharSequence(byte[] bytes) {
        this.bytes = bytes;
    }

    @Override
    public int length() {
        return bytes.length;
    }

    @Override
    public char charAt(int index) {
        return (char) bytes[index];
    }

    @Override
    public AsciiCharSequence subSequence(int start, int end) {
        return new AsciiCharSequence(Arrays.copyOfRange(bytes, start, end));
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        for (byte c : bytes) {
            str.append((char) c);
        }

        return str.toString();
    }
}