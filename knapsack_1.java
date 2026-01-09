public abstract class knapsack_1 {
    static int count=0;
    static int knapsack(int [] w,int []v,int capacity,int n){
        count++;
        System.out.println("capacity : "+capacity+" element index : "+n);
        if(capacity==0||n==0){
            System.out.println("check if capacity is finished or number of element is finished... it return 0 ");
            return 0;
        }
        // next element have great profit and is great than capacity ,skip
        if(w[n-1]>capacity){
            System.out.println("check for element weight is less than capacity:" +"=> weight= "+w[n-1]+" > "+ "capacity= "+capacity);
            return knapsack(w, v, capacity, n-1);
        }else{
            System.out.println("weight is less than capacity ");
            int include=v[n-1]+knapsack(w, v, capacity-w[n-1], n-1);
            System.out.println("profit is add "+v[n-1] );
            // if i skip this element ,then i recieve the more profit than preview
            int exclude=knapsack(w, v, capacity, n-1);
            return Math.max(include,exclude);

        }
        
    }

    public static void main(String[] args) {
        int v[]={300,200,400,500};
        int w[]={2,1,5,3};
        int c=10;
        int n=v.length;
      System.out.println(  knapsack(w,v , c, n));
      System.out.println("count "+count);
    }
}
