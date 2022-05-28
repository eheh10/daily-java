package com.heap;

import java.util.ArrayList;
import java.util.List;

class Heap {
    private List<Integer> heaps;

    public Heap(List<Integer> heaps){
        this.heaps = heaps;
    }

    public void add(int num){
        heaps.add(num);

        for(int i=heaps.size()-1; i > 0; i/=2){
            if(heaps.get(i) < heaps.get(i/2)){
                swap(i,i/2,heaps);
                continue;
            }
            break;
        }

    }

    public int pop(){
        swap(1,heaps.size()-1,heaps);
        heaps.remove(heaps.size()-1);

        int swapIdx;
        for(int i=1; i < heaps.size(); i=swapIdx){
            swapIdx = i*2;

            if (swapIdx+1 < heaps.size() && heaps.get(swapIdx) > heaps.get(swapIdx+1)){
                swapIdx += 1;
            }

            if(swapIdx < heaps.size() && heaps.get(i) > heaps.get(swapIdx)){
                swap(i,swapIdx,heaps);
                continue;
            }

            break;
        }

        return heaps.get(1);

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

public class MinHeap {
    public static void main(String[] args) {
        Heap minHeap = new Heap(new ArrayList<>(List.of(-1, 3, 10, 35, 23, 19, 47, 60, 35, 80, 44)));

        minHeap.add(5);
        System.out.println(minHeap.toString());

        minHeap.add(1);
        System.out.println(minHeap.toString());

        System.out.println(minHeap.pop());
        System.out.println(minHeap.toString());

    }
}
