package demo.dev.taskalpha.core.app

import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkManager
import dagger.hilt.android.EntryPointAccessors
import dagger.hilt.android.HiltAndroidApp
import demo.dev.taskalpha.core.di.WorkerFactoryEntryPoint

@HiltAndroidApp
class TaskApp : Application() {
    override fun onCreate() {
        super.onCreate()

        val component = EntryPointAccessors.fromApplication(
            applicationContext,
            WorkerFactoryEntryPoint::class.java
        )
//        WorkManager.initialize(
//            this,
//            Configuration.Builder()
//                .setWorkerFactory(component.workerFactory())
//                .build()
//        )
    }
}