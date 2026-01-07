import java.util.LinkedList;
import java.util.Queue;

public class edmords_karp {
    static class graph{
        int size;
        int adjMatrix[][];
        String vertexData[];
        public graph(int size){
            this.size=size;
            this.adjMatrix=new int[size][size];
            this.vertexData=new String[size];
        }
        public void addVertexData(int vertex,String data){
            vertexData[vertex]=data;
        }
        public void addEdge(int u,int v,int capacity){
            adjMatrix[u][v]=capacity;
        }
        public boolean bfs(int source,int destination,int []parents){
// check for it reached the destination or not
            boolean[]visited=new boolean[size];
            Queue<Integer>queue=new LinkedList<>();
            parents[source]=-1;
            visited[source]=true;
            queue.add(source);
            while(!queue.isEmpty()){
                int u=queue.poll();
                for(int v=0;v<size;v++){
                    if(!visited[v]&&adjMatrix[u][v]>0){
                        parents[v]=u;
                        queue.add(v);
                        visited[v]=true;
                    }
                }
            }
            return visited[destination];
        }
        public int edmords(int source,int destination){
            int u,v;
            // to hold the previous record
            int []parents=new int[size];
            int max_flow=0;
            while(bfs(source,destination,parents)){
                int path_flow=Integer.MAX_VALUE;
                
                for(v=destination;v!=source;v=parents[v]){
                    u=parents[v];

                    path_flow=Math.min(adjMatrix[u][v], path_flow);

                }
                for(v=destination;v!=source;v=parents[v]){
                    u=parents[v];
                    adjMatrix[u][v]-=path_flow;
                    adjMatrix[v][u]+=path_flow;
                }
                max_flow+=path_flow;

                LinkedList<String>path=new LinkedList<>();
                for(v=destination;v!=source;v=parents[v]){
                    path.addFirst(vertexData[v]);

                }
                System.out.println("path: "+String.join("->", path)+" , flow: "+path_flow);

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

        

        System.out.println("maximum flow in the graph : "+g.edmords(0, 5));
    }
}
