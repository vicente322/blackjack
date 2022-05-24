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
      public static String Answer(Scanner sc){
            String a = sc.next();

            while (!a.equalsIgnoreCase("sim") && !a.equalsIgnoreCase("nao")){
                  System.out.println("Insira uma resposta valida! (SIM ou NAO)");
                  a = sc.next();
            }
            return a;
      }
      public static String Begin(Scanner sc){
            System.out.println("Deseja Iniciar? (SIM ou NAO)");

            return Answer(sc);
      }
      public static String PlayAgain(Scanner sc){
            System.out.println("Jogar de novo?");

            return Answer(sc);
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
      public static int SumCards(int[] n){
            int soma = 0;
            for (int i = 0; i < 12; i++){
                  soma = soma + n[i];
            }
            return soma;
      }
      public static boolean HitMe(Scanner sc){
            String a;
            System.out.printf("\nNova carta?\n");
            a = Answer(sc);

            return a.equalsIgnoreCase("sim");
      }
      public static boolean CheckBust(int total){

            return total > 21;
      }
      public static boolean CheckBlackjack(int total){
            return total == 21;
      }


      public static void main(String args[]){
            Scanner sc;
            Random r;
            int [] pNumbers, dNumbers; //p = player, d = dealer.
            int pTotal, dTotal, pScore, dScore;
            String [] pCards, dCards;
            String answer;

            sc = new Scanner(System.in);
            r = new Random();
            pCards = new String [12];
            dCards = new String [12];
            pNumbers = new int [12];
            dNumbers = new int [12];
            pScore = 0;
            dScore = 0;

            Title("blackjack");

            answer = Begin(sc);

            while (answer.equalsIgnoreCase("sim")){


                  pCards[0] = PullCard(r);
                  dCards[0] = PullCard(r);

                  while (dCards[0].equals(pCards[0])){
                        dCards[0] = PullCard(r);
                  }
                  pCards[1] = PullCard(r);
                  while (pCards[1].equals(pCards[0]) || pCards[1].equals(dCards[0])){
                        pCards[1] = PullCard(r);
                  }
                  dCards[1] = PullCard(r);
                  while (dCards[1].equals(pCards[0]) || dCards[1].equals(dCards[0]) || dCards[1].equals(pCards[1])){
                        dCards[1] = PullCard(r);
                  }

                  pNumbers[0] = CardValue(pCards[0]);
                  dNumbers[0] = CardValue(dCards[0]);
                  pNumbers[1] = CardValue(pCards[1]);
                  dNumbers[1] = CardValue(dCards[1]);

                  pTotal = SumCards(pNumbers);
                  dTotal = SumCards(dNumbers);

                  System.out.printf("\nSuas cartas: %s e %s\nTotal: %d\n", pCards[0], pCards[1], pTotal);
                  if (CheckBlackjack(pTotal)){
                        System.out.println("BLACKJACK!!");
                  }

                  System.out.printf("\nCarta do Dealer: %s e %s\nTotal: %d\n", dCards[0], dCards[1], dTotal);
                  if (CheckBlackjack(dTotal)){
                        System.out.println("BLACKJACK!!");
                  }

                  if (CheckBlackjack(pTotal) && CheckBlackjack(dTotal)){
                        System.out.println("EMPATE!");
                  } else if (CheckBlackjack(pTotal) && !CheckBlackjack(dTotal)){
                        System.out.printf("\nVOCE GANHOU!\n\n");
                        pScore++;
                  } else if (!CheckBlackjack(pTotal) && CheckBlackjack(dTotal)){
                        System.out.printf("\nDEALER GANHOU!\n\n");
                        dScore++;
                  } else {

                        if (HitMe(sc)){
                              pCards[2] = PullCard(r);
                              pNumbers[2] = CardValue(pCards[2]);

                              pTotal = SumCards(pNumbers);
                              System.out.printf("\nSuas cartas: %s, %s e %s\nTotal: %d\n", pCards[0], pCards[1], pCards[2], pTotal);
                              if (CheckBust(pTotal)){
                                    System.out.println("QUEIMOU!!");
                                    dScore++;
                              }
                              else if (CheckBlackjack(pTotal)){
                                    System.out.println("BLACKJACK!!");
                              }
                              else if (HitMe(sc)){
                                    pCards[3] = PullCard(r);
                                    pNumbers[3] = CardValue(pCards[3]);

                                    pTotal = SumCards(pNumbers);
                                    System.out.printf("\nSuas cartas: %s, %s, %s e %s\nTotal: %d\n", pCards[0], pCards[1], pCards[2], pCards[3], pTotal);

                                    if (CheckBust(pTotal)){
                                          System.out.println("QUEIMOU!!");
                                          dScore++;
                                    }
                                    else if (CheckBlackjack(pTotal)){
                                          System.out.println("BLACKJACK!!");
                                    }
                              }
                        }

                        if (!CheckBust(pTotal) && !CheckBust(dTotal)){
                              if (pTotal > dTotal){
                                    System.out.printf("\nVOCE GANHOU!\n\n");
                                    pScore++;
                              } else if (pTotal < dTotal){
                                    System.out.printf("\nDEALER GANHOU!\n\n");
                                    dScore++;
                              } else {
                                    System.out.printf("\nEMPATE!\n\n");
                              }
                        }
                  }
                  Score(pScore, dScore);

                  answer = PlayAgain(sc);
            }

            sc.close();
      }
}
