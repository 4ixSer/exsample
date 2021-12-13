package com.gss.microrecipient.task;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

@Component
@Slf4j
public class Scheduler {

    private final AtomicInteger testGauge;
    private final Counter testCounter;

    public Scheduler(MeterRegistry meterRegistry) {
        log.info("Scheduler start");
        testGauge = meterRegistry.gauge("custom_gauge", new AtomicInteger(0));
        testCounter = meterRegistry.counter("custom_counter");
    }

    private static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    @Scheduled(fixedRateString = "1000", initialDelayString = "0")
    public void schedulingTask() {
        log.info("schedulingTask");
        testGauge.set(Scheduler.getRandomNumberInRange(0, 100));
        testCounter.increment();
    }
}