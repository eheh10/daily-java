package test;

import java.io.*;

public class Test2_V2 {
    public static void main(String[] args) throws IOException {
        InputStream is = System.in;
        BufferedInputStream bis = new BufferedInputStream(is,8192);
        BufferedReader br = new BufferedReader(new InputStreamReader(bis),8192);

        int[][] lands = {
                {10, 0, 30, 5},
                {0, 30, 20, 50},
                {30, 30, 40, 40},
        };
        int[][] wells = {
                {15, 15, 25, 25},
                {40, 10, 50, 20},
        };
        int[] point = new int[4];
        String[] input = br.readLine().split(",");

        for(int i=0; i<point.length; i++){
            point[i] = Integer.parseInt(input[i]);
        }

        System.out.println(solution(lands,wells,point));
    }

    public static boolean solution(int[][] lands, int[][] wells, int[] point) {
        if (!isOverlap(lands,point) && isOverlap(wells,point)){
            return true;
        }

        return false;
    }

    private static boolean isOverlap(int[][] area, int[] point){
        int x1 = 0;
        int y1 = 1;
        int x2 = 2;
        int y2 = 3;

        for(int i=0; i<area.length; i++){
            if (point[x1] >= area[i][x2]){
                continue;
            }

            if (point[y1] >= area[i][y2] || point[y2] <= area[i][y1]){
                continue;
            }

            return true;
        }

        return false;
    }

}
