package ca.bytetube._10_graph;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public abstract class Graph<V, E> {
    WeightManager<E> weightManager;
    public Graph(){
    }

    public Graph(WeightManager<E> weightManager) {
        this.weightManager = weightManager;
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

    public abstract Map<V, E> shortestPathWithoutPathInfos(V begin);

    public abstract Map<V, PathInfo<V, E>> shortestPath(V begin);

    public static class PathInfo<V, E>{
        protected E weight;
        protected List<EdgeInfo<V, E>> edgeInfos = new LinkedList<>();

        public PathInfo() {

        }

        public PathInfo(E weight) {
            this.weight = weight;
        }

        public E getWeight() {
            return weight;
        }

        public void setWeight(E weight) {
            this.weight = weight;
        }

        public List<EdgeInfo<V, E>> getEdgeInfos() {
            return edgeInfos;
        }

        public void setEdgeInfos(List<EdgeInfo<V, E>> edgeInfos) {
            this.edgeInfos = edgeInfos;
        }

        @Override
        public String toString() {
            return "PathInfo{" + "weight=" + weight + ", edgeInfos=" + edgeInfos + '}';
        }
    }

    public abstract Set<EdgeInfo<V, E>> mst();

    public static class EdgeInfo<V, E>{

        V from;
        V to;
        E weight;

        public EdgeInfo(V from, V to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }



        public V getFrom() {
            return from;
        }

        public void setFrom(V from) {
            this.from = from;
        }

        public V getTo() {
            return to;
        }

        public void setTo(V to) {
            this.to = to;
        }

        @Override
        public String toString() {
            return "EdgeInfo{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }

        public E getWeight() {
            return weight;
        }

        public void setWeight(E weight) {
            this.weight = weight;
        }


    }

    public interface VertexVisitor<V>{
        boolean visit(V v);

    }

    public interface WeightManager<E>{
        int compare(E w1, E w2);
        E add(E w1, E w2);
    }

}
