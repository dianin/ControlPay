import java.util.ArrayList;
import java.util.List;

public class runner {


    public static void main(String[] args) {
        runner runner = new runner();
        runner.sum();
    }

    public ArrayList<Operation> Process(LinkedList<Customer> customers, Optional<Integer> factorOptional) {
        if (customers.size() > 0) {
            ArrayList<Operation> result = new ArrayList<>();

            for (Customer customer : customers) {
                Integer factor = customers.size() * factorOptional.get();
                Operation operation = new Operation(customer, factor);
            }

            return result;
        } else return null;


        //third 3 commmmmmit
        // 4 commit
    }


    public void sum ()
    {
        String s = "Zoolatech Credible";


        char[] chars;

        chars = s.toCharArray();
        char charTemp;
       for (int i=0; i<=chars.length-1; i++)
       {
           charTemp = chars[i];
           int count=0;
           for (int j=1; j<=chars.length-1; j++){
               if (chars[i]==chars[j]){
                   count++;
               } else {count=1;}
           }
           System.out.println(charTemp + " count:" + count);
       }


    }
}
