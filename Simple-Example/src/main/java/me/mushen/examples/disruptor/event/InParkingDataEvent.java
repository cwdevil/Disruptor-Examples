package me.mushen.examples.disruptor.event;

import lombok.Getter;
import lombok.Setter;

/**
 * @Desc 1. 定义事件
 * @Author Remilia
 * @Create 2017-11-13
 */
@Getter
@Setter
public class InParkingDataEvent {
    private String carLicense = "";
}
