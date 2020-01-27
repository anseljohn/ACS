import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.stream.Stream;

public class Lempel_Ziv_Welch_Decompression {
    private static Map<Integer, String> dictionary;
    private static int bitLevel;

    public static void main( String[] args ) {

        dictionary = new TreeMap<>();
        try {
            System.out.println("Please enter a Compressed String: ");
            Scanner scan = new Scanner( System.in );
            if ( !scan.hasNextLine() ) {
                System.out.println( "Please enter something" );
            }
            String binary = scan.nextLine();
            System.out.println( "Please enter the bitLevel: " );
            if ( !scan.hasNextInt() ) {
                System.out.println( "Please put a proper integer" );
            }

            ArrayList<String> segments = decompress( binary, scan.nextInt() );
            segments.forEach(System.out::print);
        }
        catch( Exception e ) {
            e.printStackTrace();
        }

    }


    public static ArrayList<String> decompress( String binary, int bitLevel ) {
        int binaryCounter = 128;
        int i = 0;
        ArrayList<String> segments = new ArrayList<>();

        if ( binary.length() > 8 ) {
            while ( i < binary.length() ) {
                StringBuilder current = new StringBuilder( );
                current.append( binary.substring(i, i + bitLevel) );
                dictionary.put( binaryCounter, getVal( current.toString() ) );

                if ( i + (2*bitLevel) < binary.length() ) {
                    String next = binary.substring(i + bitLevel, i + (2*bitLevel));
                    String output = getVal(current.toString());
                    String addToDictionary = output;
                    if ( Integer.parseInt( next, 2 ) == binaryCounter ) {
                        addToDictionary += getVal( current.toString() ).charAt(0);
                    }
                    else {
                        addToDictionary += getVal(next).charAt( 0 );
                    }
                    dictionary.put( binaryCounter, addToDictionary);
                    segments.add( output );
                    //System.out.println( "Current: " + getVal( current.toString() ) + "     Next: " + "(" + Integer.parseInt( next, 2 ) + ")" + getVal(next) + "    Output: " + output  + "     Dictionary: " + "(" + binaryCounter + ")" + addToDictionary);
                }
                else {
                    String output = getVal(current.toString());
                    segments.add( output );
                    //System.out.println( "On Last      Output: " + output );
                }

                binaryCounter++;
                i+=bitLevel;
            }
            return segments;
        }
        return segments;
    }

    public static String getVal( String binary ) {
        if ( Integer.parseInt( binary, 2 ) >= 128 ) {
            String s = dictionary.get( Integer.parseInt( binary, 2 ) );
            //System.out.println( s );
            return s;
        }
        else {
            String s =  (char)Integer.parseInt( binary, 2 ) + "" ;
            //System.out.println( s );
            return s;
        }
    }

}
