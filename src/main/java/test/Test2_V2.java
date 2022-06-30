package test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Test2_V2 {

    static class Area{
        private final int leftX;
        private final int leftY;
        private final int rightX;
        private final int rightY;

        public Area(int[] area) { //유효성 체크 null, size
            this.leftX = area[0];
            this.leftY = area[1];
            this.rightX = area[2];
            this.rightY = area[3];
        }

        public boolean isOverlap(Area other){ //유효성 검사
            if (leftX >= other.rightX){
                return false;
            }

            if (leftY >= other.rightY || rightY <= other.leftY){
                return false;
            }

            return true;
        }
    }

    static class Areas{
        private final List<Area> values;

        public Areas(List<Area> values) { //리스트 자체 && 각 요소 null 확인, 깊은 복사
            this.values = values;
        }

        public boolean containOverlapArea(Area area){
            for(int i=0; i<values.size(); i++){
                if (values.get(i).isOverlap(area)){
                    return true;
                }
            }

            return false;
        }

        public boolean doesNotContainOverlapArea(Area area){
            return !containOverlapArea(area);
        }

        public static Areas from(int[][] areaValues){ //유효성
            List<Area> values = new ArrayList<>();

            for(int i=0; i < areaValues.length; i++){
                values.add(new Area(areaValues[i]));
            }

            return new Areas(values);
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

        Areas landAreas = Areas.from(lands);
        Areas wellAreas = Areas.from(wells);

        return landAreas.doesNotContainOverlapArea(p) && wellAreas.containOverlapArea(p);
    }

}
