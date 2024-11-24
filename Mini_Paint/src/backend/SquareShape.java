package backend;

import java.awt.*;
import java.util.Map;

public class SquareShape extends AbstractShape {

    private String name;

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    @Override
    public void draw(Graphics canvas) {
        canvas.setColor(super.getColor());
        if(super.getFillColor() != null) {
            canvas.setColor(super.getFillColor());
            canvas.fillRect(super.getPosition().x, super.getPosition().y, super.getProperties().get("side").intValue(), super.getProperties().get("side").intValue());
        }
        canvas.drawRect(super.getPosition().x, super.getPosition().y, super.getProperties().get("side").intValue(), super.getProperties().get("side").intValue());
    }
}
