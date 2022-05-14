/**
 * A classe Blackjack é um jogo de Blackjack.
 *
 * @author v.hofmeister@edu.pucrs.br
 * @version 2022-05-09
 */
import java.util.Scanner;
import java.util.Random;

public class Blackjack{

      public static void Title(String titulo){
            System.out.printf("\n\n\n" + titulo.toUpperCase() + "!\n\n");
      }
      public static void Score(int score1, int score2){
            System.out.printf("%d X %d\n", score1, score2);;
      }
      public static String PullCard(Random r){
            String c;
            int n;
            c = "";
            n = r.nextInt(13) + 1;

            if (n == 1){
                  c = "A";
            } else if (n == 11){
                  c = "J";
            } else if (n == 12){
                  c = "Q";
            } else if (n == 13){
                  c = "K";
            } else {
                  c = String.valueOf(n);
            }

            n = r.nextInt(4);

            if (n == 0){
              c += " de paus";
            } else if (n == 1){
              c += " de copas";
            } else if (n == 2){
              c += " de espadas";
            } else if (n == 3){
              c += " de ouros";
            }

            return c;
      }
      public static int CardValue(String card){
        String [] c = card.split(" ");
        int value = 0;

        if (c[0].equals("A")){
          value = 11;
        } else if (c[0].equals("K") || c[0].equals("Q") || c[0].equals("J")){
          value = 10;
        } else {
          value = Integer.parseInt(c[0]);
        }

        return value;
      }
      public static  String CheckAnswer(Scanner sc, String answer){
        String a = "";

        if (!answer.equalsIgnoreCase("sim") && !answer.equalsIgnoreCase("nao")){
              System.out.println("Insira uma resposta valida! (SIM ou NAO)");
              a = sc.next();
        } else {
          a = answer;
        }

        return a;
      }

      public static void main(String args[]){
            Scanner sc;
            Random r;
            int pNumber, dNumber, pScore, dScore; //p = player, d = dealer.
            String answer, pCard, dCard;

             sc = new Scanner(System.in);
             r = new Random();
             pScore = 0;
             dScore = 0;

             Title("blackjack");
             System.out.println("Deseja Iniciar? (SIM ou NAO)");

             answer = sc.next();

            CheckAnswer(sc, answer);

            while (answer.equalsIgnoreCase("sim")){
                  pCard = PullCard(r);
                  dCard = PullCard(r);
                  while (dCard.equals(pCard)){
                    dCard = PullCard(r);
                  }

                  pNumber = CardValue(pCard);
                  dNumber = CardValue(dCard);

                  System.out.printf("\nSua carta: %s\n",pCard);

                  System.out.printf("\nCarta do Dealer: %s\n", dCard);

                  if (pNumber > dNumber){
                        System.out.printf("\nVOCE GANHOU!\n\n");
                        pScore++;
                  } else if (pNumber < dNumber){
                        System.out.printf("\nDEALER GANHOU!\n\n");
                        dScore++;
                  } else {
                        System.out.printf("\nEMPATE!\n\n");
                  }

                  Score(pScore, dScore);

                  System.out.println("Jogar de novo?");

                  answer = sc.next();

                  answer = CheckAnswer(sc, answer);
            }

            sc.close();
      }
}
