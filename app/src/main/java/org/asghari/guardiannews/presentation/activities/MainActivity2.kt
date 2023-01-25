package org.asghari.guardiannews.presentation.activities

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.os.Bundle
import android.os.Debug
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.materialIcon
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.DpOffset
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.asghari.guardiannews.data.models.NewsList
import org.asghari.guardiannews.other.NewsListState
import org.asghari.guardiannews.presentation.composableviews.AppNavHost

import org.asghari.guardiannews.presentation.composableviews.HomePageScreen
import org.asghari.guardiannews.presentation.ui.theme.GuardianNewsTheme
import org.asghari.guardiannews.presentation.viewmodels.NewsListViewModel
import java.security.AccessController.getContext
 import androidx.compose.material.icons.filled.Menu  // ok
import androidx.compose.material.icons.filled.Print
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import okhttp3.internal.immutableListOf
import org.asghari.guardiannews.R
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@AndroidEntryPoint
class MainActivity2 : ComponentActivity() {
    @Composable
    fun SearchView(state: MutableState<TextFieldValue>) {
        TextField(
            value = state.value,
            onValueChange = { value ->
                state.value = value
            },

            modifier = Modifier.padding(0.dp)
                .clip(RoundedCornerShape(50))
                .border(
                    1.dp, Color(0x66ffffff),

                )
                .background(Color(0x44ffffff)),
            textStyle = TextStyle(color = Color.DarkGray, fontSize = 16.sp),
            leadingIcon = {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "",

                )
            },
            trailingIcon = {
                if (state.value != TextFieldValue("")) {
                    IconButton(
                        onClick = {
                            state.value =
                                TextFieldValue("") // Remove text from TextField when you press the 'X' icon
                        }
                    ) {
                        Icon(
                            Icons.Default.Close,
                            contentDescription = "",
                            modifier = Modifier
                                .padding(15.dp)
                                .size(24.dp)
                        )
                    }
                }
            },
            singleLine = true,
            shape = RectangleShape, // The TextFiled has rounded corners top left and right by default
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Gray,
                leadingIconColor = Color.Gray,
                trailingIconColor = Color.Gray,
                backgroundColor =  Color(0x55ffffff),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
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
                                  Toast.makeText(
                                      applicationContext,
                                      "Refresh Click",
                                      Toast.LENGTH_SHORT
                                  )
                                      .show()
                                  dropDownMenuExpanded.value = false
                              }) {
                                  Text("Refresh")
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
                          AppNavHost(navController = navController)

                      }
                  }
              }
              )


            }
        }
    }
}
