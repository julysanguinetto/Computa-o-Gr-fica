package cgm1;

import java.awt.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Sanguinetto
 */
public class Bresenham extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame f = new Bresenham();
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setVisible(true);
                f.add(new BresenhamPanel(), BorderLayout.CENTER);
                f.setTitle("Bresenham");
                f.setResizable(false);
                f.pack();
                f.setLocationRelativeTo(null);
            }
        });
    }
}

class BresenhamPanel extends JPanel {

    final int centerX, centerY;
    Leitura_Arquivo leitura;
    ArrayList<Ponto> pontos;
    int escolhido = 0;
    ArrayList<Ponto> pontoTransformado = new ArrayList<>();
    Transformacao p = new Transformacao();

    private float captura(float min, float max, char informacao) {
        Scanner in = new Scanner(new InputStreamReader(System.in));
        Float num;
        do {
        if (informacao == 'e') {
            System.out.println("Informe a proporção (entre 0 e 20): ");
        } else if (informacao == 'a') {
            System.out.println("Informe o ângulo (entre 0 e 360): ");
        } else if (informacao == 't') {
            System.out.println("Informe a quantidade de pontos a ser movida na posição X (entre -200 e 200): ");
        } else if (informacao == 'y') {
            System.out.println("Informe a quantidade de pontos a ser movida na posição Y(entre -200 e 200): ");
        }
        
            num = in.nextFloat();
        }while (num < min || num > max);
        return num;
    }

    private float capturaInformacao(char informacao) throws IOException {
        Float num;
        Scanner in = new Scanner(new InputStreamReader(System.in));

        if (informacao == 's') {
            System.out.println("1: Escalar: ");
            System.out.println("2: Rotação: ");
            System.out.println("3: Translação: ");
        }
        num = in.nextFloat();
        return num;
    }

    public void menu() throws IOException {
        int escolha = (int) capturaInformacao('s');

        switch (escolha) {
            case 1:
                float esc = captura(0, 20, 'e');
                pontoTransformado = p.escala(pontos, new Ponto(esc, esc));
                break;
            case 2:
                float angulo = captura(0, 360, 'a');
                pontoTransformado = p.rotaciona(pontos, angulo);
                break;
            case 3:
                float trans = captura(-200, 200, 't');
                float trans2 = captura(-200, 200, 'y');
                pontoTransformado = p.translada(pontos, new Ponto(trans, trans2));
                break;
            default:
                System.out.println("Operação finalizada");
                System.exit(0);

        }

    }

    public BresenhamPanel() {
        int w = 600;
        int h = 500;
        centerX = w / 2;
        centerY = h / 2;
        setPreferredSize(new Dimension(w, h));
        setBackground(Color.white);
        abreArq();
    }

    public void abreArq() {
        try {
            leitura = new Leitura_Arquivo("src//Resource//cg.txt");
            pontos = leitura.getArrayPonto();
            menu();
        } catch (IOException ex) {
            Logger.getLogger(BresenhamPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void posicaoPontos(ArrayList<Ponto> a, char informacao) {
        if (informacao == 'p') {
            System.out.println("Ponto recebido: ");
        } else if (informacao == 't') {
            System.out.println("Ponto transformado: ");
        }
        for (int i = 0; i < a.size(); i++) {
            System.out.println("X: " + a.get(i).getX() + " | Y: " + a.get(i).getY());
        }
    }

    private void desenha(Graphics g, ArrayList<Ponto> pontoDesenho, char informacao) {
        int limite = pontoDesenho.size() - 1;
        for (int i = 0; i < limite; i++) {
            drawLine(g, (int) pontoDesenho.get(i).getX(), (int) pontoDesenho.get(i).getY(),
                    (int) pontoDesenho.get(i + 1).getX(), (int) pontoDesenho.get(i + 1).getY());
        }
        drawLine(g, (int) pontoDesenho.get(limite).getX(), (int) pontoDesenho.get(limite).getY(),
                (int) pontoDesenho.get(0).getX(), (int) pontoDesenho.get(0).getY());
        posicaoPontos(pontoDesenho, informacao);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        desenha(g, pontos, 'p');
        desenha(g, pontoTransformado, 't');
    }

    public void plot(Graphics g, int x, int y) {
        g.setColor(Color.MAGENTA);
        g.fillRect(centerX + x, centerY + y, 3, 3);
    }

    public void drawLine(Graphics g, int x1, int y1, int x2, int y2) {
        int d = 0;

        int dy = Math.abs(y2 - y1);
        int dx = Math.abs(x2 - x1);

        int dy2 = (dy << 1);
        int dx2 = (dx << 1);

        int ix = x1 < x2 ? 1 : -1;
        int iy = y1 < y2 ? 1 : -1;

        if (dy <= dx) {
            for (;;) {
                plot(g, x1, y1);
                if (x1 == x2) {
                    break;
                }

                x1 += ix;
                d += dy2;
                if (d > dx) {
                    y1 += iy;
                    d -= dx2;
                }
            }
        } else {
            for (;;) {
                plot(g, x1, y1);
                if (y1 == y2) {
                    break;
                }
                y1 += iy;
                d += dx2;
                if (d > dy) {
                    x1 += ix;
                    d -= dy2;
                }
            }
        }
    }
}
