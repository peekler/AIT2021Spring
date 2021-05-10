package hu.bme.aut.jobschedulerworkerdemo

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.work.*
import hu.bme.aut.jobschedulerworkerdemo.databinding.ActivityMainBinding
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnWorkerStart.setOnClickListener {
            val constraints = Constraints.Builder()
                    //.setRequiresCharging(true)
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            val workRequest =
                    OneTimeWorkRequest.Builder(MyWorker::class.java).
                    setConstraints(constraints).build()
            WorkManager.getInstance().enqueue(workRequest)

            /*val workRequest = androidx.work.PeriodicWorkRequest.Builder(
                    MyWorker::class.java, PeriodicWorkRequest.MIN_PERIODIC_FLEX_MILLIS,
                    TimeUnit.MILLISECONDS).setConstraints(constraints).build()
            WorkManager.getInstance().enqueueUniquePeriodicWork(
                    "demorepeate", ExistingPeriodicWorkPolicy.REPLACE, workRequest
            )*/
        }
    }
}