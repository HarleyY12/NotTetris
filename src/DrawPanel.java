import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.util.ArrayList;

public class DrawPanel extends JPanel implements MouseListener {

    private boolean[][] grid;
    private BrickLayout layout;
    public DrawPanel() {
        this.addMouseListener(this);
        layout = new BrickLayout("src/bricks", 40, false);

    }

    private void randomizeGrid() {
        grid = new boolean[30][40];
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length;c++) {
                int chance = (int)(Math.random()*10);
                if (chance < 3) {
                    grid[r][c] = true;
                }
            }
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 10;
        int y = 10;

        Graphics2D g2 = (Graphics2D)g;

        for (int c = 0; c < 40; c++) {
            for (int r = 0; r < 30; r++) {
                g.drawRect(x, y, 20, 20);
                if (layout.checkBrickSpot(r, c)) {
                    g.setColor(Color.RED);
                    g.fillRect(x, y, 20, 20);
                    g.setColor(Color.BLACK);
                }
                y += 25;
            }
            x += 25;
            y = 10;
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        layout.doOneBrick();
        repaint();    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
