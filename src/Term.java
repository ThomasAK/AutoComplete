import java.util.Comparator;

public class Term implements Comparable<Term> {
    private String query;
    private double weight;

    public Term (String query,double weight){
        if(query == null){
            throw new NullPointerException();
        }
        if(weight < 0){
            throw new IllegalArgumentException();
        }
        this.query = query;
        this.weight = weight;
    }

    public int compareTo(Term other){
        return this.query.compareTo(other.query);
    }

    @Override
    public String toString() {
        return String.format("%.2f", this.weight) + "\t" + this.query;
    }

    public static Comparator<Term> byReverseWeightOrder(){
        return new ReverseWeightOrderComparater();
    }
    public static Comparator<Term> byPrefixOrder(int r){
        return new PrefixOrderComparator(r);
    }

    private static class ReverseWeightOrderComparater implements Comparator<Term>{
        public int compare(Term first, Term second){
            int one = (int) first.weight;
            int two = (int) second.weight;
            return two - one;
        }
    }

    private static class  PrefixOrderComparator implements Comparator<Term>{
        private int r;
        public PrefixOrderComparator(int r){
            if (r<0){
                throw new IllegalArgumentException();
            }
            this.r = r;
        }
        public  int compare(Term first,Term second){
            return first.query.substring(0,r).compareTo(second.query.substring(0,r));
        }
    }
}
