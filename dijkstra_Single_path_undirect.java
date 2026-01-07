import java.util.Arrays;

public class dijkstra_Single_path_undirect {
    static class graph{
        private int size;
        private String vertexData[];
        private int [][]adjMatrix;
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
        public void addEdge(int u,int v,int w){
            if(u>=0&&u<size&&v>=0&&v<size){
               adjMatrix[u][v]=w;
               adjMatrix[v][u]=w;
            }
        }
        public int minDistance(int []distance,boolean[]visited){
            int min=Integer.MAX_VALUE,minIndex=-1;
            for(int i=0;i<size;i++){
                if(!visited[i]&&distance[i]<=min){
                    min=distance[i];
                    minIndex=i;

                }
            }
            return minIndex;
        }

        public void dijkstra(String startVertex,String endVertex){
            int st=Arrays.asList(vertexData).indexOf(startVertex);
            int en=Arrays.asList(vertexData).indexOf(endVertex);
            boolean[]visited=new boolean[size];
            int [] distance=new int[size];
            int []prePath=new int[size];
            Arrays.fill(distance, Integer.MAX_VALUE);
            Arrays.fill(prePath, -1);

            distance[st]=0;

            for(int i=0;i<size;i++){
                int u=minDistance(distance, visited);

                if(u==-1){
                    break;
                }
                if(u==en){
                    System.out.println("breaking the loop out of vertex: "+vertexData[u]);
                    System.out.println(Arrays.toString(distance));
                    
                    break;
                }
                visited[u]=true;
                 System.out.println("current vertex : "+vertexData[u]);

                for(int v=0;v<size;v++){
                    if(!visited[v]&&adjMatrix[u][v]!=0&&distance[u]+adjMatrix[u][v]<distance[v]&&distance[u]!=Integer.MAX_VALUE){
                        distance[v]=distance[u]+adjMatrix[u][v];
                        prePath[v]=u;
                    }
                }
            }

           String path=getPath(distance,prePath,st,en);
           System.out.println(path);


        }
        public String getPath(int []distance,int []prePath,int st,int en){
           StringBuffer sb=new StringBuffer();
            for(int at=en;at!=-1;at=prePath[at]){
                sb.insert(0, vertexData[at]);

                if(at!=st){
                    sb.insert(0, "->");
                }

            }
            sb.append(" , distance: "+distance[en]);
            return sb.toString();

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

        g.addEdge(3, 0, 4);
        g.addEdge(3, 4, 2);
        g.addEdge(0, 4, 4);
        g.addEdge(0, 2, 3);
        g.addEdge(4, 2, 4);
        g.addEdge(4, 6, 5);
        g.addEdge(2, 5, 5);
        g.addEdge(2, 1, 2);
        g.addEdge(2, 6, 5);
        g.addEdge(6, 5, 5);
        g.addEdge(1, 5, 2);

        g.dijkstra("D", "F");
    }
    
}
