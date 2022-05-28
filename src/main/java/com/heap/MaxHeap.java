package com.heap;

import java.util.ArrayList;
import java.util.List;

class Heap2 {
    private List<Integer> heaps;

    public Heap2(List<Integer> heaps){
        this.heaps = heaps;
    }

    public void add(int num){
        heaps.add(num);

        for(int i=heaps.size()-1; i > 0; i/=2){
            if(i/2 > 0 && heaps.get(i) > heaps.get(i/2)){
                swap(i,i/2,heaps);
                continue;
            }
            break;
        }

    }

    public int pop(){
        int max = heaps.get(1);

        swap(1,heaps.size()-1,heaps);
        heaps.remove(heaps.size()-1);

        int swapIdx;
        for(int i=1; i < heaps.size(); i=swapIdx){
            swapIdx = i*2;

            if (swapIdx+1 < heaps.size() && heaps.get(swapIdx) < heaps.get(swapIdx+1)){
                swapIdx += 1;
            }

            if(swapIdx < heaps.size() && heaps.get(i) < heaps.get(swapIdx)){
                swap(i,swapIdx,heaps);
                continue;
            }

            break;
        }

        return max;

    }

    private void swap(int index1, int index2, List<Integer> heaps){
        int tmp = heaps.get(index1);
        heaps.set(index1,heaps.get(index2));
        heaps.set(index2,tmp);
    }

    @Override
    public String toString() {
        return heaps+"";

    }
}

public class MaxHeap {
    public static void main(String[] args) {
        Heap2 maxHeap = new Heap2(new ArrayList<>(List.of(-1, 80, 60, 47, 35, 35, 44, 19, 23, 3, 10)));

        System.out.println("==초기 상태==");
        System.out.println(maxHeap.toString());
        System.out.println();

        System.out.println("==50 추가==");
        maxHeap.add(50);
        System.out.println(maxHeap.toString());
        System.out.println();

        System.out.println("==100 추가==");
        maxHeap.add(100);
        System.out.println(maxHeap.toString());
        System.out.println();

        System.out.println("==최댓값 출력==");
        System.out.println(maxHeap.pop());
        System.out.println(maxHeap.toString());

    }
}
