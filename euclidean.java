public class euclidean {
  public static int gcd(int a,int b){
    while(a!=b){
        if(a>b){
            a=a-b;
        }else{
            b=b-a;
        }

    }
    return a;
  }
// public static int gcd(int a,int b){
//     if(b==0){
//         return a;
//     }
//     return gcd(b,a%b);
// }
    // public static int gcd(int a,int b){
    //     while(b!=0){
    //         int remainder=a%b;
    //         a=b;
    //         b=remainder;
    //     }
    //     return a;
    // }
    public static void main(String[] args) {
        int a=120;
        int b=25;
        System.out.println(gcd(120,25));
    }
}