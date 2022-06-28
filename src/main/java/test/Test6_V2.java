package test;

import java.io.*;
import java.time.LocalTime;
import java.util.*;

public class Test6_V2 {
    class Ticket{
        private int totalTicket;
        
        public Ticket(int totalTicket){
            this.totalTicket = totalTicket;
        }

        public void decrease() {
            totalTicket--;
        }

        public void increase() {
            totalTicket++;
        }

        public boolean isSoldOut(){
            return totalTicket == 0;
        }
    }
    
    class Ticketing{
        private final LocalTime startTime = LocalTime.of(9,00,00);
        private final LocalTime endTime = LocalTime.of(9,59,59);
        private final String id;
        private LocalTime accessTime;

        public Ticketing(String id, String accessTime) {
            this.id = id;
            this.accessTime = LocalTime.parse(accessTime);
        }

        public boolean notTicketingTime(){
            return accessTime.isBefore(startTime) || accessTime.isAfter(endTime);
        }

        public String getId(){
            return id;
        }

        public LocalTime getAccessTime(){
            return accessTime;
        }
    }

    class ActionFactory{
        public Action of(String action, LocalTime lastAccessTime) {
            if(Objects.equals(action,"request")){
                return new Request(lastAccessTime);
            }else if(Objects.equals(action,"leave")){
                return new Leave();
            }

            throw new RuntimeException("잘못된 명령어");
        }
    }

    interface Action{
        boolean execute(Ticket ticket, Ticketing ticketing, Set<String> ids);
    }

    class Request implements Action{
        private LocalTime lastAccessTime;
        public Request(LocalTime lastAccessTime) {
            this.lastAccessTime = lastAccessTime;
        }

        @Override
        public boolean execute(Ticket ticket, Ticketing ticketing, Set<String> ids) {
            if(ticketing.getAccessTime().isBefore(lastAccessTime.plusMinutes(1))){
                return false;
            }
            ids.add(ticketing.getId());
            ticket.decrease();

            return true;
        }
    }

    class Leave implements Action{

        @Override
        public boolean execute(Ticket ticket, Ticketing ticketing, Set<String> ids) {
            ids.remove(ticketing.getId());
            ticket.increase();

            return false;
        }
    }

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
        System.out.println(new Test6_V2().solution(totalTicket, logs));
    }

    public List<String> solution(int totalTicket, String[] logs) {
        Set<String> ids = new HashSet<>();
        Ticket ticket = new Ticket(totalTicket);
        LocalTime lastAccessTime = LocalTime.of(8,59,00);

        for(int i=0; i<logs.length; i++){
            String[] values = logs[i].split(" ");
            String id = values[0];
            String action = values[1];
            String accessTime = values[2];

            Ticketing ticketing = new Ticketing(id,accessTime);

            if (ticket.isSoldOut() || ticketing.notTicketingTime()){
                break;
            }

            ActionFactory actionFactory = new ActionFactory();
            Action act = actionFactory.of(action,lastAccessTime);
            boolean purchaseSuccess = act.execute(ticket,ticketing,ids);

            if(purchaseSuccess){
                lastAccessTime = LocalTime.parse(accessTime);
            }
        }

        List<String> answers = new ArrayList<>(ids);
        Collections.sort(answers);
        return answers;
    }

}
