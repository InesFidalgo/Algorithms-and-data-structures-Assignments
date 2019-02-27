
import java.util.ArrayList;
import java.util.Scanner;

class TP4C {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Ponto> a = new ArrayList<Ponto>();
    public static void main(String[] args) {
        if(sc.hasNextLine()){
        String linha2 = sc.nextLine();
        String []cond = linha2.split("\\s+");
        int nu = Integer.parseInt(cond[0]);
        int di = Integer.parseInt(cond[1]);
        int cnt = 0;
        
        while((cnt<nu)){
            String linha = sc.nextLine();
            String [] numer = linha.split("\\s+");
            int n = Integer.parseInt(numer[0]);
            Ponto p = new Ponto();
            p.SetN(n+999);
            if(di==3){
                int d = Integer.parseInt(numer[1]);
                int t = Integer.parseInt(numer[2]);
                p.SetD(d+999);
                p.SetT(t+999); 
                a.add(p);
                cnt++;
            }else if(di==2){
                int d = Integer.parseInt(numer[1]);
                p.SetD(d+999);
                a.add(p);
                cnt++;
                
            }else{
                a.add(p);
                cnt++;
            }
            
        }
        RadixSort(di,nu);
        Imprime(di);
               
        }  
         
    }
    
    private static int MaximoC(int nu,int j) {
        int i;
        int max=0;
        
        if(j==1){
            max = a.get(0).getN();
            for(i = 0; i < nu; i++) {
                if(a.get(i).getN() > max) {
                    max = a.get(i).getN();
                }
            }
        }
        else if(j==2){
            max = a.get(0).getD();
            for(i = 0; i < nu; i++) {
                if(a.get(i).getD() > max) {
                    max = a.get(i).getD();
                }
            }
        }
        else if(j==3){
            max = a.get(0).getT();
            for(i = 0; i < nu; i++) {
                if(a.get(i).getT() > max) {
                    max = a.get(i).getT();
                }
            }
        }
        return max;
        
    }
    
    
    
    private static void RadixSort(int di, int nu){
      
        int i, maximo;
        Ponto[] aux = new Ponto[nu];
        int[] cnt= new int[10];
        int exp = 1;
        
        for(int j=di;j>0;j--){

            maximo = MaximoC(nu,j);
            
            while (maximo / exp > 0){
      
                if(j==1){
                    for(i = 0; i < 10; i++) {
                    cnt[i] = 0;
                    }
                    for (i = 0; i < nu; i++){
                        cnt[(a.get(i).getN() / exp) % 10]++;
                    }
                    for (i = 1; i < 10; i++){
                        cnt[i] += cnt[i - 1];
                    }
                    for (i = nu - 1; i >= 0; i--){
                        aux[ cnt[ (a.get(i).getN() / exp) % 10 ]  - 1] = a.get(i);
                        cnt[ (a.get(i).getN() / exp) % 10 ]--;
                    }

                    for (i = 0; i < nu; i++){
                        a.set(i, aux[i]);
                    }
                    exp *= 10;    
                }

                else if(j==2){
                    
                    for(i = 0; i < 10; i++) {
                    cnt[i] = 0;
                    }
                    for (i = 0; i < nu; i++){
                        cnt[(a.get(i).getD() / exp) % 10]++;
                    }
                    for (i = 1; i < 10; i++){
                        cnt[i] += cnt[i - 1];
                    }
                    for (i = nu - 1; i >= 0; i--){
                        aux[ cnt[ (a.get(i).getD() / exp) % 10 ]  - 1] = a.get(i);
                        cnt[ (a.get(i).getD() / exp) % 10 ]--;
                    }

                    for (i = 0; i < nu; i++){
                        a.set(i, aux[i]);
                    }
                    exp *= 10;    
                }
                
                else if(j==3){
                    
                    for(i = 0; i < 10; i++) {
                     cnt[i] = 0;
                    }
                    for (i = 0; i < nu; i++){
                        cnt[(a.get(i).getT() / exp) % 10]++;
                    }
                    for (i = 1; i < 10; i++){
                        cnt[i] += cnt[i - 1];
                    }
                    for (i = nu - 1; i >= 0; i--){
                        aux[ cnt[ (a.get(i).getT() / exp) % 10 ]  - 1] = a.get(i);
                        cnt[ (a.get(i).getT() / exp) % 10 ]--;
                    }

                    for (i = 0; i < nu; i++){
                        a.set(i, aux[i]);
                    }
                    exp *= 10;     
                }
                
                    
            }
            exp=1;
            
        }
    }
    
    
    
    public static void Imprime(int di){
        for(int i=0;i<a.size();i++){
            if(di==3){
            System.out.println((a.get(i).getN()-999)+" "+(a.get(i).getD()-999)+" "+(a.get(i).getT()-999));}
            else if (di==2){
                System.out.println((a.get(i).getN()-999)+" "+(a.get(i).getD()-999));}
            
            else if(di==1){
                System.out.println((a.get(i).getN()-999));
            }
        }
    }
    
    
    
    static class Ponto{
        int n;
        int d;
        int t;
        public void SetN(int n){
            this.n=n;  
        }
        public void SetD(int d){
            this.d=d;
        }
        public void SetT(int t){
            this.t=t;    
        }
        public int getN(){
            return n;
        }
        public int getD(){
            return d;
        }
        public int getT(){
            return t;
        }
    }
   
}
