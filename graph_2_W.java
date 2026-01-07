public class graph_2_W {
    static class graph{
        private char ver[];
        private int [][]adj;
        private int size;
        graph(int size){
            this.ver=new char[size];
            this.adj=new int[size][size];
            this.size=size;
        }

        public void addvertex(int i,char j){
            if(i>=0&&i<=size){
                ver[i]=j;
            }
        }
        public void addedge(int i,int j,int w){
            if(i>=0 && j>=0 && i<size && j<size){
                adj[i][j]=w;
            }
        }
        public void printVertex(){
            System.out.println("vertex data:");
            for(int i=0;i<size;i++){
                System.out.println("vertex "+i+":"+ver[i]);
            }
        }

        public void printedge(){
            System.out.println("adjacency matrix");
            for(int []r:adj){
                for(int i:r){
                    System.out.print(i+" ");
                }
                System.out.println();
            }
            // System.out.println("print the vertex ");
            // for(int i=0;i<size;i++){
            //     System.out.println("vertex "+i+":"+ver[i]);
            // }
        }
    
    }
    public static void main(String[] args) {
        graph g=new graph(4);
        g.addvertex(0,'A');
        g.addvertex(1,'B');
        g.addvertex(2,'C');
        g.addvertex(3,'D');

        g.addedge(0, 1, 3);
        g.addedge(0, 2, 2);
        g.addedge(2, 1, 1);
        g.addedge(3, 0, 4);
        g.printVertex();
        g.printedge();


    }
    
}
