package com.dynamiclayer.components.bottomSheet

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.PixelFormat
import android.view.Gravity
import android.view.KeyEvent
import android.view.View
import android.view.ViewTreeObserver
import android.view.WindowManager
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerDefaults.scrimColor
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.AbstractComposeView
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.platform.ViewRootForInspector
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.collapse
import androidx.compose.ui.semantics.dismiss
import androidx.compose.ui.semantics.expand
import androidx.compose.ui.semantics.paneTitle
import androidx.compose.ui.semantics.popup
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.Velocity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.SecureFlagPolicy
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.lifecycle.setViewTreeViewModelStoreOwner
import androidx.savedstate.findViewTreeSavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.dynamiclayer.components.bottomSheet.util.components.BottomSheetMainContent
import com.dynamiclayer.components.bottomSheet.util.components.BottomSheetTop
import com.dynamiclayer.components.bottomSheet.util.models.BottomSheetContentType
import com.dynamiclayer.components.bottomSheet.util.models.BottomSheetDefaultsProperties
import com.dynamiclayer.components.bottomSheet.util.models.BottomSheetProperties
import com.dynamiclayer.components.bottomSheet.util.models.BottomSheetState
import com.dynamiclayer.components.bottomSheet.util.models.BottomSheetValue
import com.dynamiclayer.components.bottomSheet.util.models.DraggableAnchors
import com.dynamiclayer.components.bottomSheet.util.models.rememberCustomBottomSheetState
import com.dynamiclayer.components.ui.theme.ColorPalette
import kotlinx.coroutines.launch
import java.util.UUID
import kotlin.math.max

@SuppressLint("UnusedBoxWithConstraintsScope")
@ExperimentalMaterial3Api
@ExperimentalFoundationApi
@Composable
fun GenericBottomSheet(
    modifier: Modifier = Modifier,
    sheetState: BottomSheetState = rememberCustomBottomSheetState(skipPartiallyExpanded = true),
    title: String? = null,
    headLine: String,
    description: String,
    sheetContent: BottomSheetContentType = BottomSheetContentType.None,
    content: (@Composable (Modifier) -> Unit)? = null,
    mainContent: (@Composable () -> Unit)? = null,
    onDismissRequest: () -> Unit = {},
) {
    val density = LocalDensity.current
    val navBarHeight = WindowInsets.navigationBars.getBottom(density)

    val collapsedHeight = if (title != null) 72.dp else 37.dp
    SideEffect {
        sheetState.density = density
    }
    val scope = rememberCoroutineScope()
    val animateToDismiss: () -> Unit = {
        if (sheetState.anchoredDraggableState.confirmValueChange(BottomSheetValue.Hidden)) {
            scope.launch { sheetState.hide() }.invokeOnCompletion {
                if (!sheetState.isVisible) {
                    onDismissRequest()
                }
            }
        }
    }
    val settleToDismiss: (velocity: Float) -> Unit = {
        scope.launch { sheetState.settle(it) }.invokeOnCompletion {
            if (!sheetState.isVisible) onDismissRequest()
        }
    }


    BottomSheetPopup(
        modifier = modifier,
        properties = BottomSheetDefaultsProperties.properties(),
        onDismissRequest = {
            if (sheetState.currentValue == BottomSheetValue.Expanded && sheetState.hasPartiallyExpandedState) {
                scope.launch { sheetState.partialExpand() }
            } else {
                scope.launch { sheetState.hide() }.invokeOnCompletion { onDismissRequest() }
            }
        },
    ) {
        BoxWithConstraints(Modifier.fillMaxSize()) {
            val fullHeight = constraints.maxHeight + navBarHeight

            mainContent?.let { it() }
            Scrim(
                color = scrimColor,
                onDismissRequest = animateToDismiss,
                visible = sheetState.targetValue != BottomSheetValue.Hidden
            )

            Surface(
                modifier = Modifier
                    .widthIn(max = 640.dp)
                    .fillMaxWidth()
                    .align(Alignment.TopCenter)
                    .semantics { paneTitle = title ?: "" }
                    .offset {
                        IntOffset(
                            0,
                            sheetState
                                .requireOffset()
                                .toInt()
                        )
                    }
                    .nestedScroll(
                        remember(sheetState) {
                            ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(
                                sheetState = sheetState,
                                orientation = Orientation.Vertical,
                                onFling = settleToDismiss
                            )
                        }
                    )
                    .draggable(
                        state = sheetState.anchoredDraggableState.draggableState,
                        orientation = Orientation.Vertical,
                        enabled = sheetState.isVisible,
                        startDragImmediately = sheetState.anchoredDraggableState.isAnimationRunning,
                        onDragStopped = { settleToDismiss(it) }
                    )
                    .modalBottomSheetAnchors(
                        sheetState = sheetState,
                        density = density,
                        fullHeight = fullHeight.toFloat(),
                        topPartHeight = collapsedHeight
                    ),
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                color = ColorPalette.White,
                tonalElevation = 1.0.dp,
            ) {
                Box(
                    modifier = modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    ConstraintLayout(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        val collapseActionLabel = "Collapse"
                        val dismissActionLabel = "Dismiss"
                        val expandActionLabel = "Expand"

                        val (sheetTopRef, mainContentRef, contentRef) = createRefs()

                        Box(
                            Modifier
                                .constrainAs(sheetTopRef) {
                                    top.linkTo(parent.top)
                                }
                                .semantics(mergeDescendants = true) {
                                    with(sheetState) {
                                        dismiss(dismissActionLabel) {
                                            animateToDismiss()
                                            true
                                        }
                                        if (currentValue == BottomSheetValue.PartiallyExpanded) {
                                            expand(expandActionLabel) {
                                                if (anchoredDraggableState.confirmValueChange(
                                                        BottomSheetValue.Expanded
                                                    )
                                                ) {
                                                    scope.launch { sheetState.expand() }
                                                }
                                                true
                                            }
                                        } else if (hasPartiallyExpandedState) {
                                            collapse(collapseActionLabel) {
                                                if (anchoredDraggableState.confirmValueChange(
                                                        BottomSheetValue.PartiallyExpanded
                                                    )
                                                ) {
                                                    scope.launch { partialExpand() }
                                                }
                                                true
                                            }
                                        }
                                    }
                                }
                        ) {
                            BottomSheetTop(
                                title = title
                            )
                        }
                        BottomSheetMainContent(
                            modifier = Modifier.constrainAs(mainContentRef) {
                                top.linkTo(sheetTopRef.bottom)
                            },
                            headLine = headLine,
                            description = description,
                            content = sheetContent
                        )
                        content?.let {
                            it(Modifier.constrainAs(contentRef) {
                                top.linkTo(
                                    mainContentRef.bottom
                                )
                                bottom.linkTo(parent.bottom, margin = 16.dp)
                            })

                        }
                    }
                }
            }
        }

    }

    if (sheetState.hasExpandedState) {
        LaunchedEffect(sheetState) {
            sheetState.show()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun BottomSheetPopup(
    modifier: Modifier = Modifier,
    properties: BottomSheetProperties,
    onDismissRequest: () -> Unit,
    content: @Composable () -> Unit
) {
    val view = LocalView.current
    val id = rememberSaveable { UUID.randomUUID() }
    val parentComposition = rememberCompositionContext()
    val currentContent by rememberUpdatedState(content)
    val layoutDirection = LocalLayoutDirection.current
    val configuration = LocalConfiguration.current
    val density = LocalDensity.current
    val navBarHeight = WindowInsets.navigationBars.getBottom(density)
    val bottomSheetWindow = remember(configuration) {
        BottomSheetWindow(
            properties = properties,
            onDismissRequest = onDismissRequest,
            composeView = view,
            navBarHeight = navBarHeight,
            saveId = id
        ).apply {
            setCustomContent(
                parent = parentComposition,
                content = {
                    Box(
                        modifier
                            .semantics { this.popup() }
                    ) {
                        currentContent()
                    }
                }
            )
        }
    }

    DisposableEffect(bottomSheetWindow) {
        bottomSheetWindow.show()
        bottomSheetWindow.superSetLayoutDirection(layoutDirection)
        onDispose {
            bottomSheetWindow.disposeComposition()
            bottomSheetWindow.dismiss()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
private class BottomSheetWindow(
    private val properties: BottomSheetProperties,
    private var onDismissRequest: () -> Unit,
    private val composeView: View,
    private val navBarHeight: Int,
    saveId: UUID
) :
    AbstractComposeView(composeView.context),
    ViewTreeObserver.OnGlobalLayoutListener,
    ViewRootForInspector {
    init {
        id = android.R.id.content
        setViewTreeLifecycleOwner(composeView.findViewTreeLifecycleOwner())
        setViewTreeViewModelStoreOwner(composeView.findViewTreeViewModelStoreOwner())
        setViewTreeSavedStateRegistryOwner(composeView.findViewTreeSavedStateRegistryOwner())
        setTag(androidx.compose.ui.R.id.compose_view_saveable_id_tag, "Popup:$saveId")
        clipChildren = false
    }

    private val windowManager =
        composeView.context.getSystemService(Context.WINDOW_SERVICE) as WindowManager

    private val displayWidth: Int
        get() = context.resources.displayMetrics.widthPixels

    private val params: WindowManager.LayoutParams =
        WindowManager.LayoutParams().apply {
            gravity = Gravity.BOTTOM or Gravity.START
            type = WindowManager.LayoutParams.TYPE_APPLICATION_PANEL
            width = displayWidth
            height = WindowManager.LayoutParams.MATCH_PARENT

            format = PixelFormat.TRANSLUCENT
            title = composeView.context.resources.getString(
                androidx.compose.ui.R.string.default_popup_window_title
            )
            token = composeView.applicationWindowToken

            flags = flags and (
                    WindowManager.LayoutParams.FLAG_IGNORE_CHEEK_PRESSES or
                            WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM
                    ).inv()

            val secureFlagEnabled =
                properties.securePolicy.shouldApplySecureFlag(composeView.isFlagSecureEnabled())
            if (secureFlagEnabled) {
                flags = flags or WindowManager.LayoutParams.FLAG_SECURE
            } else {
                flags = flags and (WindowManager.LayoutParams.FLAG_SECURE.inv())
            }

            if (!properties.isFocusable) {
                flags = flags or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
            } else {
                flags = flags and (WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE.inv())
            }

            if (navBarHeight > 0) {
                flags =
                    flags or (WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN and WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR)
                setPadding(0, 0, 0, navBarHeight)
            }
        }

    private var content: @Composable () -> Unit by mutableStateOf({})

    override var shouldCreateCompositionOnAttachedToWindow: Boolean = false
        private set

    @Composable
    override fun Content() {
        content()
    }

    fun setCustomContent(
        parent: CompositionContext? = null,
        content: @Composable () -> Unit
    ) {
        parent?.let { setParentCompositionContext(it) }
        this.content = content
        shouldCreateCompositionOnAttachedToWindow = true
    }

    fun show() {
        windowManager.addView(this, params)
    }

    fun dismiss() {
        setViewTreeLifecycleOwner(null)
        setViewTreeSavedStateRegistryOwner(null)
        composeView.viewTreeObserver.removeOnGlobalLayoutListener(this)
        windowManager.removeViewImmediate(this)
    }

    override fun dispatchKeyEvent(event: KeyEvent): Boolean {
        if (event.keyCode == KeyEvent.KEYCODE_BACK && properties.shouldDismissOnBackPress) {
            if (keyDispatcherState == null) {
                return super.dispatchKeyEvent(event)
            }
            if (event.action == KeyEvent.ACTION_DOWN && event.repeatCount == 0) {
                val state = keyDispatcherState
                state?.startTracking(event, this)
                return true
            } else if (event.action == KeyEvent.ACTION_UP) {
                val state = keyDispatcherState
                if (state != null && state.isTracking(event) && !event.isCanceled) {
                    onDismissRequest()
                    return true
                }
            }
        }
        return super.dispatchKeyEvent(event)
    }

    override fun onGlobalLayout() {
        // No-op
    }

    override fun setLayoutDirection(layoutDirection: Int) {
    }

    fun superSetLayoutDirection(layoutDirection: LayoutDirection) {
        val direction = when (layoutDirection) {
            LayoutDirection.Ltr -> android.util.LayoutDirection.LTR
            LayoutDirection.Rtl -> android.util.LayoutDirection.RTL
        }
        super.setLayoutDirection(direction)
    }
}

private fun View.isFlagSecureEnabled(): Boolean {
    val windowParams = rootView.layoutParams as? WindowManager.LayoutParams
    if (windowParams != null) {
        return (windowParams.flags and WindowManager.LayoutParams.FLAG_SECURE) != 0
    }
    return false
}

private fun SecureFlagPolicy.shouldApplySecureFlag(isSecureFlagSetOnParent: Boolean): Boolean {
    return when (this) {
        SecureFlagPolicy.SecureOff -> false
        SecureFlagPolicy.SecureOn -> true
        SecureFlagPolicy.Inherit -> isSecureFlagSetOnParent
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
internal fun ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(
    sheetState: BottomSheetState,
    orientation: Orientation,
    onFling: (velocity: Float) -> Unit
): NestedScrollConnection = object : NestedScrollConnection {
    override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
        val delta = available.toFloat()
        return if (delta < 0 && source == NestedScrollSource.Drag) {
            sheetState.anchoredDraggableState.dispatchRawDelta(delta).toOffset()
        } else {
            Offset.Zero
        }
    }

    override fun onPostScroll(
        consumed: Offset,
        available: Offset,
        source: NestedScrollSource
    ): Offset {
        return if (source == NestedScrollSource.Drag) {
            sheetState.anchoredDraggableState.dispatchRawDelta(available.toFloat()).toOffset()
        } else {
            Offset.Zero
        }
    }

    override suspend fun onPreFling(available: Velocity): Velocity {
        val toFling = available.toFloat()
        val currentOffset = sheetState.requireOffset()
        val minAnchor = sheetState.anchoredDraggableState.anchors.minAnchor()
        return if (toFling < 0 && currentOffset > minAnchor) {
            onFling(toFling)
            available
        } else {
            Velocity.Zero
        }
    }

    override suspend fun onPostFling(consumed: Velocity, available: Velocity): Velocity {
        onFling(available.toFloat())
        return available
    }

    private fun Float.toOffset(): Offset = Offset(
        x = if (orientation == Orientation.Horizontal) this else 0f,
        y = if (orientation == Orientation.Vertical) this else 0f
    )

    @JvmName("velocityToFloat")
    private fun Velocity.toFloat() = if (orientation == Orientation.Horizontal) x else y

    @JvmName("offsetToFloat")
    private fun Offset.toFloat(): Float = if (orientation == Orientation.Horizontal) x else y
}

@Composable
private fun Scrim(
    color: Color,
    onDismissRequest: () -> Unit,
    visible: Boolean
) {
    if (color.isSpecified) {
        val alpha by animateFloatAsState(
            targetValue = if (visible) 1f else 0f,
            animationSpec = TweenSpec(), label = ""
        )
        val dismissSheet = if (visible) {
            Modifier
                .pointerInput(onDismissRequest) {
                    detectTapGestures {
                        onDismissRequest()
                    }
                }
                .clearAndSetSemantics {}
        } else {
            Modifier
        }
        Canvas(
            Modifier
                .fillMaxSize()
                .then(dismissSheet)
        ) {
            drawRect(color = color, alpha = alpha)
        }
    }
}


@ExperimentalFoundationApi
@ExperimentalMaterial3Api
private fun Modifier.modalBottomSheetAnchors(
    sheetState: BottomSheetState,
    density: Density,
    fullHeight: Float,
    topPartHeight: Dp
) = onSizeChanged { sheetSize ->

    val topPartPx = with(density) { topPartHeight.toPx() }

    val newAnchors = DraggableAnchors {
        BottomSheetValue.Hidden at fullHeight
        BottomSheetValue.Collapsed at (fullHeight - topPartPx)
        if (sheetSize.height > (fullHeight / 2) && !sheetState.skipPartiallyExpanded) {
            BottomSheetValue.PartiallyExpanded at fullHeight / 2f
        }
        if (sheetSize.height != 0) {
            BottomSheetValue.Expanded at max(0f, fullHeight - sheetSize.height)
        }
    }
    val newTarget = when (sheetState.anchoredDraggableState.targetValue) {
        BottomSheetValue.Hidden -> BottomSheetValue.Hidden
        BottomSheetValue.PartiallyExpanded, BottomSheetValue.Expanded -> {
            val hasPartiallyExpandedState =
                newAnchors.hasAnchorFor(BottomSheetValue.PartiallyExpanded)
            val newTarget = if (hasPartiallyExpandedState) BottomSheetValue.PartiallyExpanded
            else if (newAnchors.hasAnchorFor(BottomSheetValue.Expanded)) BottomSheetValue.Expanded else BottomSheetValue.Hidden
            newTarget
        }

        BottomSheetValue.Collapsed -> {
            val hasCollapsedState = newAnchors.hasAnchorFor(BottomSheetValue.Collapsed)
            val newTarget = if (hasCollapsedState) BottomSheetValue.Collapsed
            else if (newAnchors.hasAnchorFor(BottomSheetValue.Expanded)) BottomSheetValue.Expanded else BottomSheetValue.Hidden
            newTarget
        }
    }

    sheetState.anchoredDraggableState.updateAnchors(newAnchors, newTarget)
}

