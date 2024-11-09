package backend;

import java.awt.*;
import java.util.Map;

public abstract class AbstractShape implements Shape {
    private Point position;
    private Map<String, Double> properties;
    private Color color;
    private Color fillColor;

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }

    public void setProperties(Map<String, Double> properties) {
        this.properties = properties;
    }

    public Map<String, Double> getProperties() {
        return properties;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public void setFillColor(Color color) {
        this.fillColor = color;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public abstract void draw(Graphics canvas) ;
}
