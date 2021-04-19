/**
 * @author YonghShan
 * @date 3/8/21 - 13:49
 */
public class Solution2 {
    // Two Pass DP：对于每个cell 1，它的nearest distance即为它所有邻居的nearest distance中最小值+1
    // 因为DP是用precalculated value来更新自己的值，当一遍从top left到bottom right遍历的过程中，一个cell的右邻居和下邻居都还是初始值，
    // 所以这个过程中能利用的precalculated value只有左邻居和上邻居的nearest distance
    // 但是the minimum path to 0 can be in any direction，所以第一次pass结束后，还要从bottom right向left top再遍历一次
    /* Runtime: 5ms     O(r⋅c): 2 passes of r⋅c each
       Memory: 43.2MB   O(r⋅c). No additional space required than res[r][c]
     */
    public int[][] updateMatrix(int[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        // 记录最短距离的2D数组：
        int[][] res = new int[height][width];
        for (int i = 0; i < height; i++) {     // 给res赋初值： Arrays.fill()只能填充一维数组，而且内部依旧是用for循环实现的，并不能提高赋值的效率
            for (int j = 0; j < width; j++) {
                res[i][j] = Integer.MAX_VALUE-100;  // 防止从第一行开始连着几行行首元素都为1，因为如果第一行行首元素为1，则res[0][0]=2147483647
            }                                       // 若第二行行首元素也为1，则res[1][0]=Math.min(res[1][0], res[0][0]+1)=Math.min(2147483647, -2147483648)=-2147483648
        }                                           // 因为description中说明元素总数少于10000，即100x100，这里Integer.MAX_VALUE-100是防止连续100行行首元素都为1
        // 1st Iteration: Top left -> Bottom right
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (matrix[i][j] == 0) {
                    res[i][j] = 0;
                } else {
                    if (i > 0) res[i][j] = Math.min(res[i][j], res[i - 1][j] + 1); // 上邻居
                    if (j > 0) res[i][j] = Math.min(res[i][j], res[i][j - 1] + 1); // 左邻居
                }
            }
        }
        // 2nd Iteration: Bottom right -> Top left
        for (int i = height - 1; i >= 0; i--) {
            for (int j = width - 1; j >= 0; j--) {
                if (i < height - 1) res[i][j] = Math.min(res[i][j], res[i + 1][j] + 1); // 下邻居
                if (j < width - 1) res[i][j] = Math.min(res[i][j], res[i][j + 1] + 1); // 右邻居
            }
        }

        return res;
    }
}
