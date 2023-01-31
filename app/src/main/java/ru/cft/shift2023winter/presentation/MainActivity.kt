package ru.cft.shift2023winter.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.google.accompanist.themeadapter.material.MdcTheme
import ru.cft.shift2023winter.presentation.bestanime.BestAnimeScreen
import ru.cft.shift2023winter.presentation.bestanime.BestAnimeViewModel
import javax.inject.Inject

class MainActivity : ComponentActivity() {

	val component by lazy {
		(application as AnimeApplication).component
	}

	@Inject
	lateinit var viewModelFactory: ViewModelFactory

	private val viewModel by lazy {
		ViewModelProvider(this,viewModelFactory)[BestAnimeViewModel::class.java]
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		component.inject(this)
		super.onCreate(savedInstanceState)
		viewModel.loadData()
		setContent {
			MdcTheme {
				BestAnimeScreen(viewModel = viewModel)
			}
		}
	}
}