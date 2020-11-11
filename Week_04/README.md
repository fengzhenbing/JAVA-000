学习笔记
 本周作业：（必做）思考有多少种方式，在main函数启动一个新线程或线程池， * 异步运行一个方法，拿到这个方法的返回值后，退出主线程？ 写出你的方法，越多越好，提交到github。
 
- thread.join让主线程等待
- 加synchronized锁 wait让主线程等待
- LockSupport  park让主线程等待
- thread.suspend让主线程等待
- ReentrantLock lock 让主线程
- ReentrantLock condition.await 让主线程等待 
- CountDownLatch latch.await 让主线程等待
- CyclicBarrier await让主线程等待
- Semaphore acquire让主线程等待
- 线程池提交Callable，让主线程future.get() 等待
- Thread包装FutureTask执行，让主线程futureTask.get() 等待
- CompletableFuture.supplyAsync 返回futureTask，让主线程futureTask.get() 等待
