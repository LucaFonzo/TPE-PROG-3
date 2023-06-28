package servicios;

import grafos.Grafo;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;


public class ServicioDFS {

    private Grafo<?> grafo;

    public ServicioDFS(Grafo<?> grafo) {
        this.grafo = grafo;
    }

    public List<Integer> dfsForest() {
        ArrayList<Integer> lista = new ArrayList<>();
        Iterator<Integer> vertices = grafo.obtenerVertices();
        Hashtable<Integer, Integer> visitados = new Hashtable<>();

        while(vertices.hasNext()){ // O(V)
            Integer current = vertices.next();
            dfsForest(current, lista, visitados);
        }

        return lista;
    }

    private void dfsForest(Integer vertice, ArrayList<Integer> lista, Hashtable<Integer, Integer> visitados){
        if(visitados.containsKey(vertice)){ // O(V)
            return;
        }
        lista.add(vertice);
        visitados.put(vertice, vertice);
        Iterator<Integer> adyacentes = grafo.obtenerAdyacentes(vertice);
        while(adyacentes.hasNext()){ // O(A)
            dfsForest(adyacentes.next(), lista, visitados);
        }
    }
}