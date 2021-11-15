package hackerRank.prepareTopicJava;

import java.util.*;

public class PriorityQueueProb {

}
class Priorities{
    List<Student> getStudents(List<String> arr){
        StringTokenizer st;
        PriorityQueue<Student> pq = new PriorityQueue<>((s1, s2) -> {
            return s1.CGPA > s2.CGPA ? 1
                    : s1.CGPA < s2.CGPA ? -1
                    : !s1.name.equals(s2.name) ? s1.name.compareTo(s2.name)
                    : s1.id-s2.id;
        });
        List<Student> res = new ArrayList<>();

        String name;
        int id;
        double CGPA;

        for(int i = 0; i < arr.size(); i++){
            st = new StringTokenizer(arr.get(i)," ");
            if(st.nextToken().equals("ENTER")){
                name = st.nextToken();
                CGPA = Double.parseDouble(st.nextToken());
                id = Integer.parseInt(st.nextToken());
                pq.add(new Student(name, CGPA, id));
            } else {
                res.add(pq.poll());
            }
        }
        return res;
    }
}
class Student{
    double CGPA;
    int id;
    String name;
    Student(String name, double CGPA, int id){
        this.id = id;
        this.name = name;
        this.CGPA = CGPA;
    }
    String getName(){
        return name;
    }
}
