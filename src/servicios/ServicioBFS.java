package servicios;

import grafos.Arco;
import grafos.Grafo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ServicioBFS {

    private Grafo<?> grafo;

    public ServicioBFS(Grafo<?> grafo) {
        this.grafo = grafo;
    }

    public List<Integer> bfsForest() {
        ArrayList<Integer> lista = new ArrayList<>();
        LinkedList<Integer> fila = new LinkedList<>();
        Iterator<Integer> vertices = grafo.obtenerVertices();
        Iterator<Integer> adyacentes;

        while(vertices.hasNext()){ //O(V)
            Integer current = vertices.next();

            if(!lista.contains(current)){ //O(V)
                lista.add(current);
                fila.addLast(current);
            }
            while(!fila.isEmpty()){ //O(V)
                adyacentes = grafo.obtenerAdyacentes(fila.pop());
                while(adyacentes.hasNext()){ //O(A)
                    Integer adyacente = adyacentes.next();
                    if(!lista.contains(adyacente)){
                        lista.add(adyacente);
                        fila.addLast(adyacente);
                    }
                }
            }
        }

        return lista;
    }

}