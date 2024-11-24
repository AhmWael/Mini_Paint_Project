package backend;

import java.awt.*;
import java.util.Map;

public class RectangleShape extends AbstractShape {

    @Override
    public void draw(Graphics canvas) {
        canvas.setColor(super.getColor());
        if(super.getFillColor() != null) {
            canvas.setColor(super.getFillColor());
            canvas.fillRect(super.getPosition().x, super.getPosition().y, super.getProperties().get("width").intValue(), super.getProperties().get("height").intValue());
        }
        canvas.drawRect(super.getPosition().x, super.getPosition().y, super.getProperties().get("width").intValue(), super.getProperties().get("height").intValue());
    }
}
