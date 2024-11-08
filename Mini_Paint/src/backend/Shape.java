package backend;

import java.awt.*;
import java.util.*;

public interface Shape {
    public void setName(String name);
    public String getName();

    /* Set Position */
    public void setPosition(Point position);
    public Point getPosition();

    /* Set shape properties */
    public void setProperties(Map<String, Double> properties);
    public Map<String, Double> getProperties();

    /* Set color */
    public void setColor(Color color);
    public Color getColor();
    public void setFillColor(Color color);
    public Color getFillColor();

    /* Redraw the shape on the canvas */
    public void draw(Graphics canvas);

}
