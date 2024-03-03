import java.util.*;

class Route implements Comparable<Route>{
    int start;
    int end;
    
    public Route(int start, int end){
        this.start=start;
        this.end=end;
    }
    public int compareTo(Route r){
        return this.start - r.start;
    }
}

class Solution {
    public int solution(int[][] routes) {
        List<Route> routeList = new ArrayList<>();
        for(int[] route:routes){
            routeList.add(new Route(route[0], route[1]));
        }
        
        Collections.sort(routeList);
        
        Stack<Route> stack = new Stack<>();
        for(Route route:routeList){
            if(stack.isEmpty()){
                stack.push(route);
                continue;
            }
            
            Route peekedRoute = stack.peek();
            if(peekedRoute.end<route.start){
                stack.push(route);
                continue;
            }
            
            
            int newStart = route.start;
            int newEnd = peekedRoute.end<route.end?peekedRoute.end:route.end;
            
            stack.pop();
            stack.push(new Route(newStart, newEnd));
        }
        
        return stack.size();
    }
}
