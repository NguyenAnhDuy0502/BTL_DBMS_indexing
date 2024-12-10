package Product.BTL_DBMS.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import redis.clients.jedis.UnifiedJedis;
import redis.clients.jedis.DefaultJedisClientConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisClientConfig;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ConvertJsonToRedis {
    public static void main(String[] args) {
        // Tạo ObjectMapper để đọc JSON
        ObjectMapper objectMapper = new ObjectMapper();

            try{
                //Kết nối Jedis
                JedisClientConfig config = DefaultJedisClientConfig.builder()
                        .user("default")
                        .password("C5BDD3YCV8B4JpZXE3Wj7Xx9MlqCD16A")
                        .build();

                UnifiedJedis jedis = new UnifiedJedis(
                        new HostAndPort("redis-12584.c322.us-east-1-2.ec2.redns.redis-cloud.com", 12584),
                        config
                );

                // Đọc tệp JSON chứa mảng các đối tượng
                File jsonFile = new File("src/main/resources/static/10k_records_sample.json");
                List<Map<String, Object>> jsonDataList = objectMapper.readValue(jsonFile, List.class); // Đọc JSON dưới dạng danh sách


                // Lặp qua tất cả các đối tượng trong mảng
                for (Map<String, Object> jsonData : jsonDataList) {
                    String redisKey = "user:" + jsonData.get("Index"); // Tạo khóa Redis từ trường "Index"

                    // Thêm dữ liệu vào Redis dưới dạng Hash
                    jedis.hmset(redisKey, (Map<String, String>) (Map<?, ?>) jsonData);

                    System.out.println("Dữ liệu đã được thêm vào Redis với khóa: " + redisKey);
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
