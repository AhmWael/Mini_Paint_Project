package backend;

import java.awt.*;
import java.util.Map;

public class CircleShape extends AbstractShape {

    @Override
    public void draw(Graphics canvas) {
        canvas.setColor(super.getColor());
        if(super.getFillColor() != null) {
            canvas.setColor(super.getFillColor());
            canvas.fillOval(super.getPosition().x - super.getProperties().get("radius").intValue()
                    , super.getPosition().y - super.getProperties().get("radius").intValue()
                    , super.getProperties().get("radius").intValue() * 2
                    , super.getProperties().get("radius").intValue() * 2);
        }

        canvas.drawOval(super.getPosition().x - super.getProperties().get("radius").intValue()
                , super.getPosition().y - super.getProperties().get("radius").intValue()
                , super.getProperties().get("radius").intValue() * 2
                , super.getProperties().get("radius").intValue() * 2);
    }
}
