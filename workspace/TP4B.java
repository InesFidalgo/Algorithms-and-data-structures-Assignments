

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author utilizador
 */
class TP4B {

    /**
     * @param args the command line arguments
     */
    static int tam;
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Ponto> a = new ArrayList<Ponto>();
    public static void main(String[] args) {
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
            p.SetN(n);
            if(di==3){
                int d = Integer.parseInt(numer[1]);
                int t = Integer.parseInt(numer[2]);
                p.SetD(d);
                p.SetT(t); 
                a.add(p);
                cnt++;
            }else if(di==2){
                int d = Integer.parseInt(numer[1]);
                p.SetD(d);
                a.add(p);
                cnt++;
                
            }else{
                a.add(p);
                cnt++;
            }
            
        }
        final long startTime = System.nanoTime();
        Ordena(di);
        final long duration = System.nanoTime() - startTime;
        System.out.println(duration);
        //Imprime(di);
               
        
         
    }
    
    public static void Ordena(int dim){
        tam = a.size();
        quicksort(0, tam - 1,dim);
    }
    
    public static void quicksort(int min, int max, int dim){
        int i = min, j = max;
        Ponto pivot = a.get(min + (max-min)/2);
        while (i <= j) {
            
            if(dim==1){
                while(a.get(i).getN()<pivot.getN()){
                    i++;
                }
                while ((a.get(j).getN() > pivot.getN())) {
                    j--;
                }
            }
            else if(dim==2){
                while((a.get(i).getN()<pivot.getN())||
                    ((a.get(i).getN()== pivot.getN())&&(a.get(i).getD()< pivot.getD()))){
                    i++;
                }
                while ((a.get(j).getN()>pivot.getN())||
                    ((a.get(j).getN()== pivot.getN())&&(a.get(j).getD()> pivot.getD()))) {
                    j--;
                }
            }
            else{
                while((a.get(i).getN() < pivot.getN())||
                    ((a.get(i).getN()== pivot.getN())&&(a.get(i).getD()< pivot.getD()))||
                    ((a.get(i).getN()== pivot.getN())&&(a.get(i).getD()== pivot.getD())&&(a.get(i).getT()<pivot.getT()))){
                    i++;
                }
                while((a.get(j).getN() > pivot.getN())||
                    ((a.get(j).getN()== pivot.getN())&&(a.get(j).getD()> pivot.getD()))||
                    ((a.get(j).getN()== pivot.getN())&&(a.get(j).getD()== pivot.getD())&&(a.get(j).getT()>pivot.getT()))){
                    j--;
                }
            }
            if (i <= j) {
                troca(i, j);
                i++;
                j--;
            }
        }
        if (min < j){
            quicksort(min, j,dim);
        }
          
        if (i < max){
            quicksort(i, max,dim);
        }
          
    }
    public static void troca(int i, int j) {
        Ponto aux = a.get(i);
        Ponto aux2 = a.get(j);
        a.set(j,aux);
        a.set(i, aux2);
    }
    
    
    public static void Imprime(int di){
        for(int i=0;i<a.size();i++){
            if(di==3){
            System.out.println(a.get(i).getN()+" "+a.get(i).getD()+" "+a.get(i).getT());}
            else if (di==2){
                System.out.println(a.get(i).getN()+" "+a.get(i).getD());}
            
            else if(di==1){
                System.out.println(a.get(i).getN());
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
