public class graph_cyclic_directed {
    // parent and visited vertice are not cyclic
     static class graph {
        int size;
        int adj[][];
        char[] ver;

        graph(int size) {
            this.size = size;
            this.adj = new int[size][size];
            this.ver = new char[size];
        }

        public void addvertex(int i, char v) {
            if (i < size && i >= 0) {
                ver[i] = v;
            }
        }

        public void addedge(int u, int v) {
            if (u < size && u >= 0 && v >= 0 && v < size) {
                adj[u][v] = 1;
                
            }
        }

        public void printGraph() {
            for (int i = 0; i < size; i++) {
                System.out.println("vertex " + i + ":" + ver[i]);
            }
            System.out.println("adjacency matrix");
            for (int r[] : adj) {
                for (int i : r) {
                    System.out.print(i + " ");
                }
                System.out.println();
            }
        }
        public boolean dfsUtil(int v,boolean[]visited,boolean restack[]){
            visited[v]=true;
            restack[v]=true;
            System.out.println("current vertex "+ver[v]);
            for(int j=0;j<size;j++){
                if(adj[v][j]==1){
                    if(!visited[j]){
                        if(dfsUtil(j, visited, restack)){
                            return true;
                        }
                    }
                    else if(restack[j]){
                        return true;
                    }

                }
                
            }
            restack[v]=false;
            return false;
        }
      public boolean isCyclic(){
        boolean [] visited=new boolean[size];
        boolean[]restack=new boolean[size];
        for(int i=0;i<size;i++){
            if(!visited[i]){
                // vertex,visited,restack
                if(dfsUtil(i,visited,restack)){
                    return true;
                }
            }
        }
        return false;

      }

        

    }
    public static void main(String[] args) {
        graph g=new graph(7);

        g.addvertex(0,'A');
        g.addvertex(1,'B');
        g.addvertex(2,'C');
        g.addvertex(3,'D');
        g.addvertex(4,'E');
        g.addvertex(5,'F');
        g.addvertex(6,'G');
       
        g.addedge(3, 0);
        g.addedge(0, 2);
        g.addedge(2, 4);
        g.addedge(4, 0);
        g.addedge(2, 1);
        g.addedge(2, 5);
        g.addedge(2, 6);
        g.addedge(1,5);

        System.out.println(g.isCyclic()?"cyclic":"not cyclic ");

        
        
    }
}
