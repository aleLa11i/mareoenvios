package sube.interviews.mareoenvios.utils;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ThreadsUtils {

    public List<Thread> getExecutedThreads(){
        Map<Thread, StackTraceElement[]> threadMap = Thread.getAllStackTraces();

        // Crea una lista para almacenar los hilos en ejecución
        List<Thread> executedThreads = new ArrayList<>();

        for (Thread thread : threadMap.keySet()) {
            if (thread.getState() != Thread.State.TERMINATED) {
                executedThreads.add(thread);
            }
        }
        return executedThreads;
    }

}
