/*
* GitHub Repository Link: https://github.com/AhmWael/Mini_Paint_Project
*/
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
    private JButton undoButton;
    private JButton redoButton;
    private JButton saveButton;
    private JButton loadButton;
    private JButton resizeButton;
    private JButton moveButton;
    private PaintEngine paintEngine;
    private CanvasPanel canvas;
    private int circleCount;
    private int lineCount;
    private int squareCount;
    private int rectangleCount;

    public mainWindow() {
        setContentPane(mainPanel);
        setSize(new Dimension(640, 480));
        setLocationRelativeTo(null);
        setTitle("Vector Drawing Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        paintEngine = new PaintEngine();

        canvas = new CanvasPanel(paintEngine);
        canvas.setPreferredSize(canvasPanel.getSize());
        canvasPanel.add(canvas);

        circleCount = 0;
        lineCount = 0;
        squareCount = 0;
        rectangleCount = 0;

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
                Color color = dialog.getColor();

                CircleShape circle = new CircleShape();
                circleCount++;
                circle.setName("Circle" + (circleCount));
                circle.setPosition(new Point(Integer.parseInt(x), Integer.parseInt(y)));
                circle.setProperties(Map.of("radius", Double.parseDouble(radius)));
                circle.setColor(color);

                paintEngine.addShape(circle);
                chooseShapeBox.addItem(circle.getName());
                chooseShapeBox.setSelectedItem(circle.getName());
                canvas.repaint();
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
                Color color = dialog.getColor();

                LineSegmentShape lineSegment = new LineSegmentShape();
                lineCount++;
                lineSegment.setName("Line" + (lineCount));
                lineSegment.setPosition(new Point(Integer.parseInt(x1), Integer.parseInt(y1)));
                lineSegment.setProperties(Map.of("x2", Double.parseDouble(x2), "y2", Double.parseDouble(y2)));
                lineSegment.setColor(color);

                paintEngine.addShape(lineSegment);
                chooseShapeBox.addItem(lineSegment.getName());
                chooseShapeBox.setSelectedItem(lineSegment.getName());
                dialog.dispose();
                canvas.repaint();
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
                Color color = dialog.getColor();

                SquareShape square = new SquareShape();
                squareCount++;
                square.setName("Square" + (squareCount));
                square.setPosition(new Point(Integer.parseInt(x), Integer.parseInt(y)));
                square.setProperties(Map.of("side", Double.parseDouble(side)));
                square.setColor(color);

                paintEngine.addShape(square);
                chooseShapeBox.addItem(square.getName());
                chooseShapeBox.setSelectedItem(square.getName());
                dialog.dispose();
                canvas.repaint();
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
                Color color = dialog.getColor();

                RectangleShape rectangle = new RectangleShape();
                rectangleCount++;
                rectangle.setName("Rectangle" + (rectangleCount));
                rectangle.setPosition(new Point(Integer.parseInt(x), Integer.parseInt(y)));
                rectangle.setProperties(Map.of("width", Double.parseDouble(width), "height", Double.parseDouble(height)));
                rectangle.setColor(color);

                paintEngine.addShape(rectangle);
                chooseShapeBox.addItem(rectangle.getName());
                chooseShapeBox.setSelectedItem(rectangle.getName());
                dialog.dispose();
                canvas.repaint();
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
                Shape[] shapes = paintEngine.getShapes();
                for (Shape shape : shapes) {
                    if (shape.getName().equals(selectedShape)) {
                        shape.setFillColor(color);
                        break;
                    }
                }
                canvas.repaint();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedShape = (String) chooseShapeBox.getSelectedItem();
                if (selectedShape == null) {
                    return;
                }
                Shape[] shapes = paintEngine.getShapes();
                for (Shape shape : shapes) {
                    if (shape.getName().equals(selectedShape)) {
                        paintEngine.removeShape(shape);
                        chooseShapeBox.removeItem(selectedShape);
                        break;
                    }
                }
                //canvas.getGraphics().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
                canvas.repaint();
            }
        });


        moveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedShape = (String) chooseShapeBox.getSelectedItem();
                if (selectedShape == null) {
                    return;
                }
                Shape[] shapes = paintEngine.getShapes();
                for (Shape shape : shapes) {
                    if (shape.getName().equals(selectedShape)) {
                        MoveShapeDialog dialog = new MoveShapeDialog(canvas);
                        if(dialog.getStatus().equals("Cancel")) {
                            return;
                        }
                        String x = dialog.getxTF();
                        String y = dialog.getyTF();
                        shape.setPosition(new Point(Integer.parseInt(x), Integer.parseInt(y)));
                        dialog.dispose();
                        canvas.repaint();
                        break;
                    }
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintEngine.saveToFile("Shapes_List.txt");
//                JFileChooser fileChooser = new JFileChooser();
//                fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
//                    public boolean accept(java.io.File f) {
//                        if (f.getName().endsWith(".txt") || f.isDirectory()) {
//                            return true;
//                        }
//                        return false;
//                    }
//
//                    public String getDescription() {
//                        return "TXT files";
//                    }
//                });
//                int returnValue = fileChooser.showSaveDialog(null);
//                if (returnValue == JFileChooser.APPROVE_OPTION) {
//                    String path = fileChooser.getSelectedFile().getAbsolutePath();
//                    paintEngine.saveToFile(path);
//                }
            }
        });
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintEngine.loadFromFile("Shapes_List.txt", chooseShapeBox);
                canvas.repaint();
//                JFileChooser fileChooser = new JFileChooser();
//                fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
//                    public boolean accept(java.io.File f) {
//                        if (f.getName().endsWith(".txt") || f.isDirectory()) {
//                            return true;
//                        }
//                        return false;
//                    }
//
//                    public String getDescription() {
//                        return "TXT files";
//                    }
//                });
//                int returnValue = fileChooser.showOpenDialog(null);
//                if (returnValue == JFileChooser.APPROVE_OPTION) {
//                    String path = fileChooser.getSelectedFile().getAbsolutePath();
//                    paintEngine.loadFromFile(path);
//                    Shape[] shapes = paintEngine.getShapes();
//                    for (Shape shape : shapes) {
//                        chooseShapeBox.addItem(shape.getName());
//                    }
//                    canvas.repaint();
//                }
            }
        });
        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        redoButton.addActionListener(new ActionListener() {
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
