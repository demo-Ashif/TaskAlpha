# TaskAlpha
ToDo Task checking App

## Features

1. **MVVM Architecture**
2. **Jetpack Compose** 
3. **Clean Architecture**
4. **Paging 3** (Added but Not implemented)
5. **Offline Support with RemoteMediator & Room**
6. **WorkManager Demo**

## Work Manager
For demonstration purpose added Work Manager and injected via Dagger Hilt. We can trigger sync data from our use case based on situation and user events if necessayry. 

From Use Case: 
1. TriggerTaskSyncUseCase 

In Data Layer:
1. SyncManager - Method to add constraints and enqueue work
2. SyncWorker - This will do the actual work

Dependency Injection
1. WorkerModule & WorkerFactoryEntryPoint
2. In AppModule add syncmanger in TaskRepository as parameter

**Screen Recording added in ScreenRecording directory**
