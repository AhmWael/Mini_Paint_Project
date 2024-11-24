package frontend;

import backend.PaintEngine;

import javax.swing.*;
import java.awt.*;

class CanvasPanel extends JPanel {
    private PaintEngine paintEngine;

    public CanvasPanel(PaintEngine paintEngine) {
        this.paintEngine = paintEngine;
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintEngine.refresh(g);
    }
}
