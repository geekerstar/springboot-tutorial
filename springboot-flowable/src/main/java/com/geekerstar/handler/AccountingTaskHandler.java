package com.geekerstar.handler;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

/**
 * @author geekerstar
 * date: 2019/10/29 19:56
 * description: 会计
 */
public class AccountingTaskHandler implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        delegateTask.setAssignee("kj");
    }
}
