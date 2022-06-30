package test;

import java.io.*;

public class Test2_V2 {

    class Area{
        private final int x1;
        private final int y1;
        private final int x2;
        private final int y2;

        public Area(int[] area) {
            this.x1 = area[0];
            this.y1 = area[1];
            this.x2 = area[2];
            this.y2 = area[3];
        }

        public boolean isOverlap(Area other){
            if (x1 >= other.x2){
                return false;
            }

            if (y1 >= other.y2 || y2 <= other.y1){
                return false;
            }

            return true;
        }
    }
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

        System.out.println(new Test2_V2().solution(lands,wells,point));
    }

    public boolean solution(int[][] lands, int[][] wells, int[] point) {
        Area p = new Area(point);

        for(int i=0; i<lands.length; i++){
            Area l = new Area(lands[i]);

            if (p.isOverlap(l)){
                return false;
            }
        }

        for(int i=0; i<wells.length; i++){
            Area w = new Area(wells[i]);

            if (!p.isOverlap(w)){
                return false;
            }
        }

        return true;
    }

}
