import java.util.Arrays;

public class MST_prim {
    static class graph{
        int size;
        int [][]adjMatrix;
        String []vertexData;
        boolean []inMSt;
        int []keyValues;
        int []parents;
        public graph(int size){
            this.size=size;
            this.adjMatrix=new int [size][size];
            this.vertexData=new String[size];
            
            this.inMSt=new boolean[size];
            this.keyValues=new int[size];
            this.parents=new int [size];
            Arrays.fill(this.keyValues,Integer.MAX_VALUE);
            Arrays.fill(this.parents,-1);
        }
        public void addEdge(int u,int v,int w){
            if(u>=0&&v>=0&&u<size&&v<size){
                adjMatrix[u][v]=w;
                adjMatrix[v][u]=w;
            }
        }
        public void addVertexData(int i,String data){
            if(i>=0&&i<size){
                vertexData[i]=data;

            }
        }
        public void prim(){
            keyValues[0]=0;
            System.out.println("edge"+"\t"+"weight");
            for(int count=0;count<size;count++){
                int u=-1;
                int min=Integer.MAX_VALUE;
                for(int v=0;v<size;v++){
                    if(!inMSt[v]&&keyValues[v]<min){
                        min=keyValues[v];
                        u=v;
                    }
                }
                inMSt[u]=true;
                if(parents[u]!=-1){
                    System.out.println(vertexData[parents[u]]+"-"+vertexData[u]+"\t"+adjMatrix[parents[u]][u]);
                }
                for(int v=0;v<size;v++){
                    if(!inMSt[v]&&adjMatrix[u][v]!=0&&adjMatrix[u][v]<keyValues[v]){
                        parents[v]=u;
                        keyValues[v]=adjMatrix[u][v];
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        graph g=new graph(8);
        g.addVertexData(0, "A");
        g.addVertexData(1, "B");
        g.addVertexData(2, "C");
        g.addVertexData(3, "D");
        g.addVertexData(4, "E");
        g.addVertexData(5, "F");
        g.addVertexData(6, "G");
        g.addVertexData(7, "H");
        g.addEdge(0, 1, 4);
        g.addEdge(0, 3, 3);
        g.addEdge(1, 2, 3);
        g.addEdge(1, 3, 5);
        g.addEdge(1, 4, 6);
        g.addEdge(2, 4, 4);
        g.addEdge(2, 7, 2);
        g.addEdge(3, 4, 7);
        g.addEdge(3, 5, 4);
        g.addEdge(4, 5, 5);
        g.addEdge(4, 6, 3);
        g.addEdge(5, 6, 7);
        g.addEdge(6, 7, 5);

        g.prim();
    }
}
