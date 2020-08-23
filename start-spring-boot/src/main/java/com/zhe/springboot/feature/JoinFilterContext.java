package com.zhe.springboot.feature;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 进房过滤
 *
 * @author lichao
 * @since 下午3:11 2019/11/13
 */
@AllArgsConstructor
@Getter
@Builder
public class JoinFilterContext implements Feature.Context {
    /**
     * 分场类型
     */
    private Integer subType;
    /**
     * 游戏类型
     */
    private Integer gameType;
    /**
     * 房间号
     */
    private long teamId;
    /**
     * 用户Id
     */
    private String uid;

    @Setter
    private boolean filter;

}
