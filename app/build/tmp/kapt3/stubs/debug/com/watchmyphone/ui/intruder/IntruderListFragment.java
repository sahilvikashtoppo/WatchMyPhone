package com.watchmyphone.ui.intruder;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0015H\u0002J\b\u0010\u0017\u001a\u00020\u0015H\u0002J$\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010 \u001a\u00020\u0015H\u0016J\u001a\u0010!\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020\u00192\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0017J\b\u0010#\u001a\u00020\u0015H\u0002J\b\u0010$\u001a\u00020\u0015H\u0002J\b\u0010%\u001a\u00020\u0015H\u0002J\b\u0010&\u001a\u00020\u0015H\u0002J\u0010\u0010\'\u001a\u00020\u00152\u0006\u0010(\u001a\u00020)H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\tR\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u001a\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006*"}, d2 = {"Lcom/watchmyphone/ui/intruder/IntruderListFragment;", "Landroidx/fragment/app/Fragment;", "()V", "_binding", "Lcom/watchmyphone/databinding/FragmentIntruderListBinding;", "adapter", "Lcom/watchmyphone/ui/intruder/IntruderAdapter;", "binding", "getBinding", "()Lcom/watchmyphone/databinding/FragmentIntruderListBinding;", "intruderViewModel", "Lcom/watchmyphone/viewmodel/IntruderViewModel;", "getIntruderViewModel", "()Lcom/watchmyphone/viewmodel/IntruderViewModel;", "intruderViewModel$delegate", "Lkotlin/Lazy;", "permissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "", "", "checkAndRequestPermissions", "", "observeData", "observeServiceState", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onViewCreated", "view", "setupRecycler", "setupSelectionUI", "startMonitorService", "stopMonitorService", "updateSelectionUI", "count", "", "app_debug"})
public final class IntruderListFragment extends androidx.fragment.app.Fragment {
    @org.jetbrains.annotations.Nullable()
    private com.watchmyphone.databinding.FragmentIntruderListBinding _binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy intruderViewModel$delegate = null;
    private com.watchmyphone.ui.intruder.IntruderAdapter adapter;
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String[]> permissionLauncher = null;
    
    public IntruderListFragment() {
        super();
    }
    
    private final com.watchmyphone.databinding.FragmentIntruderListBinding getBinding() {
        return null;
    }
    
    private final com.watchmyphone.viewmodel.IntruderViewModel getIntruderViewModel() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    @androidx.annotation.RequiresApi(value = android.os.Build.VERSION_CODES.O)
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupRecycler() {
    }
    
    private final void observeData() {
    }
    
    /**
     * Observe service ON/OFF state from DataStore
     */
    private final void observeServiceState() {
    }
    
    private final void checkAndRequestPermissions() {
    }
    
    private final void startMonitorService() {
    }
    
    private final void stopMonitorService() {
    }
    
    private final void updateSelectionUI(int count) {
    }
    
    private final void setupSelectionUI() {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
}