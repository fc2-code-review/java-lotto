package lotto.domain;

public class LottoMoney {
    private static final int PRICE_PER_LOTTO = 1000;

    private final long purchaseAmount;

    public LottoMoney(long purchaseAmount) {
        validate(purchaseAmount);
        this.purchaseAmount = purchaseAmount;
    }

    private void validate(long purchaseAmount) {
        if (purchaseAmount % 1000 != 0)
            throw new IllegalArgumentException("금액이 잘못되었습니다. " + PRICE_PER_LOTTO + " 단위만 가능합니다.");
    }

    public long getPurchaseAmount() {
        return purchaseAmount;
    }

    public long getPurchaseCount() {
        return purchaseAmount / PRICE_PER_LOTTO;
    }
}
