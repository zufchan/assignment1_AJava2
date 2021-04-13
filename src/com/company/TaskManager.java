package com.company;
import java.util.List;
import java.util.Vector;


public class TaskManager {
    private int threadCount;
    private List<CSV_Annalyzer> tasks;
    public TaskManager(int threadCount) {
        this.threadCount = threadCount;
        //using vectors because they are thread safe
        this.tasks = new Vector<>();
    }

    public void addTask(CSV_Annalyzer t){
        tasks.add(t);
    }

    public void runTasks() {
        int desiredThreads = threadCount;
        Thread[] runners = new Thread[desiredThreads];
        for(int i = 0; i < desiredThreads; i++)
        {
            //lambda expression for thread
            runners[i] = new Thread(()->
            {
                CSV_Annalyzer t = null;
                while(true)
                {
                    //need synchronized block to prevent
                    //race condition between isEmpty and remove
                    synchronized (tasks)
                    {
                        if(!tasks.isEmpty())
                            t = tasks.remove(0);
                    }
                    if(t == null)
                    {
                        break;
                    }
                    else
                    {
                        t.run();
                        t = null;
                    }
                }
            });
            runners[i].start();
        }
        for(int i = 0; i < desiredThreads; i++)
        {
            try
            {
                runners[i].join();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
