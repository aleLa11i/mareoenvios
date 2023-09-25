package sube.interviews.mareoenvios.runnable;

import lombok.Data;

@Data
public class TaskThread extends Thread {
    private Long shippingId;

    public TaskThread( Runnable target, Long id ){
        super(target);
        this.shippingId = id;
    }

    @Override
    public synchronized void start() {
        super.start();
    }
}
