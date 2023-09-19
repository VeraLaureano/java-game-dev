import java.awt.*;
import java.awt.event.*;

public class JuegoPlataformas extends Frame implements KeyListener {
    int bloque = 50; // Tamaño de los lados (es un cuadrado)
    int x = getWidth() + bloque * 2; 
    int y = getHeight(); // Posición inicial del personaje
    int velocidadX = 0, velocidadY = 0; // Velocidad del personaje
    int gravedad = -1; // Gravedad del jueg

    public JuegoPlataformas() {
        setSize(800, 600); // Tamaño de la ventana del juego
        setVisible(true);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        while (true) {
            try {
                Thread.sleep(10); // Retardo para controlar la velocidad del juego
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            if (x > getWidth()) {
                x = 0;
            } else if (x < 0) {
                x = getWidth();
            }

            x += velocidadX;
            y += velocidadY;

            if (y < getHeight() - (bloque * 2)) { // Si el personaje está en el aire, aplicar gravedad
                velocidadY -= gravedad;
            } else { // Si el personaje está en el suelo, detener la caída
                velocidadY = 0;
                y = getHeight() - (bloque * 2);
            }

            repaint(); // Volver a dibujar la ventana del juego
        }
    }

    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight()); // Dibujar fondo negro

        g.setColor(Color.RED);
        g.fillRect(x, y, bloque, bloque); // Dibujar personaje

        g.setColor(Color.DARK_GRAY);
        g.fillRect(0, getHeight() - bloque, getWidth(), bloque); // Dibujar suelo
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT) { // Mover a la izquierda
            velocidadX = -5;    
        } else if (keyCode == KeyEvent.VK_RIGHT && x < getWidth()) { // Mover a la derecha
            velocidadX = 5;
        } else if (keyCode == KeyEvent.VK_SPACE) { // Saltar
            if (y > getHeight()/2) { // Solo se puede saltar si el personaje está en el suelo
                velocidadY = -20;
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) { // Detener el movimiento horizontal
            velocidadX = 0;
        }
    }
    
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        new JuegoPlataformas();
    }
}
