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
            } else if (c[0].equals("")){
                  value = 0;
            }else {
                  value = Integer.parseInt(c[0]);
            }

        return value;
      }
      public static String Answer(Scanner sc){
            String a = sc.next();

            while (!a.equalsIgnoreCase("sim") && !a.equalsIgnoreCase("nao")){
                  System.out.println("Insira uma resposta valida! (SIM ou NAO)");
                  a = sc.next();
            }
            return a;
      }
      public static int SumCards(int c1, int c2, int c3, int c4){
            return (c1 + c2 + c3 + c4);
      }
      public static boolean HitMe(Scanner sc){
            String a;
            System.out.printf("\nNova carta?\n");
            a = Answer(sc);

            return a.equalsIgnoreCase("sim");
      }
      public static void ShowCards(String c1, String c2, String c3, String c4){

      }

      public static void main(String args[]){
            Scanner sc;
            Random r;
            int pNumber1, pNumber2, pNumber3, pNumber4, dNumber1, dNumber2, dNumber3, dNumber4, pTotal, dTotal, pScore, dScore; //p = player, d = dealer.
            String answer, pCard1, pCard2, pCard3, pCard4, dCard1, dCard2, dCard3, dCard4;

            sc = new Scanner(System.in);
            r = new Random();
            pScore = 0;
            dScore = 0;

            Title("blackjack");
            System.out.println("Deseja Iniciar? (SIM ou NAO)");

            answer = Answer(sc);

            while (answer.equalsIgnoreCase("sim")){
                  pCard1 = PullCard(r);
                  dCard1 = PullCard(r);
                  while (dCard1.equals(pCard1)){
                        dCard1 = PullCard(r);
                  }
                  pCard2 = PullCard(r);
                  while (pCard2.equals(pCard1) || pCard2.equals(dCard1)){
                        pCard2 = PullCard(r);
                  }
                  dCard2 = PullCard(r);
                  while (dCard2.equals(pCard1) || dCard2.equals(dCard1) || dCard2.equals(pCard2)){
                        dCard2 = PullCard(r);
                  }
                  pCard3 = "";
                  dCard3 = "";
                  pCard4 = "";
                  dCard4 = "";

                  pNumber1 = CardValue(pCard1);
                  dNumber1 = CardValue(dCard1);
                  pNumber2 = CardValue(pCard2);
                  dNumber2 = CardValue(dCard2);
                  pNumber3 = CardValue(pCard3);
                  dNumber3 = CardValue(dCard3);
                  pNumber4 = CardValue(pCard4);
                  dNumber4 = CardValue(dCard4);

                  pTotal = SumCards(pNumber1, pNumber2, pNumber3, pNumber4);
                  System.out.printf("\nSuas cartas: %s e %s\nTotal: %d\n", pCard1, pCard2, pTotal);

                  dTotal = SumCards(dNumber1, dNumber2, dNumber3, dNumber4);
                  System.out.printf("\nCarta do Dealer: %s e %s\nTotal: %d\n", dCard1, dCard2, dTotal);

                  if (HitMe(sc)){
                        pCard3 = PullCard(r);
                        pNumber3 = CardValue(pCard3);

                        pTotal = SumCards(pNumber1, pNumber2, pNumber3, pNumber4);
                        System.out.printf("\nSuas cartas: %s, %s e %s\nTotal: %d\n", pCard1, pCard2, pCard3, pTotal);

                        if (HitMe(sc)){
                              pCard4 = PullCard(r);
                              pNumber4 = CardValue(pCard4);

                              pTotal = SumCards(pNumber1, pNumber2, pNumber3, pNumber4);
                              System.out.printf("\nSuas cartas: %s, %s e %s\nTotal: %d\n", pCard1, pCard2, pCard3, pCard4, pTotal);
                        }
                  }

                  if (pTotal > dTotal){
                        System.out.printf("\nVOCE GANHOU!\n\n");
                        pScore++;
                  } else if (pTotal < dTotal){
                        System.out.printf("\nDEALER GANHOU!\n\n");
                        dScore++;
                  } else {
                        System.out.printf("\nEMPATE!\n\n");
                  }

                  Score(pScore, dScore);

                  System.out.println("Jogar de novo?");

                  answer = Answer(sc);
            }

            sc.close();
      }
}
