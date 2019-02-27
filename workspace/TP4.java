

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author utilizador
 */
class TP4 {

    /**
     * @param args the command line arguments
     */
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
        Ordena(nu,di); 
        final long duration = System.nanoTime() - startTime;
        System.out.println(duration);
        //Imprime(di);
        }       
        
         
    }
    
    public static void Ordena(int nu, int di){
        
        Ponto tmp;
        int i,j;
        for(i=0;i<a.size();i++){
            tmp = a.get(i);
            for(j=i;(j>0)&&(tmp.getN()<a.get(j-1).getN()||tmp.getN()==a.get(j-1).getN());j--){
                
              if(tmp.getN()<a.get(j-1).getN()){
                  
                  Ponto aux = a.get(j-1);
                 
                  a.set((j-1),tmp);
                  a.set(j, aux);
                 
                  
              }
              if(tmp.getN()==a.get(j-1).getN()){
                  if(di>=2){
                    if(tmp.getD()<a.get(j-1).getD()){

                        Ponto aux = a.get(j-1);
                        a.set((j-1),tmp);
                        a.set(j, aux);

                        
                    }
                  }
                  if(tmp.getD()==a.get(j-1).getD()){
                      if(di>=3){
                         if(tmp.getT()<a.get(j-1).getT()){
                        
                            Ponto aux = a.get(j-1);
                            a.set((j-1),tmp);
                            a.set(j, aux);

                            
                           } 
                      }
                      
                  }
              }
                
            }
            
        }         
        
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
