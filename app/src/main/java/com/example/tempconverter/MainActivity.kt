package com.example.tempconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tempconverter.ui.theme.TempConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TempConverterTheme {
                // A surface container using the 'background' color from the theme

                    Box(modifier = Modifier
                        .fillMaxSize()
                        .background(Color(1, 3, 18))
                    ){
                        TempConverterApp()
                    }
            }
        }
    }
}

@Composable
fun TempConverterApp(){

    var celsiusTemp by remember { mutableStateOf("0.0") }
    var fahrenheitTemp by remember { mutableStateOf("32.0") }
    var kelvinTemp by remember { mutableStateOf("273.15") }


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            "TempCFK Converter",
            textAlign = TextAlign.Center,
            fontFamily = FontFamily.Cursive,
            fontSize = 40.sp,
            color = Color(178,231,238)
        )

        Spacer(modifier = Modifier.height(70.dp))

        EditTextBox(brief = celsiusTemp,
            modifier = Modifier,
//            label = R.string.c,
            icon = stringResource(R.string.c),
            onValueChange = {
                celsiusTemp = it
                var c = (celsiusTemp.toDoubleOrNull() ?: 0.0)
                if(c<-273.15) fahrenheitTemp = "InValid"
                else{
                    c = (c * 9 / 5) + 32.0
                    fahrenheitTemp = "$c"
                }
                var k = (celsiusTemp.toDoubleOrNull() ?: 0.0)
                if(k < -273.15) kelvinTemp = "InValid"
                else{
                    k += 273.15
                    kelvinTemp = "$k"
                }
            } )

        Spacer(Modifier.height(20.dp))

        EditTextBox(brief = fahrenheitTemp,
            modifier = Modifier,
//            label = R.string.c,
            icon = stringResource(R.string.f),
            onValueChange = {
                fahrenheitTemp = it
                var f = (fahrenheitTemp.toDoubleOrNull() ?: 0.0)
                if(f<-459.67) {
                    celsiusTemp = "InValid"
                }
                else{
                    f = (f-32.0)*(0.5555555555)
                    celsiusTemp = "$f"
                }
                var k = (fahrenheitTemp.toDoubleOrNull() ?: 0.0)
                if(k<-459.67){
                    kelvinTemp = "InValid"
                }else{
                    k = (k-32.0)*(0.5555555555) + 273.15
                    kelvinTemp = "$k"
                }
            } )

        Spacer(Modifier.height(20.dp))

        EditTextBox(brief = kelvinTemp,
            modifier = Modifier,
//            label = R.string.c,
            icon = stringResource(R.string.k),
            onValueChange = {
                kelvinTemp = it
                var k = (kelvinTemp.toDoubleOrNull() ?: 0.0)
                if(k<0){
                    fahrenheitTemp = "InValid"
                    celsiusTemp = "InValid"
                }else{
                    k-=273.15
                    celsiusTemp="$k"
                    k= (k* 9/5)+32.0
                    fahrenheitTemp="$k"
                }
            } )

        Spacer(Modifier.height(25.dp))

        Text(
            "*Enter values in double or float for better results.",
            fontSize = 13.sp,
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}

@Composable
fun EditTextBox(brief : String,
                icon : String,
                modifier: Modifier = Modifier,
//                @StringRes label : Int,
                onValueChange: (String) -> Unit
){
    TextField(
        value = brief,
        onValueChange = onValueChange,
        leadingIcon = {Text(icon ,
            fontWeight = FontWeight.Bold,
            fontSize = 23.sp,
            color = Color(255,127,42))},

        colors = TextFieldDefaults.textFieldColors(
            containerColor =  Color(62,64,253),
            cursorColor = Color.White,
            textColor = Color.White,
            focusedIndicatorColor =  Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
//        label = { Text(stringResource(label)) },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number
        ),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    TempConverterTheme {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color(1, 3, 18))
        ){
            TempConverterApp()
        }
    }
}