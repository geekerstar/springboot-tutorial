package com.geekerstar.handler;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

/**
 * @author geekerstar
 * date: 2019/10/29 13:11
 * description: 经理
 */
public class ManagerTaskHandler implements TaskListener {

    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.setAssignee("jl");
    }

}
