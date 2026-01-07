import java.util.Arrays;

public class bellMan_2_Detect_negativeCycle {
    static class Result{
        boolean hasNegativeCycle;
        int []distance;
        public Result(boolean hasNegativeCycle,int[]distance){
            this.hasNegativeCycle=hasNegativeCycle;
            this.distance=distance;
        }
    }
    static class graph{
        int size;
        int [][]adjMatrix;
        String []vertexData;
        public graph(int size){
            this.size=size;
            this.adjMatrix=new int [size][size];
            this.vertexData=new String[size];
        }

        public void addVertexData(int i,String data){
            if(i>=0&&i<size){
                vertexData[i]=data;
            }
        }
        public void addEdge(int u,int v,int W){
            if(u>=0&&u<size&&v>=0&&v<size){
                adjMatrix[u][v]=W;
            }
        }

        public Result bellMan(String startVertexData){
            int st=-1;
            for(int i=0;i<size;i++){
                if(vertexData[i].equals(startVertexData)){
                    st=i;
                    break;
                }
            }

            if(st==-1){
                throw new IllegalArgumentException("start vertex not found");
            }
            // only distance
            int distances[]=new int[size];
            Arrays.fill(distances, Integer.MAX_VALUE);
            distances[st]=0;
            for(int i=0;i<size-1;i++){
                for(int u=0;u<size;u++){
                    for(int v=0;v<size;v++){
                        if(distances[u]!=Integer.MAX_VALUE&&adjMatrix[u][v]!=0&&distances[u]+adjMatrix[u][v]<distances[v]){
                            distances[v]=distances[u]+adjMatrix[u][v];
                            System.out.println("relax edge "+vertexData[u]+" -> "+vertexData[v]+ " , updated distance to "+vertexData[v]+" : "+distances[v]);
                        }
                    }
                }
            }

            // check for negative cycle
            for(int u=0;u<size;u++){
                for(int v=0;v<size;v++){
                    if(distances[u]!=Integer.MAX_VALUE&&adjMatrix[u][v]!=0&&distances[u]+adjMatrix[u][v]<distances[v]){
                        return new Result(true, null);
                    }
                }
            }
            return new Result(false, distances);


        }
    }
    public static void main(String[] args) {
        graph g=new graph(5);
        g.addVertexData(0, "A");
        g.addVertexData(1, "B");
        g.addVertexData(2, "C");
        g.addVertexData(3, "D");
        g.addVertexData(4, "E");

        g.addEdge(3, 0, 4);
        g.addEdge(3, 2, 7);
        g.addEdge(3, 4, 3);
        g.addEdge(0, 2, 4);
        g.addEdge(2, 0, -9);
        g.addEdge(0, 4, 5);
        g.addEdge(4, 2, 3);
        g.addEdge(1, 2, -4);
        g.addEdge(4, 1, 2);

        System.out.println("the bell man ford start from vertex D :");
        Result r=g.bellMan("D");
        if(!r.hasNegativeCycle){
            // distance
            for(int i=0;i<r.distance.length;i++){
                System.out.println("distance from D to "+g.vertexData[i]+" : "+r.distance[i]);
            }
        }
        else{
            System.out.println("negative cycle detected. cannot compute the shortest paths");
        }
    }
}
