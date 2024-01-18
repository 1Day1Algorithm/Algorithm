import java.util.*;

class Solution {
    static PriorityQueue<Type> pq; // 라운드마다 뽑은 카드 저장
    
    static boolean[] myCards;      // 처음 카드 뭉치
    static boolean[] visitedCards; // 지나간 라운드의 카드
    static boolean[] useCards;     // 다음 라운드로 진행하기 위해 사용한 카드 (n+1)
    
    public class Type {
        int num;     // 카드 수
        int useCoin; // n+1을 만들기 위해 사용되는 코인 수 (1 or 2)
        int round;   // 라운드
        
        public Type(int num, int useCoin, int round) {
            this.num = num;
            this.useCoin = useCoin;
            this.round = round;
        }
    }
    public class Card {
        int num1;
        int num2;
        public Card(int num1, int num2) {
            this.num1 = num1;
            this.num2 = num2;
        }
    }
    public int solution(int coin, int[] cards) {
        int answer = 0;
        int n = cards.length;
        
        myCards = new boolean[n+1];
        for(int i=0; i<n/3; i++) {
            myCards[cards[i]] = true;
        }
        
        boolean[] isVisited= new boolean[n+1];
        ArrayList<Card> arr = new ArrayList<>(); // 처음 카드뭉치에서 (n+1)이 되는 두장의 카드
        int card, num;
        for(int i=0; i<n/3; i++) {
            card = cards[i];
            if(!isVisited[card]) {
                num = (n+1) - card;
                
                if(!myCards[num]) continue;
                isVisited[card] = true;
                isVisited[num] = true;
                
                arr.add(new Card(card, num));
            }
        }
        
        int card1, card2, num1, num2, round = 1;
        pq = new PriorityQueue<>((a, b) -> {
            if(a.useCoin != b.useCoin) return a.useCoin - b.useCoin;
            return a.round - b.round;
        });
        
        visitedCards = new boolean[n+1];
        useCards = new boolean[n+1];
        for(int i=n/3; i<n; i+=2) {       
            card1 = cards[i];
            card2 = cards[i+1];
            
            isContain(n, card1, round);
            isContain(n, card2, round);
            
            boolean isPossibleNext = false;
            if(arr.size() > 0) { // 기존 값만으로 다음 라운드에 넘어갈 수 있는지?
                Card c = arr.remove(0);
                useCards[c.num1] = true;
                useCards[c.num2] = true;
                isPossibleNext = true;
            } else {
                if(coin < 1) break; // 7, 11 테스트코드 이슈 해결 (예외 테스트케이스 6)
                
                while(!pq.isEmpty()) {
                    Type t = pq.poll();
                    int tempNum = (n+1) - t.num;
                    
                    if(useCards[t.num]) continue; // (1)
                    if(!visitedCards[tempNum] && !myCards[tempNum]) continue; // (2)
                    if(t.useCoin == 2 && coin < 2) continue; // (3)
                    
                    if(myCards[tempNum]) {
                        coin -= 1;
                    } else {
                        coin -= 2;
                    }
                       
                    useCards[tempNum] = true;
                    useCards[t.num] = true;
                    isPossibleNext = true;
                    break;
                }
            }
            
            if(isPossibleNext) round += 1;
            else break;
        }
        
        return round;
    }
    
    public void isContain(int n, int card, int round) {
        int num = (n+1) - card;
            
        if(myCards[num]) {                      // 처음 카드뭉치에 포함되는지?
            pq.offer(new Type(card, 1, round));
        } else {                                // 뽑는 카드뭉치에 포함되는지?
            pq.offer(new Type(card, 2, round));
        }

        visitedCards[card] = true;
    }
}
