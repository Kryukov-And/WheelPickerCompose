package com.commandiron.wheel_picker_compose

import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.commandiron.wheel_picker_compose.core.DefaultWheelDatePicker
import com.commandiron.wheel_picker_compose.core.SelectorProperties
import com.commandiron.wheel_picker_compose.core.WheelPickerDefaults
import java.time.LocalDate

@Composable
fun WheelDatePicker(
    modifier: Modifier = Modifier,
    startDate: LocalDate = LocalDate.now(),
    minDate: LocalDate = LocalDate.MIN,
    maxDate: LocalDate = LocalDate.MAX,
    yearsRange: IntRange? = IntRange(1922, 2122),
    size: DpSize = DpSize(256.dp, 128.dp),
    rowCount: Int = 3,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    textColor: Color = LocalContentColor.current,
    selectorProperties: SelectorProperties = WheelPickerDefaults.selectorProperties(),
    onSnappedDay : (snappedDate: LocalDate) -> Unit = { },
    onSnappedMonth : (snappedDate: LocalDate) -> Unit = { },
    onSnappedYear : (snappedDate: LocalDate) -> Unit = { },
    onScrollDayInProgress: () -> Unit,
    onScrollMonthInProgress: () -> Unit,
    onScrollYearInProgress: () -> Unit,
) {
    DefaultWheelDatePicker(
        modifier,
        startDate,
        minDate,
        maxDate,
        yearsRange,
        size,
        rowCount,
        textStyle,
        textColor,
        selectorProperties,
        onSnappedDay = { snappedDate ->
            onSnappedDay(snappedDate.snappedLocalDate)
            snappedDate.snappedIndex
        },
        onSnappedMonth = { snappedDate ->
            onSnappedMonth(snappedDate.snappedLocalDate)
            snappedDate.snappedIndex
        },
        onSnappedYear = { snappedDate ->
            onSnappedYear(snappedDate.snappedLocalDate)
            snappedDate.snappedIndex
        },
        onScrollDayInProgress = onScrollDayInProgress,
        onScrollMonthInProgress = onScrollMonthInProgress,
        onScrollYearInProgress = onScrollYearInProgress
    )
}