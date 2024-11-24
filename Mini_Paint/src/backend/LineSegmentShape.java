package backend;

import java.awt.*;
import java.util.Map;

public class LineSegmentShape extends AbstractShape {

    @Override
    public void draw(Graphics canvas) {
        canvas.setColor(super.getColor());
        canvas.drawLine(super.getPosition().x, super.getPosition().y, super.getProperties().get("x2").intValue(), super.getProperties().get("y2").intValue());
    }
}
