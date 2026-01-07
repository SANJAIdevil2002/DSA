public class dijkstra_2_Directed {
        static class graph{
        private int size;
        private int[][]adjMatrix;
        private String [] vertexData;
       public  graph(int size){
            this.size=size;
            this.adjMatrix=new int[size][size];
            this.vertexData=new String[size];
        }

        public void addVertexData(int i,String data){
            if(i>=0&&i<size){
                vertexData[i]=data;
            }
        }

        public void addEdge(int u,int v,int w){
            if(u>=0&&u<size&&v>=0&&v<size){
                adjMatrix[u][v]=w;
            }
        }

        public int startIndex(String data){
               for(int i=0;i<size;i++){
                if(vertexData[i].equals(data)){
                    return i;
                }
               }

            return -1;
        }

        public int minDistances(int []distance,boolean[]visited){
            
            int min=Integer.MAX_VALUE,minIndex=-1;
            for(int u=0;u<size;u++){
                if(!visited[u]&&distance[u]<=min){
                    min=distance[u];
                    minIndex=u;
                }

            }
            return minIndex;
        }

        public int [] dijkstra(String startVertex){
            boolean[]visited=new boolean[size];
            int []distance=new int[size];

            for(int i=0;i<size;i++){
                distance[i]=Integer.MAX_VALUE;
            }

            int st=startIndex(startVertex);
           
            distance[st]=0;
            for(int i=0;i<size;i++){

                int u=minDistances(distance, visited);
                if(u==-1){
                    break;
                }
                visited[u]=true;
                for(int v=0;v<size;v++){
                    if(!visited[v]&&adjMatrix[u][v]!=0&&distance[u]!=Integer.MAX_VALUE){

                        int newDistance=distance[u]+adjMatrix[u][v];
                        if(newDistance<distance[v]){
                            distance[v]=newDistance;
                            
                        }

                    }
                }

            }
            return distance;
        }

    }
    public static void main(String[] args) {
        graph g=new graph(7);

        g.addVertexData(0, "A");
        g.addVertexData(1, "B");
        g.addVertexData(2, "C");
        g.addVertexData(3, "D");
        g.addVertexData(4, "E");
        g.addVertexData(5, "F");
        g.addVertexData(6, "G");

        g.addEdge(0, 2, 3);
        g.addEdge(0, 4, 4);
        g.addEdge(1, 2, 2);
        g.addEdge(1, 5, 2);
        g.addEdge(2, 5, 5);
        g.addEdge(3, 0, 4);
        g.addEdge(3, 4, 2);
        g.addEdge(4, 2, 4);
        g.addEdge(4, 6, 5);
        g.addEdge(6, 6, 5);

        int distance[]=g.dijkstra("D");

        for(int i=0;i<distance.length;i++){
            System.out.println("shortest distance D to "+g.vertexData[i]+" : "+distance[i]);
        }
    }
}
