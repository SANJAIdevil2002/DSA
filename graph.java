class graph{
    public static void main(String []args){
        String [] vertexData={"A","B","C","D"};
        int [][] adjacencyMAtrix={{0,1,1,1},{1,0,1,0},{1,1,0,0},{1,0,0,0}};
        // print the vertex
        System.out.println("Vertex Data:");
        for(int i=0;i<vertexData.length;i++){
            
            System.out.print(vertexData[i]+" ");
        }
        System.out.println();
        printAdjacencyMatrix(adjacencyMAtrix);
        printconnectedvertix(adjacencyMAtrix,vertexData);
    }
    public static void printconnectedvertix(int [][]a,String []b){
        System.out.println("connected vertex");
        for(int i=0;i<b.length;i++){
             System.out.print(b[i]+": ");
            for(int j=0;j<b.length;j++){
               
                if(a[i][j]==1){
                    System.out.print(b[j]+" ");
                }
            }System.out.println();
        }
    }

    public static void printAdjacencyMatrix(int [][] a){
        System.out.println("Adjacency Matrix:");
        for(int row[]:a){
            for(int i:row){
                System.out.print(i+" ");
            }
            System.out.println();
        }
}}