package org.asghari.guardiannews.presentation.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.asghari.guardiannews.presentation.composableviews.AppNavHost

import org.asghari.guardiannews.presentation.ui.theme.GuardianNewsTheme
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.unit.*
import org.asghari.guardiannews.R
import org.asghari.guardiannews.other.ScreensRoute

@AndroidEntryPoint
class MainActivity2 : ComponentActivity() {
    @Composable
    fun SearchView(state: MutableState<TextFieldValue>) {

        var search_filed_state = remember { mutableStateOf(false) }

        if (!(search_filed_state.value)) {
            Box(modifier = Modifier.background(Color.Transparent)) {
                IconButton(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = {
                        search_filed_state.value = !(search_filed_state.value)
                        // Remove text from TextField when you press the 'X' icon
                    }
                ) {
                    Icon(
                        Icons.Default.Search,

                        modifier = Modifier
                            .align(Alignment.CenterStart)
                            .padding(10.dp),
                        contentDescription = "",

                        )
                }
            }
        } else {

            Row(modifier = Modifier.padding(0.dp, 0.dp, 12.dp, 0.dp))
            {
                BasicTextField(
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    value = state.value,
                    onValueChange = { value ->
                        state.value = value

                    },
                    singleLine = true,
                    modifier = Modifier
                        .padding(0.dp)
                        .fillMaxHeight()
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(50))
                        .background(Color(0x77ffffff), shape = RoundedCornerShape(50))
                        .border(1.dp, Color(0x88ffffff), shape = RoundedCornerShape(50))
                    ,  textStyle = TextStyle(
                        color = Color.Gray,
                        fontSize = 16.sp,
                        textDirection = TextDirection.ContentOrLtr,
                        textAlign = TextAlign.Start
                    ),
                    decorationBox = { innerTextField ->
                        Box(modifier = Modifier.background(Color.Transparent)) {
                            IconButton(
                                modifier = Modifier.align(Alignment.CenterStart),
                                onClick = {
                                    search_filed_state.value = false;
                                    // Remove text from TextField when you press the 'X' icon
                                }
                            ) {
                                Icon(
                                    Icons.Default.ArrowBack,

                                    modifier = Modifier
                                        .align(Alignment.CenterStart)
                                        .padding(10.dp),
                                    contentDescription = "",

                                    )
                            }
                            Box(
                                modifier = Modifier
                                    .padding(40.dp, 2.dp)
                                    .align(Alignment.CenterStart)
                            ) {
                                innerTextField()
                            }
                            if (state.value != TextFieldValue("")) {
                                IconButton(
                                    modifier = Modifier.align(Alignment.CenterEnd),
                                    onClick = {
                                        state.value =
                                            TextFieldValue("")
                                       // Remove text from TextField when you press the 'X' icon
                                    }
                                ) {
                                    Icon(
                                        Icons.Default.Close,
                                        contentDescription = "",
                                        modifier = Modifier
                                            .padding(10.dp)
                                            .size(24.dp)
                                            .align(Alignment.CenterEnd),
                                    )
                                }
                            }
                            else{
                                Icon(
                                    Icons.Default.Search,
                                    contentDescription = "",
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .size(24.dp)
                                        .align(Alignment.CenterEnd),
                                )
                            }

                        }
                    },

                    )
            }
        }
    }

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            var dropDownMenuExpanded = remember { mutableStateOf(false) }
            val navController = rememberNavController()
            var text = remember { mutableStateOf(TextFieldValue("")) }
            var pagename = remember {
                mutableStateOf(getResources().getString(R.string.app_name))
            }
            GuardianNewsTheme {
              Scaffold( topBar = {
                  TopAppBar(

                      backgroundColor = Color.LightGray,
                      title = {
                          Text(text = pagename.value, style = TextStyle(color = Color.White))
                      },
                      actions = {


                          // drop down menu
                          DropdownMenu(
                              expanded = dropDownMenuExpanded.value,
                              onDismissRequest = {
                                  dropDownMenuExpanded.value =  false
                              },
                              // play around with these values
                              // to position the menu properly
                              offset = DpOffset(x = -15.dp, y = (-50).dp)
                          ) {
                              // this is a column scope
                              // items are added vertically

                              DropdownMenuItem(onClick = {
                                  navController.navigate(ScreensRoute.SectionsScreen().route)
                                  dropDownMenuExpanded.value = false
                              }) {
                                  Text("Sections")
                              }

                              DropdownMenuItem(onClick = {
                                  Toast.makeText(
                                      applicationContext,
                                      "Settings Click",
                                      Toast.LENGTH_SHORT
                                  )
                                      .show()
                                  dropDownMenuExpanded.value = false
                              }) {
                                  Text("Settings")
                              }

                              DropdownMenuItem(onClick = {
                                  Toast.makeText(
                                      applicationContext,
                                      "Send Feedback Click",
                                      Toast.LENGTH_SHORT
                                  )
                                      .show()
                                  dropDownMenuExpanded.value = false
                              }) {
                                  Text("Send Feedback")
                              }
                          }
                              Box(modifier = Modifier
                                  .padding(1.dp, 2.dp)
                                  .height(45.dp)) {
                                  SearchView(state = text)
                              }
                              IconButton(

                                  content =
                                  { Icon(Icons.Outlined.Menu,    contentDescription = "")}, onClick = {
                                      dropDownMenuExpanded.value = !dropDownMenuExpanded.value
                                  })

                      }

                  )
              },
              content =  {
                  Column(
                      modifier = Modifier
                          .padding(it)){
                      // A surface container using the 'background' color from the theme
                      Surface(

                          modifier = Modifier.fillMaxSize(),
                          color = MaterialTheme.colorScheme.background,
                      ) {
                          AppNavHost(searchState = text, navController = navController)

                      }
                  }
              }
              )


            }
        }
    }
}
