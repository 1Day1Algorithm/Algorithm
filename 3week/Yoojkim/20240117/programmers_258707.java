import java.util.*;

enum Type{
    NON, COMPLETE, INCOMPLETE, FIRST;
}

class Solution {
    final int MAX = 1000;
    
    int coinCnt;
    int N;
    Type[] types;
    
    int passTicket = 0;
    public int solution(int coin, int[] cards) {
        N = cards.length;
        coinCnt = coin;
        initializeTypes();
        
        int pickCount = getFirstPickCardCount();
        for(int i=0;i<pickCount;i++){
            pickFirst(cards[i]);
        }
        
        int round = 1;
        for(int i=pickCount;i<N;i+=2){
            pick(cards[i]);
            pick(cards[i+1]);
            
            if(!canGoNextRound()){
                return round;
            }
            
            passTicket--;
            round++;
        }
        
        return round;
    }
    
    private void initializeTypes(){
        types = new Type[N+1];
        for(int i=1;i<=N;i++){
            types[i] = Type.NON;
        }
    }
    
    private int getFirstPickCardCount(){
        return N/3;
    }
    
    private void pickFirst(int card){
        int coupleCard = N+1-card;
        Type coupleCardType = types[coupleCard];
        
        if(coupleCardType == Type.FIRST){
            passTicket++;
            
            types[card] = Type.COMPLETE;
            types[coupleCard] = Type.COMPLETE;
            
            return;
        }
        
        types[card] = Type.FIRST;
    }
    
    private void pick(int card){
        types[card] = Type.INCOMPLETE;
        
        int coupleCard = N+1-card;
        Type coupleCardType = types[coupleCard];
        
        if(coupleCardType == Type.FIRST && coinCnt>=1){
            coinCnt-=1;
            passTicket++;
            
            types[card] = Type.COMPLETE;
            types[coupleCard] = Type.COMPLETE;
            
            return;
        }
    }    
    
    private boolean canGoNextRound(){
        if(passTicket>0){
            return true;
        }
        
        if(coinCnt < 2){
            return false;
        }
        
        for(int i=1;i<=N;i++){
            if(types[i] == Type.INCOMPLETE && types[N+1-i] == Type.INCOMPLETE){
                types[i] = Type.COMPLETE;
                types[N+1-i] = Type.COMPLETE;
                
                passTicket++;
                coinCnt-=2;
        
                return true;
            }
        }
        
        return false;
    }
}
