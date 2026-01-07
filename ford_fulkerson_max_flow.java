import java.util.*;
public class ford_fulkerson_max_flow {

    static class graph{
        int size;
        int [][]adjMatrix;
        String []vertexData;
        public graph(int size){
            this.size=size;
            this.adjMatrix=new int[size][size];
            this.vertexData=new String[size];
        }

        public void addVertexData(int vertex,String data){
            vertexData[vertex]=data;
        }
        public void addEdge(int s,int dest,int capacity){
            // directed graph
            adjMatrix[s][dest]=capacity;
        }
        public List<Integer>dfs(int source,int destination,boolean[]visited){

            if(source==destination){
                List<Integer> pathonebyone=new ArrayList<>();
                pathonebyone.add(source);
                return pathonebyone;
            }
            visited[source]=true;
            for(int v=0;v<size;v++){
                // adjMatrix is capacity is greater than 0
                if(!visited[v]&&adjMatrix[source][v]>0){
                    List<Integer>subpath=dfs(v,destination,visited);
                    if(subpath!=null){
                        subpath.add(0,source);
                        return subpath;
                    }


                }
            }
            return null;

        }
        public int  ford_fulkerson(int s,int t){
            int max_flow=0;
            // result of one by one path
            List<Integer>path;
            while ((path=dfs(s, t, new boolean[size]))!=null) {
                int path_flow=Integer.MAX_VALUE;
                for(int i=0;i<path.size()-1;i++){
                    int u=path.get(i);
                    int v=path.get(i+1);

                    // calculate the bottleneck
                    path_flow=Math.min(adjMatrix[u][v],path_flow);
                }
                for(int i=0;i<path.size()-1;i++){
                    int u=path.get(i);
                    int v=path.get(i+1);

                    // reduced the capacity
                    adjMatrix[u][v]-=path_flow;
                    // reverse direction 
                    adjMatrix[v][u]+=path_flow;
                }
                max_flow+=path_flow;
                // print the path
// result path from integer to String
                List<String>p=new ArrayList<>();
                for(int i:path){
                    p.add(vertexData[i]);
                }

                // print the path
                System.out.println("path : "+String.join("->",p)+" , flow :"+path_flow);
            }

            return max_flow;
        }
    }
    public static void main(String[] args) {
        graph g=new graph(6);
        String []ver={"s","v1","v2","v3","v4","t"};
        for(int i=0;i<ver.length;i++){
            g.addVertexData(i, ver[i]);
        }
        g.addEdge(0, 1, 3);
        g.addEdge(0, 2, 7);
        g.addEdge(1, 3, 3);
        g.addEdge(1, 4, 4);
        g.addEdge(2, 1, 5);
        g.addEdge(3, 4, 3);
        g.addEdge(3, 5, 2);
        g.addEdge(4, 5, 6);
        g.addEdge(2, 4, 3);

        

        System.out.println("maximum flow in the graph : "+g.ford_fulkerson(0, 5));
    }
}