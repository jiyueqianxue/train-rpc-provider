package io.mine.ft.train.conf.proxy;

/**
 * @author: machao
 * @Date: 2017年7月6日
 */
public interface Subject {

    /**
     * 执行给定名字的任务。
     * 
     * @param taskName
     *            任务名
     */
    public void dealTask(String taskName);

}
