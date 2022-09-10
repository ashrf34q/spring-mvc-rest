package guru.springframework.springmvcrest.controllers.v1;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

public abstract class AbstractRestController {

    @Test
     public static String asJsonString(final Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch(Exception e){
            throw new RuntimeException(e);

        }

    }
}