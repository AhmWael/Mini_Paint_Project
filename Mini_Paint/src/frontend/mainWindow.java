package frontend;

import backend.*;
import backend.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public mainWindow() {
        setContentPane(mainPanel);
        setSize(new Dimension(640, 480));
        setLocationRelativeTo(null);
        setTitle("Vector Drawing Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

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
                circle.setName("Circle" + (circleCount + 1));
                circle.setPosition(new Point(Integer.parseInt(x), Integer.parseInt(y)));
                circle.setProperties(Map.of("radius", Double.parseDouble(radius)));
                circle.setColor(Color.BLACK);
                circle.draw(canvas.getGraphics());
                draw.addShape(circle);
                chooseShapeBox.addItem(circle.getName());
                dialog.dispose();
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
                lineSegment.setName("Line" + (lineCount + 1));
                lineSegment.setPosition(new Point(Integer.parseInt(x1), Integer.parseInt(y1)));
                lineSegment.setProperties(Map.of("x2", Double.parseDouble(x2), "y2", Double.parseDouble(y2)));
                lineSegment.setColor(Color.BLACK);
                lineSegment.draw(canvas.getGraphics());
                draw.addShape(lineSegment);
                chooseShapeBox.addItem(lineSegment.getName());
                dialog.dispose();
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
                square.setName("Square" + (squareCount + 1));
                square.setPosition(new Point(Integer.parseInt(x), Integer.parseInt(y)));
                square.setProperties(Map.of("side", Double.parseDouble(side)));
                square.setColor(Color.BLACK);
                square.draw(canvas.getGraphics());
                draw.addShape(square);
                chooseShapeBox.addItem(square.getName());
                dialog.dispose();
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
                rectangle.setName("Rectangle" + (rectangleCount + 1));
                rectangle.setPosition(new Point(Integer.parseInt(x), Integer.parseInt(y)));
                rectangle.setProperties(Map.of("width", Double.parseDouble(width), "height", Double.parseDouble(height)));
                rectangle.setColor(Color.BLACK);
                rectangle.draw(canvas.getGraphics());
                draw.addShape(rectangle);
                chooseShapeBox.addItem(rectangle.getName());
                dialog.dispose();
            }
        });

        colorizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

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
