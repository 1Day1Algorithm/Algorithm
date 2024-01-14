import java.util.*;

class Solution {
    public String solution(String p) {
        return getCorrectStr(p);
    }

    private String[] seperate(String w){
        char[] chars = w.toCharArray();
        int leftCnt=0; int rightCnt=0;
        String[] result = new String[2];
        for(int i=0;i<w.length();i++){
            if(chars[i] == '('){
                leftCnt++;
            } else if(chars[i] == ')'){
                rightCnt++;
            }

            if(leftCnt!=0 && leftCnt == rightCnt){
                StringBuilder sb = new StringBuilder();
                for(int j=0;j<=i;j++){
                    sb.append(chars[j]);
                }

                result[0] = sb.toString();

                sb = new StringBuilder();
                for(int j=i+1;j<w.length();j++){
                    sb.append(chars[j]);
                }

                result[1] = sb.toString();

                break;
            }
        }

        return result;
    }

    private boolean isCorrectStr(String u){
        Stack<Character> stack = new Stack<>();

        for(char c:u.toCharArray()){

            if(!stack.isEmpty()) {
                char peeked = stack.peek();
                if (peeked == '(' && c ==')') {
                    stack.pop();
                    continue;
                }
            }

            stack.push(c);
        }

        if(stack.isEmpty()){
            return true;
        }

        return false;
    }

    private String getCorrectStr(String w){
        if(w.isEmpty()){
            return "";
        }

        String[] strs = seperate(w);

        String u = strs[0]; String v = getCorrectStr(strs[1]);

        StringBuilder sb = new StringBuilder();
        if(isCorrectStr(u)){
            sb.append(u).append(v);
        } else{
            sb.append('(').append(v).append(')').append(getCorrectUStr(u));
        }

        return sb.toString();
    }

    private String getCorrectUStr(String u){
        u = u.substring(1, u.length()-1);

        StringBuilder sb = new StringBuilder();
        for(char c:u.toCharArray()){
            if(c=='('){
                sb.append(')');
            } else if(c==')'){
                sb.append('(');
            }
        }

        return sb.toString();
    }
}
