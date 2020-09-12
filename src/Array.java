import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Array {
    static class Range{
        int l;
        int r;

        Range(int l,int r){
            this.l = l;
            this.r = r;
        }
    }
    static class Sequence{
        int x;
        int y;
        int z;

        public Sequence(int x, int y, int z){
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    static class FastReader{
        BufferedReader br;
        StringTokenizer st;
        FastReader(){
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        String next(){
            while (st==null || !st.hasMoreElements()){
                try{
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
        int nextInt(){
            return Integer.parseInt(next());
        }
    }
    public static void main(String[] args) {
        FastReader fr = new FastReader();
        int n,m,k;
        n = fr.nextInt();
        m = fr.nextInt();
        k = fr.nextInt();
        int[] a = new int[n];
        int[] b = new int[n];
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = fr.nextInt();
            b[i] = fr.nextInt();
            c[i] = fr.nextInt();
        }
        Range[] ranges = new Range[3];

        for (int i = 0; i < 3; i++) {
            int l = fr.nextInt();
            int r = fr.nextInt();
            ranges[i]= new Range(l,r);
        }

        ArrayList<Sequence> arrayList = new ArrayList<>();
        boolean exists = generateSequence(n,m,k,a,b,c,ranges,arrayList);
        if (exists){
            Sequence x = arrayList.get(0);
            System.out.println(x.x+" "+x.y+" "+x.z);
        }
        else {
            System.out.println("-1");
        }

    }

    private static boolean generateSequence(int n, int m, int k, int[] a, int[] b, int[] c, Range[] ranges, ArrayList<Sequence> arrayList) {

        for (int i = ranges[0].l; i <=Math.min(m,ranges[0].r) ; i++) {
            for (int j = ranges[1].l;j <=Math.min(m,ranges[1].r) ; j++) {
                for (int l = ranges[2].l; l <=Math.min(ranges[2].r,m); l++) {
                    generateSequenceUtil(n,m,k,a,b,c, arrayList,i,j,l);
                    if (arrayList.size()==1){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static void generateSequenceUtil(int n, int m, int k, int[] a, int[] b, int[] c, ArrayList<Sequence> arrayList, int x, int y, int z) {

        int count = 0;
        for (int i = 0; i < n; i++) {
            if((x*a[i]%m+y*b[i]%m-z*c[i]%m)%m==0){
                count++;
            }
        }

        if (count==k){
            arrayList.add(new Sequence(x,y,z));
        }
    }
}
