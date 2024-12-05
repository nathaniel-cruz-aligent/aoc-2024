import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day4 {
    public static void main(String[] args) throws IOException {

        try (var lines = Files.lines(Paths.get("./input/Day4.txt"))) {
            var linesList = lines.toList();
            var columns = linesList.getFirst().length();
            var rows = linesList.size();

            var xmasMap = new char[rows][columns];
            List<Integer> xr = new ArrayList<>();
            List<Integer> xc = new ArrayList<>();
            List<Integer> ar = new ArrayList<>();
            List<Integer> ac = new ArrayList<>();
            int r = 0;
            for(String str : linesList) {
                for(int c = 0; c<columns; c++) {
                    xmasMap[r][c] = str.charAt(c);
                    if(str.charAt(c)=='X') {
                        xr.add(r);
                        xc.add(c);
                    }
                    if(str.charAt(c)=='A') {
                        ar.add(r);
                        ac.add(c);
                    }
                }
                r++;
            }

            System.out.println(countXmas(xmasMap, xr, xc));
            System.out.println(countCrossMAS(xmasMap, ar, ac));
        }
    }

    private static int countXmas(char[][] xmasMap, List<Integer> xr, List<Integer> xc) {
        int totalCount = 0;
        for(int i= 0; i<xr.size(); i++) {
            totalCount+=findXmas(xmasMap, xr.get(i), xc.get(i), E);
            totalCount+=findXmas(xmasMap, xr.get(i), xc.get(i), W);
            totalCount+=findXmas(xmasMap, xr.get(i), xc.get(i), N);
            totalCount+=findXmas(xmasMap, xr.get(i), xc.get(i), S);
            totalCount+=findXmas(xmasMap, xr.get(i), xc.get(i), NE);
            totalCount+=findXmas(xmasMap, xr.get(i), xc.get(i), NW);
            totalCount+=findXmas(xmasMap, xr.get(i), xc.get(i), SE);
            totalCount+=findXmas(xmasMap, xr.get(i), xc.get(i), SW);
        }
        return totalCount;
    }

    private static final int[][] E = {new int[]{1,1,1}, new int[]{0,0,0}};
    private static final int[][] W = {new int[]{-1,-1,-1}, new int[]{0,0,0}};
    private static final int[][] S = {new int[]{0,0,0}, new int[]{1,1,1}};
    private static final int[][] N = {new int[]{0,0,0}, new int[]{-1,-1,-1}};
    private static final int[][] NE = {new int[]{1,1,1}, new int[]{-1,-1,-1}};
    private static final int[][] SE = {new int[]{1,1,1}, new int[]{1,1,1}};
    private static final int[][] NW = {new int[]{-1,-1,-1}, new int[]{-1,-1,-1}};
    private static final int[][] SW = {new int[]{-1,-1,-1}, new int[]{1,1,1}};



    private static int findXmas(char[][] xmasMap, int xr, int xc, int[][] direction) {
        int count = 0;
        var dx = xc;
        var dy = xr;
        for(int i=0; i<=direction.length; i++) {
            dx +=direction[0][i];
            dy +=direction[1][i];
            if(dx<0 || dx>=xmasMap[0].length || dy<0 || dy>=xmasMap.length) {
                break;
            }
            switch(i) {
                case 0:
                    if(xmasMap[dy][dx] != 'M'){
                        return 0;
                    }
                    count++;
                    break;
                case 1:
                    if(xmasMap[dy][dx] != 'A'){
                        return 0;
                    }
                    count++;
                    break;
                case 2:
                    if(xmasMap[dy][dx] != 'S'){
                        return 0;
                    }
                    count++;
                    break;
            }
        }
        if(count==3) {
            return 1;
        }
        return 0;
    }

    private static int countCrossMAS(char[][] xmasMap, List<Integer> ar, List<Integer> ac) {
        int totalCount = 0;
        for(int i= 0; i<ar.size(); i++) {
            totalCount+=findCrossMAS(xmasMap, ar.get(i), ac.get(i));

        }
        return totalCount;
    }

    private static int findCrossMAS(char[][] xmasMap, int ar, int ac){
        List<String> crossMASCombos = Arrays.asList("MMSS", "SMMS", "SSMM", "MSSM");
        int count = 0;
        for(String combination : crossMASCombos) {
            if(ar-1 < 0 || ar+1 >=xmasMap.length || ac-1<0 || ac+1 >=xmasMap[0].length) {
                break;
            }
            if(xmasMap[ar-1][ac-1] == combination.charAt(0)
                    && xmasMap[ar+1][ac-1] == combination.charAt(1)
                    && xmasMap[ar+1][ac+1] == combination.charAt(2)
                    && xmasMap[ar-1][ac+1] == combination.charAt(3)) {
                count++;
                break;
            }
        }
        return count;
    }
}
