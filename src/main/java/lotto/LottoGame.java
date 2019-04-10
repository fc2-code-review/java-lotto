package lotto;

import lotto.domain.*;
import lotto.utils.LottoRankChecker;
import lotto.utils.RandomNumberGenerator;
import lotto.view.InputConsoleView;
import lotto.view.OutputConsoleView;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LottoGame {
    private LottoMachine lottoMachine;
    private WinPrice winPrice;
    private InputConsoleView inputConsoleView;

    public LottoGame() {
        lottoMachine = new LottoMachine(new RandomNumberGenerator());
        winPrice = new WinPrice();
        inputConsoleView = new InputConsoleView();
    }

    public void run() {
        LottoMoney purchaseAmount = new LottoMoney(inputConsoleView.inputPurchaseAmount());
        List<Lotto> purchasedLottos = lottoMachine.buyLottos(purchaseAmount);
        OutputConsoleView.printLottos(purchasedLottos);

        WinningLotto winningLotto = getWinningLotto();

        List<Rank> purchasedLottosRanks = LottoRankChecker.getRanks(purchasedLottos, winningLotto);
        winPrice.addWinCount(purchasedLottosRanks);

        printResult(purchaseAmount);
    }

    private void printResult(LottoMoney purchaseAmount) {
        OutputConsoleView.printResult(winPrice);
        OutputConsoleView.printRateOfProfit(purchaseAmount, winPrice.getTotalWinPrice());
    }

    private WinningLotto getWinningLotto() {
        String winningNumbers = inputConsoleView.inputWinningNumbers();
        int bonusNo = inputConsoleView.inputBonusNo();

        return new WinningLotto(new Lotto(toListOfInteger(split(winningNumbers))), bonusNo);
    }

    private List<Integer> toListOfInteger(String[] winningNumbers) {
        return Arrays.stream(winningNumbers).map(String::trim).map(Integer::parseInt).collect(Collectors.toList());
    }

    private String[] split(String winningNumbers) {
        return winningNumbers.split(",");
    }

}
