package ca.bytetube._10_graph;

import java.util.*;

public class ListGraph<V, E> extends Graph<V, E> {
    private Map<V, Vertex<V, E>> vertices = new HashMap<V, Vertex<V, E>>();

    private Set<Edge<V, E>> edges = new HashSet<Edge<V, E>>();

    private static class Vertex<V, E>{
        V value;
        Set<Edge<V, E>> inEdges = new HashSet<>();
        Set<Edge<V, E>> outEdges = new HashSet<>();

        public Vertex(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex<?, ?> vertex = (Vertex<?, ?>) o;
            return Objects.equals(value, vertex.value) && Objects.equals(inEdges, vertex.inEdges) && Objects.equals(outEdges, vertex.outEdges);
        }
        @Override
        public int hashCode() {
            return value == null ? 0 :value.hashCode();
        }

        @Override
        public String toString() {
            return value == null ? "null" : value.toString();
        }
    }

    private static class Edge<V, E>{
        E weight;
        Vertex<V, E> from;
        Vertex<V, E> to;

        public Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this.from = from;
            this.to = to;
            weight = null;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge<?, ?> edge = (Edge<?, ?>) o;
            return Objects.equals(weight, edge.weight) && Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
        }

        @Override
        public int hashCode() {
            return from.hashCode() * 31 + to.hashCode();
        }

        @Override
        public String toString() {
            return "Edge{" +
                    ", from=" + from +
                    ", to=" + to +
                    "weight=" + weight +
                    '}';
        }
    }

    @Override
    public int verticesSize() {
        return vertices.size();
    }

    @Override
    public int edgeSize() {
        return edges.size();
    }

    @Override
    public void addVertex(V v) {
        if (vertices.containsKey(v)) return;
        vertices.put(v, new Vertex<V, E>(v));
    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void addEdge(V from, V to, E weight) {
        //1.先判断from， to是否存在 不存在就创建
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            fromVertex = new Vertex<V, E>(from);
            vertices.put(from, fromVertex);
        }
        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) {
            toVertex = new Vertex<>(to);
            vertices.put(from, toVertex);
        }

        //代码能来到这里说明一定有起点终点
        //那么接下来， 需要确定起点和终点之前是否存在这条边， 不存在则新建一条边， 如果存在则更新weight，
        //不管之前存在与否， 都尝试删除这条边， 在重新建立新的边
        Edge<V, E> edge = new Edge<V, E>(fromVertex, toVertex);
        edge.weight = weight;
        if (fromVertex.outEdges.remove(edge)){
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        edges.add(edge);

    }


    @Override
    public void removeEdge(V from, V to) {
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) return;

        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) return;

        Edge<V, E> edge = new Edge<V, E>(fromVertex, toVertex);
        if (fromVertex.outEdges.remove(edge)){
            toVertex.inEdges.remove(edge);
            edges.remove(edge);
        }
    }


    @Override
    public void removeVertex(V v) {
        Vertex<V, E> vertex = vertices.remove(v);
        if (vertex == null) return;
//  不能对于collection （arraylist linked list stack deque queue, hashset)不能边遍历边删除 会出翔ConcurrentModificationException
//        需要用迭代器删除
//        for (Edge<V, E> edge : vertex.outEdges) {
//            removeEdge(edge.from.value, edge.to.value);
//            edges.remove(edge);
//        }
//
//        for (Edge<V, E> edge : vertex.inEdges) {
//            removeEdge(edge.to.value, edge.from.value);
//            edges.remove(edge);
//        } concurrent modification error 删除的是的后需要用iterator删除 避免dead lock 会将其他运行的发生冲突 会产生并发修改异常

        for (Iterator<Edge<V, E>> iterator = vertex.outEdges.iterator(); iterator.hasNext();){
            Edge<V, E> edge = iterator.next();
            edge.to.inEdges.remove(edge);
            iterator.remove();
            edges.remove(edge);
        }

        for (Iterator<Edge<V, E>> iterator = vertex.inEdges.iterator(); iterator.hasNext();){
            Edge<V, E> edge = iterator.next();
            edge.from.outEdges.remove(edge);
            iterator.remove();
            edges.remove(edge);
        }
    }

    @Override
    public void bfs(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;

        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();

        queue.add(beginVertex);
        visitedVertices.add(beginVertex);

        while (!queue.isEmpty()){

            Vertex<V, E> poll = queue.poll();
            System.out.print(poll.value + "");

            for (Edge<V, E> outEdge : poll.outEdges) {
                if (visitedVertices.contains(outEdge.to)) continue;
                queue.offer(outEdge.to);
                visitedVertices.add(outEdge.to);

            }

        }
    }

    @Override
    public void bfs(V begin, VertexVisitor<V> visitor) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;

        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();

        queue.add(beginVertex);
        visitedVertices.add(beginVertex);

        while (!queue.isEmpty()){
            Vertex<V, E> poll = queue.poll();
            if (visitor.visit(poll.value)) return;
            System.out.print(poll.value + "");

            for (Edge<V, E> outEdge : poll.outEdges) {
                if (visitedVertices.contains(outEdge.to)) continue;
                queue.offer(outEdge.to);
                visitedVertices.add(outEdge.to);

            }

        }
    }



// recursion edition for dfs

    public void dfs1(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;
        dfs1(beginVertex, new HashSet<>());
    }


    private void dfs1(Vertex<V, E> beginVertex, Set<Vertex<V, E>> visitedVertices) {

        System.out.print(beginVertex.value + " ");
        visitedVertices.add(beginVertex);
        for (Edge<V, E> outEdge : beginVertex.outEdges) {
            if (visitedVertices.contains(outEdge.to)) continue;
            dfs1(outEdge.to, visitedVertices);
        }
    }

    // dfs non-recursion edition

    /**
     * 1.先将起点放入stack中， 将起点从中弹出并打印， 并且存入set中
     * 2. 选择弹出顶点的的一条outage， 将这条的from to按顺序放入栈中
     * 3. 再将栈顶元素弹出并打印最终放入set中
     */
    @Override
    public void dfs(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;
        Stack<Vertex<V, E>> stack = new Stack<>();
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
//        1.先将起点放入stack中， 将起点从中弹出并打印， 并且存入set中
        stack.push(beginVertex);
        System.out.print(beginVertex.value + " ");
        visitedVertices.add(beginVertex);
        while (!stack.isEmpty()){
//            2. 选择弹出顶点的的一条outage， 将这条的from to按顺序放入栈中
            Vertex<V, E> vertex = stack.pop();
            for (Edge<V, E> edge : vertex.outEdges){
                if (visitedVertices.contains(edge.to)) continue;
                stack.push(edge.from);
                stack.push(edge.to);
                System.out.print(edge.to.value + " ");
                visitedVertices.add(edge.to);

                break;
            }
        }

    }


    @Override
    public void dfs(V begin, VertexVisitor<V> visitor) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return;
        Stack<Vertex<V, E>> stack = new Stack<>();
        Set<Vertex<V, E>> visitedVertices = new HashSet<>();
//        1.先将起点放入stack中， 将起点从中弹出并打印， 并且存入set中
        stack.push(beginVertex);
        System.out.print(beginVertex.value + " ");
        visitedVertices.add(beginVertex);
        while (!stack.isEmpty()){
//            2. 选择弹出顶点的的一条outage， 将这条的from to按顺序放入栈中
            Vertex<V, E> vertex = stack.pop();
            if (visitor.visit(vertex.value)) return;
            for (Edge<V, E> edge : vertex.outEdges){
                if (visitedVertices.contains(edge.to)) continue;
                stack.push(edge.from);
                stack.push(edge.to);
                System.out.print(edge.to.value + " ");
                visitedVertices.add(edge.to);

                break;
            }
        }
    }


    /**
     *
     * 1. 需要准备一个map 用来存储图的inDegree信息， 一个queue（缓存） 一个list（存储结果）
     * 2. 将graph中inDegree=0 的顶点放入queue， inDegree ！= 0的顶点放入map中
     * 3. 出队queue的队头原粗， 放入list中， 并且更新map中的inDegree信息
     * 4. 从map找到inDegree = 0 的顶点， 并放入queue中
     * 5. 不断重复3， 4 过程直到queue为空
     *
     */
    @Override
    public List<V> toPoLogicalSort(V begin) {
        //1. 需要准备一个map 用来存储图的inDegree信息， 一个queue（缓存） 一个list（存储结果）
        Map<Vertex<V, E>, Integer> map = new HashMap<>();
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        List<V> list = new LinkedList<>();

        //2. 将graph中inDegree=0 的顶点放入queue， inDegree ！= 0的顶点放入map中
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            int in = vertex.inEdges.size();
            if (in == 0) queue.offer(vertex);
            else map.put(vertex, in);
        });


        while (!queue.isEmpty()){
            //3. 出队queue的队头原粗， 放入list中， 并且更新map中的inDegree信息
            Vertex<V, E> vertex = queue.poll();
            list.add(vertex.value);
            for (Edge<V, E> edge :vertex.outEdges) {
                int toIn = map.get(edge.to) - 1;
                //4. 从map找到inDegree = 0 的顶点， 并放入queue中
                if (toIn == 0) queue.offer(edge.to);
                else map.put(edge.to, toIn);
            }
        }
        return list;
    }

}
