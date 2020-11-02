package com.geekerstar.controller;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.engine.*;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

/**
 * @author geekerstar
 * date: 2019/10/29 13:11
 * description: 工作流测试公共方法
 * <p>
 * 使用说明：
 * 1、先启动好此项目，然后创建一个流程：
 * http://localhost:8080/test/add?userId=666666&money=20000
 * 提交成功.流程Id为：c832b312-fa0f-11e9-bf7a-76be14ce6a0f
 * <p>
 * 2、查询待办列表:
 * http://localhost:8080/test/list?userId=666666
 * [Task[id=c8357238-fa0f-11e9-bf7a-76be14ce6a0f, name=出差报销]]
 * <p>
 * 3、同意：
 * http://localhost:8080/test/apply?taskId=
 * 申请通过！
 * <p>
 * 4、驳回：
 * http://localhost:8080/test/reject?taskId=
 * 申请驳回！
 * <p>
 * 5、退回任意节点
 * http://localhost:8080/test/back?taskId=
 * 退回成功
 * <p>
 * 6、生成流程图：
 * http://localhost:8080/test/processDiagram?processId=
 */
@RestController
@RequestMapping(value = "/test")
public class ExpenseController {

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private ProcessEngine processEngine;

    /**
     * 添加流程
     *
     * @param userId    用户Id
     * @param money     报销金额
     * @param descption 描述
     */
    @GetMapping(value = "/add")
    public String addExpense(String userId, Integer money, String descption) {
        //启动流程
        HashMap<String, Object> map = new HashMap<>();
        map.put("taskUser", userId);
        map.put("money", money);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("test", map);
        return "提交成功.流程Id为：" + processInstance.getId();
    }

    /**
     * 获取流程列表
     *
     * @param userId
     * @return
     */
    @GetMapping(value = "/list")
    public Object list(String userId) {
        List<Task> tasks = taskService.createTaskQuery().taskAssignee(userId).orderByTaskCreateTime().desc().list();
        for (Task task : tasks) {
            System.out.println(task.toString());
        }
        return Arrays.toString(tasks.toArray());
    }

    /**
     * 批准流程
     *
     * @param taskId
     * @return
     */
    @GetMapping(value = "/apply")
    public String apply(String taskId) {
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        if (task == null) {
            throw new RuntimeException("流程不存在");
        }
        //通过审核
        HashMap<String, Object> map = new HashMap<>();
        map.put("outcome", "通过");
        taskService.complete(taskId, map);
        return "申请通过！";
    }

    /**
     * 驳回流程
     *
     * @param taskId
     * @return
     */
    @GetMapping(value = "/reject")
    public String reject(String taskId) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("outcome", "驳回");
        taskService.complete(taskId, map);
        return "申请驳回！";
    }

    /**
     * 退回流程到任意节点
     *
     * @param processId 流程ID
     * @param currentId 当前任务ID，xml里面定义的id
     * @param targetId  目标任务ID，xml里面定义的id
     * @return
     */
    @GetMapping(value = "/back")
    public String back(String processId, String currentId, String targetId) {
        runtimeService.createChangeActivityStateBuilder()
                .processInstanceId(processId)
                .moveActivityIdsToSingleActivityId(Collections.singletonList(currentId), targetId)
                .changeState();
        return "退回成功";
    }

    /**
     * 生成流程图
     *
     * @param processId 任务ID
     */
    @GetMapping(value = "processDiagram")
    public void genProcessDiagram(HttpServletResponse httpServletResponse, String processId) throws Exception {
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();

        //流程走完的不显示图
        if (pi == null) {
            return;
        }
        Task task = taskService.createTaskQuery().processInstanceId(pi.getId()).singleResult();
        //使用流程实例ID，查询正在执行的执行对象表，返回流程实例对象
        String InstanceId = task.getProcessInstanceId();
        List<Execution> executions = runtimeService
                .createExecutionQuery()
                .processInstanceId(InstanceId)
                .list();

        //得到正在执行的Activity的Id
        List<String> activityIds = new ArrayList<>();
        List<String> flows = new ArrayList<>();
        for (Execution exe : executions) {
            List<String> ids = runtimeService.getActiveActivityIds(exe.getId());
            activityIds.addAll(ids);
        }

        //获取流程图
        BpmnModel bpmnModel = repositoryService.getBpmnModel(pi.getProcessDefinitionId());
        ProcessEngineConfiguration engconf = processEngine.getProcessEngineConfiguration();
        ProcessDiagramGenerator diagramGenerator = engconf.getProcessDiagramGenerator();
        InputStream in = diagramGenerator.generateDiagram(bpmnModel, "png", activityIds, flows, engconf.getActivityFontName(), engconf.getLabelFontName(), engconf.getAnnotationFontName(), engconf.getClassLoader(), 1.0, true);
        OutputStream out = null;
        byte[] buf = new byte[1024];
        int legth = 0;
        try {
            out = httpServletResponse.getOutputStream();
            while ((legth = in.read(buf)) != -1) {
                out.write(buf, 0, legth);
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
