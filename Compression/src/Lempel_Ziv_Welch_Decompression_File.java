import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Lempel_Ziv_Welch_Decompression_File {
    private static Map<Integer, String> dictionary;
    private static int bitLevel;
    private static PrintWriter printWriter;
    private static int binaryCounter;

    public static void main( String[] args ) {

        dictionary = new TreeMap<>();
        try {
            File f = new File( "C:\\Users\\jmz00\\Git\\ADV-CPU-Studies-Java-Projects\\Compression\\compressed.txt" );
            Scanner scan = new Scanner( f );
            bitLevel = scan.nextInt();
            binaryCounter = 128;
            double lengthBefore = 0d;
            int lengthAfter = 0;
            printWriter = new PrintWriter("undouncompressed.txt", "UTF-8");

            while ( scan.hasNext() ) {
                String str = scan.nextLine();
                lengthBefore += str.length() * 8;
                ArrayList<String> segments = decompress( str, bitLevel );
                lengthAfter += segments.size() * bitLevel;
                //System.out.println( "New Line!" );
                for ( String s : segments ) {
                    printWriter.print( s );
                }
                printWriter.println();
            }
            System.out.println( "Compression Ratio: " + ( lengthBefore/lengthAfter ) + " :1");
            printWriter.close();
        }
        catch( Exception e ) {
            e.printStackTrace();
        }

    }

    public static ArrayList<String> decompress( String binary, int bitLevel ) {
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
            String s = new String( Character.toString( (char)Integer.parseInt( binary, 2 )  ) );
            //System.out.println( s );
            return s;
        }
    }

}
