import java.util.ArrayList;
import java.util.Arrays;

public class tsp1_brute {
    public static void bruteForceTSP(int [][]distances){
        int n=distances.length;

        ArrayList<Integer>cities=new ArrayList<>();

        for(int i=1;i<n;i++){
            cities.add(i);
        }
        ArrayList<Integer>shortestRoute=new ArrayList<>();

        int minDistance=Integer.MAX_VALUE;
        
        // 
             System.out.println(cities);
        


        ArrayList<ArrayList<Integer>>allPerms=generatePermutations(cities);

        System.out.println("\n final path collection : "+allPerms);
        System.out.println("no .of path is possible : "+allPerms.size());

        for(ArrayList<Integer>prem:allPerms){
            // System.out.print
           
            System.out.println("before add the start point :  "+prem);

            ArrayList<Integer>currentRoute=new ArrayList<>();
            currentRoute.add(0);
            currentRoute.addAll(prem);
            System.out.println("after add the start point in currentRoute: "+currentRoute);

            int currentDistance=calculateDistance(currentRoute,distances);
            if(currentDistance<minDistance){
                minDistance=currentDistance;
                shortestRoute=new ArrayList<>(currentRoute);
            }
        }
        // in route , add last to start
        shortestRoute.add(0);

       for(int city:shortestRoute){
                System.out.print(city+" ");

       }
       System.out.println();
       System.out.println("distance : "+minDistance);
    }
    public static int calculateDistance(ArrayList<Integer>route,int [][]distances){
        int totalDistance=0;
        for(int i=0;i<route.size()-1;i++){
            totalDistance+=distances[route.get(i)][route.get(i+1)];
        }
// last to start distance 
        totalDistance+=distances[route.get(route.size()-1)][route.get(0)];

        return totalDistance;
    }



    public static ArrayList<ArrayList<Integer>>generatePermutations(ArrayList<Integer> list){
        if(list.size()==0){
            ArrayList<ArrayList<Integer>>result=new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        System.out.println("result list ");
        ArrayList<ArrayList<Integer>>result=new ArrayList<>();
        System.out.println(result);
         
        System.out.println("before remove city in list : "+list);
        int FirstElement=list.remove(0);
        System.out.println("remove  city : "+FirstElement);
        System.out.println("after remove city in list : "+list);
        ArrayList<ArrayList<Integer>>recursiveReturn=generatePermutations(list);
        System.out.println("recursivereturn  :");
        System.out.println(recursiveReturn);
        for(ArrayList<Integer>li:recursiveReturn){

           System.out.println("each element "+li);

            for(int i=0;i<=li.size();i++){
               
                ArrayList<Integer>temp=new ArrayList<>(li);
                 System.out.println("temp before add the element : "+temp);
                temp.add(i,FirstElement);
                System.out.println("after the element in index in temp " +i+" ,firstElement : " +FirstElement);
                result.add(temp);
                System.out.println("after the added the temp list in result list "+result);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int [][]distances={{0,2,2,5,9,3},
        {2,0,4,6,7,8},
        {2,4,0,8,6,3},
        {5,6,8,0,4,9},
        {9,7,6,4,0,10},
        {3,8,3,9,10,0}
    
    };
    bruteForceTSP(distances);
    }
}
