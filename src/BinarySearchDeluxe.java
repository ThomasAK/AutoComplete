import java.util.Comparator;

public class BinarySearchDeluxe {
    public static <T> int firstIndexOf(T[] a,T t, Comparator<T> comparator){
        if(a==null||t==null||comparator==null){
            throw new NullPointerException();
        }
        int index = indexOf(a,t,comparator);
        if(index <= 0){
            return index;
        }
        for(int i=index; i>0; i--){
            if(comparator.compare(a[i-1],t) != 0){
                return i;
            }
        }
        return index;
    }
    public static <T> int lastIndexOf(T[] a,T t, Comparator<T> comparator){
        if(a==null||t==null||comparator==null){
            throw new NullPointerException();
        }
        int index = indexOf(a,t,comparator);
        if(index == -1 || index == a.length-1){
            return index;
        }
        for(int i=index;i<a.length-1;i++){
            if(comparator.compare(a[i+1],t) !=0){
                return i;
            }
        }
        return index;
    }

    public static <T> int indexOf (T[] a,T t,Comparator<T> comparator){
        int low = 0;
        int high = a.length-1;
        while (low <= high) {
            // Key is in a[lo..hi] or not present.
            int mid = low + (high - low) / 2;
            if      (comparator.compare(t, a[mid]) < 0) high = mid - 1;
            else if (comparator.compare(t, a[mid]) > 0) low = mid + 1;
            else return mid;
        }
        return -1;
    }
}
