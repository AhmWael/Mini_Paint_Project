package backend;

import java.awt.*;

public interface DrawingEngine {
    /* Manage shape objects */
    public void addShape(Shape shape);
    public void removeShape(Shape shape);

    /* Return the created shapes objects */
    public Shape[] getShapes();

    /* Redraw all shapes on canvas */
    public void refresh(Graphics canvas);
}
