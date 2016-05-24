package investigate.springboot.shake;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by vae on 2015/10/30.
 */
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class LotteryResult {

  /**
   * 上次抽中时间
   */
  private long lastLotteryTime;

  /**
   * 是否在抽奖时间内
   */
  private boolean inLotteryTime;

  /**
   * 剩余时间
   */
  private long remainTime;

  /**
   * 奖项，-1 处于冷却状态, 0 未中奖, 1 一等奖, 2 二等奖
   */
  private int awards = 0;

}
