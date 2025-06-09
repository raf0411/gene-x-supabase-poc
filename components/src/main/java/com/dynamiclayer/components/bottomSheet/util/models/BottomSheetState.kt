package com.dynamiclayer.components.bottomSheet.util.models
import androidx.annotation.FloatRange
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animate
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.MutatePriority
import androidx.compose.foundation.MutatorMutex
import androidx.compose.foundation.gestures.DragScope
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.DraggableState
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import com.dynamiclayer.components.bottomSheet.util.models.BottomSheetValue.Collapsed
import com.dynamiclayer.components.bottomSheet.util.models.BottomSheetValue.Expanded
import com.dynamiclayer.components.bottomSheet.util.models.BottomSheetValue.Hidden
import com.dynamiclayer.components.bottomSheet.util.models.BottomSheetValue.PartiallyExpanded
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlin.math.abs

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@Composable
fun rememberCustomBottomSheetState(
    skipPartiallyExpanded: Boolean = false,
    confirmValueChange: (BottomSheetValue) -> Boolean = { true },
) = rememberBottomSheetState(skipPartiallyExpanded, confirmValueChange, Collapsed, false)

@ExperimentalFoundationApi
@Composable
@ExperimentalMaterial3Api
internal fun rememberBottomSheetState(
    skipPartiallyExpanded: Boolean = false,
    confirmValueChange: (BottomSheetValue) -> Boolean = { true },
    initialValue: BottomSheetValue = Collapsed,
    skipHiddenState: Boolean = false,
): BottomSheetState {

    val density = LocalDensity.current
    return rememberSaveable(
        skipPartiallyExpanded, confirmValueChange,
        saver = BottomSheetState.Saver(
            skipPartiallyExpanded = skipPartiallyExpanded,
            confirmValueChange = confirmValueChange,
            density = density
        )
    ) {
        BottomSheetState(
            skipPartiallyExpanded,
            density,
            initialValue,
            confirmValueChange,
            skipHiddenState
        )
    }
}

@ExperimentalFoundationApi
@Stable
@ExperimentalMaterial3Api
class BottomSheetState(
    internal val skipPartiallyExpanded: Boolean,
    initialValue: BottomSheetValue = Collapsed,
    confirmValueChange: (BottomSheetValue) -> Boolean = { true },
    internal val skipHiddenState: Boolean = false,
) {
    @ExperimentalMaterial3Api
    constructor(
        skipPartiallyExpanded: Boolean,
        density: Density,
        initialValue: BottomSheetValue = Collapsed,
        confirmValueChange: (BottomSheetValue) -> Boolean = { true },
        skipHiddenState: Boolean = false,
    ) : this(skipPartiallyExpanded, initialValue, confirmValueChange, skipHiddenState) {
        this.density = density
    }
    init {
        if (skipPartiallyExpanded) {
            require(initialValue != PartiallyExpanded) {
                "The initial value must not be set to PartiallyExpanded if skipPartiallyExpanded " +
                        "is set to true."
            }
        }
        if (skipHiddenState) {
            require(initialValue != Hidden) {
                "The initial value must not be set to Hidden if skipHiddenState is set to true."
            }
        }
    }
    val currentValue: BottomSheetValue get() = anchoredDraggableState.currentValue
    val targetValue: BottomSheetValue get() = anchoredDraggableState.targetValue
    val isVisible: Boolean
        get() = anchoredDraggableState.currentValue != Hidden
    fun requireOffset(): Float = anchoredDraggableState.requireOffset()

    val hasExpandedState: Boolean
        get() = anchoredDraggableState.anchors.hasAnchorFor(Expanded)

    val hasPartiallyExpandedState: Boolean
        get() = anchoredDraggableState.anchors.hasAnchorFor(PartiallyExpanded)

    suspend fun expand() {
        anchoredDraggableState.animateTo(Expanded)
    }
    suspend fun partialExpand() {
        check(!skipPartiallyExpanded) {
            "Attempted to animate to partial expanded when skipPartiallyExpanded was enabled. Set" +
                    " skipPartiallyExpanded to false to use this function."
        }
        animateTo(PartiallyExpanded)
    }

    suspend fun show() {
        val targetValue = when {
            hasPartiallyExpandedState -> PartiallyExpanded
            else -> Expanded
        }
        animateTo(targetValue)
    }

    suspend fun hide() {
        animateTo(Hidden)
    }

    internal suspend fun animateTo(
        targetValue: BottomSheetValue,
        velocity: Float = anchoredDraggableState.lastVelocity
    ) {
        anchoredDraggableState.animateTo(targetValue, velocity)
    }
    internal suspend fun snapTo(targetValue: BottomSheetValue) {
        anchoredDraggableState.snapTo(targetValue)
    }

    internal suspend fun settle(velocity: Float) {
        anchoredDraggableState.settle(velocity)
    }

    internal var anchoredDraggableState = AnchoredDraggableState(
        initialValue = initialValue,
        animationSpec = SpringSpec(),
        confirmValueChange = confirmValueChange,
        positionalThreshold = { with(requireDensity()) { 56.dp.toPx() } },
        velocityThreshold = { with(requireDensity()) { 125.dp.toPx() } }
    )

    internal val offset: Float get() = anchoredDraggableState.offset

    internal var density: Density? = null
    private fun requireDensity() = requireNotNull(density) {
        "BottomSheetState did not have a density attached. Are you using BottomSheetState with " +
                "BottomSheetScaffold or ModalBottomSheet component?"
    }

    companion object {
        fun Saver(
            skipPartiallyExpanded: Boolean,
            confirmValueChange: (BottomSheetValue) -> Boolean,
            density: Density
        ) = Saver<BottomSheetState, BottomSheetValue>(
            save = { it.currentValue },
            restore = { savedValue ->
                BottomSheetState(skipPartiallyExpanded, density, savedValue, confirmValueChange)
            }
        )
        fun Saver(
            skipPartiallyExpanded: Boolean,
            confirmValueChange: (BottomSheetValue) -> Boolean
        ) = Saver<BottomSheetState, BottomSheetValue>(
            save = { it.currentValue },
            restore = { savedValue ->
                BottomSheetState(skipPartiallyExpanded, savedValue, confirmValueChange)
            }
        )
    }
}

@ExperimentalFoundationApi
class DraggableAnchorsConfig<T> {

    internal val anchors = mutableMapOf<T, Float>()
    @Suppress("BuilderSetStyle")
    infix fun T.at(position: Float) {
        anchors[this] = position
    }
}

@ExperimentalFoundationApi
fun <T : Any> DraggableAnchors(
    builder: DraggableAnchorsConfig<T>.() -> Unit
): DraggableAnchors<T> = MapDraggableAnchors(DraggableAnchorsConfig<T>().apply(builder).anchors)

@ExperimentalFoundationApi
fun <T> Modifier.anchoredDraggable(
    state: AnchoredDraggableState<T>,
    orientation: Orientation,
    enabled: Boolean = true,
    reverseDirection: Boolean = false,
    interactionSource: MutableInteractionSource? = null,
    startDragImmediately: Boolean = state.isAnimationRunning
) = draggable(
    state = state.draggableState,
    orientation = orientation,
    enabled = enabled,
    interactionSource = interactionSource,
    reverseDirection = reverseDirection,
    startDragImmediately = startDragImmediately,
    onDragStopped = { velocity -> launch { state.settle(velocity) } }
)

@ExperimentalFoundationApi
interface AnchoredDragScope {
    fun dragTo(
        newOffset: Float,
        lastKnownVelocity: Float = 0f
    )
}

@Stable
@ExperimentalFoundationApi
class AnchoredDraggableState<T>(
    initialValue: T,
    internal val positionalThreshold: (totalDistance: Float) -> Float,
    internal val velocityThreshold: () -> Float,
    val animationSpec: AnimationSpec<Float>,
    internal val confirmValueChange: (newValue: T) -> Boolean = { true }
) {
    @ExperimentalFoundationApi
    constructor(
        initialValue: T,
        anchors: DraggableAnchors<T>,
        positionalThreshold: (totalDistance: Float) -> Float,
        velocityThreshold: () -> Float,
        animationSpec: AnimationSpec<Float>,
        confirmValueChange: (newValue: T) -> Boolean = { true }
    ) : this(
        initialValue,
        positionalThreshold,
        velocityThreshold,
        animationSpec,
        confirmValueChange
    ) {
        this.anchors = anchors
        trySnapTo(initialValue)
    }

    private val dragMutex = MutatorMutex()

    internal val draggableState = object : DraggableState {

        private val dragScope = object : DragScope {
            override fun dragBy(pixels: Float) {
                with(anchoredDragScope) {
                    dragTo(newOffsetForDelta(pixels))
                }
            }
        }

        override suspend fun drag(
            dragPriority: MutatePriority,
            block: suspend DragScope.() -> Unit
        ) {
            this@AnchoredDraggableState.anchoredDrag(dragPriority) {
                with(dragScope) { block() }
            }
        }

        override fun dispatchRawDelta(delta: Float) {
            this@AnchoredDraggableState.dispatchRawDelta(delta)
        }
    }

    var currentValue: T by mutableStateOf(initialValue)
        private set

    val targetValue: T by derivedStateOf {
        dragTarget ?: run {
            val currentOffset = offset
            if (!currentOffset.isNaN()) {
                computeTarget(currentOffset, currentValue, velocity = 0f)
            } else currentValue
        }
    }

    internal val closestValue: T by derivedStateOf {
        dragTarget ?: run {
            val currentOffset = offset
            if (!currentOffset.isNaN()) {
                computeTargetWithoutThresholds(currentOffset, currentValue)
            } else currentValue
        }
    }

    var offset: Float by mutableFloatStateOf(Float.NaN)
        private set

    fun requireOffset(): Float {
        check(!offset.isNaN()) {
            "The offset was read before being initialized. Did you access the offset in a phase " +
                    "before layout, like effects or composition?"
        }
        return offset
    }

    val isAnimationRunning: Boolean get() = dragTarget != null

    @get:FloatRange(from = 0.0, to = 1.0)
    val progress: Float by derivedStateOf(structuralEqualityPolicy()) {
        val a = anchors.positionOf(currentValue)
        val b = anchors.positionOf(closestValue)
        val distance = abs(b - a)
        if (!distance.isNaN() && distance > 1e-6f) {
            val progress = (this.requireOffset() - a) / (b - a)
            // If we are very close to 0f or 1f, we round to the closest
            if (progress < 1e-6f) 0f else if (progress > 1 - 1e-6f) 1f else progress
        } else 1f
    }

    var lastVelocity: Float by mutableFloatStateOf(0f)
        private set

    private var dragTarget: T? by mutableStateOf(null)

    var anchors: DraggableAnchors<T> by mutableStateOf(emptyDraggableAnchors())
        private set

    fun updateAnchors(
        newAnchors: DraggableAnchors<T>,
        newTarget: T = if (!offset.isNaN()) {
            newAnchors.closestAnchor(offset) ?: targetValue
        } else targetValue
    ) {
        if (anchors != newAnchors) {
            anchors = newAnchors
            val snapSuccessful = trySnapTo(newTarget)
            if (!snapSuccessful) {
                dragTarget = newTarget
            }
        }
    }

    suspend fun settle(velocity: Float) {
        val previousValue = this.currentValue
        val targetValue = computeTarget(
            offset = requireOffset(),
            currentValue = previousValue,
            velocity = velocity
        )
        if (confirmValueChange(targetValue)) {
            animateTo(targetValue, velocity)
        } else {
            animateTo(previousValue, velocity)
        }
    }

    private fun computeTarget(
        offset: Float,
        currentValue: T,
        velocity: Float
    ): T {
        val currentAnchors = anchors
        val currentAnchorPosition = currentAnchors.positionOf(currentValue)
        val velocityThresholdPx = velocityThreshold()
        return if (currentAnchorPosition == offset || currentAnchorPosition.isNaN()) {
            currentValue
        } else {
            if (abs(velocity) >= abs(velocityThresholdPx)) {
                currentAnchors.closestAnchor(
                    offset,
                    offset - currentAnchorPosition > 0
                )!!
            } else {
                val neighborAnchor =
                    currentAnchors.closestAnchor(
                        offset,
                        offset - currentAnchorPosition > 0
                    )!!
                val neighborAnchorPosition = currentAnchors.positionOf(neighborAnchor)
                val distance = abs(currentAnchorPosition - neighborAnchorPosition)
                val relativeThreshold = abs(positionalThreshold(distance))
                val relativePosition = abs(currentAnchorPosition - offset)
                if (relativePosition <= relativeThreshold) currentValue else neighborAnchor
            }
        }
    }

    private fun computeTargetWithoutThresholds(
        offset: Float,
        currentValue: T,
    ): T {
        val currentAnchors = anchors
        val currentAnchor = currentAnchors.positionOf(currentValue)
        return if (currentAnchor == offset || currentAnchor.isNaN()) {
            currentValue
        } else {
            currentAnchors.closestAnchor(
                offset,
                offset - currentAnchor > 0
            ) ?: currentValue
        }
    }

    private val anchoredDragScope: AnchoredDragScope = object : AnchoredDragScope {
        override fun dragTo(newOffset: Float, lastKnownVelocity: Float) {
            offset = newOffset
            lastVelocity = lastKnownVelocity
        }
    }

    suspend fun anchoredDrag(
        dragPriority: MutatePriority = MutatePriority.Default,
        block: suspend AnchoredDragScope.(anchors: DraggableAnchors<T>) -> Unit
    ) {
        try {
            dragMutex.mutate(dragPriority) {
                restartable(inputs = { anchors }) { latestAnchors ->
                    anchoredDragScope.block(latestAnchors)
                }
            }
        } finally {
            val closest = anchors.closestAnchor(offset)
            if (closest != null &&
                abs(offset - anchors.positionOf(closest)) <= 0.5f &&
                confirmValueChange.invoke(closest)
            ) {
                currentValue = closest
            }
        }
    }

    suspend fun anchoredDrag(
        targetValue: T,
        dragPriority: MutatePriority = MutatePriority.Default,
        block: suspend AnchoredDragScope.(anchors: DraggableAnchors<T>, targetValue: T) -> Unit
    ) {
        if (anchors.hasAnchorFor(targetValue)) {
            try {
                dragMutex.mutate(dragPriority) {
                    dragTarget = targetValue
                    restartable(
                        inputs = { anchors to this@AnchoredDraggableState.targetValue }
                    ) { (latestAnchors, latestTarget) ->
                        anchoredDragScope.block(latestAnchors, latestTarget)
                    }
                }
            } finally {
                dragTarget = null
                val closest = anchors.closestAnchor(offset)
                if (closest != null &&
                    abs(offset - anchors.positionOf(closest)) <= 0.5f &&
                    confirmValueChange.invoke(closest)
                ) {
                    currentValue = closest
                }
            }
        } else {
            // Todo: b/283467401, revisit this behavior
            currentValue = targetValue
        }
    }

    internal fun newOffsetForDelta(delta: Float) =
        ((if (offset.isNaN()) 0f else offset) + delta)
            .coerceIn(anchors.minAnchor(), anchors.maxAnchor())

    fun dispatchRawDelta(delta: Float): Float {
        val newOffset = newOffsetForDelta(delta)
        val oldOffset = if (offset.isNaN()) 0f else offset
        offset = newOffset
        return newOffset - oldOffset
    }

   private fun trySnapTo(targetValue: T): Boolean = dragMutex.tryMutate {
        with(anchoredDragScope) {
            val targetOffset = anchors.positionOf(targetValue)
            if (!targetOffset.isNaN()) {
                dragTo(targetOffset)
                dragTarget = null
            }
            currentValue = targetValue
        }
    }

    companion object {
        @ExperimentalFoundationApi
        fun <T : Any> Saver(
            animationSpec: AnimationSpec<Float>,
            positionalThreshold: (distance: Float) -> Float,
            velocityThreshold: () -> Float,
            confirmValueChange: (T) -> Boolean = { true },
        ) = Saver<AnchoredDraggableState<T>, T>(
            save = { it.currentValue },
            restore = {
                AnchoredDraggableState(
                    initialValue = it,
                    animationSpec = animationSpec,
                    confirmValueChange = confirmValueChange,
                    positionalThreshold = positionalThreshold,
                    velocityThreshold = velocityThreshold
                )
            }
        )
    }
}

@ExperimentalFoundationApi
suspend fun <T> AnchoredDraggableState<T>.snapTo(targetValue: T) {
    anchoredDrag(targetValue = targetValue) { anchors, latestTarget ->
        val targetOffset = anchors.positionOf(latestTarget)
        if (!targetOffset.isNaN()) dragTo(targetOffset)
    }
}
@ExperimentalFoundationApi
suspend fun <T> AnchoredDraggableState<T>.animateTo(
    targetValue: T,
    velocity: Float = this.lastVelocity,
) {
    anchoredDrag(targetValue = targetValue) { anchors, latestTarget ->
        val targetOffset = anchors.positionOf(latestTarget)
        if (!targetOffset.isNaN()) {
            var prev = if (offset.isNaN()) 0f else offset
            animate(prev, targetOffset, velocity, animationSpec) { value, velocity ->
                dragTo(value, velocity)
                prev = value
            }
        }
    }
}

private class AnchoredDragFinishedSignal : CancellationException() {
    override fun fillInStackTrace(): Throwable {
        stackTrace = emptyArray()
        return this
    }
}

private suspend fun <I> restartable(inputs: () -> I, block: suspend (I) -> Unit) {
    try {
        coroutineScope {
            var previousDrag: Job? = null
            snapshotFlow(inputs)
                .collect { latestInputs ->
                    previousDrag?.apply {
                        cancel(AnchoredDragFinishedSignal())
                        join()
                    }
                    previousDrag = launch(start = CoroutineStart.UNDISPATCHED) {
                        block(latestInputs)
                        this@coroutineScope.cancel(AnchoredDragFinishedSignal())
                    }
                }
        }
    } catch (anchoredDragFinished: AnchoredDragFinishedSignal) {
        // Ignored
    }
}

private fun <T> emptyDraggableAnchors() = MapDraggableAnchors<T>(emptyMap())

@OptIn(ExperimentalFoundationApi::class)
private class MapDraggableAnchors<T>(private val anchors: Map<T, Float>) : DraggableAnchors<T> {

    override fun positionOf(value: T): Float = anchors[value] ?: Float.NaN
    override fun hasAnchorFor(value: T) = anchors.containsKey(value)

    override fun closestAnchor(position: Float): T? = anchors.minByOrNull {
        abs(position - it.value)
    }?.key

    override fun closestAnchor(
        position: Float,
        searchUpwards: Boolean
    ): T? {
        return anchors.minByOrNull { (_, anchor) ->
            val delta = if (searchUpwards) anchor - position else position - anchor
            if (delta < 0) Float.POSITIVE_INFINITY else delta
        }?.key
    }

    override fun minAnchor() = anchors.values.minOrNull() ?: Float.NaN

    override fun maxAnchor() = anchors.values.maxOrNull() ?: Float.NaN

    override val size: Int
        get() = anchors.size

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is MapDraggableAnchors<*>) return false

        return anchors == other.anchors
    }

    override fun forEach(block: (anchor: T, position: Float) -> Unit) {

    }

    override fun hashCode() = 31 * anchors.hashCode()

    override fun toString() = "MapDraggableAnchors($anchors)"
}
