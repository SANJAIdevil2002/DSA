import java.util.Arrays;

public class tsp_greedy {

     static int [] tsp_g(int distances[][]){
        int n=distances.length;
        
        int route[]=new int[n+1];//order storing 
        boolean visited[]=new boolean[n];
           route[0]=0;//start
           visited[0]=true;
           
           int mincost=0;
        for(int i=0;i<n-1;i++){
            int last=route[i];
            int next=-1;
            int cost=Integer.MAX_VALUE;
            for(int j=0;j<n;j++){
                if(!visited[j]&&distances[last][j]<cost){
                    cost=distances[last][j];
                      next=j;
            }

            }
            mincost+=cost;
            route[i+1]=next;
            visited[next]=true;
            
           

        }
        //last to start
        mincost+=distances[route[route.length-2]][0];
        // store mincost in route [n+1]
        route[route.length-1]=mincost;
        return route;
     }


     public static void main(String args[]){
     int [][]distances={{0,2,2,5,9,3},
        {2,0,4,6,7,8},
        {2,4,0,8,6,3},
        {5,6,8,0,4,9},
        {9,7,6,4,0,10},
        {3,8,3,9,10,0}
    
    };
    int []result=tsp_g(distances);
    for(int i=0;i<result.length-1;i++){
        if(i==result.length-2){
                    System.out.println(result[i]+"-> 0\n mindistance:"+result[result.length-1]);

        }else{
                    System.out.print(result[i]+" -> ");

        }
    }
    }
}
