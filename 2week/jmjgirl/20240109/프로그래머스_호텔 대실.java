import java.util.*;
class Solution {
    public int solution(String[][] book_time) {
        int answer = 0;
        Arrays.sort(book_time, (a,b) -> a[0].compareTo(b[0]));

        ArrayList<Integer> rooms = new ArrayList<>();
        for(int i=0; i<book_time.length; i++) {
            int checkIn = timeToMinute(book_time[i][0]);
            int checkOut = timeToMinute(book_time[i][1]);
            if(rooms.size()==0) rooms.add(checkOut + 10);
            else {
                // 입실 불가능하면
                rooms.add(checkOut+10);
                
                for(int j=0; j<rooms.size(); j++) {
                    if(rooms.get(j) <= checkIn) { // 입실 가능
                        rooms.set(j,checkOut+10);
                        rooms.remove(rooms.size()-1); 
                        break;
                    }
                }
            }
        }
        return rooms.size();
    }
    
    public int timeToMinute(String time) {
        String[] times = time.split(":");
        int minutes = 60 * Integer.parseInt(times[0]) + Integer.parseInt(times[1]);
        return minutes;
    }
}
