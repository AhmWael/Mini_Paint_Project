package backend;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PaintEngine implements DrawingEngine{
    private ArrayList<Shape> shapes;

    public PaintEngine() {
        shapes = new ArrayList<>();
    }

    @Override
    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    @Override
    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }

    @Override
    public Shape[] getShapes() {
        return shapes.toArray(new Shape[0]);
    }

    @Override
    public void refresh(Graphics canvas) {
        for (Shape shape : shapes) {
            shape.draw(canvas);
        }
    }

    public void saveToFile(String path) {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            for (Shape shape : shapes) {
                writer.write(shape.toString());
                writer.newLine();
            }
            writer.close();
            JOptionPane.showMessageDialog(null, "Shapes saved successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (IOException e){
            JOptionPane.showMessageDialog(null, "Error saving to file\nPlease try again later\nError: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void loadFromFile(String path, JComboBox chooseShapeBox) {
        try{
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                int x = Integer.parseInt(data[1]);
                int y = Integer.parseInt(data[2]);
                Color color = new Color(Integer.parseInt(data[3]));
                Color fillColor = data[4].equals("null") ? null : new Color(Integer.parseInt(data[4]));
                Map<String, Double> properties = new HashMap<>();
                for (int i = 5; i < data.length; i++) {
                    String[] property = data[i].split(":");
                    properties.put(property[0], Double.parseDouble(property[1]));
                }
                Shape shape;
                if(name.startsWith("Circle")) {
                    shape = new CircleShape();
                } else if(name.startsWith("Line")) {
                    shape = new LineSegmentShape();
                } else if(name.startsWith("Rectangle")) {
                    shape = new RectangleShape();
                } else if(name.startsWith("Square")) {
                    shape = new SquareShape();
                } else {
                    throw new IllegalArgumentException("Invalid shape name");
                }
                shape.setName(name);
                shape.setPosition(new Point(x, y));
                shape.setColor(color);
                shape.setFillColor(fillColor);
                shape.setProperties(properties);
                addShape(shape);
                chooseShapeBox.addItem(name);
            }
            reader.close();
        }
        catch (IOException e){
            JOptionPane.showMessageDialog(null, "Error reading file \"" + path + "\"\nPlease try again later\nError: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
