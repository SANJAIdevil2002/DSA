public class knapsack_memoization {
    static int count=0;
    public static int knapsack_memo(int []w,int []v,Integer [][]memo,int capacity,int n){
        count++;
        if(memo[n][capacity]!=null){
            return memo[n][capacity];
        }
        int result;
        if(capacity==0||n==0){
            result=0;
        }
        else if(w[n-1]>capacity){
            result=knapsack_memo(w, v, memo, capacity, n-1);

        }else{
            int include=v[n-1]+knapsack_memo(w, v, memo, capacity-w[n-1], n-1);
            int exclude=knapsack_memo(w, v, memo, capacity, n-1);
            result=Math.max(include,exclude);
        }
        return result;
    }
     public static void main(String[] args) {
        int v[]={300,200,400,500};
        int w[]={2,1,5,3};
        int c=10;
        int n=v.length;
        Integer [][]memo=new Integer[n+1][c+1];
      System.out.println(  knapsack_memo(w,v,memo , c, n));
      System.out.println("count = "+count);
    }
}
