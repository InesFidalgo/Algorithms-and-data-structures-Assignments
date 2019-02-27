/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.ArrayList;
import java.util.Scanner;


class TP3MetaB {
    static No base = null; 
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        final long startTime = System.nanoTime();
    	while(sc.hasNextLine()){
           
           String a = sc.nextLine();
           String [] comando = a.split("\\s+");
           
           if(!a.isEmpty()){
               
               if ("CARREGA".equals(comando[0])){
                   
                   CARREGA();
                   final long finalc = System.nanoTime();
                   System.out.println(finalc);
                   
                   

               }
               if("PESQUISA".equals(comando[0])){

                   PESQUISA(comando[1].toLowerCase());
                   if(PESQUISA(comando[1])==null){
                       //System.out.println("PALAVRA NAO EXISTENTE");
                   }
                   else{

                       //System.out.println(PESQUISA(comando[1]));
                   }

                   
               }

               if("ACRESCENTA".equals(comando[0])){
                   String b = "";
                   for(int i=1;i<comando.length;i++){
                       if(i==1){
                           b+=comando[1];

                       }
                       else{
                           b+=" "+comando[i];

                       }
                   }
                   if(PESQUISA(comando[1])!= null){
                      System.out.println("PALAVRA JA EXISTENTE"); 
                   }
                   
                   else{
                       ACRESCENTA(b.toLowerCase());
                       System.out.println("PALAVRA ACRESCENTADA");
                   }
                   
                   
               }
               if("MARCA".equals(comando[0])){
                   MARCA(comando[1].toLowerCase());
                   
               }
               if("LISTA_MARCADAS".equals(comando[0])){
                   LISTA_MARCADAS(base);
                   System.out.println("FIM MARCADAS");
                   
               }
               if("LISTA_ALFANUM".equals(comando[0])){
                   LISTA_ALFANUM(base);
                   System.out.println("FIM LISTA");
                   
               }
              
               
               }
                
           }
        final long duration = System.nanoTime() - startTime;
        System.out.println(duration);
        //System.out.println(duration-finalc);

    }
    /*
    public static void updateAlturas(No... nos){
       for (No no : nos){
        if (no!=null){
            if( getAltura( no.esquerdo )>= getAltura( no.direito ) ){
                 no.altura = getAltura( no.esquerdo )+1;
                 System.out.println("altura:"+no.altura+ " "+no.palavra);
            }
            else{
                no.altura = getAltura( no.direito ) +1;
                System.out.println("altura:"+no.altura+ " "+no.palavra);
            }
        }
    
    }}*/
    
    public static void inserepalavra(String a) { 
        String [] linha = a.split("\\s+");
        String pt = linha[0].trim();
        ArrayList<String> traducao = new ArrayList<String>();
        for(int i = 1;i<linha.length;i++){
            traducao.add(linha[i].trim());
        }
 
        base = insere(pt,traducao, base); 
    }
    public static No insere(String palavra, ArrayList<String> traducao, No no){
        No novo = new No(palavra, traducao); 
        if(no==null){
            no = novo;
            
        }
        else {
            if(palavra.compareTo(no.palavra)<0){
                no.esquerdo = insere(palavra, traducao, no.esquerdo);
                

                if(getAltura(no.esquerdo)-getAltura(no.direito)==2){
                    if(palavra.compareTo(no.esquerdo.palavra)<0){
                        no = withLeftChild(no);
                    }
                    else{
                        no = doubleWithLeftChild(no);
                    }
                }
            }
            
            if(palavra.compareTo(no.palavra)>0){
                no.direito = insere(palavra,traducao,no.direito);


                if(getAltura(no.direito)-getAltura(no.esquerdo)==2){
                    if(palavra.compareTo(no.direito.palavra)>0){
                        no = withRightChild(no);
                    }
                    else{
                        no = doubleWithRightChild(no);
                    }
                }
            }

            
            if(getAltura(no.esquerdo)>= getAltura(no.direito)){
                no.SetAltura(getAltura(no.esquerdo)+1);
                
            }
            else{
                no.SetAltura(getAltura(no.direito)+1);
                
            }
            
        }
       
        
        return no;
    }
    
    
    public static void CARREGA() {
        
        String a=  sc.nextLine().toLowerCase();
        while(!a.equals("fim$dicionario")){
            if(PESQUISA(a)==null){
                inserepalavra(a);

            }
            else{
                System.out.println("PALAVRA JA EXISTENTE"); 
            }

        
            a =  sc.nextLine().toLowerCase();
                
        }
        
        
 
        System.out.println("DICIONARIO CARREGADO");
        return;
    }
    public static void ACRESCENTA(String a){
        inserepalavra(a);

    }
 
    public static void MARCA(String palavra){
        if(PESQUISA(palavra)== null){
            System.out.println("PALAVRA NAO EXISTENTE");
            return;
        }
        else{
            No a = DevolveNo(palavra);
            a.x=true;
            //System.out.println(palavra+" MARCADA");
            return;
        }
    }
 
 
    public static String PESQUISA(String palavra){
        No focusno = base;
        boolean a = false;
 
        if(focusno==null){
 
                a = false;
 
        }
  
        else{
        while((a==false)||(focusno.esquerdo!=null && focusno.direito!=null)){
 
 
            if(focusno.palavra==null){break;}
 
            if(focusno.palavra.compareTo(palavra)== 0){
 
                a = true;
                break;
            }
 
 
 
            if(focusno.palavra.compareTo(palavra) > 0 ){
 
                if(focusno.esquerdo!=null){
                    focusno = focusno.esquerdo;
                }
                else{
                    if(focusno.direito!=null){
                        focusno = focusno.direito;
                    }
                    else{
                        break;
                    }
                }
 
 
            }
            if(focusno.palavra.compareTo(palavra) < 0 ){
 
                if(focusno.direito!=null){
 
                    focusno = focusno.direito;
 
                }
                else{
 
                    break;
                    }
                }
 
            }
 
 
        }
        if(a==true){
            String sol = focusno.palavra;
                for(int i = 0;i< focusno.traducao.size();i++){
                    sol += " "+focusno.traducao.get(i);
 
                }
 
            return sol; 
        }
        else{
           return null;
        }
    }
 
    public static No DevolveNo(String palavra){
        No focusno = base;
        boolean a = false;
        while((a==false)||(focusno.esquerdo==null && focusno.direito==null)){
            if(focusno.palavra.compareTo(palavra)== 0){
                a = true;
                break;
            }
            if(focusno.palavra.compareTo(palavra) > 0 ){
 
                if(focusno.esquerdo!=null){
                    focusno = focusno.esquerdo;
                }
                else{
                    if(focusno.direito!=null){
                        focusno = focusno.direito;
                    }
                    else{
                        break;
                    }
                } 
            }
            if(focusno.palavra.compareTo(palavra) < 0 ){
 
                if(focusno.direito!=null){
 
                    focusno = focusno.direito;
 
                }
                else{
 
                    break;
                    }
                }
 
        }
        if(a==false){
            System.out.println("PALAVRA NAO EXISTENTE");
            return null;
        }
        else{
 
            return focusno;
        }
    }
 
    public static void LISTA_ALFANUM(No base){
        No focusnode = base;
 
        if(focusnode!=null){
            LISTA_ALFANUM(focusnode.esquerdo);
            System.out.println(focusnode.palavra.trim());
            LISTA_ALFANUM(focusnode.direito);
 
        }
        return;
 
    }
 
    public static void LISTA_MARCADAS(No base){
 
        No focusnode = base;
 
        if((focusnode!=null)){
            LISTA_MARCADAS(focusnode.esquerdo);
            if((focusnode.x==true)){
            System.out.println(focusnode.palavra.trim());}
            LISTA_MARCADAS(focusnode.direito);
 
        }
 
        return;
    }
 
 
    public static boolean Compara(No a, No b){
        if(a.palavra.compareTo(b.palavra)>0){
            return false;
        }
        else{
            return true;
        }
    }
 
    static class No{
        int nconsultas = 0;
        String  palavra;
        ArrayList<String> traducao;
        int altura;
        boolean x = false;
        No esquerdo;
        No direito;
        int fe;
 
        No(String palavra, ArrayList<String> traducao){
            this.palavra = palavra;
            this.traducao = traducao;
        }
        public void Setnconsultas(int a){
            this.nconsultas = a;
        }
        public int getNconsultas(){
            return nconsultas;
        }
        public void SetAltura(int a){
            this.altura = a;
        }
        public int getFe(){
            return fe;
        }
        public void setFe(int fe){
            this.fe = fe;
        }
        
       
    }
    

        public static No withLeftChild(No k1){
            
            No k2 = k1.esquerdo;
            k1.esquerdo = k2.direito;
            k2.direito = k1;
            
            
            if(getAltura(k1.esquerdo)>=getAltura(k1.direito)){
                k1.altura = getAltura(k1.esquerdo) +1;
                
            }
            else{
                k1.altura = getAltura(k1.direito) +1;
               
            }
            if(getAltura(k2.esquerdo)>=getAltura(k1)){
                k2.altura = getAltura(k2.esquerdo) +1;
               
            }
            else{
                k2.altura = getAltura(k1) +1;
               
            }
           
            return k2;
        }
        public static No withRightChild(No k1){
            
            No k2 = k1.direito;
            k1.direito = k2.esquerdo;
            k2.esquerdo = k1;
           
            
            if(getAltura(k1.esquerdo)>= getAltura(k1.direito)){
                k1.SetAltura(getAltura(k1.esquerdo) +1); 
                
            }
            else{
                k1.SetAltura(getAltura(k1.direito)+1);
                
            }
            
            if(getAltura(k2.direito)>=getAltura(k1)){
                k2.SetAltura(getAltura(k2.direito) +1);
               
            }
            else{
                k2.SetAltura(getAltura(k1) +1);
                
            }
            
            return k2;
        }
        public static No doubleWithLeftChild(No k1 ){
            
            k1.esquerdo = withRightChild(k1.esquerdo );
            
            return withLeftChild(k1);
        }
        
        public static No doubleWithRightChild(No k1){
            
            k1.direito = withLeftChild( k1.direito );
            
            return withRightChild( k1 );
        
        }
        
        public static int getAltura(No t ){
         return t == null ? -1 : t.altura;

        }
    
     
}