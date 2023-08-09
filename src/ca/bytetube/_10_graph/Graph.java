package ca.bytetube._10_graph;

public abstract class Graph<V, E> {
    public Graph(){
    }


    public abstract int verticesSize();

    public abstract int edgeSize();

    public abstract void addVertex(V v);

    public abstract void removeVertex(V v);

    public abstract void addEdge(V from, V to);

    public abstract void addEdge(V from, V to, E weight);

    public abstract void removeEdge(V from, V to);

    public abstract void bfs(V begin);

    public abstract void dfs(V begin);

}
