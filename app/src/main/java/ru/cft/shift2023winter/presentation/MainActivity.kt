package ru.cft.shift2023winter.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.accompanist.themeadapter.material.MdcTheme
import ru.cft.shift2023winter.presentation.main.MainScreen
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    private val component by lazy {
        (application as AnimeApplication).component
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContent {
            MdcTheme {
                MainScreen(viewModelFactory)
            }
        }
    }
}