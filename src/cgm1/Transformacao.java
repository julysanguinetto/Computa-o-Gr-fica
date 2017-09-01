/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgm1;

import static java.lang.Math.*;
import java.util.ArrayList;

/**
 *
 * @author Sanguinetto
 */
public class Transformacao {

    private Operacao operacao = new Operacao();

    public ArrayList<Ponto> translada(ArrayList<Ponto> a, Ponto b) {
        ArrayList<Ponto> pontos = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            Ponto e = operacao.soma(a.get(i), b);
            pontos.add(e);
        }
        return pontos;
    }

    public ArrayList<Ponto> escala(ArrayList<Ponto> a, Ponto b) {
        Ponto c;
        Ponto centro = centroDesenho(a);
        ArrayList<Ponto> pontos = new ArrayList<>();
        ArrayList<Ponto> pontos2 = new ArrayList<>();

        for (int i = 0; i < a.size(); i++) {
            pontos.add(new Ponto((a.get(i).getX()-centro.getX()),(a.get(i).getY()-centro.getY())));
            c = operacao.escalar(pontos.get(i), b);
            pontos2.add(new Ponto((c.getX()+centro.getX()),(c.getY()+centro.getY())));
        }
        return pontos2;
    }

    public ArrayList<Ponto> rotaciona(ArrayList<Ponto> a, double angulo) {
        Ponto ponto = new Ponto();
        Ponto centro = centroDesenho(a);
        ArrayList<Ponto> pontos = new ArrayList<>();
        double rad = toRadians(angulo);

        float[][] matrizRotacao = new float[][]{{(float) cos(rad), (float) sin(rad)},
                                                {(float) (-sin(rad)), (float) cos(rad)}};

        for (int i = 0; i < a.size(); i++){
            double dx = a.get(i).getX() - centro.getX();
            double dy = a.get(i).getY() - centro.getY();
            
            ponto.setX((float) dx);
            ponto.setY((float) dy);
            
            Ponto pontoAux = this.operacao.multiplicacao(ponto, matrizRotacao);
            pontos.add(new Ponto((pontoAux.getX() + centro.getX()), (pontoAux.getY() + centro.getY())));
        }
        return pontos;

    }

    private Ponto centroDesenho(ArrayList<Ponto> a) {
        Ponto centro = new Ponto(
                (((maior(a).getX() - menor(a).getX()) / 2) + menor(a).getX()),
                (((maior(a).getY() - menor(a).getY()) / 2) + menor(a).getY())
        );
        return centro;
    }

    private Ponto menor(ArrayList<Ponto> pontos) {
        float menorX = pontos.get(0).getX();
        float menorY = pontos.get(0).getY();
        for (int i = 1; i < pontos.size(); i++) {
            if (pontos.get(i).getX() < menorX) {
                menorX = pontos.get(i).getX();
            }
            if (pontos.get(i).getY() < menorY) {
                menorY = pontos.get(i).getY();
            }
        }
        Ponto p = new Ponto(menorX, menorY);
        return p;
    }

    private Ponto maior(ArrayList<Ponto> pontos) {
        float maiorX = pontos.get(0).getX();
        float maiorY = pontos.get(0).getX();
        for (int i = 1; i < pontos.size(); i++) {
            if (pontos.get(i).getX() > maiorX) {
                maiorX = pontos.get(i).getX();
            }
            if (pontos.get(i).getY() > maiorY) {
                maiorY = pontos.get(i).getY();
            }
        }
        Ponto p = new Ponto(maiorX, maiorY);

        return p;
    }
}
