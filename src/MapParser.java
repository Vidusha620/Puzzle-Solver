import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MapParser {
    public static Map parseMapFromFile(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line = reader.readLine();
            int height = 0;
            char[][] grid = null;

            while (line != null) {
                if (grid == null) {
                    height = line.length();
                    grid = new char[height][line.length()];
                }
                for (int i = 0; i < line.length(); i++) {
                    grid[height - 1][i] = line.charAt(i);
                }
                height--;
                line = reader.readLine();
            }

            return new Map(grid);
        }
    }
}

