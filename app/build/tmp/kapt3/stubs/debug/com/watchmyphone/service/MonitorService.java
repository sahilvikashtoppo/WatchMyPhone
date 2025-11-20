package com.watchmyphone.service;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\n\b\u0007\u0018\u0000 (2\u00020\u0001:\u0001(B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0083@\u00a2\u0006\u0002\u0010\u0017J\u0012\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0014H\u0016J\b\u0010\u001d\u001a\u00020\u0014H\u0016J\"\u0010\u001e\u001a\u00020\u001f2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u001fH\u0017J\b\u0010\"\u001a\u00020\u0014H\u0002J\b\u0010#\u001a\u00020\u0014H\u0002J\b\u0010$\u001a\u00020\u0014H\u0002J\u0010\u0010%\u001a\u00020\u00142\u0006\u0010&\u001a\u00020\u0016H\u0002J\b\u0010\'\u001a\u00020\u0014H\u0002R\u001e\u0010\u0003\u001a\u00020\u00048\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\f8\u0006@\u0006X\u0087.\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lcom/watchmyphone/service/MonitorService;", "Landroidx/lifecycle/LifecycleService;", "()V", "cameraHelper", "Lcom/watchmyphone/util/Camera2Helper;", "getCameraHelper", "()Lcom/watchmyphone/util/Camera2Helper;", "setCameraHelper", "(Lcom/watchmyphone/util/Camera2Helper;)V", "monitoringJob", "Lkotlinx/coroutines/Job;", "repo", "Lcom/watchmyphone/data/repository/IntruderRepository;", "getRepo", "()Lcom/watchmyphone/data/repository/IntruderRepository;", "setRepo", "(Lcom/watchmyphone/data/repository/IntruderRepository;)V", "screenReceiver", "Landroid/content/BroadcastReceiver;", "captureImage", "", "reason", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onBind", "Landroid/os/IBinder;", "intent", "Landroid/content/Intent;", "onCreate", "onDestroy", "onStartCommand", "", "flags", "startId", "startForegroundSafely", "startMonitoring", "stopMonitoring", "triggerCapture", "action", "unregisterScreenReceiver", "Companion", "app_debug"})
public final class MonitorService extends androidx.lifecycle.LifecycleService {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CHANNEL_ID = "monitor_service";
    public static final int NOTIFICATION_ID = 101;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_START_MONITORING = "com.watchmyphone.START_MONITORING";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_STOP_MONITORING = "com.watchmyphone.STOP_MONITORING";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_CAPTURE = "com.watchmyphone.ACTION_CAPTURE";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ACTION_USER_PRESENT = "com.watchmyphone.ACTION_USER_PRESENT";
    @kotlin.jvm.Volatile()
    private static volatile boolean isRunning = false;
    @javax.inject.Inject()
    public com.watchmyphone.util.Camera2Helper cameraHelper;
    @javax.inject.Inject()
    public com.watchmyphone.data.repository.IntruderRepository repo;
    @org.jetbrains.annotations.Nullable()
    private android.content.BroadcastReceiver screenReceiver;
    @org.jetbrains.annotations.Nullable()
    private kotlinx.coroutines.Job monitoringJob;
    @org.jetbrains.annotations.NotNull()
    public static final com.watchmyphone.service.MonitorService.Companion Companion = null;
    
    public MonitorService() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.watchmyphone.util.Camera2Helper getCameraHelper() {
        return null;
    }
    
    public final void setCameraHelper(@org.jetbrains.annotations.NotNull()
    com.watchmyphone.util.Camera2Helper p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.watchmyphone.data.repository.IntruderRepository getRepo() {
        return null;
    }
    
    public final void setRepo(@org.jetbrains.annotations.NotNull()
    com.watchmyphone.data.repository.IntruderRepository p0) {
    }
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @java.lang.Override()
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.P)
    public int onStartCommand(@org.jetbrains.annotations.Nullable()
    android.content.Intent intent, int flags, int startId) {
        return 0;
    }
    
    private final void startMonitoring() {
    }
    
    private final void stopMonitoring() {
    }
    
    private final void unregisterScreenReceiver() {
    }
    
    private final void triggerCapture(java.lang.String action) {
    }
    
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.P)
    private final java.lang.Object captureImage(java.lang.String reason, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final void startForegroundSafely() {
    }
    
    @java.lang.Override()
    public void onDestroy() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public android.os.IBinder onBind(@org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0086T\u00a2\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u000f"}, d2 = {"Lcom/watchmyphone/service/MonitorService$Companion;", "", "()V", "ACTION_CAPTURE", "", "ACTION_START_MONITORING", "ACTION_STOP_MONITORING", "ACTION_USER_PRESENT", "CHANNEL_ID", "NOTIFICATION_ID", "", "<set-?>", "", "isRunning", "()Z", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        public final boolean isRunning() {
            return false;
        }
    }
}