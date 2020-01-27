import javax.swing.*;
import java.util.Scanner;

public class StringThing {
    public static void main( String[] args ) {

        try {
             System.out.println("Please enter a String: ");
             Scanner scan = new Scanner( System.in );

             printBinary( scan );
        }
        catch( Exception e ) {
            e.printStackTrace();
        }

    }

    public static void printBinary( Scanner scan ) {
        String str = scan.nextLine();
        str.chars().
                mapToObj( i -> (char)i ).
                map( i -> Integer.toBinaryString( i ) ).
                forEach( StringThing::printSize );
    }

    public static void printSize( String i ) {
        StringBuilder stringBuilder = new StringBuilder( i );
        while ( stringBuilder.length() < 10 ) {
            stringBuilder.insert(0, "0" );
        }
        System.out.println( stringBuilder.toString() );
    }
}
