import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class PaintPanel extends JPanel {
    /* -------------------
    CLASS VARIABLES
     ---------------------*/

    private final int downscaledSize = 28;  // MNIST DB is 28x28 px

    private int size;               //
    private int[][] coordinates;    // contains map of where user drew
    private boolean drawing;        // is user currently drawing?
    private int mouseX, mouseY;     // current mouse position
    private int drawSize;           // size of paint brush


    /* -------------------
    CONSTRUCTORS
     ---------------------*/

    public PaintPanel (int size) {
        this.size = size;
        this.coordinates = new int[size][size];
        this.drawing = false;
        this.drawSize = 2;

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


    /* -------------------
    METHODS
     ---------------------*/
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
            g.setColor(Color.BLACK);
            for (int i = 0; i < this.coordinates.length; i++) {
                for (int j = 0; j < this.coordinates[i].length; j++) {
                    if (this.coordinates[i][j] != 0) {
                        g.fillOval(i, j, this.drawSize, this.drawSize);
                    }
                }
            }
        }
    }

    public void reset () {
        for (int i = 0; i < this.coordinates.length; i++) {
            for (int j = 0; j < this.coordinates[i].length; j++) {
                this.coordinates[i][j] = 0;
            }
        }
    }

    public int[][] getCoordinates () {
        return this.coordinates;
    }

    public int[][] getCoordinatesDownscaled () {
        int[][] result = new int[this.size/2][this.size/2];

        double tmp;

        for (int i = 0; i < result.length; i++) {
            tmp = 0;
            for (int j = 0; j< result[i].length; j++) {
                tmp += this.coordinates[i*2][j*2];
                tmp += this.coordinates[i*2+1][j*2];
                tmp += this.coordinates[i*2][j*2+1];
                tmp += this.coordinates[i*2+1][j*2+1];
                tmp /= 4;
                result[i][j] = tmp >= 0.5 ? 1 : 0;
            }
        }

        return result;
    }
}
