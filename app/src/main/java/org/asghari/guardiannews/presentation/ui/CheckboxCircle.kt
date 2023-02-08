package org.asghari.guardiannews.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ProgressIndicatorDefaults.StrokeWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.outlined.Circle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.model.content.CircleShape
import okhttp3.internal.wait
import java.lang.Math.floor
@Composable
fun RoundedCheckView(Checkboxtext:String,isSelected:Boolean=false) {
    val isChecked = remember { mutableStateOf(isSelected) }
    val checkedText = remember { mutableStateOf(Checkboxtext) }
    val circleSize = remember { mutableStateOf(18.dp) }
    val circleThickness = remember { mutableStateOf(2.dp) }
    val color = remember { mutableStateOf(Color.DarkGray) }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(5.dp,2.dp)
            .toggleable(value = isChecked.value, role = Role.Checkbox) {
                isChecked.value = it

                if (isChecked.value) {
                    circleSize.value = 18.dp
                    circleThickness.value = 2.dp
                    color.value = Color.Black

                } else {

                    circleSize.value = 18.dp
                    circleThickness.value = 2.dp
                    color.value = Color.DarkGray
                }
            }) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(circleSize.value)
                .background(color.value)
                .padding(circleThickness.value)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            if (isChecked.value) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "")
            }
        }

        Text(
            text = checkedText.value,
            color = color.value,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 5.dp)
        )
    }

}
