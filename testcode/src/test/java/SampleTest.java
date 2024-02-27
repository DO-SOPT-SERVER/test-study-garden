import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class SampleTest {
    @Test
    void 성공_일더하기일은2이다() {
        Assertions.assertThat(1+1).isEqualTo(2);
    }

    @Test
    void 실패_일더하기일은3이다() {
        Assertions.assertThat(1+1).isEqualTo(3);
    }
}
