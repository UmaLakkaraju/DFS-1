import java.util.LinkedList;
import java.util.Queue;

public class FloodFill {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor){
        if (image[sr][sc] == newColor)
            return image;
        int color = image[sr][sc];
        dfs(image,sr,sc,newColor, color);
        return image;
    }

    private void dfs(int[][] image, int sr, int sc, int newColor, int color)
    { // base case
        if(sr < 0 || sr >= image.length || sc < 0 || sc >= image[0].length || image[sr][sc] != color)
            return;
//logic
        image[sr][sc] = newColor;
        int [][] dirs = new int[][] {{0,1}, {1,0}, {-1,0}, {0,-1}};
        for(int[] dir : dirs)
        {
            int r = sr + dir[0];
            int c = sc + dir[1];
            dfs(image, r , c, newColor, color);
        }
    }
}
/* BFS
 public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) return image;
        if (image == null || image.length == 0) return image;
        Queue<int[]> q = new LinkedList<>();
        int color = image[sr][sc];
        q.add(new int[]{sr, sc});
        image[sr][sc] = newColor;
        int[][] dirs = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int[] dir : dirs) {
                int i = dir[0] + curr[0];
                int j = dir[1] + curr[1];
                if (i >= 0 && i < image.length &&
                        j >= 0 && j < image[0].length
                        && image[i][j] == color
                ) {
                    q.add(new int[]{i, j});
                    image[i][j] = newColor;
                }
            }
        }
        return image;
    }
* */