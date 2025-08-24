import java.util.Arrays;

class Main{
    public static void main(String[] args) {
        int[] arr = {4,6,5,7,8,9,1,2};
        System.err.println(Arrays.toString(mergesort(arr)));
    }

    private static int[] mergesort(int[] arr){
        if(arr.length <= 1){
            return arr;
        }

        int[] arrLeft = Arrays.copyOfRange(arr, 0, arr.length / 2);
        int[] arrRight = Arrays.copyOfRange(arr, arr.length / 2, arr.length);

        arrLeft = mergesort(arrLeft);
        arrRight = mergesort(arrRight);

        int[] sorted = new int[arrLeft.length + arrRight.length];
        int lPtr = 0;
        int rPtr = 0;
        int sortedPtr = 0;
        while(lPtr < arrLeft.length && rPtr < arrRight.length){
            if(arrLeft[lPtr] < arrRight[rPtr]){
                sorted[sortedPtr++] = arrLeft[lPtr++];
            }else{
                sorted[sortedPtr++] = arrRight[rPtr++];
            }
        }

        while(lPtr < arrLeft.length){
            sorted[sortedPtr++] = arrLeft[lPtr++];
        }

        while(rPtr < arrRight.length){
            sorted[sortedPtr++] = arrRight[rPtr++];
        }

        return sorted;
    }
}