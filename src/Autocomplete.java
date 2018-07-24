import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;
import java.util.Comparator;

public class Autocomplete {
    private Term[] terms;

    public Autocomplete(Term[] terms){
        if(terms == null){
            throw new NullPointerException();
        }
        this.terms = terms;
        Arrays.sort(terms);
    }
    public Term[] allMatches(String prefix){
        if(prefix==null){
            throw new NullPointerException();
        }
        Term term = new Term(prefix,0);
        int first = BinarySearchDeluxe.firstIndexOf(terms,term,Term.byPrefixOrder(prefix.length()));
        int last = BinarySearchDeluxe.lastIndexOf(terms,term,Term.byPrefixOrder(prefix.length()));
        Comparator<Term> weight = Term.byReverseWeightOrder();
        int i = 0;
        Term[] matches = new Term[numberOfMatches(prefix)+1];
        while(first<=last){
            matches[i] = terms[first];
            first++;
            i++;
        }
        Arrays.sort(matches,weight);
        return matches;
    }
    public int numberOfMatches(String prefix){
        if(prefix==null){
            throw new NullPointerException();
        }
        Term term = new Term(prefix,0);
        int first = BinarySearchDeluxe.firstIndexOf(terms,term,Term.byPrefixOrder(prefix.length()));
        int last = BinarySearchDeluxe.lastIndexOf(terms,term,Term.byPrefixOrder(prefix.length()));
        int matches = last - first;
        return matches;
    }

    public static void main(String[] args) {

        // read in the terms from a file
        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            double weight = in.readDouble();       // read the next weight
            in.readChar();                         // scan past the tab
            String query = in.readLine();          // read the next query
            terms[i] = new Term(query, weight);    // construct the term
        }

        // read in queries from standard input and print out the top k matching terms
        int k = Integer.parseInt(args[1]);
        Autocomplete autocomplete = new Autocomplete(terms);
        while (StdIn.hasNextLine()) {
            String prefix = StdIn.readLine();
            Term[] results = autocomplete.allMatches(prefix);
            for (int i = 0; i < Math.min(k, results.length); i++)
                StdOut.println(results[i]);
        }
    }

}
