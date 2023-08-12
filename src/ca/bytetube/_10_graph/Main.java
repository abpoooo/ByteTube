package ca.bytetube._10_graph;

public class Main {
    public static void main(String[] args) {
        test1();
    }

    public static void test(){

    }

    public static void test2(){
//        unDirectGraph();
    }
    public static void test1(){
        Graph<String, Integer> graph = new ListGraph<>();
//        graph.addEdge("0", "1", null);
//        graph.addEdge("0", "4", null);
//        graph.addEdge("1", "2", null);
//        graph.addEdge("2", "0", null);
//        graph.addEdge("2", "4", null);
//        graph.addEdge("2", "5", null);
//        graph.addEdge("3", "1", null);
//        graph.addEdge("4", "6", null);
//        graph.addEdge("4", "7", null);
//        graph.addEdge("5", "7", null);
//        graph.addEdge("5", "3", null);
//        graph.addEdge("6", "2", null);
//        graph.addEdge("6", "7", null);
        graph.addEdge("a", "b", null);
        graph.addEdge("a", "e", null);
        graph.addEdge("b", "e", null);
        graph.addEdge("c", "b", null);
        graph.addEdge("d", "a", null);
        graph.addEdge("e", "f", null);
        graph.addEdge("e", "c", null);
        graph.addEdge("f", "c", null);


//        graph.bfs("0");
        graph.dfs("a");

    }
    private static Graph<Object, Double> directGraph(Object[][] data){
        Graph<Object, Double> graph = new ListGraph<>();
        for (Object[] edge : data) {
            if (edge.length == 1){
                graph.addVertex(edge[0]);
            }else if (edge.length == 2){
                graph.addEdge(edge[0], edge[1]);
            }else if (edge.length == 3){
                double weight = Double.parseDouble(edge[2].toString());

                graph.addEdge(edge[0], edge[1], weight);
            }
        }

        return graph;
    }

    private static Graph<Object, Double> unDirectGraph(Object[][] data){
        Graph<Object, Double> graph = new ListGraph<>();
        for (Object[] edge : data) {
            if (edge.length == 1){
                graph.addVertex(edge[0]);
            }else if (edge.length == 2){
                graph.addEdge(edge[0], edge[1]);
            }else if (edge.length == 3){
                double weight = Double.parseDouble(edge[2].toString());

                graph.addEdge(edge[0], edge[1], weight);
            }
        }

        return graph;
    }
}
