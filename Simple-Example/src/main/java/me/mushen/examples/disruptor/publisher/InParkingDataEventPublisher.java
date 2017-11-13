package me.mushen.examples.disruptor.publisher;

import com.lmax.disruptor.EventTranslator;
import com.lmax.disruptor.dsl.Disruptor;
import me.mushen.examples.disruptor.event.InParkingDataEvent;

import java.util.concurrent.CountDownLatch;

/**
 * @Desc
 * @Author Remilia
 * @Create 2017-11-13
 */
public class InParkingDataEventPublisher implements Runnable {
    private Disruptor<InParkingDataEvent> disruptor;

    private CountDownLatch latch;

    private static int LOOP = 10;//模拟10车辆入场

    public InParkingDataEventPublisher(Disruptor<InParkingDataEvent> disruptor, CountDownLatch latch) {
        this.disruptor = disruptor;
        this.latch = latch;
    }

    @Override
    public void run() {
        InParkingDataEventTranslator tradeTransloator = new InParkingDataEventTranslator();
        for(int i = 0; i < LOOP; i++){
            disruptor.publishEvent(tradeTransloator);
            try {
                Thread.currentThread().sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        latch.countDown();
        System.out.println("生产者写完" +LOOP + "个消息");
    }

    private class InParkingDataEventTranslator implements EventTranslator<InParkingDataEvent> {
        @Override
        public void translateTo(InParkingDataEvent event, long sequence) {
            this.generateTradeTransaction(event);
        }

        private InParkingDataEvent generateTradeTransaction(InParkingDataEvent event){
            int num =  (int)(Math.random()*8000);
            num = num + 1000;
            event.setCarLicense("京Z" + num);
            System.out.println("Thread Id " + Thread.currentThread().getId() + " 写完一个event");
            return event;
        }
    }
}
