package ca.bytetube._10_graph;

import java.util.List;

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

    public abstract void dfs(V begin, VertexVisitor<V> visitor);

    public abstract void bfs(V begin, VertexVisitor<V> visitor);

    public abstract void dfs(V begin);

    public abstract List<V> toPoLogicalSort(V begin);
    public interface VertexVisitor<V>{
        boolean visit(V v);

    }

}
