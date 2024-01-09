class Solution {
    public String solution(String melody, String[] musicInfos) {
        // 음악 제목을 None으로 초기화
        String answer = "(None)";
        
        // 최대 재생 시간 초기화
        int maxPlayTime = -1;

        // 네오의 멜로디에서 #를 안쓰는 알파벳으로 변경.
        melody = convertingScale(melody);

        // 모든 음악 정보 분리 반복
        for (String info : musicInfos) {
       
            String[] splitInfo = info.split(",");
            int startTime = convertToMinute(splitInfo[0]);
            int endTime = convertToMinute(splitInfo[1]);
            String title = splitInfo[2];
            String musicMelody = convertingScale(splitInfo[3]);

           
            int playTime = endTime - startTime;
            
            // 음악의 악보에서 #를 안쓰는 알파벳으로 변경.
            musicMelody = convertingScale(musicMelody);

            // 악보의 길이가 재생 시간보다 길다면, 재생 시간만큼으로 악보를 자르기.
           
            musicMelody = getFullMelody(musicMelody, playTime);

           
            if (musicMelody.contains(melody) && playTime > maxPlayTime) {
                answer = title;
                maxPlayTime = playTime;
            }
        }

        //  최대 재생 시간을 가진 음악 리턴
        return maxPlayTime == -1 ? "(None)" : answer;
    }

    // 
    private static String convertingScale(String s) {
        return s.replace("C#", "c")
                .replace("D#", "d")
                .replace("F#", "f")
                .replace("G#", "g")
                .replace("A#", "a");
    }

    // 분 단위로 변환하는 메서드
    private static int convertToMinute(String time) {
        String[] hourAndMinute = time.split(":");
        return Integer.parseInt(hourAndMinute[0]) * 60 + Integer.parseInt(hourAndMinute[1]);
    }

    // 악보를 재생 시간만큼 생성 메서드
    private static String getFullMelody(String melody, int playTime) {
        return melody.length() >= playTime ?
               melody.substring(0, playTime) :
               new String(new char[playTime / melody.length() + 1]).replace("\0", melody)
               + melody.substring(0, playTime % melody.length());
    }
}
