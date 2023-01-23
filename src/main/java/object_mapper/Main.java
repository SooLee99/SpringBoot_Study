package object_mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import object_mapper.dto.Car;
import object_mapper.dto.User;
import java.util.Arrays;
import java.util.List;

// - ObjectMapper란?
//      JSON 형식을 사용할 때, 응답들을 직렬화하고 요청들을 역직렬화 할 때 사용하는 기술이다.

// - JSON(Javascript Object Notation)
//      "키 :값" 쌍으로 이루어진 데이터 객체를 전달하기 위해 사람이 읽을 수 있는 텍스트를 사용하는 포맷이다

// - 직렬화 (Serialize)
//      데이터를 전송하거나 저장할 때 바이트 문자열이어야 하기 때문에 객체들을 문자열로 바꾸어 주는 것
//      [Object -> String 문자열]

// - 역직렬화(Deserialize)
//      데이터가 모두 전송된 이후, 수신측에서 다시 문자열을 기존의 객체로 회복시켜주는 것
//      [String 문자열 -> Object]

public class Main {
    public static void main(String args[]) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        User user = new User();
        user.setAge(10);
        user.setName("홍길동");

        Car car1 = new Car();
        car1.setName("AUDI");
        car1.setNumber("1111");

        Car car2 = new Car();
        car2.setName("BMW");
        car2.setNumber("2222");

        List<Car> carList = Arrays.asList(car1, car2);
        user.setCar(carList);

        String json = objectMapper.writeValueAsString(user);
        System.out.println(json);

        User parsing = objectMapper.readValue(json, User.class);
        System.out.println(parsing);

        // node parsing

        JsonNode jsonNode = objectMapper.readTree(json);
        String name = jsonNode.get("name").asText();
        int age = jsonNode.get("age").asInt();
        JsonNode cars = jsonNode.get("car");
        ArrayNode arrayNode = (ArrayNode)cars;
        System.out.println(name);
        System.out.println(age);
        List<Car> _car = objectMapper.convertValue(arrayNode, new TypeReference<List<Car>>() {});
        System.out.println(_car);


        ObjectNode objectNode = (ObjectNode)jsonNode;
        objectNode.put("name","abcd");
        System.out.println(objectNode.toPrettyString());

    }
}