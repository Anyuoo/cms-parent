package com.anyu.common.result;

/**
 * 统一结果接口，获取结果业务代码及描述信息
 *
 * @author Anyu
 * @since 2020/12/29
 */
public interface Result {
    int getCode();

    String getMessage();
}
