import java.util.*;

public class knapsack_table {
 static void knapsack_t(int []weights,int []values,int[][]table,int capacity,int n){
               for(int i=1;i<=n;i++){
                for(int w=1;w<=capacity;w++){
                    if( weights[i-1]<=w){
                        System.out.println("values[i-1] : "+values[i-1]+"table[i-1][w-weights[i-1]]  "+table[i-1][w-weights[i-1]]);
                        int include=values[i-1]+table[i-1][w-weights[i-1]];
                        System.out.println("i-1 "+ (i-1)+" w-weights[i-1] : "+(w-weights[i-1]));
                        int exclude=table[i-1][w];

                        System.out.println("include : "+include+" which one greater exclude : "+exclude);
                        table[i][w]=Math.max(include,exclude);
                        print_1(table);
                    }
                    else{
                        System.out.println("capacity is less.... so previous data ");
                        
                        table[i][w]=table[i-1][w];
                        print_1(table);
                    }
                }
               }
            //    backtrack
            List<Integer>items=new ArrayList<>();
            int w=capacity;
            for(int i=n;i>0;i--){
                if(table[i][w]!=table[i-1][w]){
                    items.add(i-1);
                    w-=weights[i-1];
                }
            }
          System.out.println(items);
               
               System.out.println(table[n][capacity]);
 }   
 public static void print_1(int [][]table){
    for(int []i:table){
                for(int j:i){
                    System.out.print(j+" ");
                }
                System.out.println();
               }
 }
 public static void main(String[] args) {
     
        int v[]={300,200,400,500};
        int w[]={2,1,5,3};
        int c=10;
        int n=v.length;
        int table[][]=new int[n+1][c+1];
        knapsack_t(w, v, table, c, n);
        
    
 }
}
