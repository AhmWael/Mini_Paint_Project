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
                        paintEngine.updateShape();
                        shape.setFillColor(color);
                        //paintEngine.updateShape();
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
                        MoveShapeDialog dialog = new MoveShapeDialog(canvas, shape.getPosition().x, shape.getPosition().y);
                        if(dialog.getStatus().equals("Cancel")) {
                            return;
                        }
                        paintEngine.updateShape();
                        String x = dialog.getxTF();
                        String y = dialog.getyTF();
                        double x1 = shape.getPosition().getX();
                        double y1 = shape.getPosition().getY();
                        double dx = Double.parseDouble(x) - x1;
                        double dy = Double.parseDouble(y) - y1;
                        shape.setPosition(new Point(Integer.parseInt(x), Integer.parseInt(y)));
                        if(shape instanceof LineSegmentShape){
                            shape.setProperties(Map.of("x2", shape.getProperties().get("x2") + dx, "y2", shape.getProperties().get("y2") + dy));
                        }
                        dialog.dispose();
                        canvas.repaint();
                        break;
                    }
                }
            }
        });
        resizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedShape = (String) chooseShapeBox.getSelectedItem();
                if (selectedShape == null) {
                    return;
                }
                Shape[] shapes = paintEngine.getShapes();
                for (Shape shape : shapes) {
                    if (shape.getName().equals(selectedShape)) {
                        if(shape instanceof CircleShape){
                            String newRadius;
                            while (true) {
                                newRadius = JOptionPane.showInputDialog("Enter the new radius", shape.getProperties().get("radius"));
                                if (newRadius == null) {
                                    return;
                                }
                                if (newRadius.isEmpty() || Double.parseDouble(newRadius) <= 0) {
                                    JOptionPane.showMessageDialog(null, "Please enter a valid radius", "Error", JOptionPane.ERROR_MESSAGE);
                                    continue;
                                }
                                try {
                                    Double.parseDouble(newRadius);
                                    break;
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(null, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            paintEngine.updateShape();
                            shape.setProperties(Map.of("radius", Double.parseDouble(newRadius)));
                            canvas.repaint();
                        } else if(shape instanceof LineSegmentShape){
                            LineResizeDialog dialog = new LineResizeDialog(canvas, shape.getProperties().get("x2").intValue(), shape.getProperties().get("y2").intValue());
                            if(dialog.getStatus().equals("Cancel")) {
                                return;
                            }
                            String x2 = dialog.getx2TF();
                            String y2 = dialog.gety2TF();
                            paintEngine.updateShape();
                            shape.setProperties(Map.of("x2", Double.parseDouble(x2), "y2", Double.parseDouble(y2)));
                            dialog.dispose();
                            canvas.repaint();
                        } else if(shape instanceof SquareShape){
                            String newSide;
                            while (true) {
                                newSide = JOptionPane.showInputDialog("Enter the new side length", shape.getProperties().get("side"));
                                if (newSide == null) {
                                    return;
                                }
                                if (newSide.isEmpty() || Double.parseDouble(newSide) <= 0) {
                                    JOptionPane.showMessageDialog(null, "Please enter a valid side length", "Error", JOptionPane.ERROR_MESSAGE);
                                    continue;
                                }
                                try {
                                    Double.parseDouble(newSide);
                                    break;
                                } catch (NumberFormatException ex) {
                                    JOptionPane.showMessageDialog(null, "Please enter a valid number", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                            paintEngine.updateShape();
                            shape.setProperties(Map.of("side", Double.parseDouble(newSide)));
                            canvas.repaint();
                        } else if(shape instanceof RectangleShape){
                            RectangleResizeDialog dialog = new RectangleResizeDialog(canvas, shape.getProperties().get("width").intValue(), shape.getProperties().get("height").intValue());
                            if(dialog.getStatus().equals("Cancel")) {
                                return;
                            }
                            String width = dialog.getwidthTF();
                            String height = dialog.getheightTF();
                            paintEngine.updateShape();
                            shape.setProperties(Map.of("width", Double.parseDouble(width), "height", Double.parseDouble(height)));
                            dialog.dispose();
                            canvas.repaint();
                        }
                    }
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(chooseShapeBox.getItemCount() == 0) {
                    JOptionPane.showMessageDialog(null, "No shapes to save", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                    public boolean accept(java.io.File f) {
                        return f.isDirectory() || f.getName().toLowerCase().endsWith(".txt");
                    }

                    public String getDescription() {
                        return "Text Files (*.txt)";
                    }
                });
                fileChooser.setDialogTitle("Save Shapes");
                fileChooser.setCurrentDirectory(new java.io.File("."));
                int returnValue = fileChooser.showSaveDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    java.io.File selectedFile = fileChooser.getSelectedFile();
                    String path = selectedFile.getAbsolutePath();
                    if (!path.toLowerCase().endsWith(".txt")) {
                        path += ".txt";
                    }
                    paintEngine.saveToFile(path);
                }
            }
        });
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                    public boolean accept(java.io.File f) {
                        if (f.getName().endsWith(".txt") || f.isDirectory()) {
                            return true;
                        }
                        return false;
                    }

                    public String getDescription() {
                        return "Text Files (*.txt)";
                    }
                });
                fileChooser.setDialogTitle("Choose a file to load shapes from");
                fileChooser.setCurrentDirectory(new java.io.File("."));
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    String path = fileChooser.getSelectedFile().getAbsolutePath();
                    paintEngine.loadFromFile(path, chooseShapeBox);
                    updateShapesCounts();
                    canvas.repaint();
                }
            }
        });

        undoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintEngine.undo();
                canvas.repaint();
                updateDropDown();
            }
        });
        redoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintEngine.redo();
                canvas.repaint();
                updateDropDown();
            }
        });
    }

    private void updateDropDown() {
        chooseShapeBox.removeAllItems();
        Shape[] shapes = paintEngine.getShapes();
        for (Shape shape : shapes) {
            chooseShapeBox.addItem(shape.getName());
        }
    }

    private void updateShapesCounts(){
        Shape[] shapes = paintEngine.getShapes();
        for (Shape shape : shapes) {
            if(shape instanceof CircleShape){
                circleCount++;
            }
            else if(shape instanceof LineSegmentShape){
                lineCount++;
            }
            else if(shape instanceof SquareShape){
                squareCount++;
            }
            else if(shape instanceof RectangleShape){
                rectangleCount++;
            }
        }
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
