package test;

import java.io.*;
import java.time.LocalTime;
import java.util.*;

public class Test6 {
    public static void main(String[] args) throws IOException {
        InputStream is = System.in;
        BufferedInputStream bis = new BufferedInputStream(is,8192);
        BufferedReader br = new BufferedReader(new InputStreamReader(bis),8192);

        int totalTicket = Integer.parseInt(br.readLine());
        String[] logs = {
                "woni request 09:12:29",
                "brown request 09:23:11",
                "brown leave 09:23:44",
                "jason request 09:33:51",
                "jun request 09:33:56",
                "cu request 09:34:02",
        };
        System.out.println(solution(totalTicket, logs));
    }

    public static List<String> solution(int totalTicket, String[] logs) {
        Set<String> result = new HashSet<>();
        LocalTime endTime = LocalTime.of(9,59,59);
        LocalTime accessTime = LocalTime.of(8,59,00);

        for(int i=0; i<logs.length; i++){
            if (totalTicket == 0){
                break;
            }

            String[] values = logs[i].split(" ");
            String id = values[0];
            String action = values[1];
            LocalTime time = LocalTime.parse(values[2]);

            if (time.isAfter(endTime)){
                break;
            }

            if(Objects.equals(action,"request")){
                if(time.isBefore(accessTime.plusMinutes(1))){
                    continue;
                }

                accessTime = time;

                if(!result.contains(id)){
                    result.add(id);
                    totalTicket--;
                }
            }else if(Objects.equals(action,"leave")){
                result.remove(id);
                totalTicket++;
            }
        }

        List<String> answers = new ArrayList<>(result);
        Collections.sort(answers);

        return answers;
    }

}
