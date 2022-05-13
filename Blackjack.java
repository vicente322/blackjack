/**
 * A classe Blackjack é um jogo de Blackjack.
 *
 * TODO:
 * - ajustar valor das cartas.
 * - apresentar cartas com letras para A, J, Q e K.
 * - adicionar segunda carta.
 * - permitir mais cartas.
 * - perder ao passar de 21.
 * - esconder cartas do dealer.
 * - criar placar.
 *
 * @author v.hofmeister@edu.pucrs.br
 * @version 2022-05-09
 */
import java.util.Scanner;
import java.util.Random;

public class Blackjack{

      public static String Title(String titulo){
            System.out.printf("\n" + titulo.toUpperCase() + "!\n\n");
            return "";
      }
      public static String Score(int score1, int score2){
            System.out.printf("%d X %d\n", score1, score2);
            return "";
      }
      public static String Card(int card){
            String c;
            c = "";

            if (card == 1){
                  c = "A";
            } else if (card == 11){
                  c = "J";
            } else if (card == 12){
                  c = "Q";
            } else if (card == 13){
                  c = "K";
            } else {
                  c = String.valueOf(card);
            }

            return c;
      }
      public static int CardValue(String card){
        int value;
        if (card.equals("A")){
          value = 11;
        } else if (card.equals("K") || card.equals("Q") || card.equals("J")){
          value = 10;
        } else {
          value = Integer.parseInt(card);
        }

        return value;
      }

      public static void main(String args[]){
            Scanner sc;
            Random r;
            int pNumber, dNumber, pScore, dScore; //p = player, d = dealer.
            String answer, pCard, dCard;

             sc = new Scanner(System.in);
             r = new Random();

             Title("blackjack");
             System.out.println("Deseja Iniciar? (SIM ou NAO)");

             answer = sc.next();

            if (!answer.equalsIgnoreCase("sim") && !answer.equalsIgnoreCase("nao")){
                  System.out.println("Insira uma resposta valida! (SIM ou NAO)");
                  answer = sc.next();
            }

            while (answer.equalsIgnoreCase("sim")){
                  pNumber = r.nextInt(13) + 1;
                  dNumber = r.nextInt(13) + 1;

                  pCard = Card(pNumber);
                  dCard = Card(dNumber);

                  pNumber = CardValue(pCard);
                  dNumber = CardValue(dCard);

                  System.out.printf("\nSua carta: %s\n",pCard);

                  System.out.printf("\nCarta do Dealer: %s\n", dCard);

                  if (pNumber > dNumber){
                        System.out.printf("\nVOCE GANHOU!\n\n");
                  } else if (pNumber < dNumber){
                        System.out.printf("\nDEALER GANHOU!\n\n");
                  } else {
                        System.out.printf("\nEMPATE!\n\n");
                  }

                  System.out.println("Jogar de novo?");

                  answer = sc.next();

                  if (!answer.equalsIgnoreCase("sim") && !answer.equalsIgnoreCase("nao")){
                        System.out.println("Insira uma resposta valida! (SIM ou NAO)");
                        answer = sc.next();
                  }
            }

            sc.close();
      }
}
