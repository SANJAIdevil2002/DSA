import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
public class MST_kruskral {
    
    static class Edge implements Comparable<Edge>{
             int src,dest,weight;
             public int compareTo(Edge compareEdge){
                return this.weight-compareEdge.weight;
             }
    }
    static class subset{
           int parent,rank;
    }

    static class graph{
        int v;
        ArrayList<Edge> edges;
        String[]vertexNames;

        graph(int v){
            this.v=v;
            edges=new ArrayList<>();
            vertexNames=new String[v];
        }

        public void addEdge(int src,int dest,int weight){
            Edge newEdge=new Edge();
            newEdge.src=src;
            newEdge.dest=dest;
            newEdge.weight=weight;

            edges.add(newEdge);
        }
        public void addVertexName(int vertex,String names){
            vertexNames[vertex]=names;
        }
        public int find(subset subsets[],int i){
            for(int j=0;j<v;j++){
                System.out.print(subsets[j].parent+" "+subsets[j].rank +"->");
            }System.out.println();
            System.out.println("find the i "+i );
            if(subsets[i].parent!=i){
                subsets[i].parent=find(subsets,subsets[i].parent);
            }
            return subsets[i].parent;
        } 
        void union(subset []subsets,int x,int y){
            int xRoot=find(subsets,x);
            int yRoot=find(subsets,y);
            System.out.println("x "+subsets[xRoot].parent +" "+subsets[xRoot].rank);
            System.out.println("y "+subsets[yRoot].parent +" "+subsets[yRoot].rank);
           
            if(subsets[xRoot].rank<subsets[yRoot].rank){
                subsets[xRoot].parent=yRoot;
            }
            else if(subsets[xRoot].rank>subsets[yRoot].rank){
                subsets[yRoot].parent=xRoot;
            }
            else{
                subsets[yRoot].parent=xRoot;
                subsets[xRoot].rank++;
            }
        }
        void kruskal(){
            // result
            ArrayList<Edge>a=new ArrayList<>();
            Collections.sort(edges);

            subset subsets[]=new subset[v];
            for(int v=0;v<this.v;v++){
                subsets[v]=new subset();
                subsets[v].parent=v;
                subsets[v].rank=0;
            }
            int i=0;
            // index to pick next edge

            while(i<edges.size()){
                Edge nextEdge=edges.get(i++);
                System.out.println("in kruskal method check for union option");
                int x=find(subsets, nextEdge.src);
                int y=find(subsets, nextEdge.dest);
                System.out.println("check x and y"+x+" "+y);
                if(x!=y){
                    a.add(nextEdge);
                    union(subsets, x, y);
                }
            }
            System.out.println("edge \t weight");
            for(Edge e:a){
                System.out.println(vertexNames[e.src]+"-"+vertexNames[e.dest]+"\t"+e.weight);
            }
        }
    }
    public static void main(String[] args) {
        graph g=new graph(7);
        g.addVertexName(0,"A");
        g.addVertexName(1,"B");
        g.addVertexName(2,"C");
        g.addVertexName(3,"D");
        g.addVertexName(4,"E");
        g.addVertexName(5,"F");
        g.addVertexName(6,"G");

        g.addEdge(0, 1, 4);
        g.addEdge(0, 6, 10);
        g.addEdge(0, 2, 9);
        g.addEdge(1, 2, 8);
        g.addEdge(2, 3, 5);
        g.addEdge(2, 4, 2);
        g.addEdge(2, 6, 7);
        g.addEdge(3, 4, 3);
        g.addEdge(3, 5, 7);
        g.addEdge(4, 6, 6);
        g.addEdge(5, 6, 11);

        g.kruskal();
    }
}
