/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sk.umb.fpv.columnarsearch;

/**
 *
 * @author Tomas
 */
import java.awt.*;
import javax.swing.*;
/**
 *
 * @author tomraffaj
 */
public class ArrayDrawer extends JPanel {
    private int[][] array;
    private int startX, startY, endX, endY;

    public ArrayDrawer(int[][] array, int startX, int startY, int endX, int endY) {
        this.array = array;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }
    
    public static void paintOut(int startx,int endx, int starty, int endy, int [][] array) {
        
        JFrame frame = new JFrame("Found match");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        ArrayDrawer drawer = new ArrayDrawer(array, startx, starty, endx, endy);
        frame.add(drawer);

        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();
        int cellWidth = width / array[0].length;
        int cellHeight = height / array.length;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                int x = j * cellWidth;
                int y = i * cellHeight;

                if ((i >= startY && i <= endY) && (j >= startX && j <= endX)) {
                    g.setColor(new Color(255, 165, 0));
                    g.fillRect(x, y, cellWidth, cellHeight);
                } else if ((i == startY - 1 || i == endY + 1) && (j >= startX - 1 && j <= endX + 1)) {
                    g.setColor(new Color(255, 165, 0));
                    g.fillRect(x, y, cellWidth, cellHeight);
                } else if ((j == startX - 1 || j == endX + 1) && (i >= startY && i <= endY)) {
                    g.setColor(new Color(255, 165, 0));
                    g.fillRect(x, y, cellWidth, cellHeight);
                } else if ((j == startX || j == endX) && (i >= startY - 1 && i <= endY + 1)) {
                    g.setColor(new Color(255, 165, 0));
                    g.fillRect(x, y, cellWidth, cellHeight);
                } else {
                    g.setColor(Color.LIGHT_GRAY);
                    g.drawRect(x, y, cellWidth, cellHeight);
                }

                g.setColor(Color.BLACK);
                String number = String.valueOf(array[i][j]);
                FontMetrics fm = g.getFontMetrics();
                int numberWidth = fm.stringWidth(number);
                int numberHeight = fm.getHeight();
                int numberX = x + (cellWidth - numberWidth) / 2;
                int numberY = y + (cellHeight + numberHeight) / 2;
                g.drawString(number, numberX, numberY - fm.getDescent() + 1);
            }
        }
    }
}
