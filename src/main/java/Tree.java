
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.util.*;


public class Tree {

    static class Person {
        String name;
        String parentName;

        Person child;

        public void setChild(Person child) {
            this.child = child;
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
        parameters.put("Soheyl",new Person("Soheyl" ,""));

        ObjectMapper objectMapper = new ObjectMapper();
        for (String key : parameters.keySet()){
            try{
                Person p = parameters.get(key);
                Person parent = parameters.get(p.parentName);
                parent.setChild(p);
                parameters.remove(key);
            }catch (Exception e){
                System.out.println(key + " is a grand parent");
            }
        }
        Gson gson = new Gson();
        JsonNode jsonNode = objectMapper.readTree(gson.toJson(parameters));

        System.out.println(jsonNode.toString());
    }

}