import java.util.*;

class Solution {
    public static Map<String, Member> memberMap;
    public class Member {
        int amount; // 판매량
        String parent; // 추천인
        public Member(int amount, String parent) {
            this.amount = amount;
            this.parent = parent;
        }
    }
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        
        memberMap = new HashMap<>();
        
        String member, recommend;
        ArrayList<String> tempArr;
        for(int i=0; i<enroll.length; i++) {
            member = enroll[i];
            recommend = referral[i];
            tempArr = new ArrayList<>();
            
            memberMap.put(member, new Member(0, recommend));
        }
        
        for(int i=0; i<seller.length; i++) {
            dfsAllocate(seller[i], (int)(amount[i]*100));
        }
        
        int[] answer = new int[enroll.length];
        for(int i=0; i<enroll.length; i++) {
            answer[i] = memberMap.get(enroll[i]).amount;
        }
        
        return answer;
    }
    
    public static int dfsAllocate(String seller, int amount) {
        if(amount < 1) return amount;
        if("-".equals(seller)) return amount;
        
        Member m = memberMap.get(seller);
        m.amount += amount - dfsAllocate(m.parent, (int)(amount * 0.1));
        memberMap.put(seller, m);
        
        return amount;
    }
}
