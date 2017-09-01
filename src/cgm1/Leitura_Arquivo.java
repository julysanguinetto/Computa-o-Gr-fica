/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cgm1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Sanguinetto
 */
public class Leitura_Arquivo {

    private ArrayList<String> string = new ArrayList<>();
    private ArrayList<Float> arrayX = new ArrayList<>();
    private ArrayList<Float> arrayY = new ArrayList<>();
    private ArrayList<Ponto> arrayPonto = new ArrayList<>();


    public Leitura_Arquivo(String caminho) throws FileNotFoundException, IOException {

        FileReader file = new FileReader(caminho);
        BufferedReader br = new BufferedReader(file);
        String linha = br.readLine();

        while (linha != null) {
            string.add(linha);
            linha = br.readLine();
        }
        le_Arquivo();
    }

    private void le_Arquivo() {
        int i = 1;
        while (string.get(i).charAt(0) != ('y')) {
            arrayX.add(Float.valueOf(string.get(i)));
            i++;
        }
        i++;
        while (i < string.size()) {
            arrayY.add(Float.valueOf(string.get(i)));
            i++;
        }
        transfereArrayPonto();
    }

    private void transfereArrayPonto() {
        for(int i=0; i< arrayX.size(); i++){
            Ponto p = new Ponto();
            p.setX(arrayX.get(i));
            p.setY(arrayY.get(i));
            arrayPonto.add(p);
        }
    }

    public ArrayList<Ponto> getArrayPonto() {
        return arrayPonto;
    }

}
