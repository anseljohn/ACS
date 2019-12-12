import java.util.Scanner;

public class CompressionAssignment {

    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        System.out.print("Enter string >>  ");
        String input = scanner.next();
        System.out.println(toBinaryString(input));
    }

    public static String toBinaryString(String in) {
        byte[] bytes = in.getBytes();
        StringBuilder binary = new StringBuilder();
        byte previousByte = bytes[bytes.length - 1];
        for (byte b : bytes)
        {
            int val = b;
            if( b != previousByte) {
                binary.append("00");
            }
            for (int i = 0; i < 8; i++)
            {
                binary.append((val & 128) == 0 ? 0 : 1);
                val <<= 1;
            }
            binary.append(' ');
            previousByte = b;
        }
        return String.valueOf(binary);
    }
}
