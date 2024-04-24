package presentation.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import font

@Composable
fun getTypography() : Typography {
    val interRegular = FontFamily(
        font(
            "Inter",
            "inter_400",
            FontWeight.Normal,
            FontStyle.Normal
        )
    )

    val interMedium = FontFamily(
        font(
            "Inter",
            "inter_500",
            FontWeight.Normal,
            FontStyle.Normal
        )
    )

    val interBold = FontFamily(
        font(
            "Inter",
            "inter_700",
            FontWeight.Normal,
            FontStyle.Normal
        )
    )
    val mulishRegular = FontFamily(
        font(
            "Mulish",
            "mulish_400",
            FontWeight.Normal,
            FontStyle.Normal
        )
    )

    val mulishSemiBold = FontFamily(
        font(
            "Mulish",
            "mulish_600",
            FontWeight.Normal,
            FontStyle.Normal
        )
    )

    val mulishBold = FontFamily(
        font(
            "Mulish",
            "mulish_700",
            FontWeight.Normal,
            FontStyle.Normal
        )
    )

    return Typography(
        h1 = TextStyle(
            fontFamily = interBold,
            fontWeight = FontWeight.W700,
            fontSize = 36.sp,
            color = textColorDark
        ),
        h2 = TextStyle(
            fontFamily = interBold,
            fontWeight = FontWeight.W700,
            fontSize = 24.sp,
            color = textColorDark
        ),
        h3 = TextStyle(
            fontFamily = interBold,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = textColorDark
        ),
        h4 = TextStyle(
            fontFamily = interMedium,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = textColorDark
        ),
        h5 = TextStyle(
            fontFamily = mulishRegular,
            fontWeight = FontWeight.W600,
            fontSize = 18.sp,
            color = white
        ),
        subtitle1 = TextStyle(
            fontFamily = mulishRegular,
            fontWeight = FontWeight.W600,
            fontSize = 16.sp,
            color = textColorSemiDark
        ),
        subtitle2 = TextStyle(
            fontFamily = mulishSemiBold,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            color = textColorHint
        ),
        button = TextStyle(
            fontFamily = interMedium,
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
            color = textColorDark
        ),
        body2 = TextStyle(
            fontFamily = mulishSemiBold,
            fontWeight = FontWeight.Normal,
            fontSize = 10.sp,
            color = textColorHint
        ),
        caption = TextStyle(
            fontFamily = mulishRegular,
            fontWeight = FontWeight.W600,
            fontSize = 10.sp,
            color = textColorHint
        ),
        overline = TextStyle(
            fontFamily = interBold,
            fontWeight = FontWeight.W700,
            fontSize = 20.sp,
            color = textColorDark
        )

    )


}