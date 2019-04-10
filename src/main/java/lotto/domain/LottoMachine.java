package lotto.domain;

import lotto.utils.NumberGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * 로또를 생성해주는 객체
 */
public class LottoMachine {
    private static final int LOOP_COUNT_LIMIT = 10000;

    private NumberGenerator numberGenerator;

    public LottoMachine(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }

    public List<Lotto> buyLottos(LottoMoney lottoMoney) {
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < lottoMoney.getPurchaseCount(); i++)
            lottos.add(getLotto());
        return lottos;
    }

    protected Lotto getLotto() {
        return new Lotto(generateNonDuplicateNumbers());
    }

    private List<Integer> generateNonDuplicateNumbers() {
        Set<Integer> nonDuplicateNumbers = new TreeSet<>();
        int cnt = 0;
        while (nonDuplicateNumbers.size() < Lotto.LOTTO_NUMBER_SIZE
                && cnt++ < LOOP_COUNT_LIMIT) {
            nonDuplicateNumbers.add(numberGenerator.getNumber());
        }
        validateInfiniteLoop(cnt);

        return toList(nonDuplicateNumbers);
    }

    private void validateInfiniteLoop(int cnt) {
        if (cnt > LOOP_COUNT_LIMIT)
            throw new RuntimeException("로또 번호 생성과정에서 무한루프 발생.");
    }

    private ArrayList<Integer> toList(Set<Integer> nonDuplicateNumbers) {
        return new ArrayList<>(nonDuplicateNumbers);
    }

    public void setNumberGenerator(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
    }
}
