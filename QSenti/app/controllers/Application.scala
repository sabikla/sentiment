package controllers

import java.util.Date
import org.quartz.JobBuilder.newJob
import org.quartz.SimpleScheduleBuilder.simpleSchedule
import org.quartz.CronScheduleBuilder._
import org.quartz.TriggerBuilder.newTrigger
import org.quartz.impl.StdSchedulerFactory
import org.quartz.Job
import org.quartz.JobExecutionContext
import play.api._
import play.api.mvc._
import play.api.data._
import play.api.data.Forms._
import org.quartz.JobKey


object Application extends Controller {
  
  def index = Action {implicit request =>
    val scheduler = StdSchedulerFactory .getDefaultScheduler();
    Logger.info("Quarz scheduler starting...")

    scheduler.start();
    
    val job = newJob(classOf[MyWorker]).withIdentity("job1", "group1").build();
    val jobKey = new JobKey("job1", "group1")
    
    if(scheduler.checkExists(jobKey)){      
      Logger.info("Quartz scheduler shutdown.")
      scheduler.deleteJob(jobKey)      
    }
    
//    val trigger = newTrigger()
//      .withIdentity("trigger1", "group1")
//      .startNow()
//      .withSchedule(simpleSchedule()
//        .withIntervalInSeconds(10)
//        .repeatForever())
//      .build();
//    
//     val trigger = newTrigger()
//    .withIdentity("trigger1", "group1")
//    .withSchedule(cronSchedule("0 3 12 * * ?"))
//    .forJob("job1", "group1")
//    .build();
//     
//    scheduler.scheduleJob(job, trigger);
    
  
    Logger.debug("Hello Worlds")
    Ok(views.html.main("QSenti | Login", Login.loginForm))
//    Ok("Working with the the log scheduler")
  }
  
  

  

}
