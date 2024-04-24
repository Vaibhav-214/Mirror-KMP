package journal_list.presentation.components.svg_composables

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp


private var _Namecardgroup: ImageVector? = null

public val Namecardgroup: ImageVector
    get() {
        if (_Namecardgroup != null) {
            return _Namecardgroup!!
        }
        _Namecardgroup = ImageVector.Builder(
            name = "Namecardgroup",
            defaultWidth = 137.dp,
            defaultHeight = 111.dp,
            viewportWidth = 137f,
            viewportHeight = 111f
        ).apply {
            group {
                path(
                    fill = SolidColor(Color(0xFFEFEEEE)),
                    fillAlpha = 0.23f,
                    stroke = null,
                    strokeAlpha = 0.23f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(68.1732f, 33.2126f)
                    arcTo(34.0866f, 33.2126f, 0f, isMoreThanHalf = false, isPositiveArc = true, 34.0866f, 66.4252f)
                    arcTo(34.0866f, 33.2126f, 0f, isMoreThanHalf = false, isPositiveArc = true, 0f, 33.2126f)
                    arcTo(34.0866f, 33.2126f, 0f, isMoreThanHalf = false, isPositiveArc = true, 68.1732f, 33.2126f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFEFEEEE)),
                    fillAlpha = 0.23f,
                    stroke = null,
                    strokeAlpha = 0.23f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(136.3466f, 60.7441f)
                    arcTo(34.0866f, 33.6496f, 0f, isMoreThanHalf = false, isPositiveArc = true, 102.26f, 94.3937f)
                    arcTo(34.0866f, 33.6496f, 0f, isMoreThanHalf = false, isPositiveArc = true, 68.17340000000002f, 60.7441f)
                    arcTo(34.0866f, 33.6496f, 0f, isMoreThanHalf = false, isPositiveArc = true, 136.3466f, 60.7441f)
                    close()
                }
                path(
                    fill = SolidColor(Color(0xFFF6F6F6)),
                    fillAlpha = 0.23f,
                    stroke = null,
                    strokeAlpha = 0.23f,
                    strokeLineWidth = 1.0f,
                    strokeLineCap = StrokeCap.Butt,
                    strokeLineJoin = StrokeJoin.Miter,
                    strokeLineMiter = 1.0f,
                    pathFillType = PathFillType.NonZero
                ) {
                    moveTo(71.2324f, 0f)
                    lineTo(119.297f, 83.25f)
                    horizontalLineTo(23.168f)
                    lineTo(71.2324f, 0f)
                    close()
                }
            }
        }.build()
        return _Namecardgroup!!
    }