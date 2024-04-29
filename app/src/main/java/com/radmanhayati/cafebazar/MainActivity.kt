package com.radmanhayati.cafebazar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.radmanhayati.cafebazar.presentation.MovieScreen
import com.radmanhayati.cafebazar.presentation.MovieViewModel
import com.radmanhayati.cafebazar.ui.component.SplashScreen
import com.radmanhayati.cafebazar.ui.theme.CafebazarTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CafebazarTheme {
                var isSplashScreenVisible by rememberSaveable { mutableStateOf(true) }

                LaunchedEffect(key1 = true) {
                    delay(1000)
                    isSplashScreenVisible = false
                }

                SharedTransitionLayout {
                    AnimatedContent(targetState = isSplashScreenVisible, label = "splash-screen") { target ->
                        if (target) {
                            SplashScreen(
                                modifier = Modifier.background(Color.Black),
                                animatedContentScope = this@AnimatedContent
                            )
                        } else {
                            Scaffold(
                                containerColor = Color.Black,
                                topBar = {
                                    CenterAlignedTopAppBar(
                                        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                            containerColor = Color.Transparent,
                                        ),
                                        title = {
                                            Text(text = "Discover")
                                        },
                                        actions = {
                                            Image(
                                                painter = painterResource(id = R.drawable.logo),
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .size(32.dp)
                                                    .sharedElement(
                                                        state = rememberSharedContentState(key = "icon"),
                                                        animatedVisibilityScope = this@AnimatedContent,
                                                    )
                                            )
                                        })
                                },
                            ) { paddingValues ->
                                Surface(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(paddingValues),
                                ) {

                                    val viewModel = hiltViewModel<MovieViewModel>()
                                    val movies =
                                        viewModel.moviePagingFlow.collectAsLazyPagingItems()
                                    MovieScreen(movies = movies)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}