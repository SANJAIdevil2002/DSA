public class graph_1 {
    static class graph {
        private int[][] adj;
        private char[] ver;
        private int size;

        public graph(int size) {
            this.adj = new int[size][size];
            this.ver = new char[size];
            this.size = size;
        }

        public void addvertex(int i, char a) {
            if (i >= 0 && i < size) {
                ver[i] = a;
            }
        }
        public void addedge(int i,int j){
            if(i>=0&&i<size&&j>=0&&j<size){
                adj[i][j]=1;
                adj[j][i]=1;

            }
        }
        public void printVertex(){
            System.out.println("vertex data: ");
            for(int i=0;i<size;i++){
                System.out.println("vertex "+i+":"+ver[i]);
            }
        }
        public void printedge(){
            System.out.println("adjacency martix:");
            for(int i=0;i<size;i++){
                for(int j=0;j<size;j++){
                    System.out.print(adj[i][j]+" ");

                }
                System.out.println();
            }
        }
         

    }
    public static void main(String[] args) {
        graph a=new graph(4);
        a.addvertex(0,'A' );
        a.addvertex(1,'B' );
        a.addvertex(2,'C' );
        a.addvertex(3,'D' );

        a.addedge(0, 1);//A-B
        a.addedge(0, 2);//A-c
        a.addedge(0, 3);//A-d
        a.addedge(1, 2);//b-c
        a.printedge();
        a.printVertex();
        // traversal


        


    }

}
