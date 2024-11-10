package frontend;

import backend.*;
import backend.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class mainWindow extends JFrame {
    private JPanel mainPanel;
    private JComboBox chooseShapeBox;
    private JButton colorizeButton;
    private JButton deleteButton;
    private JButton circleButton;
    private JButton lineSegmentButton;
    private JButton squareButton;
    private JButton rectangleButton;
    private JPanel canvasPanel;
    private Draw draw;
    private Canvas canvas;
    private Map<String, Shape> shapesList;

    public mainWindow() {
        setContentPane(mainPanel);
        setSize(new Dimension(640, 480));
        setLocationRelativeTo(null);
        setTitle("Vector Drawing Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        shapesList = new HashMap<>();

        canvas = new Canvas();
        canvas.setPreferredSize(canvasPanel.getSize());
        canvas.setBackground(Color.WHITE);
        canvasPanel.add(canvas);

        draw = new Draw();

        circleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CirclePropertiesDialog dialog = new CirclePropertiesDialog(canvas);

                if(dialog.getStatus().equals("Cancel")) {
                    return;
                }
                String x = dialog.getxTF();
                String y = dialog.getyTF();
                String radius = dialog.getRadiusTF();
                CircleShape circle = new CircleShape();
                Shape[] shapes = draw.getShapes();
                int circleCount = 0;
                for (Shape shape : shapes) {
                    if (shape instanceof CircleShape) {
                        circleCount++;
                    }
                }
                shapesList.put("Circle" + (circleCount + 1), circle);
                circle.setPosition(new Point(Integer.parseInt(x), Integer.parseInt(y)));
                circle.setProperties(Map.of("radius", Double.parseDouble(radius)));
                circle.setColor(Color.BLACK);
                circle.draw(canvas.getGraphics());
                draw.addShape(circle);
                chooseShapeBox.addItem("Circle" + (circleCount + 1));
                dialog.dispose();
                chooseShapeBox.setSelectedItem("Circle" + (circleCount + 1));
            }
        });
        lineSegmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LinePropertiesDialog dialog = new LinePropertiesDialog(canvas);
                if(dialog.getStatus().equals("Cancel")) {
                    return;
                }
                String x1 = dialog.getx1TF();
                String y1 = dialog.gety1TF();
                String x2 = dialog.getx2TF();
                String y2 = dialog.gety2TF();
                LineSegmentShape lineSegment = new LineSegmentShape();
                Shape[] shapes = draw.getShapes();
                int lineCount = 0;
                for (Shape shape : shapes) {
                    if (shape instanceof LineSegmentShape) {
                        lineCount++;
                    }
                }
                shapesList.put("Line" + (lineCount + 1), lineSegment);
                lineSegment.setPosition(new Point(Integer.parseInt(x1), Integer.parseInt(y1)));
                lineSegment.setProperties(Map.of("x2", Double.parseDouble(x2), "y2", Double.parseDouble(y2)));
                lineSegment.setColor(Color.BLACK);
                lineSegment.draw(canvas.getGraphics());
                draw.addShape(lineSegment);
                chooseShapeBox.addItem("Line" + (lineCount + 1));
                dialog.dispose();
                chooseShapeBox.setSelectedItem("Line" + (lineCount + 1));
            }
        });
        squareButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SquarePropertiesDialog dialog = new SquarePropertiesDialog(canvas);
                if(dialog.getStatus().equals("Cancel")) {
                    return;
                }
                String x = dialog.getxTF();
                String y = dialog.getyTF();
                String side = dialog.getLengthTF();
                SquareShape square = new SquareShape();
                Shape[] shapes = draw.getShapes();
                int squareCount = 0;
                for (Shape shape : shapes) {
                    if (shape instanceof SquareShape) {
                        squareCount++;
                    }
                }
                shapesList.put("Square" + (squareCount + 1), square);
                square.setPosition(new Point(Integer.parseInt(x), Integer.parseInt(y)));
                square.setProperties(Map.of("side", Double.parseDouble(side)));
                square.setColor(Color.BLACK);
                square.draw(canvas.getGraphics());
                draw.addShape(square);
                chooseShapeBox.addItem("Square" + (squareCount + 1));
                dialog.dispose();
                chooseShapeBox.setSelectedItem("Square" + (squareCount + 1));
            }
        });
        rectangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RectanglePropertiesDialog dialog = new RectanglePropertiesDialog(canvas);
                if(dialog.getStatus().equals("Cancel")) {
                    return;
                }
                String x = dialog.getxTF();
                String y = dialog.getyTF();
                String width = dialog.getWidthTF();
                String height = dialog.getHeightTF();
                RectangleShape rectangle = new RectangleShape();
                Shape[] shapes = draw.getShapes();
                int rectangleCount = 0;
                for (Shape shape : shapes) {
                    if (shape instanceof RectangleShape) {
                        rectangleCount++;
                    }
                }
                shapesList.put("Rectangle" + (rectangleCount + 1), rectangle);
                rectangle.setPosition(new Point(Integer.parseInt(x), Integer.parseInt(y)));
                rectangle.setProperties(Map.of("width", Double.parseDouble(width), "height", Double.parseDouble(height)));
                rectangle.setColor(Color.BLACK);
                rectangle.draw(canvas.getGraphics());
                draw.addShape(rectangle);
                chooseShapeBox.addItem("Rectangle" + (rectangleCount + 1));
                dialog.dispose();
                chooseShapeBox.setSelectedItem("Rectangle" + (rectangleCount + 1));
            }
        });

        colorizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedShape = (String) chooseShapeBox.getSelectedItem();
                if (selectedShape == null) {
                    return;
                }
                Color color = JColorChooser.showDialog(null, "Choose a color", Color.BLACK);
                Shape shape = shapesList.get(selectedShape);
                shape.setFillColor(color);
                shape.draw(canvas.getGraphics());
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedShape = (String) chooseShapeBox.getSelectedItem();
                if (selectedShape == null) {
                    return;
                }
                Shape shape = shapesList.get(selectedShape);
                draw.removeShape(shape);
                chooseShapeBox.removeItem(selectedShape);
                shapesList.remove(selectedShape);
                canvas.getGraphics().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                draw.refresh(canvas.getGraphics());
            }
        });
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        new mainWindow();
    }

}
