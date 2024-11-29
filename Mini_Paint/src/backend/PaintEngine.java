package backend;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class PaintEngine implements DrawingEngine{
    private ArrayList<Shape> shapes;
    private Stack<String> undoStack;
    private Stack<String> redoStack;

    public PaintEngine() {
        shapes = new ArrayList<>();
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    @Override
    public void addShape(Shape shape) {
        saveState();
        shapes.add(shape);
        redoStack.clear();
    }

    @Override
    public void removeShape(Shape shape) {
        saveState();
        shapes.remove(shape);
        redoStack.clear();
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
            writer.write("SHAPES_FILE_V1"); // Custom signature to ensure file type
            writer.newLine();
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
            String signature = reader.readLine();
            if (!"SHAPES_FILE_V1".equals(signature)) {
                throw new IOException("Invalid file format");
            }
            chooseShapeBox.removeAllItems();
            saveState();
            shapes.clear();
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

    private void saveState(){
        String currentState = serializeState();
        undoStack.push(currentState);
        redoStack.clear();
        System.out.println("\nUndo stack:");
        System.out.println(undoStack.toString());
    }

    private String serializeState() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        for (Shape shape : shapes) {
            builder.append(shape.toString()).append("\n");
        }
        return builder.toString();
    }

    private ArrayList<Shape> deserializeState(String state) {
        ArrayList<Shape> newShapes = new ArrayList<>();
        String[] lines = state.split("\n");
        for (String line : lines) {
            if (line.isEmpty()) {
                continue;
            }
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
            newShapes.add(shape);
        }
        return newShapes;
    }

    public void updateShape() {
        saveState();
        redoStack.clear();
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(serializeState());
            String previousState = undoStack.pop();
            shapes = deserializeState(previousState);
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(serializeState());
            String nextState = redoStack.pop();
            shapes = deserializeState(nextState);
        }
    }
}
