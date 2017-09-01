/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgm1;

/**
 *
 * @author Sanguinetto
 */
public class Operacao {

    public Ponto soma(Ponto a, Ponto b) {
        Ponto c = new Ponto();
        System.out.println("a: " + a.getX());
        System.out.println("b: " + b.getX());
        c.setX(a.getX() + b.getX());
        c.setY(a.getY() + b.getY());
        System.out.println("c: " + c.getX());
        return c;
    }

    public Ponto subtracao(Ponto a, Ponto b) {
        Ponto c = new Ponto();
        c.setX(a.getX() - b.getX());
        c.setY(a.getY() - b.getY());
        return c;
    }

    public Ponto multiplicacao(Ponto a, float[][] matriz) {
        float[][] novaMatriz = new float[1][2];
        float[][] matrizPonto = {{a.getX(), a.getY()}};
        
        Ponto ponto = new Ponto();

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    novaMatriz[i][j] += matrizPonto[i][k] * matriz[k][j];
                }
            }
        }
        ponto.setX(novaMatriz[0][0]);
        ponto.setY(novaMatriz[0][1]);
        return ponto;
    }

    public Ponto escalar(Ponto a, Ponto b) {
        Ponto c = new Ponto();
        //float c ;
        c.setX(a.getX() * b.getX());
        c.setY(a.getY() * b.getY());
        //c = a.getX()*b;
        //c += a.getY()*b;
        return c;
        
    }

}
