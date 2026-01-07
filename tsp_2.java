import java.util.ArrayList;

public class tsp_2 {
   static void bruteTSP(int [][]distance){
    int n=distance.length;

    ArrayList<Integer>cities=new ArrayList<>();
    // skip the cities first 0
    for(int i=1;i<n;i++){
              cities.add(i);
    }
    // final total and route
    int total=Integer.MAX_VALUE;
    ArrayList<Integer>shortestRoute=new ArrayList<>();
    // n!
    ArrayList<ArrayList<Integer>>allperm=generatePermutation(cities);
    // find the dist for all n! route
    for(ArrayList<Integer>route:allperm){
        // before calculate the distance for each route first add the first cities or start cities
        ArrayList<Integer>currentRoute=new ArrayList<>();

        currentRoute.add(0);
        // add all cities in route per
        currentRoute.addAll(route);
        int currentdistance=calcualteDistance(currentRoute,distance);
        if(currentdistance<total){
            total=currentdistance;
            // copy the route in final
            shortestRoute=new ArrayList<>(currentRoute);
        }
    }
     // add the last to start cities [i.e] return
        shortestRoute.add(0);

    // final route 
    for(int i=0;i<shortestRoute.size();i++){
        if(i==shortestRoute.size()-1){
            System.out.println(shortestRoute.get(i));
        }
        else
            System.out.print(shortestRoute.get(i)+"->");
    }
    System.out.println("total mincost : "+total);
   }

   static int calcualteDistance(ArrayList<Integer>cities,int [][]dist){
    int total=0;
    for(int i=0;i<cities.size()-1;i++){
        total+=dist[cities.get(i)][cities.get(i+1)];
    }
    // return to last cities to first cities
    total+=dist[cities.get(cities.size()-1)][cities.get(0)];

    return total;
   }

   static ArrayList<ArrayList<Integer>>generatePermutation(ArrayList<Integer>cities){

    if(cities.size()==0){
        ArrayList<ArrayList<Integer>>result=new ArrayList<>();
        result.add(new ArrayList<>());
        return result;
    }

    // total ways of route
    ArrayList<ArrayList<Integer>> result=new ArrayList<>();
    int firstElement=cities.remove(0);

    // recursice call to remove all cities for permutation
    ArrayList<ArrayList<Integer>> perm=generatePermutation(cities);
// after remove all the element in the cities ,then perm initial have the empty ArrayList  [[]] 
    for(ArrayList<Integer> li:perm){
        // 
        for(int i=0;i<=li.size();i++){
            // copy the one route
            ArrayList<Integer>temp=new ArrayList<>(li);
            temp.add(i,firstElement);

            result.add(temp);

        }
    }
    return result;

   }
    


    public static void main(String [] args){
        int dis[][]={
              {0,2,2,5,9,3},
              {2,0,4,6,7,8},
              {2,4,0,8,6,3},
              {5,6,8,0,4,9},
              {9,7,6,4,0,10},
              {3,8,3,9,10,0}
        };

        bruteTSP(dis);
    }
}
