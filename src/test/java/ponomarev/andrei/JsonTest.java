package ponomarev.andrei;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class JsonTest {


    @Test
    void test() throws Exception {
        ClassLoader classLoader = JsonTest.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("auto.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Auto auto = objectMapper.readValue(inputStream, Auto.class);


        assertThat(auto
                .getName())
                .isEqualTo("Subaru");

        assertThat(auto
                .getAge())
                .isEqualTo(7);

        assertThat(auto
                .getBody())
                .contains("hatchback", "blue", "Х777ХХ199");

        assertThat(auto
                .getdriveUnit())
                .isEqualTo(true);

        assertThat(auto
                .getEngine()
                .getType())
                .isEqualTo("petrol");


        assertThat(auto
                .getEngine()
                .getCylinders())
                .isEqualTo(8);

        assertThat(auto
                .getEngine()
                .getValves())
                .isEqualTo(16);

        assertThat(auto
                .getEngine()
                .getTurbine())
                .isEqualTo(true);

    }
}
