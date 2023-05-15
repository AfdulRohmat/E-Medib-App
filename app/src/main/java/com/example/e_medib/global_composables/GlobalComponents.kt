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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.UiComposable
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.e_medib.ui.theme.mGrayScale
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

@Composable
fun CustomInputField(
    modifier: Modifier = Modifier,
    valueState: MutableState<String>,
    placeholder: String,
    isEnable: Boolean = true,
    isSingleLine: Boolean = true,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default,
    trailingIcon: @Composable() (() -> Unit)?,
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    OutlinedTextField(
        value = valueState.value, onValueChange = { valueState.value = it },
        placeholder = { Text(text = placeholder) },
        singleLine = isSingleLine,
        textStyle = TextStyle(fontSize = 18.sp, color = MaterialTheme.colors.onBackground),
        enabled = isEnable,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        shape = RoundedCornerShape(10.dp),
        visualTransformation = visualTransformation,
        trailingIcon = trailingIcon,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = mLightGrayScale,
            unfocusedBorderColor = mLightGrayScale,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Black,
            placeholderColor = mGrayScale
        ),
        modifier = modifier
            .fillMaxWidth(),
    )
}