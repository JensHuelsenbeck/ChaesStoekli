package com.example.cheas_stoeckli


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cheas_stoeckli.data.services.AuthenticationService
import com.example.cheas_stoeckli.navigation.CheeseRoute
import com.example.cheas_stoeckli.navigation.FavoriteRoute
import com.example.cheas_stoeckli.navigation.FondueRoute
import com.example.cheas_stoeckli.navigation.NewsRoute
import com.example.cheas_stoeckli.navigation.OfferRoute
import com.example.cheas_stoeckli.navigation.RacletteRoute
import com.example.cheas_stoeckli.navigation.TeamRoute
import com.example.cheas_stoeckli.ui.enums.OfferKind
import com.example.cheas_stoeckli.ui.enums.TabItem
import com.example.cheas_stoeckli.ui.screens.CheeseScreen
import com.example.cheas_stoeckli.ui.screens.FavoriteScreen
import com.example.cheas_stoeckli.ui.screens.FondueScreen
import com.example.cheas_stoeckli.ui.screens.LoginScreen
import com.example.cheas_stoeckli.ui.screens.NewsScreen
import com.example.cheas_stoeckli.ui.screens.OfferScreen
import com.example.cheas_stoeckli.ui.screens.RacletteScreen
import com.example.cheas_stoeckli.ui.screens.TeamScreen
import com.example.cheas_stoeckli.ui.theme.screenBackgroundPrimary
import com.example.cheas_stoeckli.ui.viewModel.NetworkViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AppStart(
    modifier: Modifier = Modifier,
    authenticationService: AuthenticationService = AuthenticationService(),
    networkViewmodel: NetworkViewModel = koinViewModel()
) {


    val isDeviceOnline by networkViewmodel.isOnline.collectAsState()

    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val navController = rememberNavController()
    var selectedTab by rememberSaveable { mutableStateOf(TabItem.NEWS) }

    val isSignedIn by authenticationService.isSignedIn.collectAsState(false)


    val onOfferNavigate: (OfferKind) -> Unit = { kind ->
        when (kind) {
            OfferKind.KAESE -> navController.navigate(CheeseRoute)
            OfferKind.RACLETTE -> navController.navigate(RacletteRoute)
            OfferKind.FONDUE -> navController.navigate(FondueRoute)
            OfferKind.ALLGEMEIN -> {}
        }
    }

    LaunchedEffect(isDeviceOnline) {
        if (!isDeviceOnline) {
            snackbarHostState.showSnackbar(
                message = "Keine Internetverbindung",
                duration = SnackbarDuration.Indefinite
            )
        } else {
            snackbarHostState.currentSnackbarData?.dismiss()
        }
    }


    if (!isSignedIn) {
        LoginScreen()
    } else {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
                .background(screenBackgroundPrimary)
                .navigationBarsPadding()
                .systemBarsPadding(),
            snackbarHost = { SnackbarHost(snackbarHostState) },
            bottomBar = {
                NavigationBar(
                    containerColor = screenBackgroundPrimary,
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    TabItem.entries.forEach { tabItem ->
                        NavigationBarItem(
                            selected = selectedTab == tabItem,
                            onClick = { selectedTab = tabItem },
                            icon = {
                                Image(
                                    painter = painterResource(tabItem.tabIcon),
                                    contentDescription = "TabItem",
                                    colorFilter = if (selectedTab == tabItem)
                                        ColorFilter.tint(Color.Black)
                                    else ColorFilter.tint(Color(0xFFC49F72)),
                                )
                            },
                            label = {
                                Text(tabItem.title)
                            },
                            colors = NavigationBarItemDefaults.colors(
                                indicatorColor = Color.Transparent
                            )
                        )
                    }
                }
            }
        )

        { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                NavHost(
                    navController = navController,
                    startDestination = selectedTab.route,
                ) {
                    composable<NewsRoute> {
                        NewsScreen(
                            snackbarHostState = snackbarHostState,
                            snackbarScope = scope
                        )
                    }
                    composable<OfferRoute> {
                        OfferScreen(
                            snackbarHostState = snackbarHostState,
                            snackbarScope = scope,
                            onClickToDetailedOfferScreen = onOfferNavigate
                        )
                    }
                    composable<TeamRoute> {
                        TeamScreen()
                    }
                    composable<FavoriteRoute> {
                        FavoriteScreen()
                    }
                    composable<CheeseRoute> {
                        CheeseScreen(
                            popBackStack = { navController.popBackStack() },
                            snackbarHostState = snackbarHostState,
                            snackbarScope = scope,

                        )
                    }
                    composable<RacletteRoute> {
                        RacletteScreen(popBackStack = { navController.popBackStack() })
                    }
                    composable<FondueRoute> {
                        FondueScreen(popBackStack = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}


