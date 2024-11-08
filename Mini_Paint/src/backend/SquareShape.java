package backend;

import java.awt.*;
import java.util.Map;

public class SquareShape implements Shape{
    private Point position;
    private Map<String, Double> properties;
    private Color color;
    private Color fillColor;
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

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
        if(fillColor != null) {
            canvas.setColor(fillColor);
            canvas.fillRect(position.x, position.y, properties.get("side").intValue(), properties.get("side").intValue());
        }
        canvas.drawRect(position.x, position.y, properties.get("side").intValue(), properties.get("side").intValue());
    }
}
