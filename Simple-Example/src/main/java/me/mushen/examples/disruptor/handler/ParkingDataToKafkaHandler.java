package me.mushen.examples.disruptor.handler;

import com.lmax.disruptor.EventHandler;
import me.mushen.examples.disruptor.event.InParkingDataEvent;

/**
 * @Desc
 * @Author Remilia
 * @Create 2017-11-13
 */
public class ParkingDataToKafkaHandler implements EventHandler<InParkingDataEvent> {

    @Override
    public void onEvent(InParkingDataEvent event, long sequence, boolean endOfBatch) throws Exception {
        long threadId = Thread.currentThread().getId();
        String carLicense = event.getCarLicense();
        System.out.println(String.format("Thread Id %s send %s in plaza messsage to kafka...", threadId, carLicense));
    }
}

