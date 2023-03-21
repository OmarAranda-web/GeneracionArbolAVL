package avlgraphviz;
import avl.ArbolAVL;
import javax.swing.JOptionPane;
public class AVLGraphviz {
    public static void main(String[] args) {
        generarArbol();
    }
    //Generamos un metodo para mandarlo a llamar en el metodo principal
    public static void generarArbol(){
        //Y en este creamos un árbol cuyos nodos contendrán solamente texto
        System.out.println();
        //Creamos un árbol cuyos nodos contendrán solamente numeros
        ArbolAVL arbol_numeros=new ArbolAVL();//Hciendo una intancia a la calse ArbolAVL para generar el arbol
        //Llenamos con información el árbol
        //Definiremos 2 variables que nos ayudaran a recolectar los nodos de nuestro arbol
        int o=1;
        int x=0;
        //solicitaremos Los Nodos por medio de un JOption Pane.
        x=Integer.parseInt(JOptionPane.showInputDialog("Ingrese Nodo"));
        arbol_numeros.insertar(x);//Y lo incertaremos en la clase nodoAVL en el metodo insertar que se encargara de poscisionar el nodo
        do {            
            o=Integer.parseInt(JOptionPane.showInputDialog("Quieres agregar otro nodo \n1.Si \n2.No"));//Preguntamos si quiere agregar otro nodo
            if (o==1) {
                x=Integer.parseInt(JOptionPane.showInputDialog("Ingrese Nodo"));
                arbol_numeros.insertar(x);
            }
        } while (o==1);
        //Una  vez que terminemos de preguntar los nodos 
        //Graficamos el árbol generando la imagen arbol_numeros.jpg
        arbol_numeros.graficar("arbol_numeros.jpg");
        //Y lo Mostraremos en una interfaz al usuario para que vea el resultado final
        Grafico graf= new Grafico();
        graf.show();
        //Imprimimos el contenido del árbol ordenado
        // arbol_numeros.inorden();
    }
}
