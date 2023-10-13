package demo.dev.taskalpha.core.di

import android.content.Context
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.Module
import dagger.Provides
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@EntryPoint
interface WorkerFactoryEntryPoint {
    fun workerFactory(): HiltWorkerFactory
}

@Module
@InstallIn(SingletonComponent::class)
object WorkerModule {
    @Provides
    fun provideWorkManagerConfiguration(
        @ApplicationContext appContext: Context,
        workerFactory: HiltWorkerFactory
    ): Configuration {
        return Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()
    }
}
