import java.util.LinkedList;
import java.util.Queue;

public class ZeroOneMatrix {
    private int m;
    private int n;
    int[][] dirs;
    int[][] dp;
    public int[][] updateMatrix(int[][] matrix) {
        this.m = matrix.length;
        this.n = matrix[0].length;
        if (matrix == null || m == 0) return matrix;
        this.dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        this.dp=new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(matrix[i][j]==1){
                    dp[i][j]=dfs(matrix,i,j);
                }
            }
        }
        return dp;
    }

    private int dfs(int[][] matrix,int i,int j){
        //base
        // if(dp[i][j] != 0) return dp[i][j];
        //logic
        //going to right and bottom so that infinite loop cannot happen.
        if(j<n-1 && matrix[i][j+1]==0) return 1;
        if(j>0 && matrix[i][j-1]==0) return 1;
        if(i>0 && matrix[i-1][j]==0) return 1;
        if(i<m-1 && matrix[i+1][j]==0) return 1;
        //logic
        int top=99999; //dfs(matrix,i-1,j);
        if(i>0 && dp[i-1][j] != 0){
            top=dp[i-1][j];
        }
        int left=99999;//dfs(matrix,i,j-1);
        if(j>0 && dp[i][j-1] != 0){
            left=dp[i][j-1];
        }
        int bottom=99999;//dfs(matrix,i+1,j);
        int right=99999;
        if(i<m-1) {
            if(dp[i+1][j]==0){
                dp[i+1][j]=dfs(matrix,i+1,j);
            }
            bottom = dp[i+1][j];
        }
        if(j<n-1) {
            if(dp[i][j+1]==0){
                dp[i][j+1]=dfs(matrix,i,j+1);
            }
            right = dp[i][j+1];
        }
        int re=Math.min(right,Math.min(top,Math.min(bottom,left)))+1;
        dp[i][j]=re;
        return re;
    }

}





/*
 public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return matrix;
        int m = matrix.length;
        int n = matrix[0].length;
        Queue<int[]> q;
        int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        boolean[][] visited;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1) {
                    q = new LinkedList<>();
                    visited = new boolean[m][n];
                    q.add(new int[]{i, j});
                    visited[i][j] = true;
                    int dist = 0;
                    boolean flag = false;
                    while (!q.isEmpty() && !flag) {
                        int size = q.size();
                        for (int k = 0; k < size; k++) {
                            int[] curr = q.poll();
                            if (matrix[curr[0]][curr[1]] == 0) {
                                flag = true;
                                break;
                            }
                            for (int[] dir : dirs) {
                                int nr = curr[0] + dir[0];
                                int nc = curr[1] + dir[1];
                                if (nr >= 0 && nc >= 0 && nr < m && nc < n && !visited[nr][nc]) {
                                    q.add(new int[]{nr, nc});
                                    visited[nr][nc] = true;
                                }

                            }

                        }
                        dist++;
                    }
                    matrix[i][j] = dist - 1;
                }
            }
        }
       return matrix;
    }
 */


/*
  private int m;
    private int n;
    public int[][] updateMatrix(int[][] matrix) {
       m = matrix.length;
// edge case
      if (matrix == null || m == 0) return matrix;
      n = matrix[0].length;
     int[][] result = new int[m][n];
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            result[i][j] = dfs(matrix, result, i, j);
        }
        }
        return result;
    }
    private int dfs(int[][] matrix, int [][] result,int i, int j){
// base case
        if(i < 0 || i >= m || j < 0 || j >= n) return 9999;
        if(matrix[i][j] == 0) return 0;
        if(i > 0 && matrix[i-1][j] == 0) return 1;
        if(j > 0 && matrix[i][j-1] == 0) return 1;
        if(i < m - 1 && matrix[i+1][j] == 0) return 1;
        if(j < n-1 && matrix[i][j+1] == 0) return 1;
// Don't have any neighboring zero'
      int top, left, right, bottom;
       top = left = 9999; // Declare them as infinity
       if(i > 0 && result[i-1][j] != 0){
        top = result[i-1][j];
   }

   if(j > 0 && result[i][j-1] != 0){
      left = result[i][j-1];
     }
    bottom = dfs(matrix, result, i+1, j);
    right = dfs(matrix, result, i, j+1 );
     return Math.min(Math.min(left, right), Math.min(top, bottom)) + 1;
 }
*/