public class Map {
    private final char[][] grid;
    private final int width;
    private final int height;
    private int startRow;
    private int startColumn;

    public Map(char[][] grid) {
        this.grid = grid;
        this.height = grid.length;
        this.width = grid[0].length;
        findStart();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getStartRow() {
        return startRow;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public char getCell(int row, int column) {
        return grid[row][column];
    }

    public boolean isValidCell(int row, int column) {
        return row >= 0 && row < height && column >= 0 && column < width && grid[row][column] != '0';
    }

    private void findStart() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] == 'S') {
                    startRow = i;
                    startColumn = j;
                    return;
                }
            }
        }
    }
}
