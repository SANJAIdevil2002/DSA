public class graph_cyclic_direct_union {
         static class graph {
        int size;
        int adj[][];
        char[] ver;
        int []parent;

        graph(int size) {
            this.size = size;
            this.adj = new int[size][size];
            this.ver = new char[size];
            this.parent=new int[size];
            for(int i=0;i<size;i++){
                parent[i]=i;
            }

        }

        public void addvertex(int i, char v) {
            if (i < size && i >= 0) {
                ver[i] = v;
            }
        }

        public void addedge(int u, int v) {
            if (u < size && u >= 0 && v >= 0 && v < size) {
                adj[u][v] = 1;
                adj[v][u]=1;
                
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
        int find(int i){
            System.out.println("find method check equal "+parent[i]+" and "+i);
             System.out.println(java.util.Arrays.toString(parent));

            if(parent[i]==i){
                return i;
            }
            return find(parent[i]);
        }
        public void union(int x,int y){
            System.out.println("union method start...");
            int xRoot=find(x);
            int yRoot=find(y);
              
            System.out.println("union : "+ver[xRoot]+" + "+ver[yRoot]);

            parent[xRoot]=yRoot;

            System.out.println(java.util.Arrays.toString(parent));
        }

        public boolean isCyclic(){
            for(int i=0;i<size;i++){
                for(int j=i+1;j<size;j++ ){
                   int x=find(i);
                   int y=find(j);
                  System.out.println("i : "+i+" and j : "+j);
                  System.out.println("check x and y : "+x+" and "+y);
                 if(adj[i][j]==1){
                    System.out.println("it have connect the vertex :" +ver[i]+"->"+ver[j]);
                       if(x==y){
                        System.out.println("have same value x "+x+" and "+y);
                    return true;
                   }
                   union(x,y);
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
       
        g.addedge(1, 0);
        g.addedge(0, 3);
        g.addedge(0, 2);
        g.addedge(2, 3);
        g.addedge(3, 6);
        g.addedge(3, 5);
        g.addedge(3, 4);
        g.addedge(4,5);

        System.out.println(g.isCyclic()?"cyclic":"not cyclic ");

        
        
    }
    
}
