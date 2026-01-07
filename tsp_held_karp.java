import java.util.Arrays;

public class tsp_held_karp {
 
    static int[][]parent;
    static int totalCost(int mark,int curr,int [][]cost,int [][]dp){
        int n=cost.length;

        // all city are visited return the cost
        System.out.println("all city is visted or not: mark==(1<<n)-1 => mark: "+mark+"==((1<<n)-1) "+((1<<n)-1));
        if(mark==(1<<n)-1){
            System.out.println(" all city is visited");
            return cost[curr][0];
        }

        // memoization to check the process is already do the task ,then not do task
        System.out.println("memoization to check the process is already do the task ,then not do task");
        System.out.println("curr ="+curr+" mark= "+mark +"dp[curr][mark] "+dp[curr][mark]+" == -1");
        if(dp[curr][mark]!=-1){
            return dp[curr][mark];

        }
        int ans=Integer.MAX_VALUE;
        // try visiting every city that has not been visited yet
        for(int i=0;i<n;i++){
              System.out.println("check city is visited or not: that is (mark&(1<<i))==0 "+(mark&(1<<i))+"==0");
            if((mark&(1<<i))==0){
                System.out.println("not visited");
                // if city i is not visited
                // visited city i and update  the mark
                System.out.println("totalCost(mark|(1<<i), i, cost, dp) mark= "+(mark|(1<<n))+" i "+i);
                int newCost=cost[curr][i]+totalCost(mark|(1<<i), i, cost, dp);
                System.out.println("compare the newcost and ans "+newCost+" and= "+ans);
                if(newCost<ans){

                    System.out.println("update the ans =newcost");
                    ans=newCost;
                    System.out.println("parent array : curr "+curr +" mark ="+mark+" == "+i);
                    parent[curr][mark]=i;
                    printMAtrix(parent);
                }
            }
        }
        System.out.println("dp[curr][mark]=ans"+" curr : "+curr+" mark:"+mark+" ans: "+ans+"\n array of dp :");
         // print array
         System.out.println("before assign the answer ");
        printMAtrix(dp);
        System.out.println("after assign the answer ");
        dp[curr][mark]=ans;
        printMAtrix(dp);
      return dp[curr][mark]=ans;
    }
static int tsp(int[][]cost){
    int n=cost.length;
    System.out.println("dp initializing the array "+(1<<n));
    int dp[][]=new int [n][1<<n];
    System.out.println("\n after initial the dp ");
    // print the array
    printMAtrix(dp);

     
    parent=new int[n][1<<n];
    System.out.println("parent to store the city path");
    // print array
    printMAtrix(parent);
    int mark=1;//one city is visited
    int curr=0;//start the city
    for(int i=0;i<n;i++){
        Arrays.fill(dp[i], -1);
        Arrays.fill(parent[i],-1);
    }
    System.out.println("after assign all value as -1 in dp\n");
    // print the array
    printMAtrix(dp);


    System.out.println("after assign city in parent\n");

     // print array
    printMAtrix(parent);

    int minCost=totalCost(mark, curr, cost, dp);
    printPath(cost.length);
    return minCost;
}
static void printPath(int n){
    int mark=1;
    int curr=0;
    System.out.print("path= 0");
    while (mark!=(1<<n)-1) {
        int next=parent[curr][mark];
        System.out.print("->"+next+" ");
        mark|=(1<<next);
        curr=next;
        
    }
    System.out.println("->0");
}
static void   printMAtrix(int [][]prin){
   for(int i=0;i<prin.length;i++){
    System.out.print("[ ");
    for(int j=0;j<prin[i].length;j++){
            System.out.print(prin[i][j]+" , ");
    }
    System.out.print(" ]");
    System.out.println();
   }
}
public static void main(String[] args) {
    int [][]distances={{0,2,2,5,9,3},
        {2,0,4,6,7,8},
        {2,4,0,8,6,3},
        {5,6,8,0,4,9},
        {9,7,6,4,0,10},
        {3,8,3,9,10,0}
    
    };

    System.out.println("min cost: "+tsp(distances));
}
    }
