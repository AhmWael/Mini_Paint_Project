package backend;

import java.awt.*;
import java.util.Map;

public class LineSegmentShape extends AbstractShape {

    @Override
    public void draw(Graphics canvas) {
        canvas.setColor(super.getColor());
        if(super.getFillColor() != null) {
            canvas.setColor(super.getFillColor());
            canvas.drawLine(super.getPosition().x, super.getPosition().y, super.getProperties().get("x2").intValue(), super.getProperties().get("y2").intValue());
        }
        canvas.drawLine(super.getPosition().x, super.getPosition().y, super.getProperties().get("x2").intValue(), super.getProperties().get("y2").intValue());
    }
}
