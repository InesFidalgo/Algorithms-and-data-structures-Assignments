/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author utilizador
 */

import java.util.ArrayList;
import java.util.Scanner;
 
class TP3MetaA {
    static No base = null; 
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        long startTime = System.nanoTime();
    	while(sc.hasNextLine()){
           
           String a = sc.nextLine();
           String [] comando = a.split("\\s+");
           
            if(!a.isEmpty()){
               
               if ("CARREGA".equals(comando[0])){
                   
                CARREGA();
                long finalc = System.nanoTime();
                System.out.println(finalc);
                   

               }
               if("PESQUISA".equals(comando[0])){

                   PESQUISA(comando[1].toLowerCase());
                   if(PESQUISA(comando[1])==null){
                       System.out.println("PALAVRA NAO EXISTENTE");
                   }
                   else{

                       System.out.println(PESQUISA(comando[1]));
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

                   ACRESCENTA(b.toLowerCase());
                   
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
        
        long stopTime = System.nanoTime();
        System.out.println(((stopTime - startTime)) +" nano segundos");
        //System.out.println(finalc);
        

    }
    public static void CARREGA() {
 
        String a=  sc.nextLine().toLowerCase();
 
        do{
            String [] linha = a.split("\\s+");
            String pt = linha[0].trim();
 
                ArrayList<String> traducao = new ArrayList<String>();
                for(int i = 1;i<linha.length;i++){
                    traducao.add(linha[i].trim());
                }
 
                No novo = new No(pt, traducao); 
                boolean acaba = true;
                if(base == null){
                    base = novo;
 
                }
                else{
                    if(PESQUISA(pt)==null){
                        No focusno = base;
                        No pai;
                        while(acaba == true){
                            pai = focusno;
 
                            if(Compara(novo, focusno)== true){
                                focusno = focusno.esquerdo;
                                if(focusno==null){
                                    pai.esquerdo = novo;
                                    acaba =  false;
                                }
                            }else{
                                focusno = focusno.direito;
                                if(focusno == null){
                                    pai.direito = novo;
                                    acaba =  false;
                                }
 
                            }
 
                        }
                    }
 
                else{
                    System.out.println("PALAVRA JA EXISTENTE");
                }
 
            }
            a =  sc.nextLine(); 
 
        }
        while(!a.equals("fim$dicionario"));
 
        System.out.println("DICIONARIO CARREGADO");
        return;
    }
    public static void ACRESCENTA(String a){
 
            String [] linha = a.split("\\s+");
            String pt = linha[0].trim();
            ArrayList<String> traducao = new ArrayList<String>();
            for(int i = 1;i<linha.length;i++){
                traducao.add(linha[i].trim());
            }
 
 
            No x = new No(pt, traducao); 
 
 
            boolean acaba = true;
            if(base == null){
                base = x;
                System.out.println("PALAVRA ACRESCENTADA");
            }
            else{
                    if(PESQUISA(pt)==null){
                        No focusno = base;
                        No pai;
                        while(acaba == true){
                            pai = focusno;
 
                            if(Compara(x, focusno)== true){
                                focusno = focusno.esquerdo;
                                if(focusno==null){
                                    pai.esquerdo = x;
                                    acaba =  false;
                                }
                            }else{
                                focusno = focusno.direito;
                                if(focusno == null){
                                    pai.direito = x;
                                    acaba =  false;
                                }
 
                            }
 
                        }
                        System.out.println("PALAVRA ACRESCENTADA");
                    }
 
                else{
                    System.out.println("PALAVRA JA EXISTENTE");
                }
 
            }
 
 
    }
 
    public static void MARCA(String palavra){
        if(PESQUISA(palavra)== null){
            System.out.println("PALAVRA NAO EXISTENTE");
            return;
        }
        else{
            No a = DevolveNo(palavra);
            a.x=true;
            System.out.println(palavra+" MARCADA");
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
        boolean x = false;
        No esquerdo;
        No direito;
 
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
    }
 
 
 
}