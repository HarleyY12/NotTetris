import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class BrickLayout {


    private ArrayList<Brick> bricks;
    private int[][] brickLayout;
    private int cols;
    private ArrayList<Brick> fallingBricks = new ArrayList<>();

    public BrickLayout(String fileName, int cols, boolean dropAllBricks) {
        this.cols = cols;
        ArrayList<String> fileData = getFileData(fileName);
        bricks = new ArrayList<Brick>();
        for (String line : fileData) {
            String[] points = line.split(",");
            int start = Integer.parseInt(points[0]);
            int end = Integer.parseInt(points[1]);
            Brick b = new Brick(start, end);
            bricks.add(b);
        }
        brickLayout = new int[30][cols];
        if (dropAllBricks) {
            while (bricks.size() != 0) {
                doOneBrick();
            }
        }
    }


    public void doOneBrick() {
        if (bricks.size() == 0) return;
        Brick b = bricks.remove(0);
        int dropPosition = -1;
        boolean hitObstacle = false;


        for (int i = 0; i < brickLayout.length && !hitObstacle; i++) {
            boolean canPlace = true;
            for (int j = b.getStart(); j <= b.getEnd(); j++) {
                if (brickLayout[i][j] == 1) {
                    canPlace = false;
                }
            }
            if (canPlace) {
                dropPosition = i;
            } else {
                hitObstacle = true;
            }
        }
        if (dropPosition != -1) {
            for (int column = b.getStart(); column <= b.getEnd(); column++) {
                brickLayout[dropPosition][column] = 1;
            }
            b.setHeight(dropPosition);
        }
    }
    public void updateBricks() {
        if (bricks.size() > 0) {
            Brick next = bricks.remove(0);
            fallingBricks.add(next);
        }
        ArrayList<Brick> landedBricks = new ArrayList<>();
        for (Brick b : fallingBricks) {
            int row = b.getFallingRow();
            boolean canFall = true;
            if (row + 1 >= brickLayout.length) {
                canFall = false;
            }
            if (canFall) {
                for (int col = b.getStart(); col <= b.getEnd(); col++) {
                    if (brickLayout[row + 1][col] == 1) {
                        canFall = false;
                    }
                }
            }
            if (canFall) {
                b.moveDown();
            } else {
                int r = b.getFallingRow();
                for (int c = b.getStart(); c <= b.getEnd(); c++) {
                    brickLayout[r][c] = 1;
                }
                landedBricks.add(b);
            }
        }
        fallingBricks.removeAll(landedBricks);
    }
    public ArrayList<Brick> getActiveBricks() {
        return fallingBricks;
    }
    public ArrayList<String> getFileData(String fileName) {
        File f = new File(fileName);
        Scanner s = null;
        try {
            s = new Scanner(f);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found.");
            System.exit(1);
        }
        ArrayList<String> fileData = new ArrayList<String>();
        while (s.hasNextLine())
            fileData.add(s.nextLine());


        return fileData;
    }


    public void printBrickLayout() {
        for (int r = 0; r < brickLayout.length; r++) {
            for (int c = 0; c < brickLayout[0].length; c++) {
                System.out.print(brickLayout[r][c] + " ");
            }
            System.out.println();
        }
    }


    public boolean checkBrickSpot(int r, int c) {
        if (brickLayout[r][c] == 1) {
            return true;
        }
        else {
            return false;
        }
    }
}
