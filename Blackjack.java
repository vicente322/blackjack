/**
 * A classe Blackjack é um jogo de Blackjack.
 *
 * @author v.hofmeister@edu.pucrs.br
 * @version 2022-05-25
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
      public static boolean HitDealer(int total){
            return total < 17;
      }
      public static boolean CheckBust(int total){

            return total > 21;
      }
      public static boolean CheckBlackjack(int total){
            return total == 21;
      }
      public static void PrintPCards(String[] cards){
            System.out.printf("\nSuas Cartas: ");
            int [] cardsValue;
            cardsValue = new int [12];
            for (int i = 0; i < 12; i++){
                  if (cards[i] != null){
                        cardsValue[i] = CardValue(cards[i]);
                  }
            }

            int soma = SumCards(cardsValue);

            for (int i = 1; i < 13; i++){
                  if (cards[i] != null){
                        System.out.print(cards[i-1] + ", ");
                  }
                  else {
                        System.out.print("e " + cards[i-1]);
                        break;
                  }
            }

            System.out.printf("\nTotal: %d\n", soma);
      }
      public static void PrintDCards(String[] cards){
            System.out.printf("\nCarta do Dealer: ");
            int [] cardsValue;
            cardsValue = new int [12];
            for (int i = 0; i < 12; i++){
                  if (cards[i] != null){
                        cardsValue[i] = CardValue(cards[i]);
                  }
            }

            int soma = SumCards(cardsValue);

            for (int i = 1; i < 13; i++){
                  if (cards[i] != null){
                        System.out.print(cards[i-1] + ", ");
                  }
                  else {
                        System.out.print("e " + cards[i-1]);
                        break;
                  }
            }

            System.out.printf("\nTotal: %d\n", soma);
      }
      public static String[] NewCard(String[] oldCards){
            Random r = new Random();
            String [] newCards = new String [oldCards.length];

            for (int i = 0; i < 12; i++){
                  if (oldCards[i] != null){
                        newCards[i] = oldCards[i];
                  } else {
                        newCards[i] = PullCard(r);
                        break;
                  }
            }
            return newCards;
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
            pScore = 0;
            dScore = 0;

            Title("blackjack");

            answer = Begin(sc);

            while (answer.equalsIgnoreCase("sim")){
                  pCards = new String [12];
                  dCards = new String [12];
                  pNumbers = new int [12];
                  dNumbers = new int [12];

                  pCards = NewCard(pCards);
                  dCards = NewCard(dCards);

                  while (dCards[0].equals(pCards[0])){
                        dCards[0] = PullCard(r);
                  }
                  pCards = NewCard(pCards);
                  while (pCards[1].equals(pCards[0]) || pCards[1].equals(dCards[0])){
                        pCards[1] = PullCard(r);
                  }
                  dCards = NewCard(dCards);
                  while (dCards[1].equals(pCards[0]) || dCards[1].equals(dCards[0]) || dCards[1].equals(pCards[1])){
                        dCards[1] = PullCard(r);
                  }

                  pNumbers[0] = CardValue(pCards[0]);
                  dNumbers[0] = CardValue(dCards[0]);
                  pNumbers[1] = CardValue(pCards[1]);
                  dNumbers[1] = CardValue(dCards[1]);

                  pTotal = SumCards(pNumbers);
                  dTotal = SumCards(dNumbers);

                  PrintPCards(pCards);
                  if (CheckBlackjack(pTotal)){
                        System.out.printf("BLACKJACK!!\n\n");
                  }

                  PrintDCards(dCards);
                  if (CheckBlackjack(dTotal)){
                        System.out.printf("BLACKJACK!!\n\n");
                  }

                  if (CheckBlackjack(pTotal) && CheckBlackjack(dTotal)){
                        System.out.printf("\nEMPATE!\n\n");
                  } else if (CheckBlackjack(pTotal) && !CheckBlackjack(dTotal)){
                        System.out.printf("\nVOCE GANHOU!\n\n");
                        pScore++;
                  } else if (!CheckBlackjack(pTotal) && CheckBlackjack(dTotal)){
                        System.out.printf("\nDEALER GANHOU!\n\n");
                        dScore++;
                  } else {

                        if (HitMe(sc)){
                              pCards = NewCard(pCards);
                              pNumbers[2] = CardValue(pCards[2]);

                              pTotal = SumCards(pNumbers);
                              PrintPCards(pCards);
                              if (CheckBust(pTotal)){
                                    System.out.printf("\nQUEIMOU!!\n\n");
                                    dScore++;
                              }
                              else if (CheckBlackjack(pTotal)){
                                    System.out.printf("BLACKJACK!!\n\n");
                              }
                              else if (HitMe(sc)){
                                    pCards = NewCard(pCards);
                                    pNumbers[3] = CardValue(pCards[3]);

                                    pTotal = SumCards(pNumbers);
                                    PrintPCards(pCards);

                                    if (CheckBust(pTotal)){
                                          System.out.printf("\nQUEIMOU!!\n\n");
                                          dScore++;
                                    }
                                    else if (CheckBlackjack(pTotal)){
                                          System.out.printf("BLACKJACK!!\n\n");
                                    }
                              }
                        }

                        if (HitDealer(dTotal)){
                              dCards= NewCard(dCards);
                              dNumbers[2] = CardValue(dCards[2]);

                              dTotal = SumCards(dNumbers);
                              PrintDCards(dCards);

                              if (CheckBust(dTotal)){
                                    System.out.printf("\nQUEIMOU!!\n\n");
                              }
                              else if (CheckBlackjack(dTotal)){
                                    System.out.printf("BLACKJACK!!\n\n");
                              }
                              else if (HitDealer(dTotal)){
                                    dCards= NewCard(dCards);
                                    dNumbers[3] = CardValue(dCards[3]);

                                    dTotal = SumCards(dNumbers);
                                    PrintDCards(dCards);

                                    if (CheckBust(dTotal)){
                                          System.out.printf("\nQUEIMOU!!\n\n");
                                    }
                                    else if (CheckBlackjack(dTotal)){
                                          System.out.printf("BLACKJACK!!\n\n");
                                    }
                              }
                        }

                        if (HitDealer(dTotal)){
                              dCards[2] = PullCard(r);
                              dNumbers[2] = CardValue(dCards[2]);

                              dTotal = SumCards(dNumbers);
                              System.out.printf("\nCartas do Dealer: %s, %s e %s\nTotal: %d\n", dCards[0], dCards[1], dCards[2], dTotal);

                              if (CheckBust(dTotal)){
                                    System.out.println("QUEIMOU!!");
                              }
                              else if (CheckBlackjack(dTotal)){
                                    System.out.println("BLACKJACK!!");
                              }
                              else if (HitDealer(dTotal)){
                                    dCards[3] = PullCard(r);
                                    dNumbers[3] = CardValue(dCards[3]);

                                    dTotal = SumCards(dNumbers);
                                    System.out.printf("\nCartas do Dealer: %s, %s, %s e %s\nTotal: %d\n", dCards[0], dCards[1], dCards[2], dCards[3], dTotal);

                                    if (CheckBust(dTotal)){
                                          System.out.println("QUEIMOU!!");
                                    }
                                    else if (CheckBlackjack(dTotal)){
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
                        } else if (!CheckBust(pTotal) && CheckBust(dTotal)){
                              System.out.printf("\nVOCE GANHOU!\n\n");
                              pScore++;
                        }
                  }
                  Score(pScore, dScore);

                  answer = PlayAgain(sc);
            }

            sc.close();
      }
}
