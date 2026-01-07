import java.util.ArrayList;
import java.util.Collections;

public class tsp3 {
    static ArrayList<Integer>shortestPath=new ArrayList<>();
    static int minCost=Integer.MAX_VALUE;
   

    public static void tsp_chatgpt(int [][]dist){

        int n=dist.length;
        ArrayList<Integer>cities=new ArrayList<>();
        // first skip the firstelement
        for(int i=1;i<n;i++){
            cities.add(i);
        }

        permutationAll(cities,dist,0);//start element
        System.out.print("0 -> ");
        for(int city:shortestPath){
            if(city==shortestPath.get(shortestPath.size()-1)){
                System.out.println(city);
            }
            else{
                System.out.print(city+" -> ");}
        }
        System.out.println("min cost : "+minCost);

    }
    static void permutationAll(ArrayList<Integer>cities,int[][]dist,int index ){
          
        if(index==cities.size()){
            int cost=0;
            int prev=0;//start
            for(int i=0;i<cities.size();i++){
                cost+=dist[prev][cities.get(i)];
                prev=cities.get(i);
            }

            // last to start
            cost+=dist[cities.get(cities.size()-1)][0];
            if(cost<minCost){
                minCost=cost;
                shortestPath=new ArrayList<>(cities);
                shortestPath.add(0);
            }
            return;
        }
        for(int i=index;i<cities.size();i++){
            System.out.println("before swap the cities list "+cities+" index "+index );
            Collections.swap(cities, index, i);
            System.out.println("after swap the cities List :"+cities+" index "+index);
            

            permutationAll(cities, dist, index+1);


            Collections.swap(cities, index, i);//backtrack
            System.out.println("back track "+cities);
          }
    }
     public static void main(String args[]){
     int [][]distances={{0,2,2,5,9,3},
        {2,0,4,6,7,8},
        {2,4,0,8,6,3},
        {5,6,8,0,4,9},
        {9,7,6,4,0,10},
        {3,8,3,9,10,0}
    
    };
    tsp_chatgpt(distances);
    }
}