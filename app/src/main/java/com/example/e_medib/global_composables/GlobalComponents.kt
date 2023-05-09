import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.example.e_medib.ui.theme.mLightGrayScale
import com.example.e_medib.ui.theme.mRedMain

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CustomExpandedCard(
    modifier: Modifier = Modifier,
    isExpanded: Boolean = true,
    header: @Composable @UiComposable () -> Unit,
    body: @Composable @UiComposable () -> Unit,
) {
    val expandedState = remember() { mutableStateOf(false) }
    val icon = Icons.Default.KeyboardArrowDown
    val rotationState by animateFloatAsState(targetValue = if (expandedState.value) 180f else 0f)

    Card(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
            ),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(2.dp, color = mLightGrayScale),
        elevation = 0.dp,
        onClick = { expandedState.value = !expandedState.value }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // header
            header()

            if (isExpanded) {
                Divider(
                    modifier = Modifier.padding(top = 12.dp),
                    color = mLightGrayScale,
                    thickness = 1.dp
                )

                // body
                if (expandedState.value)
                    body()


                // footer
                IconButton(modifier = Modifier
                    .padding(top = 8.dp)
                    .rotate(rotationState),
                    onClick = { expandedState.value = !expandedState.value }) {
                    Icon(
                        imageVector = icon,
                        contentDescription = "arrow up",
                        tint = mRedMain,
                    )
                }
            }


        }


    }
}