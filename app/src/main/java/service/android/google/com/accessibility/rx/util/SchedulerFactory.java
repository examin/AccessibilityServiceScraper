package service.android.google.com.accessibility.rx.util;

import rx.Scheduler;
import rx.schedulers.Schedulers;

public class SchedulerFactory {
    public Scheduler schedulerIO() {
        return Schedulers.io();
    }
}