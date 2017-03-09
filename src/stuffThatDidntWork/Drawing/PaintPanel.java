import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Extension of JPanel that can be drawn on.
 */
class PaintPanel extends JPanel {
    /* ---------------------------
       CLASS VARIABLES
     * --------------------------- */
    private int mouseX;             // last x position of mouse
    private int mouseY;             // last y position of mouse
    private int[][] coordinates;    // previous x/y positions of mouse
    private int drawSize;           // size of drawing brush
    private boolean drawing;        // is user currently drawing?


    /* ---------------------------
       CONSTRUCTORS
     * --------------------------- */
    PaintPanel () {
        this.coordinates = new int[56][56]; // 28x28px * 2 for bigger size -> downscale later
        this.drawSize = 3;                  // size of drawing brush
        this.setBackground(Color.LIGHT_GRAY);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setMouseXY(e.getX(), e.getY());
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                setMouseXY(e.getX(), e.getY());
            }
        });
    }


    /* ---------------------------
       METHODS
     * --------------------------- */
    // mouse input was detected
    private void setMouseXY (int x, int y) {
        drawing = true;     // user is drawing now
        if ((mouseX != x) || (mouseY != y)) {   // update mouse coordinates
            mouseX = x;
            mouseY = y;
            repaint();      // calls paintComponent
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (drawing) {
            addToCoordinates(mouseX, mouseY);

            // draw on panel
            g.setColor(Color.BLACK);
            for (int i = 0; i < coordinates.length; i++)
                for (int j = 0; j < coordinates[i].length; j++)
                    if (coordinates[j][i] != 0)
                        //g.fillRect(i, j, drawSize, drawSize);
                        g.fillOval(i, j , drawSize, drawSize);
        }
        else {
            // resets panel coloring
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
    }

    private void addToCoordinates (int x, int y) {
        for (int i = 0; i < drawSize; i++)
            for (int j = 0; j < drawSize; j++)
                coordinates[y + i][x + j] = 255;
    }

    public void reset () {
        drawing = false;
        for (int i = 0; i < coordinates.length; i++)
            for (int j = 0; j < coordinates[i].length; j++)
                coordinates[i][j] = 0;
        repaint();
    }

    public int[][] getCoordinates () {
        return coordinates;
    }

    public int[][] getCoordinatesDownscaled () {
        int[][] result = new int[this.coordinates.length/2][this.coordinates.length/2];

        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j< result[i].length; j++) {
                result[i][j] += this.coordinates[i][j];
                result[i][j] += this.coordinates[i+1][j];
                result[i][j] += this.coordinates[i][j+1];
                result[i][j] += this.coordinates[i+1][j+1];
                result[i][j] /= 4;
            }
        }

        return result;
    }
}
