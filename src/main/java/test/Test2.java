package test;

import java.io.*;

public class Test2 {
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
        for(int i=0; i<lands.length; i++){
            if (isOverlap(lands[i],point)){
                return false;
            }
        }

        for(int i=0; i<wells.length; i++){
            if (!isOverlap(wells[i],point)){
                return false;
            }
        }

        return true;
    }

    private static boolean isOverlap(int[] area, int[] point){
        int x1 = 0;
        int y1 = 1;
        int x2 = 2;
        int y2 = 3;

        if (point[x1] >= area[x2]){
            return false;
        }

        if (point[y1] >= area[y2] || point[y2] <= area[y1]){
            return false;
        }

        return true;
    }

}
