package journal_list.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import presentation.theme.textColorHint
import presentation.theme.textColorSemiDark
import util.DateTimeUtil

@OptIn(ExperimentalResourceApi::class)
@Composable
fun JournalItemView(
//    image: ToBeAddedlater
    title: String,
    bodyOverview: String,
    date: LocalDateTime
) {
    Row (
        modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
    ){
        Image(
            modifier = Modifier.size(70.dp).align(Alignment.Top),
            contentDescription = null,
            painter = painterResource("logoyellovarient.png")
        )

        Spacer(Modifier.width(16.dp))

        Column (
            modifier = Modifier.fillMaxWidth().align(Alignment.CenterVertically),
            horizontalAlignment = Alignment.Start
        ){
            Text(
                text = title,
                style = MaterialTheme.typography.h2,
                fontSize = 16.sp
            )

            Spacer(Modifier.height(4.dp))

            Text(
                text = bodyOverview,
                style = MaterialTheme.typography.subtitle1,
                fontSize = 14.sp,
                fontWeight = FontWeight.W400
            )

            Spacer(Modifier.height(10.5.dp))

            Text(
                text = DateTimeUtil.formatLocalDateTime(date),
                style = MaterialTheme.typography.button,
                fontSize = 12.sp,
                fontWeight = FontWeight.W700,
                color = textColorHint
            )
        }

    }



}