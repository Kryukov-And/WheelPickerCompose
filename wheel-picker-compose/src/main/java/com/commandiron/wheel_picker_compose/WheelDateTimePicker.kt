package com.commandiron.wheel_picker_compose

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.commandiron.wheel_picker_compose.core.DefaultWheelDateTimePicker
import com.commandiron.wheel_picker_compose.core.SelectorProperties
import com.commandiron.wheel_picker_compose.core.TimeFormat
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import java.time.LocalDateTime

@Composable
fun WheelDateTimePicker(
    modifier: Modifier = Modifier,
    startDateTime: LocalDateTime = LocalDateTime.now(),
    minDateTime: LocalDateTime = LocalDateTime.MIN,
    maxDateTime: LocalDateTime = LocalDateTime.MAX,
    yearsRange: IntRange? = IntRange(1922, 2122),
    timeFormat: TimeFormat = TimeFormat.HOUR_24,
    size: DpSize = DpSize(256.dp, 128.dp),
    rowCount: Int = 3,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    textColor: Color = LocalContentColor.current,
    selectorProperties: SelectorProperties = WheelPickerDefaults.selectorProperties(),
    onSnappedDay : (snappedDateTime: LocalDateTime) -> Unit = { },
    onSnappedMonth : (snappedDateTime: LocalDateTime) -> Unit = { },
    onSnappedYear : (snappedDateTime: LocalDateTime) -> Unit = { },
    onSnappedHour : (snappedDateTime: LocalDateTime) -> Unit = { },
    onSnappedMinute : (snappedDateTime: LocalDateTime) -> Unit = { },
    onSnappedAmPm : (snappedDateTime: LocalDateTime) -> Unit = { },
    onScrollDayInProgress: () -> Unit,
    onScrollMonthInProgress: () -> Unit,
    onScrollYearInProgress: () -> Unit,
    onScrollHourInProgress: () -> Unit,
    onScrollMinuteInProgress: () -> Unit,
    onScrollAmPmInProgress: () -> Unit,
) {
    DefaultWheelDateTimePicker(
        modifier,
        startDateTime,
        minDateTime,
        maxDateTime,
        yearsRange,
        timeFormat,
        size,
        rowCount,
        textStyle,
        textColor,
        selectorProperties,
        onSnappedDay = { snappedDateTime ->
            onSnappedDay(snappedDateTime.snappedLocalDateTime)
            snappedDateTime.snappedIndex
        },
        onSnappedMonth = { snappedDateTime ->
            onSnappedMonth(snappedDateTime.snappedLocalDateTime)
            snappedDateTime.snappedIndex
        },
        onSnappedYear = { snappedDateTime ->
            onSnappedYear(snappedDateTime.snappedLocalDateTime)
            snappedDateTime.snappedIndex
        },
        onSnappedHour = { snappedDateTime ->
            onSnappedHour(snappedDateTime.snappedLocalDateTime)
            snappedDateTime.snappedIndex
        },
        onSnappedMinute = { snappedDateTime ->
            onSnappedMinute(snappedDateTime.snappedLocalDateTime)
            snappedDateTime.snappedIndex
        },
        onSnappedAmPm = { snappedDateTime ->
            onSnappedAmPm(snappedDateTime.snappedLocalDateTime)
            snappedDateTime.snappedIndex
        },
        onScrollDayInProgress = onScrollDayInProgress,
        onScrollMonthInProgress = onScrollMonthInProgress,
        onScrollYearInProgress = onScrollYearInProgress,
        onScrollHourInProgress = onScrollHourInProgress,
        onScrollMinuteInProgress = onScrollMinuteInProgress,
        onScrollAmPmInProgress = onScrollAmPmInProgress
    )
}