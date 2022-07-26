package test.algorithm.tomato;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

class SolutionTestWithBFS {
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    static Queue<Node> bucket = new ArrayDeque<>();

    static class Node{
        private final int row;
        private final int col;
        private final List<int[]> path;

        public Node(int row, int col, List<int[]> path) {
            this.row = row;
            this.col = col;

            List<int[]> copy = new ArrayList<>();
            for(int[] p : path){
                copy.add(new int[]{p[0],p[1]});
            }
            this.path = copy;
        }

        public boolean isInvalid(int maxRow, int maxColumn){
            return !(0 <= row && row <= maxRow && 0 <= col && col <= maxColumn);
        }

        public Node updatePath(){
            path.add(new int[]{row, col});
            return new Node(row,col,path);
        }

        public Node scalNode(int scalRow, int scalCol) {
            return new Node(row+scalRow, col+scalCol, path);
        }

    }

    @DisplayName("입출력으로 주어진 예시를 통과하는지 테스트 합니다.")
    @ParameterizedTest
    @ArgumentsSource(SolutionTestSuiteArgumentsProvider.class)
    void testExample(int M, int N, int[][]box, int expectAnswer) {
        int answer = -1;

        for(int row=N-1; 0 <= row ; row--){
            for(int col=M-1; 0 <= col; col--){
                if(box[row][col] != 1) {
                    continue;
                }

                Node node = new Node(row,col, new ArrayList<>());
                bucket.add(node);
            }
        }

        while(!bucket.isEmpty()) {
            Node node = bucket.poll();

//            System.out.println("current: "+node.row+","+node.col);

            if (bucket.isEmpty() && isAllRipe(box)){
//                for(int[] path:node.path){
//                    System.out.print(Arrays.toString(path)+"->");
//                }

                answer = node.path.size();
                break;
            }

            for (int i = 0; i < 4; i++) {
                Node nextNode = node.scalNode(dy[i],dx[i]);

                if(nextNode.isInvalid(N-1, M-1)) {
                    continue;
                }

                if(alreadyRipe(box,nextNode)){
                    continue;
                }

                if(notExist(box,nextNode)){
                    continue;
                }

//                System.out.println("next: "+nextNode.row+","+nextNode.col);
                ripe(box, nextNode.updatePath());
            }
        }
        assertThat(answer).isEqualTo(expectAnswer);
    }

    private boolean isAllRipe(int[][] arr) {
        for(int[] row : arr){
            for(int n : row){
                if (n==0){
                    return false;
                }
            }
        }
        return true;
    }

    public void ripe(int[][] arr, Node node) {
        arr[node.row][node.col] = 1;
        bucket.add(node);
    }
    private boolean notExist(int[][] arr, Node node) {
        return arr[node.row][node.col]==-1;
    }

    public boolean alreadyRipe(int[][] arr, Node node) {
        return arr[node.row][node.col]==1;
    }
}
