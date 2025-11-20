package com.watchmyphone.ui.intruder;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 %2\u0012\u0012\u0004\u0012\u00020\u0002\u0012\b\u0012\u00060\u0003R\u00020\u00000\u0001:\u0002%&B-\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0006J\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\f0\u0019J\u001c\u0010\u001a\u001a\u00020\u00062\n\u0010\u001b\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u001c\u001a\u00020\bH\u0016J\u001c\u0010\u001d\u001a\u00060\u0003R\u00020\u00002\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\bH\u0016J\u0014\u0010!\u001a\u00020\u00062\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00020\u0019J\u0010\u0010#\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\fH\u0002R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\u000e@BX\u0086\u000e\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u00a8\u0006\'"}, d2 = {"Lcom/watchmyphone/ui/intruder/IntruderAdapter;", "Landroidx/recyclerview/widget/ListAdapter;", "Lcom/watchmyphone/data/local/IntruderEntity;", "Lcom/watchmyphone/ui/intruder/IntruderAdapter$VH;", "onClick", "Lkotlin/Function1;", "", "onSelectionChanged", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "selectedIds", "", "", "<set-?>", "", "selectionMode", "getSelectionMode", "()Z", "changeItemTextAlpha", "b", "Lcom/watchmyphone/databinding/ItemIntruderBinding;", "alpha", "", "clearSelection", "getSelectedIds", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "toggleSelectAll", "items", "toggleSelection", "id", "Companion", "VH", "app_debug"})
public final class IntruderAdapter extends androidx.recyclerview.widget.ListAdapter<com.watchmyphone.data.local.IntruderEntity, com.watchmyphone.ui.intruder.IntruderAdapter.VH> {
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<com.watchmyphone.data.local.IntruderEntity, kotlin.Unit> onClick = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function1<java.lang.Integer, kotlin.Unit> onSelectionChanged = null;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Set<java.lang.Long> selectedIds = null;
    private boolean selectionMode = false;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.recyclerview.widget.DiffUtil.ItemCallback<com.watchmyphone.data.local.IntruderEntity> DIFF = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.watchmyphone.ui.intruder.IntruderAdapter.Companion Companion = null;
    
    public IntruderAdapter(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.watchmyphone.data.local.IntruderEntity, kotlin.Unit> onClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Integer, kotlin.Unit> onSelectionChanged) {
        super(null);
    }
    
    public final boolean getSelectionMode() {
        return false;
    }
    
    private final void toggleSelection(long id) {
    }
    
    public final void toggleSelectAll(@org.jetbrains.annotations.NotNull()
    java.util.List<com.watchmyphone.data.local.IntruderEntity> items) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.lang.Long> getSelectedIds() {
        return null;
    }
    
    public final void clearSelection() {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public com.watchmyphone.ui.intruder.IntruderAdapter.VH onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.watchmyphone.ui.intruder.IntruderAdapter.VH holder, int position) {
    }
    
    public final void changeItemTextAlpha(@org.jetbrains.annotations.NotNull()
    com.watchmyphone.databinding.ItemIntruderBinding b, float alpha) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/watchmyphone/ui/intruder/IntruderAdapter$Companion;", "", "()V", "DIFF", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "Lcom/watchmyphone/data/local/IntruderEntity;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lcom/watchmyphone/ui/intruder/IntruderAdapter$VH;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "b", "Lcom/watchmyphone/databinding/ItemIntruderBinding;", "(Lcom/watchmyphone/ui/intruder/IntruderAdapter;Lcom/watchmyphone/databinding/ItemIntruderBinding;)V", "bind", "", "item", "Lcom/watchmyphone/data/local/IntruderEntity;", "app_debug"})
    public final class VH extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        @org.jetbrains.annotations.NotNull()
        private final com.watchmyphone.databinding.ItemIntruderBinding b = null;
        
        public VH(@org.jetbrains.annotations.NotNull()
        com.watchmyphone.databinding.ItemIntruderBinding b) {
            super(null);
        }
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.watchmyphone.data.local.IntruderEntity item) {
        }
    }
}