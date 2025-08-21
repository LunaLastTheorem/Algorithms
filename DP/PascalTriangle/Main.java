import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Main{
    public static void main(String[] args) {
        System.out.println(pascal(7));
    }

    public static List<List<Integer>> pascal(int row){
        List<List<Integer>> res = new ArrayList<>();
        helper(row, res);
        return res;
    }

    private static List<Integer> helper(int row, List<List<Integer>> list){
        if(row == 1){
            List<Integer> base = new ArrayList<>();
            base.add(1);
            list.add(base);
            return base;
        }

        List<Integer> previousRow = helper(row - 1, list);
        List<Integer> next = new ArrayList<>();
        int left = -1;
        int right = 0;
        while(left < previousRow.size()){
            if(left == -1){
                next.add(previousRow.get(right));
            }else if (right == previousRow.size()){
                next.add(previousRow.get(left));
            }else{
                next.add(previousRow.get(left) + previousRow.get(right));
            }
            left++;
            right++;
        }
        list.add(next);

        return next;
    }
}