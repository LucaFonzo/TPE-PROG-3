package servicios;

import grafos.Grafo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ServicioDFS {

    private Grafo<?> grafo;

    public ServicioDFS(Grafo<?> grafo) {
        this.grafo = grafo;
    }

    /**
     * Complejidad: O(V^2*A) porque el anidamiento se interpreta como una
     * multiplicación mientras que los bucles secuenciales se interpretan como sumas:
     * O( V * ( V + A * V) ) = O(V^2 + V^2*A) = O(V^2*A).
     */
    public List<Integer> dfsForest() {
        ArrayList<Integer> lista = new ArrayList<>();
        Iterator<Integer> vertices = grafo.obtenerVertices();

        while(vertices.hasNext()){ // O(V)
            Integer current = vertices.next();
            dfsForest(current, lista);
        }

        return lista;
    }

    private void dfsForest(Integer vertice, ArrayList<Integer> lista){
        if(lista.contains(vertice)){ // O(V)
            return;
        }
        lista.add(vertice);
        Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(vertice);
        while(adyacentes.hasNext()){ // O(A)
            dfsForest(adyacentes.next(), lista); // O(V)
        }
    }
}