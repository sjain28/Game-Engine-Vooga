package tools;

import java.util.Comparator;

public class ScoreCompare implements Comparator<Pair<String, Double>>{
    @Override
    public int compare (Pair<String, Double> o1, Pair<String, Double> o2) {
        if (o1.getLast() > o2.getLast()) {
            return 1;
        }
        else if (o1.getLast() < o2.getLast()) {
            return -1;
        }
        return 0;
    }
}
