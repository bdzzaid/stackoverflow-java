package zbo;

import org.junit.Before;
import org.junit.Test;

import java.text.RuleBasedCollator;
import java.util.*;

public class CollectionTest
{
    List<String> data ;

    @Before
    public void init() {
        data = Arrays.asList(
                "1G7EE147578", "1G6EE147548", "1G1EE147576", "1G0EE147546", "1G8EE147545",
                "1G4EE147577", "1G6EE147574", "1GXEE147579", "1G9EE147575", "1G3EE147547");
    }

    @Test
    public void sort() throws Exception{

        Collections.sort(data, new SimpleComparator());
        System.out.println(data.toString().replaceAll(",", "\n"));

        Set<String> datas = new TreeSet<>(new SimpleComparator());
    }

    @Test
    public void sortCollator() throws Exception{

        String rules = "< a, A < b, B < c, C < d, D < e, E < f, F < g, G < h, H < i, I < j, J < k, K < l, L < m, M < n, N < o, O < p, P < q, Q < r, R" +
                " < s, S < t, T < u, U < v, V < w, W < x, X < y, Y < z, Z < 0 < 1 < 2 < 3 < 4 < 5 < 6 < 7 < 8 < 9";
        RuleBasedCollator myCollator = new RuleBasedCollator(rules);
        System.out.println("Before: " + data);
        Collections.sort(data, myCollator);
        System.out.println("After:  " + data);
    }


    static class SimpleComparator implements Comparator<String>
    {
        @Override
        public int compare(String s, String s2)
        {
            if(s == s2)
                return 0;
            if(s != null && s2 == null)
                return -1;
            if(s == null && s2 != null)
                return 1;
            return (s.charAt(2) == 'X' ? s.substring(0, 2) + ' ' + s.substring(3) : s).compareTo(s2);
        }
    }

}
