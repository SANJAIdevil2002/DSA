
public class Avl_1 {
    static class TreeNode {
        char value;
        TreeNode left,right;
        int height;
        public TreeNode(char value){
            this.value=value;
            height=1;
        }
    }

    TreeNode root;
    //calculate the height
    int height(TreeNode N){
        if(N==null) return 0;
        return N.height;
    }
    //calculate the balance
    int getBalance(TreeNode N){
        if(N==null) return 0;
        return height(N.right)-height(N.left);
        
    }

    public void inOrderTraversal(TreeNode N){
        if(N==null) return;
        inOrderTraversal(N.left);
        System.out.print(N.value+" ");
        inOrderTraversal(N.right);
    }
     public TreeNode rightRotation(TreeNode N){
        System.out.println("rightRotation on node "+N.value);
        TreeNode x=N.left;
        TreeNode y=x.right;

        x.right=N;
        N.left=y;

         N.height=Math.max(height(N.left), height(N.right))+1;
        x.height=Math.max(height(x.right),height(x.left))+1;

        return x;
       
     }
      public TreeNode leftRotation(TreeNode N){
        System.out.println("leftRotation on node "+N.value);
        TreeNode x=N.right;

        TreeNode y=x.left;

        x.left=N;
        N.right=y;

         N.height=Math.max(height(N.left), height(N.right))+1;
        x.height=Math.max(height(x.right),height(x.left))+1;

        return x;
       
     }

    public TreeNode insertNode(TreeNode N,char value){
        if(N==null) return new TreeNode(value);
        if(value<N.value){
            N.left=insertNode(N.left, value);
        }else if(value>N.value){
            N.right=insertNode(N.right, value);
        }else{
            //duplicate are not allowed
            return N;
        }
         N.height=1+Math.max(height(N.left),height(N.right));
        int balance=getBalance(N);
        //  unbalanced node and left child are left heavy then rightRotation needed
        if(balance< -1 &&getBalance(N.left)<=0){
          return rightRotation(N);
        }
     //  unbalanced node and right child are right heavy then leftRotation needed

        if(balance >1 && getBalance(N.right)>=0){
           return leftRotation(N);
        }

        if(balance <-1 && getBalance(N.left)>0){
            N.left=leftRotation(N.left);
            return rightRotation(N);
        }
        if(balance>1&&getBalance(N.right)<0){
            N.right=rightRotation(N.right);
            return leftRotation(N);
        }

        return N;
    }


    // delete the node
    // below to find the min value to merge the with before node
    public TreeNode minValue(TreeNode r){
        TreeNode temp=r;
        while(temp.left!=null){
            temp=temp.left;
        }
        return temp;

    }
    // delete
    public TreeNode delete(TreeNode r,char v){
        if(r==null){
            return r;
        }
        if(v<r.value){
            r.left=delete(r.left, v);
        }
        else if(v>r.value){
            r.right=delete(r.right, v);
        }
        else{
            //find the data
            if(r.left==null){
                return r.right;
            }
            if(r.right==null){
                return r.left;
            }else{
                //two node it have as child

                TreeNode t=r;
                t.value=minValue(t.right).value;
                t.right=delete(t.right, t.value);

            }
        }
        // if in tree have one node that have to delete, after delete root is null
        // if in tree have one node that have to delete, after delete root is null
        // if in tree have one node that have to delete, after delete root is null
    if(r==null) {
        return r;
     }
        r.height=1+Math.max(height(r.left),height(r.right));
        int balance=getBalance(r);
        // ll
        if(balance<-1&&getBalance(r.left)<=0){
            return rightRotation(r);
        }
        // rr
        if(balance>1&&getBalance(r.right)>=0){
            return leftRotation(r);
        }
        // left and right
        if(balance<-1&&getBalance(r.left)>0){
            r.left=leftRotation(r.left);
            return rightRotation(r);
        }
        // right and left
        if(balance>1&&getBalance(r.right)<0){
            r.right=rightRotation(r.right);
            return leftRotation(r);
        }
        return r;
    }

    public static void main(String[] args) {
        Avl_1 tree=new Avl_1();
        char [] letter={'C', 'B', 'E', 'A', 'D', 'H', 'G', 'F'};
        for(char ch:letter){
            tree.root=tree.insertNode(tree.root,ch);
        }
        tree.inOrderTraversal(tree.root);
        System.out.println();
        tree.root=tree.delete(tree.root,'A');
        System.out.println("after delete...");
                tree.inOrderTraversal(tree.root);


    }

}

// Rotate right on node H
// A, B, C, D, E, F, G, H, 