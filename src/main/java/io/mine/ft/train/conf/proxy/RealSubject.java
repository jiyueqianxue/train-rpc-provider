package io.mine.ft.train.conf.proxy;

/**
 * @author: machao
 * @Date: 2017年7月6日
 */
public class RealSubject implements Subject {

    /**
     * @Title:执行给定名字的任务。这里打印出任务名，并休眠500ms模拟任务执行了很长时间 
     * @author:machao
     * @Date:2017年7月6日
     * @param:
     * @return:
     */
    @Override
    public void dealTask(String taskName) {
        System.out.println("正在执行任务：" + taskName);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
