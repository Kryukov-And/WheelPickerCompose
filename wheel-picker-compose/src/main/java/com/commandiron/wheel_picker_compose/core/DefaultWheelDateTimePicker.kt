package com.commandiron.wheel_picker_compose.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Composable
internal fun DefaultWheelDateTimePicker(
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
    onSnappedDay : (snappedDateTime: SnappedDateTime) -> Int? = { _ -> null },
    onSnappedMonth : (snappedDateTime: SnappedDateTime) -> Int? = { _ -> null },
    onSnappedYear : (snappedDateTime: SnappedDateTime) -> Int? = { _ -> null },
    onSnappedHour : (snappedDateTime: SnappedDateTime) -> Int? = { _ -> null },
    onSnappedMinute : (snappedDateTime: SnappedDateTime) -> Int? = { _ -> null },
    onSnappedAmPm : (snappedDateTime: SnappedDateTime) -> Int? = { _ -> null },
    onScrollDayInProgress: () -> Unit,
    onScrollMonthInProgress: () -> Unit,
    onScrollYearInProgress: () -> Unit,
    onScrollHourInProgress: () -> Unit,
    onScrollMinuteInProgress: () -> Unit,
    onScrollAmPmInProgress: () -> Unit,
) {

    var snappedDateTime by remember { mutableStateOf(startDateTime.truncatedTo(ChronoUnit.MINUTES)) }

    val yearTexts = yearsRange?.map { it.toString() } ?: listOf()

    Box(modifier = modifier, contentAlignment = Alignment.Center){
        if(selectorProperties.enabled().value){
            Surface(
                modifier = Modifier
                    .size(size.width, size.height / rowCount),
                shape = selectorProperties.shape().value,
                color = selectorProperties.color().value,
                border = selectorProperties.border().value
            ) {}
        }
        Row {
            //Date
            DefaultWheelDatePicker(
                startDate = startDateTime.toLocalDate(),
                yearsRange = yearsRange,
                size = DpSize(
                    width = if(yearsRange == null ) size.width * 3 / 6 else size.width * 3 / 5 ,
                    height = size.height
                ),
                rowCount = rowCount,
                textStyle = textStyle,
                textColor = textColor,
                selectorProperties = WheelPickerDefaults.selectorProperties(
                    enabled = false
                ),
                onSnappedDay = { snappedDate ->
                    val newDateTime = snappedDateTime.withDayOfMonth(snappedDate.snappedLocalDate.dayOfMonth)

                    if(!newDateTime.isBefore(minDateTime) && !newDateTime.isAfter(maxDateTime)) {
                        snappedDateTime = newDateTime
                    }

                    onSnappedDay(SnappedDateTime.DayOfMonth(snappedDateTime,snappedDateTime.dayOfMonth - 1))
                    return@DefaultWheelDatePicker snappedDateTime.dayOfMonth - 1
                },
                onSnappedMonth = { snappedDate ->
                    val newDateTime = snappedDateTime.withMonth(snappedDate.snappedLocalDate.monthValue)

                    if(!newDateTime.isBefore(minDateTime) && !newDateTime.isAfter(maxDateTime)) {
                        snappedDateTime = newDateTime
                    }

                    onSnappedMonth(SnappedDateTime.Month(snappedDateTime,snappedDateTime.month.value - 1))
                    return@DefaultWheelDatePicker snappedDateTime.month.value - 1
                },
                onSnappedYear = { snappedDate ->
                    val newDateTime = snappedDateTime.withYear(snappedDate.snappedLocalDate.year)

                    if(!newDateTime.isBefore(minDateTime) && !newDateTime.isAfter(maxDateTime)) {
                        snappedDateTime = newDateTime
                    }

                    onSnappedYear(SnappedDateTime.Year(snappedDateTime, yearTexts.indexOf(snappedDateTime.year.toString())))
                    return@DefaultWheelDatePicker yearTexts.indexOf(snappedDateTime.year.toString())
                },
                onScrollDayInProgress = onScrollDayInProgress,
                onScrollMonthInProgress = onScrollMonthInProgress,
                onScrollYearInProgress = onScrollYearInProgress
            )
            //Time
            DefaultWheelTimePicker(
                startTime = startDateTime.toLocalTime(),
                timeFormat = timeFormat,
                size = DpSize(
                    width = if(yearsRange == null ) size.width * 3 / 6  else size.width * 2 / 5 ,
                    height = size.height
                ),
                rowCount = rowCount,
                textStyle = textStyle,
                textColor = textColor,
                selectorProperties = WheelPickerDefaults.selectorProperties(
                    enabled = false
                ),
                onSnappedHour = { snappedTime, timeFormat ->
                    val newDateTime = snappedDateTime.withHour(snappedTime.snappedLocalTime.hour)

                    if(!newDateTime.isBefore(minDateTime) && !newDateTime.isAfter(maxDateTime)) {
                        snappedDateTime = newDateTime
                    }

                    onSnappedHour(SnappedDateTime.Hour(snappedDateTime, snappedDateTime.hour))
                    return@DefaultWheelTimePicker if(timeFormat == TimeFormat.HOUR_24) snappedDateTime.hour else
                        localTimeToAmPmHour(snappedDateTime.toLocalTime()) - 1
                },
                onSnappedMinute = { snappedTime, timeFormat ->
                    val newDateTime = snappedDateTime.withMinute(snappedTime.snappedLocalTime.minute)

                    if(!newDateTime.isBefore(minDateTime) && !newDateTime.isAfter(maxDateTime)) {
                        snappedDateTime = newDateTime
                    }

                    onSnappedMinute(SnappedDateTime.Minute(snappedDateTime, snappedDateTime.minute))
                    return@DefaultWheelTimePicker snappedDateTime.minute
                },
                onSnappedAmPm = { snappedTime, timeFormat ->
                    val newDateTime = snappedDateTime.withHour(snappedTime.snappedLocalTime.hour)
                    if(!newDateTime.isBefore(minDateTime) && !newDateTime.isAfter(maxDateTime)) {
                        snappedDateTime = newDateTime
                    }

                    onSnappedAmPm(SnappedDateTime.Hour(snappedDateTime, snappedDateTime.hour))
                    return@DefaultWheelTimePicker if(timeFormat == TimeFormat.HOUR_24) snappedDateTime.hour else
                        localTimeToAmPmHour(snappedDateTime.toLocalTime()) - 1
                },
                onScrollHourInProgress = onScrollHourInProgress,
                onScrollMinuteInProgress = onScrollMinuteInProgress,
                onScrollAmPmInProgress = onScrollAmPmInProgress
            )
        }
    }
}












