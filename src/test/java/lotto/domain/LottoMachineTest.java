package lotto.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class LottoMachineTest {
    private int cnt = 1;

    @Test
    public void 테스트() {
        Lotto lotto1 = new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6));

        LottoMachine lottoMachine = new LottoMachine(() -> cnt++);
        Lotto lotto2 = lottoMachine.getLotto();

        Assert.assertEquals(lotto2.toString(), lotto1.toString());
    }

    /**
     * LottoMachine.getLotto()를 실행하면 중복되지 않는 번호를 6개를 구할 때까지 루프를 도는데
     * 계속 잘못된 입력을 할 경우 무한루프를 돈다.
     * 이런 상황에서 에러처리를 해주고 그것을 테스트 해주는 메소드.
     */
    @Test(expected = RuntimeException.class)
    public void 무한루프에러_테스트(){
        LottoMachine lottoMachine = new LottoMachine(() -> 1);
        lottoMachine.getLotto();
    }
}
