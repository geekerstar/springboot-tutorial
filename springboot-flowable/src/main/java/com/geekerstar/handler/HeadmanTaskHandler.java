package com.geekerstar.handler;

import org.flowable.engine.delegate.TaskListener;
import org.flowable.task.service.delegate.DelegateTask;

/**
 *
 *
 * @author: jiangxu
 * @创建日期: 2019年10月29日 下午8:14:16
 * @Description: 组长
 */
public class HeadmanTaskHandler implements TaskListener {
	@Override
	public void notify(DelegateTask delegateTask) {
		delegateTask.setAssignee("zz");
	}

}
