public class graph_traversal_direct_without_w {

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

        // bfs
        public void bfs(char st){
            boolean[]visited=new boolean[size];
            int []queue=new int[size];
            // for printing
            int queueStart=0;
            // add at end
            int queueEnd=0;

            int s=new String(ver).indexOf(st);
            // new are add at end
            queue[queueEnd++]=s;
            visited[s]=true;
            while(queueStart<queueEnd){
                int currentVertex=queue[queueStart++];
                System.out.print(ver[currentVertex]+" ");
                for(int i=0;i<size;i++){
                    if(adj[currentVertex][i]==1&&!visited[i]){
                        queue[queueEnd++]=i;
                        visited[i]=true;
                    }
                }
            }
        }
          // dfs
        public void dfs(char v) {
            System.out.println("dfs traversal .....");
            boolean[] visited = new boolean[size];
            int start = new String(ver).indexOf(v);
            dfsUtil(start, visited);
        }

        public void dfsUtil(int st, boolean[] visited) {
            visited[st] = true;
            System.out.print(ver[st] + " ");
            for (int i = 0; i < size; i++) {
                if (adj[st][i] == 1 && !visited[i]) {
                    dfsUtil(i, visited);
                }
            }

        }

    }
    public static void main(String[] args) {
        graph g=new graph(7);
        g.addvertex(0, 'A');
        g.addvertex(1, 'B');
        g.addvertex(2, 'C');
        g.addvertex(3, 'D');
        g.addvertex(4, 'E');
        g.addvertex(5, 'F');
        g.addvertex(6, 'G');

     g.addedge(3, 0);
     g.addedge(3, 4);
     g.addedge(4, 0);
     g.addedge(0, 2);
     g.addedge(2, 5);
     g.addedge(2, 6);
     g.addedge(5, 1);
     g.addedge(1, 2);

        g.printGraph();
        System.out.println("dfs");
        g.dfs('D');
        System.out.println();
        System.out.println("bfs");
        g.bfs('D');
    }
}
