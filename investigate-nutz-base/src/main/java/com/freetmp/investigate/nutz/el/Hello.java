package com.freetmp.investigate.nutz.el;

import org.nutz.el.opt.RunMethod;
import org.nutz.plugin.Plugin;

import java.util.List;

/**
 * Created by LiuPin on 2015/3/3.
 */
public class Hello implements RunMethod,Plugin {
    /**
     * @return 当前插件是否能正常工作
     */
    @Override
    public boolean canWork() {
        return true;
    }

    /**
     * 根据传入的参数执行方法
     *
     * @param fetchParam 参数, 即EL表达式中, 函数括号内的内容.
     */
    @Override
    public Object run(List<Object> fetchParam) {
        StringBuilder sb = new StringBuilder();
        sb.append("Hello ");
        if(fetchParam.size() == 0){
            sb.append("there is no greeters!");
        }else if(fetchParam.size() >= 1){
            sb.append(fetchParam.get(0));
            for(int index = 1; index < fetchParam.size(); index++){
                sb.append("," + fetchParam.get(index));
            }

        }
        return sb.toString();
    }

    /**
     * 取得方法自身的符号
     */
    @Override
    public String fetchSelf() {
        return "hello";
    }
}
