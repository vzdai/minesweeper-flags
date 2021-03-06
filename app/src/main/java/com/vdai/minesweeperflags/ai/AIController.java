package com.vdai.minesweeperflags.ai;

import com.vdai.minesweeperflags.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.Math.sqrt;

/**
 * Created by Vivian on 2017-06-11.
 */

public abstract class AIController {
    public abstract int getNextClick(List<Tile> tiles, List<Tile> actual);

    public int clickRandomTile(List<Tile> tiles) {
        int numEmpty = 0;
        for(Tile tile : tiles) {
            if (tile.getState().equals("unrevealed")) numEmpty++;
        }

        Random rand = new Random();
        int n = rand.nextInt(numEmpty);

        for(int i = 0; i < tiles.size(); i++) {
            if (tiles.get(i).getState().equals("unrevealed")) {
                if (n == 0) {
                    return i;
                }
                n--;
            }
        }

        return 0;
    }

    // returns a list of all the valid indices surrounding a position
    public List<Integer> findSurroundingIndices(List<Tile> tiles, int position) {
        int totalSize = tiles.size();
        int gridSize = (int) sqrt(totalSize);
        List<Integer> indices = new ArrayList<>();

        int checkPosition = position - gridSize - 1;
        if((checkPosition + 1) % gridSize != 0) { // if it is not at the left edge of the screen
            for(int i = 0; i < 3; i++) {
                if(checkPosition >= 0 && checkPosition < totalSize) {
                    indices.add(checkPosition);
                }
                checkPosition = checkPosition + gridSize;
            }
        }

        checkPosition = position - gridSize + 1;
        if(checkPosition % gridSize != 0) { // if it is not at the right edge of the screen
            for(int i = 0; i < 3; i++) {
                if(checkPosition >= 0 && checkPosition < totalSize) {
                    indices.add(checkPosition);
                }
                checkPosition = checkPosition + gridSize;
            }
        }

        checkPosition = position - gridSize;
        if(checkPosition >= 0 && checkPosition < totalSize) {
            indices.add(checkPosition);
        }

        checkPosition = position + gridSize;
        if(checkPosition >= 0 && checkPosition < totalSize) {
            indices.add(checkPosition);
        }

        return indices;
    }
}
