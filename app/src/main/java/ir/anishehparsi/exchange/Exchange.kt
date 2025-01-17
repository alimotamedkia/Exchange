package ir.anishehparsi.exchange

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Exchange(modifier: Modifier = Modifier) {

    val exchangeValue = ExchangeLogic()
    var amountInput by remember { mutableStateOf("") }
    var amountExchange by remember { mutableStateOf("مبلغ ریالی را وارد کنید") }
    var isUsdIrr by remember { mutableStateOf(true) }

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

        Column(
            modifier
                .padding(8.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                modifier = Modifier.size(250.dp),
                painter = painterResource(R.drawable.exchange),
                contentDescription = ""
            )
            Text(
                text = amountExchange,
                fontSize = 32.sp,
                fontFamily = FontFamily(Font(R.font.vazirmatn_bold))
            )

            OutlinedTextField(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
                label = { Text(text = if (isUsdIrr) "ریال" else "دلار") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                value = amountInput,
                onValueChange = { amountInput = it })
            Spacer(modifier = Modifier.size(32.dp))
            Row(Modifier.padding(horizontal = 32.dp)) {
                Button(
                    onClick = {
                        amountExchange =
                            if (amountInput.isNotEmpty() && amountInput.toDoubleOrNull() != null) {
                                if (isUsdIrr) {
                                    exchangeValue.exchangeCalculate(amountInput.toDouble())
                                        .toString()
                                } else {
                                    exchangeValue.exchangeReverse(amountInput.toDouble()).toString()
                                }

                            } else {
                                "مقدار نامعتبر"
                            }
                    },
                    Modifier.weight(2f)
                ) {
                    Text(
                        if (isUsdIrr) {
                            "چنددلار میشه؟"
                        } else {
                            "چند ریال میشه؟"
                        }
                    )
                }
                TextButton(
                    onClick = {
                        isUsdIrr = !isUsdIrr
                        amountInput = ""
                        amountExchange =
                            if (isUsdIrr) "مبلغ ریالی وارد کنید" else "مبلغ دلاری وارد کنید"
                    },
                    Modifier.weight(1f)
                ) { Text("تعویض ارز") }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview(modifier: Modifier = Modifier) {
    Exchange()
}