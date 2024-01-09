import java.util.*;

class Time implements Comparable<Time>{
    int hour;
    int minute;

    public Time(int hour, int minute){
        this.hour = hour;
        this.minute = minute;
    }

    public int compareTo(Time time){
        if(this.hour < time.hour){
            return -1;
        } else if(this.hour == time.hour){
            return this.minute - time.minute;
        } else{
            return 1;
        }
    }
}

class Book implements Comparable<Book>{
    Time start;
    Time end;

    public Book(Time start, Time end){
        this.start = start;
        this.end=end;
    }

    //정렬 기준: 예약 시작 시간 
    public int compareTo(Book book){
        return this.start.compareTo(book.start);
    }
}

class Solution {
    public int solution(String[][] book_time) {
        PriorityQueue<Book> schedule = new PriorityQueue<>();

        for(String[] book:book_time){
            String[] startStr = book[0].split(":");
            String[] endStr = book[1].split(":");

            Time start = new Time(Integer.parseInt(startStr[0]), Integer.parseInt(startStr[1]));
            Time end = new Time(Integer.parseInt(endStr[0]), Integer.parseInt(endStr[1]));
            schedule.add(new Book(start, end));
        }

        int roomCount =0;
        PriorityQueue<Time> roomQueue = new PriorityQueue<>(); 
        while(!schedule.isEmpty()){
            Book book = schedule.poll();

            if(!roomQueue.isEmpty()){
                Time minEndTime = roomQueue.peek();

                if(canUseThisRoom(minEndTime, book)){
                    roomQueue.poll();
                } else{
                    roomCount++;
                }

            } else {
                roomCount++;
            }

                roomQueue.add(book.end);
        }

        return roomCount;
    }

    private boolean canUseThisRoom(Time room, Book book){
        Time startTime = book.start;

        int hour = startTime.hour - room.hour;
        int minute = startTime.minute - room.minute;

        int wholeMinute = hour*60 + minute;

        if(wholeMinute>=10){
            return true;
        }

        return false;
    }
}
