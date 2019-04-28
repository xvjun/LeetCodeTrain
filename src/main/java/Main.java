import java.util.*;

public class Main {


    /*请完成下面这个函数，实现题目要求的功能
    当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^
    ******************************开始写代码******************************/
    static int[][] temp;
    static int x1, y1;
    static int longpath(int[][] matrix) {
        int sum = 1;
        if (matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }
        y1 = matrix[0].length;
        x1 = matrix.length;
        temp = new int[x1][y1];
        for (int i = 0; i < x1; i++){
            for (int j = 0; j < y1; j++){
                if (temp[i][j] != 0) {
                    continue;
                }
                sum = Math.max(sum, fun1(Integer.MIN_VALUE, i, j, matrix));
            }
        }

        return sum;
    }

    public static int fun1(int OLD, int i, int j, int[][] mat){
        if (i < 0 || i >= x1 || j < 0 || j >= y1 || mat[i][j] <= OLD) {
            return 0;
        }
        if (temp[i][j] == 0){
            int current = mat[i][j];
            int shang = fun1( current, i + 1, j,mat);
            int xia = fun1( current, i - 1, j,mat);
            int zuo = fun1( current, i, j + 1,mat);
            int you = fun1( current, i, j - 1,mat);
            temp[i][j] = 1 + Math.max(Math.max(shang, xia), Math.max(zuo, you));
        }
        return temp[i][j];
    }
    /******************************结束写代码******************************/


    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int res;

        int _matrix_rows = 0;
        int _matrix_cols = 0;
        _matrix_rows = Integer.parseInt(in.nextLine().trim());
        _matrix_cols = Integer.parseInt(in.nextLine().trim());

        int[][] _matrix = new int[_matrix_rows][_matrix_cols];
        for(int _matrix_i=0; _matrix_i<_matrix_rows; _matrix_i++) {
            for(int _matrix_j=0; _matrix_j<_matrix_cols; _matrix_j++) {
                _matrix[_matrix_i][_matrix_j] = in.nextInt();

            }
        }

        if(in.hasNextLine()) {
            in.nextLine();
        }

        res = longpath(_matrix);
        System.out.println(String.valueOf(res));

    }
}
