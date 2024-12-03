import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day2 {
    public static void main(String[] args) throws IOException {

        try (var lines = Files.lines(Paths.get("./input/Day2.txt"))) {
            var listInt = lines.map(line -> Arrays.stream(line.split("\\s+"))
                            .map(Integer::valueOf)
                            .collect(Collectors.toList()))
                    .toList();

            P1(listInt);
            P2(listInt);
        }
    }

    private static void P1(List<List<Integer>> listInt) {
        var safeCount = 0;
        for (List<Integer> list: listInt) {
            safeCount+=safeChecker(list);
        }

        System.out.println(safeCount);
    }

    private static void P2(List<List<Integer>> listInt) {
        var safeCount = 0;
        for (List<Integer> list: listInt) {
            if(safeChecker(list)==1) {
                safeCount++;
            }
            else{
                // Do exhaustive search by removing each element of the list and check if safe
                for(int i=0; i<list.size(); i++) {
                    if(safeCheckerWhenIndexRemoved(list, i)==1) {
                        safeCount++;
                        break;
                    }
                }
            }

        }

        System.out.println(safeCount);
    }

    private static int safeChecker(List<Integer> list) {
        var safe = 1;
        var trend = 0;
        for(int i=0; i<list.size()-1; i++) {
            var diff = list.get(i+1)-list.get(i);
            // Check if difference is greater than 3 or equal to 0.
            // Consider it as unsafe and then break loop.
            if(Math.abs(diff)>3 || Math.abs(diff)==0) {
                safe = 0;
                break;
            }
            // Set the trend. 1 for increasing. -1 for decreasing
            if (i==0) {
                trend = diff > 0 ? 1 : -1;
                continue;
            }
            var currTrend = diff > 0 ? 1 : -1;
            // Check if there is a change in the trend, then consider it as unsafe and break loop.
            if(trend != currTrend) {
                safe = 0;
                break;
            }
        }
        return safe;
    }

    private static int safeCheckerWhenIndexRemoved(List<Integer> list, int index) {
        List<Integer> testList = new ArrayList<>(list);
        testList.remove(index);
        return safeChecker(testList);
    }
}
