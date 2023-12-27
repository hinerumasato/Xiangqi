package AI;

import enums.EColor;

public class Minimax extends AMoveAlgorithm {

    public Minimax() {
        super();
    }

    public Minimax(Node node) {
        super(node);
    }

    @Override
    public int move() {
        return minimax(node, DEPTH, MIN);
    }

    private int minimax(Node node, int depth, boolean isMax) {
        if(depth == 0) {
            return Heuristic.compute(node.getBoard());
        }
        else {
            if(isMax) {
                int bestValue = Integer.MIN_VALUE;
                for (Node neighbor : getFutureNodes(node.getBoard(), EColor.RED)) {
                    node.addNeighbor(neighbor);
                    int heuristic = Math.max(bestValue, minimax(neighbor, depth - 1, MIN));
                    neighbor.setHeuristicValue(heuristic);
                    if(heuristic > bestValue)
                        bestValue = heuristic;
                }
                return bestValue;
            } else {
                int wrostValue = Integer.MAX_VALUE;
                for (Node neighbor : getFutureNodes(node.getBoard(), EColor.BLACK)) {
                    node.addNeighbor(neighbor);
                    int heuristic = Math.min(wrostValue, minimax(neighbor, depth - 1, MAX));
                    neighbor.setHeuristicValue(heuristic);
                    if(heuristic < wrostValue) 
                        wrostValue = heuristic;
                }

                return wrostValue;
            }
        }
    }
    
}
