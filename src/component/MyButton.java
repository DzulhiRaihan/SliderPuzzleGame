package component;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.*;

public class MyButton extends JButton {
    public MyButton(String text){
        super(text);
        setContentAreaFilled(false);
        setColor(Color.WHITE);
        colorOver = new Color(179,250,160);
        colorClick = new Color(152,184,144);
        borderColor = new Color(30,136,56);

        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setOver(true);
                setBackground(colorOver);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setOver(false);
                setBackground(color);
            }

            public void mousePressed(java.awt.event.MouseEvent evt) {
                setBackground(colorClick);
            }

            public void mouseReleased(java.awt.event.MouseEvent evt) {
                setBackground(colorOver);
            }
        });
        
    }

    private boolean over;
    private Color color;
    private Color colorOver;
    private Color colorClick;
    private Color borderColor;
    private int radius = 0;

    public void setColor(Color color) {
        this.color = color;
        setBackground(color);
    }

    public void setOver(boolean over) {
        this.over = over;
    }

    public void setColorOver(Color colorOver) {
        this.colorOver = colorOver;
    }

    public void setColorClick(Color colorClick) {
        this.colorClick = colorClick;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public boolean isOver() {
        return over;
    }

    public Color getColor() {
        return color;
    }

    public Color getColorOver() {
        return colorOver;
    }

    public Color getColorClick() {
        return colorClick;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    protected void paintComponent(Graphics g) {
       Graphics2D g2 = (Graphics2D)g;
       g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

       g2.setColor(borderColor);
       g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
       g2.setColor(getBackground());
       g2.fillRoundRect(2,2,getWidth()-4,getHeight()-4,radius,radius);



       super.paintComponent(g);
    }


}
