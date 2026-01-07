import java.util.Arrays;
public class bellMan_3_direct_path_allVertex {
      static class graph{
        private int size;
        int adjMatrix[][];
        private String vertexData[];
        public graph(int size){
            this.adjMatrix=new int [size][size];
            this.vertexData=new String[size];
            this.size=size;

        }
        public void addEdge(int u,int v,int w){
            if(u>=0&&u<size&&v>=0&&v<size){
                adjMatrix[u][v]=w;
            }
        }
        public void addVertexData(int i,String data){
            if(i>=0&&i<size){
                vertexData[i]=data;
            }
        }
        public int findIndex(String startVertex){
            for(int i=0;i<size;i++){
                if(vertexData[i].equals(startVertex)){
                    return i;
                }
            }
            return -1;
        }

        public Result bellManFord(String startVertex){
            int st=findIndex(startVertex);
            System.out.println(st);
            int distances[]=new int[size];
            Arrays.fill(distances,Integer.MAX_VALUE);
            distances[st]=0;
            // not possible for -1 so here we use null
            Integer []prepath=new Integer[size];
            Arrays.fill(prepath, null);

            for(int i=0;i<size-1;i++){
                for(int u=0;u<size;u++){
                    for(int v=0;v<size;v++){
                        if(adjMatrix[u][v]!=0&&distances[u]!=Integer.MAX_VALUE&&distances[u]+adjMatrix[u][v]<distances[v]){
                            distances[v]=distances[u]+adjMatrix[u][v];
                            prepath[v]=u;
                            System.out.printf("relax vertex %s -> %s , updated to %s distance : %d%n",vertexData[u],vertexData[v],vertexData[v],distances[v]);
                        }
                    }
                }
            }
            // check for the negative cycle

            for(int u=0;u<size;u++){
                for(int v=0;v<size;v++ ){
                    if (adjMatrix[u][v]!=0&&distances[u]!=Integer.MAX_VALUE&&distances[u]+adjMatrix[u][v]<distances[v]) {
                        // detection of cycle confirmed
                        return new Result(true,null,null);
                    }
                }
            }
            return new Result(false,distances,prepath);
        }
        
        public String getPath(Integer []prePath,String start,String end){
            int current=findIndex(end);
            if(prePath==null){
                return " no path available ";
            }

            StringBuffer sb=new StringBuffer();
            if(current==-1||prePath[current]==null){
                return " no path from vertex from "+start+" to "+end;
            }

            while(current!=-1){
               sb.insert(0, vertexData[current]);
               Integer pred=prePath[current];
               if(pred!=null&&pred!=findIndex(start)){
                sb.insert(0, "->");
                current=pred;
               }else{
                if(pred!=null){
                    sb.insert(0, start+"->");
                }
                break;
               }
            }
            return sb.toString();
        }

      }
      static class Result{
        int []distances;
        boolean hasNegativeCycle;
        Integer prePath[];
        public Result(boolean hasNegativeCycle,int []distances,Integer []prePath){
            this.hasNegativeCycle=hasNegativeCycle;
            this.distances=distances;
            this.prePath=prePath;
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
        // see important
        g.addEdge(2, 0, -3);
        g.addEdge(0, 4, 5);
        g.addEdge(4, 2, 3);
        g.addEdge(1, 2, -4);
        g.addEdge(4, 1, 2);

        Result r=g.bellManFord("D");

        if(!r.hasNegativeCycle){
            for(int i=0;i<r.distances.length;i++){
                if(r.distances[i]!=Integer.MAX_VALUE){
                System.out.println(g.getPath(r.prePath, "D", g.vertexData[i])+" , distance : "+r.distances[i]);

                }else{
                    System.out.println("no path from D to "+g.vertexData[i]+" , distances : infinite" );
                }
            }
        }
        else{
            System.out.println("negative cyclic detected. cannot compute the shortest path");
        }
      }
}
