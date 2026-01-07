public class graph_traversal_bfs {
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
                adj[v][u] = 1;
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
                System.out.println("start index "+(queueStart)+"end index "+queueEnd);
                int currentVertex=queue[queueStart++];
                System.out.println(ver[currentVertex]+" ");
                for(int i=0;i<size;i++){
                    if(adj[currentVertex][i]==1&&!visited[i]){
                        queue[queueEnd++]=i;
                        visited[i]=true;
                    }
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

        g.addedge(0, 3);
        g.addedge(0, 2);
        g.addedge(0, 4);
        g.addedge(2, 4);
        g.addedge(2, 5);
        g.addedge(2, 1);
        g.addedge(2, 6);
        g.addedge(1, 5);

        g.printGraph();

        g.bfs('D');
    }

}
