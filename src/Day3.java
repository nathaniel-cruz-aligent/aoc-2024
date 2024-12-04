import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day3 {
    public static void main(String[] args) throws IOException {

        try (var lines = Files.lines(Paths.get("./input/Day3.txt"))) {
            var str = lines.collect(Collectors.joining());
            P1(str);
            P2(str);
        }
    }

    private static void P1(String str) {
        Matcher m = getMatcher(str);
        System.out.println("P1: "+getSum(m));
    }

    private static void P2(String str) {
        var newStr = str.replaceAll("don't\\(\\)(.*?)do\\(\\)", "")
                // replace residual "don't()"s at the end of the string where do() is no longer found.
                .replaceAll("don't\\(\\).*", "");
        Matcher m = getMatcher(newStr);
        System.out.println("P2: "+getSum(m));
    }

    private static Matcher getMatcher(String str) {
        String regex = "mul\\((\\d+),\\s*(\\d+)\\)";
        return Pattern.compile(regex).matcher(str);
    }

    private static int getSum(Matcher m) {
        int sum =0;
        while(m.find()) {
            sum+=(Integer.parseInt(m.group(1))*Integer.parseInt(m.group(2)));
        }
        return sum;
    }
}
