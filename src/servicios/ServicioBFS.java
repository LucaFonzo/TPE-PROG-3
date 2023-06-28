package servicios;

import grafos.Arco;
import grafos.Grafo;

import java.util.*;

public class ServicioBFS {

    private Grafo<?> grafo;

    public ServicioBFS(Grafo<?> grafo) {
        this.grafo = grafo;
    }

    public List<Integer> bfsForest() {
        ArrayList<Integer> lista = new ArrayList<>();
        Hashtable<Integer, Integer> visitados = new Hashtable<>();
        LinkedList<Integer> fila = new LinkedList<>();
        Iterator<Integer> vertices = grafo.obtenerVertices();
        Iterator<Integer> adyacentes;

        while(vertices.hasNext()){
            Integer current = vertices.next();

            if(!visitados.containsKey(current)){
                lista.add(current);
                visitados.put(current, current);
                fila.addLast(current);
            }
            while(!fila.isEmpty()){
                adyacentes = grafo.obtenerAdyacentes(fila.pop());
                while(adyacentes.hasNext()){
                    Integer adyacente = adyacentes.next();
                    if(!visitados.containsKey(adyacente)){
                        lista.add(adyacente);
                        visitados.put(adyacente, adyacente);
                        fila.addLast(adyacente);
                    }
                }
            }
        }

        return lista;
    }

}