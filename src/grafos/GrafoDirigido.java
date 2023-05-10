package grafos;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GrafoDirigido<T> implements Grafo<T> {
    private HashMap<Integer, ArrayList<Arco<T>>> vertices;
    private int cantVertices;
    private int cantArcos;

    public GrafoDirigido(){
        this.vertices = new HashMap<>();
        this.cantVertices = 0;
        this.cantArcos = 0;
    }

    /**
     * Complejidad: en general, O(1) porque traduce el ID a una dirección de memoria
     * utilizando una función de hash.
     */
    @Override
    public void agregarVertice(int verticeId) {
        if(!vertices.containsKey(verticeId)) {
            vertices.put(verticeId, new ArrayList<Arco<T>>());
            cantVertices++;
        }
    }

    /**
     * Complejidad: O(V*A) donde V es vértices y A es arcos, pues
     * es necesario pasar por todos los vértices y por todos los arcos
     * para encontrar aquellos que deben ser borrados.
     */
    @Override
    public void borrarVertice(int verticeId) {
        if(vertices.containsKey(verticeId)){
            int arcosBorrados = vertices.get(verticeId).size();
            vertices.remove(verticeId);
            cantVertices--;
            cantArcos -= arcosBorrados;
            for(ArrayList<Arco<T>> arr : vertices.values()){
                for(Arco<T> arco : arr){
                    if(arco.getVerticeDestino() == verticeId){
                        arr.remove(arco);
                        cantArcos--;
                        break;
                    }
                }
            }
        }
    }

    /**
     * Complejidad: O(A) porque en el peor de los casos, un vértice tiene todos los arcos
     * y tiene que repasar uno por uno para chequear que no exista el arco que se quiere agregar. Esto es O(A).
     * Luego al añadir, en el peor de los casos el arraylist se queda sin memoria y tiene que reacomodar
     * su contenido, lo cual también es O(A), y da como resultado O(A) + O(A) = O(A).
     */
    @Override
    public void agregarArco(int verticeId1, int verticeId2, T etiqueta) {
        if(vertices.containsKey(verticeId1) && vertices.containsKey(verticeId2)){
            Arco<T> arco = new Arco<>(verticeId1, verticeId2, etiqueta);
            if(!vertices.get(verticeId1).contains(arco)){
                vertices.get(verticeId1).add(arco);
                cantArcos++;
            }
        }
    }

    /**
     * Complejidad: O(A) porque va a buscar la lista de adyacencia de un vértice en específico, que en el peor
     * de los casos tiene la totalidad de los arcos y el arco a borrar es el último de los arcos, por ende debe iterar
     * sobre todos los arcos para buscarlo. Los métodos containsKey() y get() de HashMap tienen complejidad O(1)
     * y no suman al resultado final.
     */
    @Override
    public void borrarArco(int verticeId1, int verticeId2) {
        if(vertices.containsKey(verticeId1)){
            ArrayList<Arco<T>> arr = vertices.get(verticeId1);
            for(Arco<T> arco : arr){
                if(arco.getVerticeDestino() == verticeId2){
                    arr.remove(arco);
                    cantArcos--;
                    break;
                }
            }
        }
    }

    /**
     * Complejidad: O(1) porque el HashMap transforma la id a una dirección de memoria usando la función de hasheo,
     * y automáticamente puede chequear si existe o no la entrada.
     */
    @Override
    public boolean contieneVertice(int verticeId) {
        return vertices.containsKey(verticeId);
    }

    /**
     * Complejidad: O(A) por el mismo motivo que el método borrarArco().
     */
    @Override
    public boolean existeArco(int verticeId1, int verticeId2) {
        if (!vertices.containsKey(verticeId1) || !vertices.containsKey(verticeId2)){
            return false;
        }
        for(Arco<T> arco : vertices.get(verticeId1)){
            if(arco.getVerticeDestino() == verticeId2){
                return true;
            }
        }
        return false;
    }

    /**
     * Complejidad: O(A) por el mismo motivo que el método borrarArco() y existeArco().
     */
    @Override
    public Arco<T> obtenerArco(int verticeId1, int verticeId2) {
        if (!vertices.containsKey(verticeId1) || !vertices.containsKey(verticeId2)){
            return null;
        }
        for(Arco<T> arco : vertices.get(verticeId1)){
            if(arco.getVerticeDestino() == verticeId2){
                return arco;
            }
        }
        return null;
    }

    /**
     * Complejidad: O(1) porque es acceder a un único valor en memoria.
     */
    @Override
    public int cantidadVertices() {
        return cantVertices;
    }

    /**
     * Complejidad: O(1) porque es acceder a un único valor en memoria.
     */
    @Override
    public int cantidadArcos() {
        return cantArcos;
    }

    /**
     * Complejidad: O(1) porque keySet() es O(1) e iterator() es O(1).
     */
    @Override
    public Iterator<Integer> obtenerVertices() {
        return vertices.keySet().iterator();
    }

    /**
     * Complejidad: O(A) porque el algoritmo va directamente a un vértice y guarda todos los arcos en
     * un array. En el peor de los casos, este vértice tiene todos los arcos.
     */
    @Override
    public Iterator<Integer> obtenerAdyacentes(int verticeId) {
        ArrayList<Integer> array = new ArrayList<>();
        for(Arco<T> arco : vertices.get(verticeId)){
            array.add(arco.getVerticeDestino());
        }
        return array.iterator();
    }

    /**
     * Complejidad: O(V*A) porque hay que pasar por cada vértice, y por cada vértice hay que guardar
     * todos los arcos con el método addAll() que tiene complejidad O(n) con n el tamaño de la colección
     * a añadir.
     */
    @Override
    public Iterator<Arco<T>> obtenerArcos() {
        ArrayList<Arco<T>> array = new ArrayList<>();
        for(ArrayList<Arco<T>> a : vertices.values()){
            array.addAll(a);
        }
        return array.iterator();
    }

    /**
     * Complejidad: O(A) porque en el constructor del ArrayList se le pasa por parámetro una colección y tiene
     * que copiar cada uno de los elementos de la colección, que es la lista de arcos del vértice con ID "verticeId".
     * Por ende, si en el peor caso dicho vértice tiene todos los arcos, la complejidad temporal es O(A).
     */
    @Override
    public Iterator<Arco<T>> obtenerArcos(int verticeId) {
        ArrayList<Arco<T>> array = new ArrayList<>(vertices.get(verticeId));
        return array.iterator();
    }

}