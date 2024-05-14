//Name : S.M.P.V.Hasindi
//UoW ID : w1986531
//IIT ID : 20221995

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Queue;

public class SlidingPuzzles {
    private static final int[][] directions = {
            {-1, 0}, // Up
            {1, 0},  // Down
            {0, -1}, // Left
            {0, 1}   // Right
    };

    private static final String[] direction_names = {"Up", "Down", "Left", "Right"};

    public static void main(String[] args) {
        try {
            Map map = MapParser.parseMapFromFile("input.txt");
            int startRow = map.getStartRow();
            int startColumn = map.getStartColumn();

            boolean[][] visited = new boolean[map.getHeight()][map.getWidth()];
            int[][] distance = new int[map.getHeight()][map.getWidth()];
            Queue<int[]> queue = new ArrayDeque<>();

            queue.offer(new int[]{startRow, startColumn});
            visited[startRow][startColumn] = true;
            distance[startRow][startColumn] = 0;

            int finishRow = -1;
            int finishColumn = -1;

            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                int row = current[0];
                int column = current[1];

                for (int direction = 0; direction < directions.length; direction++) {
                    int newRow = row + directions[direction][0];
                    int newColumn = column + directions[direction][1];

                    if (map.isValidCell(newRow, newColumn) && !visited[newRow][newColumn]) {
                        visited[newRow][newColumn] = true;
                        distance[newRow][newColumn] = distance[row][column] + 1;
                        queue.offer(new int[]{newRow, newColumn});

                        if (map.getCell(newRow, newColumn) == 'F') {
                            finishRow = newRow;
                            finishColumn = newColumn;
                            break;
                        }
                    }
                }
            }

            if (finishRow != -1 && finishColumn != -1) {
                printShortestPath(map, distance, startRow, startColumn, finishRow, finishColumn);
            } else {
                System.out.println("No valid path from S to F!");
            }
        } catch (IOException e) {
            System.err.println("Error reading input file!");
        }
    }

    private static void printShortestPath(Map map, int[][] distance, int startRow, int startColumn, int finishRow, int finishColumn) {
        int currentRow = finishRow;
        int currentColumn = finishColumn;
        int steps = distance[finishRow][finishColumn];

        System.out.println("Done!");
        for (int step = steps; step > 0; step--) {
            System.out.print(step+1 + ". Move " + getDirectionName(currentRow, currentColumn, distance, step));
            System.out.println(" to (" + currentRow + "," + currentColumn + ")");
            for (int[] direction : directions) {
                int newRow = currentRow - direction[0];
                int newColumn = currentColumn - direction[1];
                if (map.isValidCell(newRow, newColumn) && distance[newRow][newColumn] == step - 1) {
                    currentRow = newRow;
                    currentColumn = newColumn;
                    break;
                }
            }
        }
        System.out.println("1. Start at ("+startRow+","+startColumn+")");
    }
    private static String getDirectionName(int row, int column, int[][] distance, int step) {
        for (int direction = 0; direction < directions.length; direction++) {
            int newRow = row + directions[direction][0];
            int newColumn = column + directions[direction][1];
            if (isValidIndex(newRow, newColumn, distance) && distance[newRow][newColumn] == step - 1) {
                return direction_names[direction];
            }
        }
        return "";
    }

    private static boolean isValidIndex(int row, int column, int[][] array) {
        return row >= 0 && row < array.length && column >= 0 && column < array[0].length;
    }



}
