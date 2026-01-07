import java.util.Arrays;

import javax.rmi.ssl.SslRMIClientSocketFactory;

public class dijkstra_3_unDirect_path {
    static class graph{
        private int size;
        private String []vertexData;
        private int [][]adjMatrix;
        public graph(int size){
            this.size=size;
            this.adjMatrix=new int[size][size];
            this.vertexData=new String[size];
        }

        public void addEdge(int u,int v,int w){
            if(u>=0&&u<size&&v>=0&&v<size){
                adjMatrix[u][v]=w;
                adjMatrix[v][u]=w;

            }
        }
        public void addVertexData(int i,String data){
            if(i>=0&&i<size){
                vertexData[i]=data;
            }
        }

        public String dijkstra(int startIndex,int endVertex){
            boolean []visited=new boolean[size];
            int distances[]=new int[size];
            // to have the path 
            int prePath[]=new int[size];
            for(int i=0;i<size;i++){
                distances[i]=Integer.MAX_VALUE;
                prePath[i]=-1;
            }
            distances[startIndex]=0;

            for(int i=0;i<size;i++){
                int u=-1;
                for(int j=0;j<size;j++){
                
                if(!visited[j]&&(u==-1||distances[j]<distances[u])){
                      u=j;
                }
            }
            if(u==-1){
                break;
            }
            visited[u]=true;
            for(int v=0;v<size;v++){
                if(!visited[v]&&adjMatrix[u][v]!=0&&distances[u]+adjMatrix[u][v]<distances[v]){
                    distances[v]=distances[u]+adjMatrix[u][v];
                    prePath[v]=u;
                }
            }
            }

            StringBuffer sb=new StringBuffer();
            for(int at=endVertex;at!=-1;at=prePath[at]){
                sb.insert(0, vertexData[at]+(sb.length()>0?"->":""));
            }
             sb.append( " , distance :"+distances[endVertex]);
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


        for(int i=0;i<g.size;i++){
           System.out.println(g.dijkstra(3, i)); 
        }
    }
}
