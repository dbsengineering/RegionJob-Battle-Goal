package sujet3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Classe Main.
 * Sujet 3 (Goal) de la battle RegionJob du 27/03/2018.
 * Une solution possible.
 * 
 * @author Jeremy Cavron
 * @version 1.0
 */
public class Main {

  /**
   * Procédure princpale
   * @param args
   */
  public static void main(String[] args) throws FileNotFoundException {

    File file = new File("fichiers/input1.txt");
    Scanner sc = new Scanner(file);

    String[] mesNotes = sc.nextLine().split(" "); // Mes notes sur les 5 premiers films
    int nbAmis = Integer.parseInt(sc.nextLine()); // Nombre d'amis
    int meilleurA = Integer.parseInt(sc.nextLine()); // Nombre des meilleurs amis
    int[][] mResult = new int[nbAmis][6];
    int[][] mEcart = new int[nbAmis][5];
    int[] sommeEcart = new int[nbAmis];
    int count = 0;
    int[] mIndice = new int[nbAmis];
    int[] sommeEcartBis = new int[nbAmis];

    // Récupération des résultat des amis dans une matrice
    for (int i = 0; i < nbAmis; i++) {

      String[] lineAmi = sc.nextLine().split(" ");

      for (int j = 0; j < 6; j++) {
        mResult[i][j] = Integer.parseInt(lineAmi[j]);
      }
    }

    // Les écarts
    for (int i = 0; i < nbAmis; i++) {
      int somme = 0;
      for (int j = 0; j < 5; j++) {
        mEcart[i][j] = Math.abs(mResult[i][j] - Integer.parseInt(mesNotes[j]));
        somme += mEcart[i][j];
      }
      sommeEcart[i] = somme;
    }

    //Création d'une copie du tableau des écart
    for (int i = 0; i < nbAmis; i++) {
      sommeEcartBis[i] = sommeEcart[i];
    }
    
    // Tri tableau Bis
    int taille = sommeEcartBis.length;
    while (taille > 1) {
      for (int i = 0; i < taille - 1; i++) {
        if (sommeEcartBis[i] > sommeEcartBis[i + 1]) {
          int tmp = sommeEcartBis[i];
          sommeEcartBis[i] = sommeEcartBis[i + 1];
          sommeEcartBis[i + 1] = tmp;
        }
      }
      taille = taille - 1;
    }

    //Récupère les indices dans l'ordre pour les valeurs identiques
    for (int i = 0; i < nbAmis; i++) {
      for (int j = 0; j < nbAmis; j++) {
        if (sommeEcartBis[j] == sommeEcart[i]) {
          mIndice[j] = i;
        }
      }

    }
    
    //On récupère les résultat du dernier film et on calcul
    int total = 0;
    
    while(count < meilleurA) {
     total += mResult[mIndice[count]][5];
     count++;
    }
    
    //On affiche le résultat
    System.out.println(Math.abs(total/meilleurA));
  }

}
