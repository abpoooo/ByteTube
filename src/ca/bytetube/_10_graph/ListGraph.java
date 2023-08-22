package ca.bytetube._10_graph;

import java.util.*;

public class ListGraph<V, E> extends Graph<V, E> {
    private Map<V, Vertex<V, E>> vertices = new HashMap<V, Vertex<V, E>>();

    private Set<Edge<V, E>> edges = new HashSet<Edge<V, E>>();

    private Comparator<Edge<V, E>> edgeComparator = new Comparator<Edge<V, E>>() {
        @Override
        public int compare(Edge<V, E> o1, Edge<V, E> o2) {
            return weightManager.compare(o1.weight, o2.weight);
        }
    };



    private static class Vertex<V, E> {
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
            return value == null ? 0 : value.hashCode();
        }

        @Override
        public String toString() {
            return value == null ? "null" : value.toString();
        }
    }

    private static class Edge<V, E> {
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

        public EdgeInfo<V, E> info() {
            return new EdgeInfo(from, to, weight);
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
        if (fromVertex.outEdges.remove(edge)) {
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
        if (fromVertex.outEdges.remove(edge)) {
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

        for (Iterator<Edge<V, E>> iterator = vertex.outEdges.iterator(); iterator.hasNext(); ) {
            Edge<V, E> edge = iterator.next();
            edge.to.inEdges.remove(edge);
            iterator.remove();
            edges.remove(edge);
        }

        for (Iterator<Edge<V, E>> iterator = vertex.inEdges.iterator(); iterator.hasNext(); ) {
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

        while (!queue.isEmpty()) {

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

        while (!queue.isEmpty()) {
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
        while (!stack.isEmpty()) {
//            2. 选择弹出顶点的的一条outage， 将这条的from to按顺序放入栈中
            Vertex<V, E> vertex = stack.pop();
            for (Edge<V, E> edge : vertex.outEdges) {
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
        while (!stack.isEmpty()) {
//            2. 选择弹出顶点的的一条outage， 将这条的from to按顺序放入栈中
            Vertex<V, E> vertex = stack.pop();
            if (visitor.visit(vertex.value)) return;
            for (Edge<V, E> edge : vertex.outEdges) {
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
     * 1. 需要准备一个map 用来存储图的inDegree信息， 一个queue（缓存） 一个list（存储结果）
     * 2. 将graph中inDegree=0 的顶点放入queue， inDegree ！= 0的顶点放入map中
     * 3. 出队queue的队头原粗， 放入list中， 并且更新map中的inDegree信息
     * 4. 从map找到inDegree = 0 的顶点， 并放入queue中
     * 5. 不断重复3， 4 过程直到queue为空
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


        while (!queue.isEmpty()) {
            //3. 出队queue的队头原粗， 放入list中， 并且更新map中的inDegree信息
            Vertex<V, E> vertex = queue.poll();
            list.add(vertex.value);
            for (Edge<V, E> edge : vertex.outEdges) {
                int toIn = map.get(edge.to) - 1;
                //4. 从map找到inDegree = 0 的顶点， 并放入queue中
                if (toIn == 0) queue.offer(edge.to);
                else map.put(edge.to, toIn);
            }
        }
        return list;
    }



    @Override
    public Set<EdgeInfo<V, E>> mst() {
        return prim();
    }

    private Set<EdgeInfo<V, E>> prim() {
        //A mst 边集
        Set<EdgeInfo<V, E>> edgeInfo = new HashSet<>();


        //S mst 点集
        Set<Vertex<V, E>> addedVertices = new HashSet<>();

        Iterator<Vertex<V, E>> iterator = vertices.values().iterator();
        Vertex<V, E> vertex = iterator.next();
        addedVertices.add(vertex);
        //minHeap(vertex.outEdges, edgeComparator)
        //从堆顶去除最小节点
        //Edge<V, E> edge = minHeap.remove();
//        if (addedVertices.contains(edge.to)) continue;
        //AB ---> A mst 边集
        //edgeInfo.add(edge.info());
        ////B ---> B mst 点集
        // addedVertices.add(edge.to);

        //将所有B的out edges放入Heap中， 继续寻找边集中的最小crossing out edges
        //minHeap.addAll(edge.to.outedge);
        return edgeInfo;
    }

    private Set<EdgeInfo<V, E>> kruscal() {
        int edgeSize = edges.size() - 1;
        if (edgeSize == -1) return null;
        Set<EdgeInfo<V, E>> edgeInfo = new HashSet<>();
        // minHeap edges, edgeComparator

        //hub judge whether it will be a circle if added
        //UnionFind

        return edgeInfo;

    }

    //shortest path algorithm
    @Override
    public Map<V, E> shortestPathWithoutPathInfos(V begin) {
        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return null;

        Map<Vertex<V, E>, E> paths = new HashMap<>(); //红色容器
        Map<V, E> selectedPaths = new HashMap<>(); //绿色容器

        /**
         * Map<V, E> paths = new HashMap<>();
         * map.put("B", 10);
         * map.put("D", 30);
         * map.put("E", 100);
         *
         * 从 paths中找到最小权重， 即是第一个起飞点： 找到B点
         * 如果继续把B留在map中， 可能会被重复选择， 所以可能会选择删除起始点B
         * 因此我们需要再增加一个map用来存储已经走过的路径
         * */

        //1. 初始化paths：将B, D, E的当前的最短路径先装入map
        for (Edge<V, E> edge :beginVertex.outEdges){
            paths.put(edge.to, edge.weight);
        }

        while (!paths.isEmpty()){
            Map.Entry<Vertex<V, E>, E> minEntry = getMinPathWithoutPathInfos(paths); //("B", 10)

            Vertex<V, E> minVertex = minEntry.getKey();

            E minWeight = minEntry.getValue();
            selectedPaths.put(minVertex.value, minWeight); //B点起飞
            paths.remove(minVertex);


            //Relaxation
            //对起飞点所有的outEdges分别计算更新后的weight和老的weight， 然后进行大小比较
            //如果newWeight小于oldWeight，则更新paths
            //否则， 不用更新

            //对起飞点所有的outEdges分别计算更新后的weight
            for (Edge<V, E> edge : minVertex.outEdges){
                //计算newWeight
                E newWeight = weightManager.add(minEntry.getValue(), edge.weight);
                //计算oldWeight
                E oldWeight = paths.get(edge.to);
                if (oldWeight == null || weightManager.compare(newWeight, oldWeight) < 0){
                    paths.put(edge.to, newWeight);
                }

            }
        }



        return selectedPaths;
    }

    //返回值是map中的一个entry 即ending point 和 weight
    private Map.Entry<Vertex<V, E>, E> getMinPathWithoutPathInfos(Map<Vertex<V,E>,E> paths) {
        Iterator<Map.Entry<Vertex<V, E>, E>> iterator = paths.entrySet().iterator();
        Map.Entry<Vertex<V, E>, E> minEntry = iterator.next();
        while (iterator.hasNext()){
            Map.Entry<Vertex<V, E>, E> nextEntry = iterator.next();
            if (weightManager.compare(nextEntry.getValue(), minEntry.getValue()) < 0){
                minEntry = nextEntry;
            }
        }
        return minEntry;
    }


    @Override
    public Map<V, PathInfo<V, E>> shortestPath(V begin) {
        return dijkstra(begin);
    }

    public Map<V, PathInfo<V, E>> dijkstra(V begin) {

        Vertex<V, E> beginVertex = vertices.get(begin);
        if (beginVertex == null) return null;

        Map<Vertex<V, E>, PathInfo<V, E>> paths = new HashMap<>(); //红色容器
        Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>(); //绿色容器

        /**
         * Map<V, E> paths = new HashMap<>();
         * map.put("B", 10);
         * map.put("D", 30);
         * map.put("E", 100);
         *
         * 从 paths中找到最小权重， 即是第一个起飞点： 找到B点
         * 如果继续把B留在map中， 可能会被重复选择， 所以可能会选择删除起始点B
         * 因此我们需要再增加一个map用来存储已经走过的路径
         * */

        //1. 初始化paths：将B, D, E的当前的最短路径先装入map
        for (Edge<V, E> edge :beginVertex.outEdges){
            PathInfo<V, E> path = new PathInfo<>();
            path.weight = edge.weight;
            path.edgeInfos.add(edge.info());
            paths.put(edge.to, path);
        }

        while (!paths.isEmpty()){
            Map.Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = getMinPath(paths); //("B", 10)

            Vertex<V, E> minVertex = minEntry.getKey();
            PathInfo<V, E> minPath = minEntry.getValue();

            selectedPaths.put(minVertex.value, minPath); //B点起飞
            paths.remove(minVertex);


            //Relaxation
            //对起飞点所有的outEdges分别计算更新后的weight和老的weight， 然后进行大小比较
            //如果newWeight小于oldWeight，则更新paths
            //否则， 不用更新

            //对起飞点所有的outEdges分别计算更新后的weight
            for (Edge<V, E> edge : minVertex.outEdges){
//                计算newWeight
//                E newWeight = weightManager.add(minEntry.getValue(), edge.weight);
//                //计算oldWeight
//                E oldWeight = paths.get(edge.to);
//                if (oldWeight == null || weightManager.compare(newWeight, oldWeight) < 0){
//                    paths.put(edge.to, newWeight);
//                }
                if (selectedPaths.containsKey(edge.to.value)) continue;
                dijkstraRelaxation(edge, paths, minPath);
            }
        }
        return selectedPaths;
    }

    private void dijkstraRelaxation(Edge<V, E> edge, Map<Vertex<V, E>, PathInfo<V, E>> paths, PathInfo<V, E> minPath){
        //                计算newWeight
                E newWeight = weightManager.add(minPath.weight, edge.weight);
                PathInfo<V, E> oldPath = paths.get(edge.to);
                if (oldPath == null || weightManager.compare(newWeight, oldPath.weight) < 0){
                    PathInfo<V,E> path = new PathInfo<>();
                    path.weight = newWeight;
                    path.edgeInfos.addAll(minPath.edgeInfos);
                    path.edgeInfos.add(edge.info());
                    paths.put(edge.to, path);
                }

    }
    private Map.Entry<Vertex<V, E>, PathInfo<V, E>> getMinPath(Map<Vertex<V, E>, PathInfo<V, E>> paths) {
        Iterator<Map.Entry<Vertex<V, E>, PathInfo<V, E>>> iterator = paths.entrySet().iterator();
        Map.Entry<Vertex<V, E>, PathInfo<V, E>> minEntry = iterator.next();
        while (iterator.hasNext()){
            Map.Entry<Vertex<V, E>, PathInfo<V, E>> nextEntry = iterator.next();
            if (weightManager.compare(nextEntry.getValue().weight, minEntry.getValue().weight) < 0){
                minEntry = nextEntry;
            }
        }
        return minEntry;
    }

}
