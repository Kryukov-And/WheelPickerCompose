package com.commandiron.wheel_picker_compose

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.commandiron.wheel_picker_compose.core.DefaultWheelTimePicker
import com.commandiron.wheel_picker_compose.core.SelectorProperties
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import java.time.LocalTime

@Composable
fun WheelTimePicker(
    modifier: Modifier = Modifier,
    startTime: LocalTime = LocalTime.now(),
    minTime: LocalTime = LocalTime.MIN,
    maxTime: LocalTime = LocalTime.MAX,
    timeFormat: TimeFormat = TimeFormat.HOUR_24,
    size: DpSize = DpSize(128.dp, 128.dp),
    rowCount: Int = 3,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    textColor: Color = LocalContentColor.current,
    selectorProperties: SelectorProperties = WheelPickerDefaults.selectorProperties(),
    onSnappedHour : (snappedTime: LocalTime) -> Unit = { },
    onSnappedMinute : (snappedTime: LocalTime) -> Unit = { },
    onSnappedAmPm : (snappedTime: LocalTime) -> Unit = { },
    onScrollHourInProgress: () -> Unit,
    onScrollMinuteInProgress: () -> Unit,
    onScrollAmPmInProgress: () -> Unit,
) {
    DefaultWheelTimePicker(
        modifier,
        startTime,
        minTime,
        maxTime,
        timeFormat,
        size,
        rowCount,
        textStyle,
        textColor,
        selectorProperties,
        onSnappedHour = { snappedTime, _ ->
            onSnappedHour(snappedTime.snappedLocalTime)
            snappedTime.snappedIndex
        },
        onSnappedMinute = { snappedTime, _ ->
            onSnappedMinute(snappedTime.snappedLocalTime)
            snappedTime.snappedIndex
        },
        onSnappedAmPm = { snappedTime, _ ->
            onSnappedAmPm(snappedTime.snappedLocalTime)
            snappedTime.snappedIndex
        },
        onScrollHourInProgress = onScrollHourInProgress,
        onScrollMinuteInProgress = onScrollMinuteInProgress,
        onScrollAmPmInProgress = onScrollAmPmInProgress
    )
}