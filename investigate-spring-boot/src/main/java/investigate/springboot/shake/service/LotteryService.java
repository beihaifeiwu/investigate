package investigate.springboot.shake.service;

import com.google.common.base.Strings;
import investigate.springboot.shake.LotteryResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.temporal.ValueRange;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by LiuPin on 2015/10/30.
 */
@Service @Slf4j
public class LotteryService {

  ConcurrentMap<String, LotteryResult> resultMap = new ConcurrentHashMap<>();

  @Value("${prize.first.probability}") double first = 0.02;
  @Value("${prize.second.probability}") double second = 0.125;
  @Value("${prize.today.first.num}") int firstNumToday = 10;
  @Value("${prize.today.second.num}") int secondNumToday = 80;
  @Value("${prize.delay.time}") int delayTime = 30;

  int firstConsumedNum = 0;
  int secondConsumedNum = 0;

  ValueRange afternoon = ValueRange.of(LocalTime.of(12, 0).toNanoOfDay(), LocalTime.of(17, 0).toNanoOfDay());
  ValueRange morning = ValueRange.of(LocalTime.of(9, 30).toNanoOfDay(), LocalTime.of(12, 0).toNanoOfDay());

  @Scheduled(cron = "0 0 0 * * ?")
  public synchronized void resetPrizePool(){
    this.firstConsumedNum = 0;
    this.secondConsumedNum = 0;
    this.resultMap.clear();
    log.info("Reset the prize pool first -> {}, second -> {}", firstNumToday, secondNumToday);
  }

  public LotteryResult getCachedResult(String openId){
    if(Strings.isNullOrEmpty(openId) || !resultMap.containsKey(openId)){
      return null;
    }
    return resultMap.get(openId);
  }

  public LotteryResult lottery(String openId){
    // 非合法用户
    if(Strings.isNullOrEmpty(openId) || openId.equalsIgnoreCase("doubi")){
      return LotteryResult.builder().inLotteryTime(false).awards(0).remainTime(delayTime * 60 * 1000).build();
    }

    LotteryResult result = resultMap.computeIfAbsent(openId, u -> new LotteryResult());

    // 不在抽奖时间
    long now = LocalTime.now().toNanoOfDay();
    if(!afternoon.isValidValue(now) && !morning.isValidValue(now)){
      result.setInLotteryTime(false);
      result.setAwards(0);
      return result;
    }

    // 判断是否处在冷却时间
    if (judgeAndResetRemainTime(result)) return result;

    // 奖池已发光
    if(firstConsumedNum >= firstNumToday || secondConsumedNum >= secondNumToday){
      result.setAwards(0);
      return result;
    }

    // 重置奖项
    if(result.getAwards() == 1) result.setAwards(-1);

    // 计算是否中奖
    synchronized (this) {

      int rand = (int) (Math.random() * 10000);

      int firstLimit = (int) (first * 10000);
      int secondLimit = (int) ((first + second) * 10000);

      log.info("OpenID[{}] lottery: first [0 ~ {}], second [{} ~ {}], rand = {}", openId, firstLimit, firstLimit, secondLimit, rand);

      // 判断是否中了一等奖
      if (rand > 0 && rand < firstLimit && firstConsumedNum <= firstNumToday) {

        if(firstConsumedNum >= firstNumToday / 2 && morning.isValidValue(now)){
          result.setAwards(0);
        } else {
          result.setAwards(1);
          firstConsumedNum += 1;
          log.info("OpenID[{}] win the first prize", openId);
        }
      }

      // 判断是否中了二等奖
      if (rand >= firstLimit && rand < secondLimit && secondConsumedNum <= secondNumToday) {

        if(secondConsumedNum >= secondNumToday / 2 && morning.isValidValue(now)){
          result.setAwards(0);
        }else {
          result.setAwards(2);
          secondConsumedNum += 1;
          log.info("OpenID[{}] win the second prize", openId);
        }
      }
    }

    // 设置本次抽奖时间
    result.setLastLotteryTime(System.currentTimeMillis());

    return result;
  }

  public boolean judgeAndResetRemainTime(LotteryResult result) {
    // 为中奖者可重复参与抽奖
    if(result.getAwards() != 1) return false;

    int delayTimeInMis = delayTime * 60 * 1000;
    long remainTime = result.getLastLotteryTime() + delayTimeInMis - System.currentTimeMillis();

    log.info("last lottery time is {}, delay time is {}, remain time is {}", result.getLastLotteryTime(), delayTimeInMis, remainTime);

    if(result.getLastLotteryTime() != 0 && remainTime > 0){
      result.setInLotteryTime(false);
      result.setAwards(-1);
      result.setRemainTime(remainTime);
      return true;
    }
    return false;
  }
}
