public class Brick {
    private int start;
    private int end;
    private int height;
    private int fallingRow;


    public Brick(int start, int end) {
        this.start = start;
        this.end = end;
        this.height = 0;
        this.fallingRow = 0;
    }


    public int getFallingRow() {
        return fallingRow;
    }


    public void setFallingRow(int fallingRow) {
        this.fallingRow = fallingRow;
    }


    public void moveDown() {
        this.fallingRow++;
    }
    public int getStart() {
        return start;
    }


    public void setStart(int start) {
        this.start = start;
    }


    public int getEnd() {
        return end;
    }


    public void setEnd(int end) {
        this.end = end;
    }


    public String toString() {
        return start + "," + end + " --> Height: " + height;
    }


    public void setHeight(int height) {
        this.height = height;
    }


    public int getHeight() {
        return height;
    }
}

