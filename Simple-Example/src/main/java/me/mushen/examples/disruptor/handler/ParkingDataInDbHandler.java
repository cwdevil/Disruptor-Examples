package me.mushen.examples.disruptor.handler;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;
import me.mushen.examples.disruptor.event.InParkingDataEvent;

/**
 * @Desc
 * @Author Remilia
 * @Create 2017-11-13
 */
public class ParkingDataInDbHandler implements EventHandler<InParkingDataEvent>, WorkHandler<InParkingDataEvent> {
    @Override
    public void onEvent(InParkingDataEvent event) throws Exception {
        long threadId = Thread.currentThread().getId();
        String carLicense = event.getCarLicense();
        System.out.println(String.format("Thread Id %s save %s into db ....", threadId, carLicense));
    }

    @Override
    public void onEvent(InParkingDataEvent event, long sequence, boolean endOfBatch) throws Exception {
        this.onEvent(event);
    }
}
