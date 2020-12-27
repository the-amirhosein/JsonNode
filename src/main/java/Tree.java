
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.util.*;


public class Tree {

    static class Person {
        String name;
        String parentName;

        List<Person> child = new ArrayList<>();

        public void setChild(Person child) {
            this.child.add(child);
        }

        public Person(String name , String parentName){
            this.name = name;
            this.parentName = parentName;
        }
    }


    public static void main(String[] args) throws JsonProcessingException {

        Map<String, Person> parameters = new HashMap<>();
        parameters.put("Amir",new Person("Amir" ,"Hesam"));
        parameters.put("Hesam",new Person("Hesam" ,""));
        parameters.put("Soheyl",new Person("Soheyl" ,"Hesam"));
        parameters.put("Sina",new Person("Sina" ,""));

        ObjectMapper objectMapper = new ObjectMapper();
        List<String> keys = new LinkedList<>(parameters.keySet());

        for (String key : keys){

                Person child = parameters.get(key);
                Person parent = parameters.get(child.parentName);
                if (parent == null){
                    System.out.println(key + " is grand parent");
                    continue;
                }
                parent.setChild(child);
                parameters.remove(key);

        }
        Gson gson = new Gson();
        JsonNode jsonNode = objectMapper.readTree(gson.toJson(parameters));

        System.out.println(jsonNode.toString());
    }

}