package com.zhe.springboot.bean;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Table {

    /**
     * 房间编号
     */
    private String roomId;

    /**
     * 房间名
     */
    private String name;

    /**
     * 分场编号
     */
    private int subType;

    private String matchId;

    /**
     * 当前牌局玩家用户信息
     */
    private String players;

    /**
     * 阶段状态
     */
    private int stage;

    /**
     * 桌台状态
     */
    private int tableStatus;

    /**
     * 桌台最后所出的牌组信息
     */
    private String tableCards;

    /**
     * 当前操作玩家位置
     */
    private String actionSid;

    /**
     * 操作超时时间戳
     */
    private String timeOut;

    /**
     * 庄家位置
     */
    private String buttonSid;

    /**
     * 回合数
     */
    private String round;

    /**
     * 游戏开始的时间戳
     */
    private Long startTime;

    /**
     * 游戏结束时间戳
     */
    private Long countDown;

}
