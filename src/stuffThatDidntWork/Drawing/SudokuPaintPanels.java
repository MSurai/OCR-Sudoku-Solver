class SudokuPaintPanels {
    /* ---------------------------
       CLASS VARIABLES
     * --------------------------- */
    private PaintPanel[][] paintPanels; // collection of paintPanels
    private int margin;                 // margin from top left corner on contentPane in px
    private int gap;                    // gap between the panels in px


    /* ---------------------------
       CONSTRUCTORS
     * --------------------------- */
    public SudokuPaintPanels (int margin, int gap) {
        paintPanels = new PaintPanel[9][9];
        this.margin = margin;
        this.gap = gap;

        createPanelInstances();
        setPanelBounds();
    }


    /* ---------------------------
       METHODS
     * --------------------------- */
    private void createPanelInstances () {
        for (int i = 0; i < paintPanels.length; i++)
            for (int j = 0; j < paintPanels[i].length; j++)
                paintPanels[i][j] = new PaintPanel();
    }

    private void setPanelBounds () {
        for (int i = 0; i < paintPanels.length; i++)
            for (int j = 0; j < paintPanels[i].length; j++)
                paintPanels[i][j].setBounds(j * gap + j * 56 + margin, i * gap + i * 56 + margin, 56, 56);
    }

    public PaintPanel[][] getPaintPanels () {
        return paintPanels;
    }

    public PaintPanel getPanel (int x, int y) {
        return paintPanels[y][x];
    }
}
