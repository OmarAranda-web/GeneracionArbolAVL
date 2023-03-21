package avl;

/*
 Clase que representa un árbol AVL.
 */
public class ArbolAVL {
    //Declaramos una variable Que alacenara los el nodo raiz del arbol
    private NodoAVL raiz;
    // Constructor de la clase, incialmente la raíz es nula porque el árbol está vacío.
    public ArbolAVL() {
        raiz=null;
    }
    /*
     Método que se encarga de insertar un valor en el árbol AVL.
     @param 'valor' Valor específico que se desea insertar.
    */
    public void insertar(Comparable valor) {
        raiz = insertar(valor,raiz);
    }
    /*
     El Método que genera la imagen del árbol binario de búsqueda en la ruta 
     que se le indica en la clase NodoAVL @param path Ruta específica en la que se guardará la imagen generada.
     que practicamente es la ruta en la que se encuentra el proyecto 
     */
    public void graficar(String path) {
        raiz.graficar(path);
    }
    // Este método se encarga de imprimir el recorrido inorden del árbol binario de búsqueda, haciendo 
    //referencia la metodo inorden privado que se encarga de acomodar los valores en la terminal
    public void inorden(){
        System.out.println("Recorrido inorden del árbol binario de búsqueda:");
        inorden(raiz);
        System.out.println();
    }
    /*
      El método privado de inorden que ejecuta la tarea de hacer un recorrido inorden del árbol binario de búsqueda.
      @param a Nodo específico que se recorrerá conforme el método se llama recursivamente.
     */
    private void inorden(NodoAVL a){
        if(a==null)
            return;
        inorden(a.izquierdo);
        System.out.print(a.valor+",");
        inorden(a.derecho);
    }
    /*
     El siguient método se encarga de insertar un nuevo nodo en el árbol.
     que llevara los siguientes parametros:
     -->@param valor
     -->@param raiz
    y el tipo de retorno es del tipo Nodo AVL
     @return 
     */
    private NodoAVL insertar(Comparable valor, NodoAVL raiz){
        //Si en nodo recibido fuera nulo entonces el nuevo nodo se puede insertar 
        //en esa posición y se terminan las llamadas recursivas a este método.
        if(raiz == null){
            raiz = new NodoAVL(valor);//Se Inserta el nuevo nodo 
        //Si el nuevo valor fuera menor que el nodo de actual entonces
        }else if(valor.compareTo(raiz.valor) < 0){
            //Se llama recursivamente al método para explorar el subarbol izquierdo
            //porque el valor a insertar es menor que el del nodo actual.
            raiz.izquierdo = insertar(valor, raiz.izquierdo);            
            if(altura(raiz.derecho)-altura(raiz.izquierdo) == -2)
                //Si el factor de equilibrio esta desbalanceado, hay que hacer 
                //rotación de nodos, como el fe(factor de Equilibrio)=-2 hay dos posibilidades de 
                //rotación dependiendo de:
                if(valor.compareTo(raiz.izquierdo.valor) < 0)
                    //Si el nuevo valor fuera menor que la izquierda del nodo des-
                    //balanceado, se sabe que el nuevo nodo será insertado a la 
                    //izquierda de la actual izquierda, entonces tenemos una rotación 
                    //simple por la izquierda o sea una IzquierdaIzquierda.
                    raiz = IzquierdaIzquierda(raiz);
                else
                    //de lo contrario, se sabe que el nuevo nodo será insertado 
                    //a la derecha del la actual izquierda, por lo que se tiene 
                    //un caso de rotación doble por la izquierda 
                    //o sea una IzquierdaDerecha.
                    raiz = IzquierdaDerecha(raiz);
        }
        else if(valor.compareTo(raiz.valor)>0)
        //Si el nuevo valor fuera mayor que el nodo de la actual entonces:
        {
            //Se llama recursivamente al método para explorar el subarbol derecho
            //porque el valor a insertar es mayor que el del nodo actual.            
            raiz.derecho=insertar(valor, raiz.derecho);            
            if(altura(raiz.derecho)-altura(raiz.izquierdo) == 2)
                //Si el factor de equilibrio esta desbalanceado, hay que hacer 
                //rotación de nodos, como el fe=2 hay dos posibilidades de 
                //rotación dependiendo de:                
                if(valor.compareTo(raiz.derecho.valor) > 0)
                    //Si el nuevo valor fuera mayor que la derecha del nodo des-
                    //balanceado, se sabe que el nuevo nodo será insertado a la 
                    //derecha de la actual derecha, entonces tenemos una rotación 
                    //simple por la derecha o sea una DerechaDerecha.                    
                    raiz = DerechaDerecha(raiz);
                else
                    //de lo contrario, se sabe que el nuevo nodo será insertado 
                    //a la izquierda del la actual derecha, por lo que se tiene 
                    //un caso de rotación doble por la derecha
                    //o sea una DerechaIzquierda.
                    raiz = DerechaIzquierda(raiz);
        }
        else  
        // De lo contrario signifca que el valor que se quiere insertar ya existe, 
        //como no se permite la duplicidad de este dato no se hace nada.
        System.err.println("No se permiten los valores duplicados: \"" 
                +  String.valueOf(valor)+"\".");        
        
        //finalmente, por cada llamada recursiva debe hacerse una reasignacion 
        //de la altura esta se hará hasta para los nodos que no cambiaron de nivel 
        //en el transcurso porque no hay forma de saber cuales cambiaron de nivel 
        //y cuales no. La altura,será la altura del hijo que tiene
        //la altura más grande, es decir, la rama mas profunda, más 1.
        raiz.altura = mayor(altura(raiz.izquierdo), altura(raiz.derecho))+1;
        return raiz;
    }
    /**
     Este método nos devuelve la altura de un nodo.
     @param nodo
     @return 
     */
    private int altura( NodoAVL nodo )
    {
        //El cual si el nodo es igual nullo tentra un nivel menos
        if(nodo==null)
            return -1;
        else//En caso de que no nos retornara la altura del ultimo nodo
            return nodo.altura;
    }
    /*
     Este método recibira como parámetros dos numeros y devuelve el mayor.
     @param n1
     @param n2
     @return 
     El cual nos ayudara si hacer rotacion a la izquierda o a la derecha
    */
    private int mayor(int n1, int n2)
    {
        if(n1 > n2)
            return n1;
        return n2;
    }
    /*
     Este metodo se encarfara de realizar
        Rotación simple izquierda izquierda para el proceso de balanceo.
     @param n1
     @return 
    */
    private NodoAVL IzquierdaIzquierda(NodoAVL n1){
        NodoAVL n2 = n1.izquierdo;//Cambiaremos los lugares del los numeros el nuevo pasara a la izquierda
        n1.izquierdo = n2.derecho;//Y el anterior a a la derecha siempre y cuando el segundo sea mayor
        n2.derecho = n1;
        n1.altura = mayor(altura(n1.izquierdo), altura(n1.derecho))+1;
        n2.altura = mayor(altura(n2.izquierdo), n1.altura)+1;
        return n2;
    }
    /*
      Este otro metodo se encarfara de la
      Rotación simple derecha derecha para el proceso de balanceo.
      Y en este caso se Realizara el mismo proce que el anterior solo que en vez de realizar la rotacion a la 
      Izquierda sera a la derecha ahora
     */
    private NodoAVL DerechaDerecha( NodoAVL n1 ){
        NodoAVL n2 = n1.derecho;
        n1.derecho = n2.izquierdo;
        n2.izquierdo = n1;
        n1.altura = mayor(altura(n1.izquierdo), altura(n1.derecho))+1;
        n2.altura = mayor(altura(n2.derecho), n1.altura)+1;
        return n2;
    }
    /*
     En este caso es el metodo de rotación doble izuquierda derecha para el proceso de balanceo.
     Y en este caso se hara uso del metodo derecha derecha solo que con recursividad para realizar la doble rotacion
    */
    private NodoAVL IzquierdaDerecha(NodoAVL n1){
        n1.izquierdo = DerechaDerecha(n1.izquierdo);
        return IzquierdaIzquierda(n1);
    }
    /*
      Y en este caso se hace rotación doble derecha izquierda para el proceso de balanceo.
     */
    private NodoAVL DerechaIzquierda(NodoAVL n1 ){
        n1.derecho = IzquierdaIzquierda(n1.derecho);
        return DerechaDerecha(n1);
    }       
}