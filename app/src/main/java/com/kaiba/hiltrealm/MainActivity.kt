package com.kaiba.hiltrealm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.kaiba.hiltrealm.ui.theme.HiltRealmTheme
import com.kaiba.hiltrealm.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewmodel by viewModels<MainViewModel>()
        setContent {
            HiltRealmTheme {

                val coroutineScope = rememberCoroutineScope()
                Column {
                    Text(text = "${viewmodel.size.value}")
                    Button(onClick = { coroutineScope.launch {
                        viewmodel.add(viewmodel.text.value)

                    }

                    }) {
                        Text(text = "Add")
                    }
                    Button(onClick = { coroutineScope.launch {
                        viewmodel.delete()

                    }

                    }) {
                        Text(text = "Delete")
                    }
                    TextField(
                        value = viewmodel.text.value,
                        onValueChange = { viewmodel.text.value = it }

                    )
                    LazyColumn(content = {
                        items(viewmodel.items.value){
                            Text(text = it.name)
                        }
                    })
                }
               

            }
        }
    }
}

