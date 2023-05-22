import grafos.Arco;
import grafos.GrafoDirigido;
import servicios.ServicioBFS;
import servicios.ServicioCaminos;
import servicios.ServicioDFS;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

        GrafoDirigido grafo = new GrafoDirigido();

        //Añadir vertices
        grafo.agregarVertice(1);
        grafo.agregarVertice(2);
        grafo.agregarVertice(3);
        grafo.agregarVertice(4);
        grafo.agregarVertice(5);
        grafo.agregarVertice(6);
        grafo.agregarVertice(7);
        grafo.agregarVertice(8);
        grafo.agregarVertice(9);
        System.out.println("Cant vertices: "  + grafo.cantidadVertices());

        //Añadir arcos
        grafo.agregarArco(1,5,0);
        grafo.agregarArco(1,2,0);
        grafo.agregarArco(2,3,0);
        grafo.agregarArco(3,5,0);
        grafo.agregarArco(3,4,0);
        grafo.agregarArco(2,6,0);
        grafo.agregarArco(3,7,0);
        grafo.agregarArco(7,8,0);
        grafo.agregarArco(8,9,0);
        grafo.agregarArco(5,9,0);
        grafo.agregarArco(4,2,0);
        System.out.println("Cant arcos: " + grafo.cantidadArcos() + "\n");

        //Borrar un vertice
        System.out.println("Borrar vertice 1");
        grafo.borrarVertice(1);
        System.out.println("Cant vertices: " + grafo.cantidadVertices());
        System.out.println("Cant arcos: " + grafo.cantidadArcos() + "\n");

        //Borrar un arco
        System.out.println("Borrar arco 2-3");
        grafo.borrarArco(2,3);
        System.out.println("Cant arcos: " + grafo.cantidadArcos() + "\n");

        //Contiene Vertice
        System.out.println("Contiene vertice 1: " + grafo.contieneVertice(1));
        System.out.println("Contiene vertice 2: " + grafo.contieneVertice(3));

        //Contiene Arco
        System.out.println("Existe arco 1-5: " + grafo.existeArco(1,5));
        System.out.println("Existe arco 3-5: " + grafo.existeArco(3,5));

        // Obtener un arco
        System.out.println("Obtener un arco, el 3-5: " + grafo.obtenerArco(3,5) + "\n");

        //Obtener adyacentes
        System.out.println("Obtener adyacentes al 3");
        Iterator<Integer> iteratorAdyacentes = grafo.obtenerAdyacentes(3);
        while (iteratorAdyacentes.hasNext()){
            System.out.println("Adyacente: " + iteratorAdyacentes.next());
        }
        System.out.println("\n");

        //Obtener arcos
        System.out.println("Obtener todos los arcos");
        Iterator<Arco> iteratorArcos = grafo.obtenerArcos();
        while (iteratorArcos.hasNext()){
            System.out.println("Arco: " + iteratorArcos.next());
        }
        System.out.println("\n");

        //Obtener arcos por id
        System.out.println("Obtener arcos por ID");
        Iterator<Arco> iteratorArcosPorID = grafo.obtenerArcos(3);
        while (iteratorArcosPorID.hasNext()){
            System.out.println("Arco: " + iteratorArcosPorID.next());
        }
        System.out.println("\n");

        System.out.println("Recorrido BFS");
        ServicioBFS bfs = new ServicioBFS(grafo);
        System.out.println(bfs.bfsForest());

        System.out.println("Recorrido DFS");
        ServicioDFS dfs = new ServicioDFS(grafo);
        System.out.println(dfs.dfsForest());


    }
}