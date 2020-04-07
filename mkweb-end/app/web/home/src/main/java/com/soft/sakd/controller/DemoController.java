package com.soft.sakd.controller;

import com.google.common.collect.Lists;
import com.soft.sakd.core.model.entity.SResource;
import com.soft.sakd.core.model.mapper.SResourceMapper;
import com.soft.sakd.task.BigMysqlDataTask;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import javax.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
public class DemoController {

  @Autowired private SResourceMapper sResourceMapper;

  private ExecutorService executor = Executors.newFixedThreadPool(100);
  //  private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 10, 2000,
  //      TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(100));

  /**
   * 并发插入死锁
   *
   * @return
   */
  @RequestMapping("/deadlock")
  @ResponseBody
  @Transactional
  public SResource deadlock() {
    SResource entity = sResourceMapper.findByName("test");
    if (entity != null) {
      SResource entity1 = new SResource();
      entity1.setName("test");
      sResourceMapper.insert(entity1);
    }
    return entity;
  }

  /**
   * 模拟并发抢红包,金额100,每个人机会平等,一次抢100
   *
   * <p>1.使用乐观锁，前端传来ResourcesEntity信息,根据name和sortNum>=100找到test记录,对比version,更新记录
   * 2.使用悲观锁[排他锁和共享锁],前端传来ResourcesEntity信息,根据name和sortNum>=100 for update or lock in share mode
   * 找到test记录,更新记录
   *
   * @param entity
   * @return
   */
  @RequestMapping("/seckill")
  @ResponseBody
  @Transactional
  public SResource seckill(@RequestBody @NotNull SResource entity) {
    if (StringUtils.isEmpty(entity.getName())) {
      return entity;
    }
    SResource r = sResourceMapper.findByName(entity.getName());
    //    if (r != null && r.getVersion().equals(entity.getVersion())) {
    //      r.setSortNum(r.getSortNum() - 100);
    //      r.setNotes(Thread.currentThread().getName());
    //      sResourceMapper.save(r);
    //    }
    return r;
  }

  /**
   * 200万数据查询
   *
   * @return
   */
  @RequestMapping("/watch")
  @ResponseBody
  public String watch() {
    StopWatch stopWatch = StopWatch.createStarted();
    List<SResource> result = sResourceMapper.selectAll();
    stopWatch.stop();
    String times = "总耗时：" + stopWatch.getTime(TimeUnit.SECONDS) + "s, 查询条数：" + result.size();
    System.out.println(times);
    return times;
  }

  /**
   * thread 200万的数据量 18s内查询
   *
   * <p>线程屏障 1.Semaphore 2.共享volatile flag,使用wait(),notifyAll()实现
   *
   * <p>public static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor( 10, 30, 2000,
   * TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(100));
   *
   * <p>ExecutorService executorService = Executors.newCachedThreadPool();
   *
   * <p>
   *
   * @return
   */
  @RequestMapping("/thread")
  @ResponseBody
  public String thread() {
    StopWatch stopWatch = StopWatch.createStarted();
    List<Future<List<SResource>>> result = Lists.newArrayList();
    for (int i = 0; i < 100; i++) {
      result.add(executor.submit(new BigMysqlDataTask(i, 20000, sResourceMapper)));
    }
    int count = 0;
    // 分100个线程, 每个线程跑2w条数据,所以第一条数据是 0,0 最后一条数据是99,199999
    try {
      for (Future<List<SResource>> item : result) {
        List<SResource> rs = item.get();
        count = count + rs.size();
      }
    } catch (InterruptedException e) {
    } catch (ExecutionException e) {
    }
    stopWatch.stop();
    String times = "总耗时 : " + stopWatch.getTime(TimeUnit.SECONDS) + " s, 查询总数为 : " + count;
    System.out.println(times);
    return times;
  }
}
