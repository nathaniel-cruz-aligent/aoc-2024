import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day1 {
    public static void main(String[] args) throws IOException {

        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        try (var lines = Files.lines(Paths.get("./input/Day1.txt"))) {
            var listInt = lines.map(line -> Arrays.stream(line.split("\\s+"))
                    .map(Integer::valueOf)
                    .collect(Collectors.toList()))
                    .toList();

            for (List<Integer> list: listInt) {
                list1.add(list.getFirst());
                list2.add(list.getLast());
            }
            Collections.sort(list1);
            Collections.sort(list2);

            P1(list1, list2);
            P2(list1, list2);

        }
    }

    private static void P1(List<Integer> list1, List<Integer> list2) {
        var sum = 0;
        for(int i = 0; i<list1.size(); i++) {
            sum+=Math.abs(list2.get(i)-list1.get(i));
        }
        System.out.println("Total distance: "+sum);
    }

    private static void P2(List<Integer> list1, List<Integer> list2) {
        var similarity = 0;

        for (Integer element : list1) {
            int occurrences = Collections.frequency(list2, element);
            similarity += (element * occurrences);
        }

        System.out.println("Similarity: "+similarity);
    }
}
