public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}

class Solution {
    private int[] w_cum;
    private int sum;


    // example where w = {1,3,4,5,2}
    public Solution(int[] w) {
        sum = 0;
        w_cum = new int[w.length]; // define new array of = len to w to store weighted ranges
        for(int i = 0; i < w.length; i++){
            sum+= w[i]; // increment sum by prefix sum + cur val
            w_cum[i] = sum; // add totaled sum so far to respective index
        }// net result is {1,4,8,13,15}
    }

    public int pickIndex() {
        int idx = (int)(Math.random() * sum); // math.random will be a val between 0 and 1
        // such that is sum is 15, idx could be any double between 0-15 and then rounded to
        // an int via casting. this is obviously not the real index since there are only 5
        // indexes but numbers within those ranges correspond to an index. for example
        // index 0 covers 1, index 1 covers ints 2-4, index 2 covers 5,6,7,8 etc. where the #
        // of ints attributed to an index will be proportional with the pick weight of the index
        return binarySearch(idx + 1); // the +1 is because idx will generate values from
        // 0-(sum-1) where it should be from 1-sum because we can't have a pick weight of 0
    }

    private int binarySearch(int val){
        int l = 0;
        int r = w_cum.length-1; // in example, this will be 4 for index 4 of len 5)
        while (l<r){
            int mid = l + (r-l)/2;
            if(w_cum[mid] < val){
                l = mid+1;
            }else {
                r = mid;
            }
        }
        return l;
    }
}