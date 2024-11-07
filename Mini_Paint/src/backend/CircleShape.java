package backend;

import java.awt.*;
import java.util.Map;

public class CircleShape implements Shape{
    private Point position;
    private Map<String, Double> properties;
    private Color color;
    private Color fillColor;

    @Override
    public void setPosition(Point position) {
        this.position = position;
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setProperties(Map<String, Double> properties) {
        this.properties = properties;
    }

    @Override
    public Map<String, Double> getProperties() {
        return properties;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setFillColor(Color color) {
        this.fillColor = color;
    }

    @Override
    public Color getFillColor() {
        return fillColor;
    }

    @Override
    public void draw(Graphics canvas) {
        canvas.setColor(color);
        canvas.drawOval(position.x, position.y, properties.get("radius").intValue(), properties.get("radius").intValue());
    }
}
