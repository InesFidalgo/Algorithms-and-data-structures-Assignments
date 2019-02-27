
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Comparator;
import java.util.PriorityQueue;



class TP3MetaC {
    static No base = null;
    static No baseS = null;
    static MaxHeap maxHeap = new MaxHeap(10);
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        final long startTime = System.nanoTime();
    	while(sc.hasNextLine()){
           
           String a = sc.nextLine();
           String [] comando = a.split("\\s+");
           
           if(!a.isEmpty()){
               
               if ("CARREGA".equals(comando[0])){
                   
                   CARREGA();
                   
                   
                   

               }
               if("PESQUISA".equals(comando[0])){

                   if(SplayTree.PESQUISAST(comando[1].toLowerCase())==null){
                       if(PESQUISA(comando[1])==null){
                        System.out.println("PALAVRA NAO EXISTENTE");
                        }
                       else{

                       System.out.println(PESQUISA(comando[1]));
                        }
                   }
                   
                   else{

                       System.out.println(SplayTree.PESQUISAST(comando[1]));
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
               
               if("LISTA_MAIS_ACESSOS".equals(comando[0])){
                   SplayTree.LISTA_MAIS_ACESSOS();
                   System.out.println("FIM LISTA ACESSOS");
               }
               
               }
                
           }
           final long duration = System.nanoTime() - startTime;
            System.out.println(duration);

    }
   
    
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
            SplayTree.insereSplay(palavra, a.traducao);
            //BinaryHeap.Acrescenta(palavra);
            a.x=true;
            a.nconsultas++;
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
            focusno.nconsultas++;
            No no = DevolveNo(sol);
            SplayTree.insereSplay(palavra, no.traducao);
            MaxHeap.Insere(no);
            
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
            
            focusnode.nconsultas++;
            //System.out.println(focusnode.nconsultas);
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
            focusnode.nconsultas++;
            //System.out.println(focusnode.nconsultas);
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

    
    
   
    
    static class SplayTree
    {
        //adiciona quando é marcado ou pesquisado
        //criar nova arvore
        private static No header = new No(null,null);
        
        public static void insereSplay(String palavra, ArrayList<String> traducao ) {
            No n;
            int c;
            if (baseS == null) {
                baseS = new No(palavra, traducao);
                return;
            }
            n = new No(palavra, traducao);
            splayT(n.nconsultas);
            if ((c = palavra.compareTo(baseS.palavra)) == 0) {
                //ja existe
                return;
            }
            
            if (c < 0) {
                n.esquerdo = baseS.esquerdo;
                n.direito = baseS;
                baseS.esquerdo = null;
            } else {
                n.direito = baseS.direito;
                n.esquerdo = baseS;
                baseS.direito = null;
            }
            baseS = n;
        }
        
        private static void splayT(int n) {
            //usar em acessos sim, questão da ordem não é soluçao
            No l, r, t, y;
            t = baseS;
            l = r = header;
            header.esquerdo = header.direito = null;

            for (;;) {
                if (n<(t.nconsultas)) {
                    if (t.esquerdo == null) break;
                    if (n<t.esquerdo.nconsultas) {
                        y = t.esquerdo;                            
                        t.esquerdo = y.direito;
                        y.direito = t;
                        t = y;
                        if (t.esquerdo == null) break;
                    }
                    r.esquerdo = t;                                
                    r = t;
                    t = t.esquerdo;
                } else if (n>t.nconsultas) {
                    if (t.direito == null) break;
                    if (n>t.direito.nconsultas) {
                        y = t.direito;                           
                        t.direito = y.esquerdo;
                        y.esquerdo = t;
                        t = y;
                        if (t.direito == null) break;
                    }
                    l.direito = t;                              
                    l = t;
                    t = t.direito;
                } else {
                    break;
                }
            }
            l.direito = t.esquerdo;                                   
            r.esquerdo = t.direito;
            t.esquerdo = header.direito;
            t.direito = header.esquerdo;
            baseS = t;
        }
    
    
     public static  void LISTA_MAIS_ACESSOS(){
        MaxHeap.maxHeap();
        toString(maxHeap);
        
        return;
     }
     
    public static void toString(MaxHeap heap) {
      for(int k = 1; k < MaxHeap.Heap.length; k++){
          if(MaxHeap.Heap[k]!=null){
               System.out.println(MaxHeap.Heap[k].palavra);
               System.out.println("n acessos"+MaxHeap.Heap[k].nconsultas);
          }
         
      }
      return;
   }
        
    public static String PESQUISAST(String palavra){
        No focusno = baseS;
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
            
            focusno.nconsultas++;
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

    }
    
    public static class MaxHeap{

        private static No[] Heap;
        private static int size;
        private static int maxsize;

        public MaxHeap(int maxsize)
        {
            MaxHeap.maxsize = maxsize;
            MaxHeap.size = 0;
            Heap = new No[MaxHeap.maxsize + 1];
        }

        private static int Nopai(int pos)
        {
            return pos / 2;
        }

        private static int leftChild(int pos)
        {
            return (2 * pos);
        }

        private static int rightChild(int pos)
        {
            return (2 * pos) + 1;
        }

        private static boolean isLeaf(int pos)
        {
            if (pos >=  (size / 2)  &&  pos <= size)
            {
                return true;
            }
            return false;
        }

        private static void troca(int fpos,int spos)
        {
            No tmp;
            tmp = Heap[fpos];
            Heap[fpos] = Heap[spos];
            Heap[spos] = tmp;
        }

        private static void maxHeapify(int pos)
        {
            if (!isLeaf(pos))
            { 
                if ( Heap[pos].nconsultas < Heap[leftChild(pos)].nconsultas  || Heap[pos].nconsultas < Heap[rightChild(pos)].nconsultas)
                {
                    if (Heap[leftChild(pos)].nconsultas > Heap[rightChild(pos)].nconsultas)
                    {
                        
                        troca(pos, leftChild(pos));
                        maxHeapify(leftChild(pos));

                    }else
                    {
                        troca(pos, rightChild(pos));
                        maxHeapify(rightChild(pos));
                    }

                }

            }

        }

        public static void Insere(No elemento)
        {
            
            
            Heap[++size] = elemento;
            int current = size;
            int cnt =0;
            for(int j = 0;j<size;j++ ){
                if(Heap[j]!=null){
                if(Heap[j].palavra == elemento.palavra){
                    cnt++;
                }}
            }
            if(cnt==0){
            for(int i = 0; i<size;i++){
                if((Heap[current]!=null)&&(Heap[Nopai(current)]!=null)){
                    if((Heap[current].nconsultas > Heap[Nopai(current)].nconsultas)){
                        troca(current,Nopai(current));
                        current = Nopai(current);
                    }
                    else{
                        break;
                    }
                }
            }
            }
            
        }
        public static No removerNo(){

            No retirado = Heap[1];

            Heap[1] = Heap[size--]; 

            maxHeapify(1);

            return retirado;

        }
       

        public static void maxHeap()
        {
            for (int pos = (size / 2); pos >= 1; pos--)
            {
                maxHeapify(pos);

            }

        }
        public static void HeapSort(){
           for(int i =0;i< Heap.length;i++){
               if(Heap[i]!=null){
                   System.out.println(Heap[1].palavra);
                   removerNo();
               }
           }
           
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

