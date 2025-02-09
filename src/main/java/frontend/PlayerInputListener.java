package frontend;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.function.Consumer;

public class PlayerInputListener extends JFrame implements KeyListener  {

    private Consumer<Character> on_key_pressed = key ->{};

    public PlayerInputListener(Consumer<Character> on_key_pressed) {
        this.on_key_pressed = on_key_pressed;
        setTitle("Player Input Listener");
        setSize(200, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(this);
        setVisible(true);
    }
    @Override
    public void keyReleased(KeyEvent e) {
        on_key_pressed.accept(e.getKeyChar());
    }
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {}

}
