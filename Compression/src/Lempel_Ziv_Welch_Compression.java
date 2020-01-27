import java.util.*;

public class Lempel_Ziv_Welch_Compression {

    private static  Map<String, Integer> dictionary;

    public static void main( String[] args ) {

        dictionary = new TreeMap<>();
        try {
            System.out.println("Please enter a String: ");
            Scanner scan = new Scanner( System.in );

            String str = scan.nextLine();
            ArrayList<String> binaryVals = compress( str );
            String compressed = print( binaryVals );
            System.out.println( "Compression Ratio: " +  ( str.length() * 8 ) /( ( (double)binaryVals.size() * 10 ) ) + " :1");
        }
        catch( Exception e ) {
            e.printStackTrace();
        }

    }


    public static ArrayList<String> compress(String str ) {
        int binaryCounter = 128;
        int i = 0;
        ArrayList<String> binaryVals = new ArrayList<>();

        if ( str.length() > 1 ) {
            while ( i < str.length() ) {
                StringBuilder current = new StringBuilder( );
                current.append( str.charAt( i ) );
                char next = '\u0000';
                if ( i < str.length() - 1 ) {
                    next = str.charAt(i + 1);
                }
                boolean atEnd = false;

                while ( dictionary.containsKey( current.toString() + next ) && i < str.length() - 1 ) {
                    current.append( next );
                    if ( i < str.length() - 2) {
                        i++;
                        next = str.charAt( i + 1 );
                    }
                    else {
                        next = '\u0000';
                        i++;
                        atEnd = true;
                    }
                }
                if ( i >= str.length()-1 ) {
                    atEnd = true;
                }

                if ( !atEnd ) {
                    System.out.println( i + ": " + current.toString() );
                    binaryVals.add( current.toString() );
                    dictionary.put( current.append(next).toString(),  binaryCounter++ );
                }
                else {
                    System.out.println( "Last: " + current.toString() );
                    binaryVals.add( current.toString() );
                }
                i++;
            }
            return binaryVals;
        }
        return binaryVals;
    }

    public static String print( ArrayList<String> binaryVals ) {
        StringBuilder stringBuilder = new StringBuilder( );
        StringBuilder decimal = new StringBuilder( );
        for ( String s : binaryVals ) {
            if ( dictionary.containsKey( s ) ) {
                stringBuilder.append( resizeBinaryToBitLevel( dictionary.get( s ) , 10 ) );
                decimal.append(dictionary.get(s) + " ");
            }
            else {
                stringBuilder.append( resizeBinaryToBitLevel( (int)(s.charAt( 0 ) ), 10 ) );
                decimal.append( (int)s.charAt(0) + " ");
            }
        }
        System.out.println( stringBuilder.toString() );
        System.out.println( decimal.toString());
        return stringBuilder.toString();
    }

    private static String resizeBinaryToBitLevel( Integer val, int bitLevel ) {
        StringBuilder stringBuilder = new StringBuilder();
        String binary = Integer.toBinaryString( val );
        for ( int i = 0; i < bitLevel; i -= -1 ) {
            if ( binary.length() > i ) {
                stringBuilder.append( binary.charAt( i ) );
            }
            else {
                stringBuilder.insert(0, 0);
            }
        }
        return stringBuilder.toString();
    }
}
