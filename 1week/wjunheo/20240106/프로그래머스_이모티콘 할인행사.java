class Solution {
    //최대 가입자 와 최대 매출
        int maxCount =0;
        int maxSales =0;
    public int[] solution(int[][] users, int[] emoticons) {
        
        int[] answer = new int[2];
        //할인 조합용 배열
        int[] discounts = new int[emoticons.length];
        //가능한 모든 할인 조합 만들가
            combinationsGen(discounts,0,users, emoticons);
        //최종 결과를 배열에 저장
        answer[0]= maxCount;
        answer[1]= maxSales;
            return answer;
        
    
    }
    //할인 조합 만들기
    public void combinationsGen(int[]discounts, int index, int[][] users, int[] emoticons){
     if(index == discounts.length){
         doCompare(discounts, users, emoticons);
         return;
     }   
        for(int discount =10; discount<=40; discount +=10){
           discounts[index] =discount;
            // 할인 조합 만들기
            combinationsGen(discounts, index +1, users, emoticons);
            
        }
        
    }
    public void doCompare(int[] discounts, int[][] users, int[] emoticons){
        int count =0;
        int sales=0;
        
        for(int[]user :users){
            int userDiscount =user[0];
            int userBudget =user[1];
            int totalCost =0;
            
            for(int i =0; i<discounts.length; i++){
                if(discounts[i]>= userDiscount){
                   totalCost+= (emoticons[i] * (100 - discounts[i]) / 100);
                }
            }
            if(totalCost >= userBudget){
                count++;
            }else {
                sales += totalCost;
            }
        }
        if(count > maxCount || (count == maxCount && sales>maxSales)){
            maxCount =count;
            maxSales =sales;
        }
    }
}
