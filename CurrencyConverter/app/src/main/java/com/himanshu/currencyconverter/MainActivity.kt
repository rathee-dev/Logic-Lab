package com.himanshu.currencyconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.himanshu.currencyconverter.ui.theme.CurrencyConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CurrencyConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    CurrencyConverterApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun CurrencyConverterApp(modifier: Modifier = Modifier) {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("INR") }
    var outputUnit by remember { mutableStateOf("INR") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }

    var conversionFactor by remember { mutableDoubleStateOf(1.0) }
    var oConversionFactor by remember { mutableDoubleStateOf(1.0) }

    fun convertCurrency() {
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor * 100.0 / oConversionFactor).roundToInt() / 100.0
        outputValue = result.toString()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.currencyicon),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.5f,
            modifier = Modifier.fillMaxSize()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
            .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Currency Converter",
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = inputValue,
                onValueChange = {
                    inputValue = it
                    convertCurrency()
                },
                label = { Text(text = "Enter Amount") }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Box {
                    Button(onClick = { iExpanded = true }) {
                        Text(text = inputUnit)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                    }
                    DropdownMenu(
                        expanded = iExpanded,
                        onDismissRequest = { iExpanded = false },
                        modifier = Modifier.heightIn(max = 250.dp)
                    ) {
                        DropdownMenuItem(
                            text = { Text(text = "INR") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "INR"
                                conversionFactor = 1.0
                                convertCurrency()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "USD") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "USD"
                                conversionFactor = 94.51
                                convertCurrency()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "EUR") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "EUR"
                                conversionFactor = 109.15
                                convertCurrency()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "CNY") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "CNY"
                                conversionFactor = 13.71
                                convertCurrency()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "RUB") },
                            onClick = {
                                iExpanded = false
                                inputUnit = "RUB"
                                conversionFactor = 1.17
                                convertCurrency()
                            }
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Box {
                    Button(onClick = { oExpanded = true }) {
                        Text(text = outputUnit)
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                    }
                    DropdownMenu(
                        expanded = oExpanded,
                        onDismissRequest = { oExpanded = false },
                        modifier = Modifier.heightIn(max = 250.dp)
                    ) {
                        DropdownMenuItem(
                            text = { Text(text = "INR") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "INR"
                                oConversionFactor = 1.0
                                convertCurrency()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "USD") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "USD"
                                oConversionFactor = 94.51
                                convertCurrency()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "EUR") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "EUR"
                                oConversionFactor = 109.15
                                convertCurrency()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "CNY") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "CNY"
                                oConversionFactor = 13.71
                                convertCurrency()
                            }
                        )
                        DropdownMenuItem(
                            text = { Text(text = "RUB") },
                            onClick = {
                                oExpanded = false
                                outputUnit = "RUB"
                                oConversionFactor = 1.17
                                convertCurrency()
                            }
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Result: $outputValue $outputUnit",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CurrencyConverterAppPreview() {
    CurrencyConverterTheme {
        CurrencyConverterApp()
    }
}
