package ru.cft.shift2023winter.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import ru.cft.shift2023winter.presentation.main.BestAnimeScreen
import javax.inject.Inject

class MainActivity : ComponentActivity() {

	val component by lazy {
		(application as AnimeApplication).component
	}

	@Inject
	lateinit var viewModelFactory: ViewModelFactory

	private val viewModel by lazy {
		ViewModelProvider(this,viewModelFactory)[AnimeListViewModel::class.java]
	}

	override fun onCreate(savedInstanceState: Bundle?) {
		component.inject(this)
		super.onCreate(savedInstanceState)
		viewModel.loadData()
		setContent {
			BestAnimeScreen(viewModel = viewModel)
		}
	}
}