package com.example.footballgeeks.landingPage.presentation.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.footballgeeks.common.remote.model.MatchesDTO
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.example.footballgeeks.common.remote.model.Match
import com.example.footballgeeks.landingPage.data.remote.LandingPageService
import com.example.mycinema.common.data.remote.RetroFitClient
import retrofit2.Call
import retrofit2.Response
import coil.size.Size
import com.example.footballgeeks.landingPage.presentation.LandingPageViewModel
import com.example.footballgeeks.ui.theme.blueLight
import com.example.footballgeeks.ui.theme.gamesColor
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Callback

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LandingPageScreen(landingPageViewModel: LandingPageViewModel = hiltViewModel<LandingPageViewModel>(), navHostController: NavHostController, modifier: Modifier = Modifier) {

    val uiCurrentGames by landingPageViewModel.uiCurrentGames.collectAsState()

    /*var matches by remember { mutableStateOf<List<Match>>(emptyList()) }
    val apiService = RetroFitClient.retrofit.create(LandingPageService::class.java)
    val callMatchesDTO = apiService.getMatches()
    callMatchesDTO.enqueue(object : Callback<MatchesDTO> {
        override fun onResponse(
            call: Call<MatchesDTO?>,
            response: Response<MatchesDTO?>
        ) {
            println("AQUI 4")
            println(response.body())
            if (response.isSuccessful) {
                val movies = response.body()?.matches
                if (movies != null) {
                    matches = movies
                    println("AQUI 3")
                }
            }
            else {
                println(response.toString())
                Log.d("MainActivity", "Request Error :: ${response.errorBody()}")
            }
        }

        override fun onFailure(call: Call<MatchesDTO?>, t: Throwable) {
            Log.d("MainActivity", "Network Error :: ${t.message}")

        }
    })
    //val test = listOf<String>("1","2")*/
    Log.d("RESPOSTA", uiCurrentGames.toString())
    LandingScreenContent(uiCurrentGames, onClick =  { itemClicked: Match -> navHostController.navigate("match/${itemClicked.id}")}, navHostController)
}

@Composable
private fun LandingScreenContent(matches: MatchesListUiState, onClick: (itemClicked: Match) -> Unit,navHostController: NavHostController, modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize().background(Color.LightGray)) {
        TopBar(navHostController)
       /* Text(
            text = "Current Games",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(16.dp).align()
        )*/
        LazyColumn(contentPadding = PaddingValues(16.dp)) {
            items(matches.list) { game ->
                MatchTest(game, navHostController)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navHostController: NavHostController) {
   // var currentExample by remember { mutableStateOf<(@Composable () -> Unit)?>(null) }

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    TopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                "Football Geeks",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.White,
            )
        },
        /*navigationIcon = {
            IconButton(onClick = { /* do something */ }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Localized description"
                )
            }
        },*/
        actions = {

            IconButton(onClick = {

                //currentExample = { MinimalDropdownMenu() }
            }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    tint = Color.White,
                    contentDescription = "Localized description"
                )

                MinimalDropdownMenu(navHostController)
            }
        },
        scrollBehavior = scrollBehavior,
    )
}

@Composable
fun MinimalDropdownMenu(navHostController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .padding(16.dp)
    ) {
        IconButton(onClick = { expanded = !expanded }) {
            Icon(Icons.Default.MoreVert, contentDescription = "More options")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = { Text("Competitions") },
                onClick = { navHostController.navigate("competitions") }
            )
            DropdownMenuItem(
                text = { Text("Teams") },
                onClick = { navHostController.navigate("teams") }
            )
        }
    }
}

@Composable
fun MatchTest(game: Match, navHostController: NavHostController) {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp).clickable(enabled = true) { navHostController.navigate(route = "match/${game.id}")}
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TeamInfo(
                teamName = game.homeTeam.name,
                teamCrest = game.homeTeam.crest
            )
            MatchScore(
                homeScore = game.score.fullTime.home,
                awayScore = game.score.fullTime.away,
                intervalHome = game.score.halfTime.home,
                intervalAway = game.score.halfTime.away
            )
            TeamInfo(
                teamName = game.awayTeam.name,
                teamCrest = game.awayTeam.crest
            )
        }


        /*Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            val svgPainter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(game.area.flag).placeholder(com.example.footballgeeks.R.drawable.ic_launcher_foreground)
                    .decoderFactory(SvgDecoder.Factory()) // Suporte a SVG
                    .size(Size.ORIGINAL) // Tamanho original da imagem
                    .build()
            )
            // Imagem que ocupa o espaço da barra
            // Imagem que ocupa o espaço da barra
            Spacer(modifier = Modifier.width(10.dp))
           /* Image(
                painter = svgPainter,
                contentDescription = null, // Descrição para acessibilidade
                modifier = Modifier
                    .width(60.dp)
                    .fillMaxHeight(),
                contentScale = ContentScale.Crop
            )*/

            Spacer(modifier = Modifier.width(16.dp))
            Image(
                painter = rememberAsyncImagePainter(game.homeTeam.crest),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(game.homeTeam.name, fontWeight = FontWeight.Bold)
                    Text(game.awayTeam.name, fontWeight = FontWeight.Bold)
                }
                Text("FH: ${game.score}", fontSize = 14.sp)
            }
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                painter = rememberAsyncImagePainter(game.awayTeam.crest),
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )

        }*/
    }
}

@Composable
fun TeamInfo(teamName: String, teamCrest: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(teamCrest),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        /*Text(
            text = teamName,
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )*/
    }
}

@Composable
fun MatchScore(homeScore: Int, awayScore: Int, intervalHome: Int, intervalAway: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "$intervalHome - $intervalAway",
            fontWeight = FontWeight.Bold,
            fontSize = 12.sp,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "$homeScore - $awayScore",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            color = MaterialTheme.colorScheme.primary
        )

    }
}


@Composable
private fun MatchDisplay(match: Match, onClick: (itemClicked: Match) -> Unit, modifier: Modifier = Modifier) {
        Column(modifier = modifier.clickable {onClick.invoke(match)}) {
        EachRowMatchDisplay(teamName = match.homeTeam.name, teamSymbol = match.homeTeam.crest, teamHalfScore = match.score.halfTime.home, teamFinalScore = match.score.fullTime.home, countrySymbol = match.area.flag)
        Spacer(modifier= Modifier.size(1.dp).background(Color.LightGray))
        EachRowMatchDisplay(teamName = match.awayTeam.name, teamSymbol = match.awayTeam.crest, teamHalfScore = match.score.halfTime.away, teamFinalScore = match.score.fullTime.away, countrySymbol = match.area.flag)
        }



}

@Composable
fun EachRowMatchDisplay(teamName: String, teamSymbol: String, teamHalfScore: Int, teamFinalScore:Int, countrySymbol: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(gamesColor),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val svgPainter = rememberAsyncImagePainter(
            model = ImageRequest.Builder(LocalContext.current)
                .data(countrySymbol)
                .decoderFactory(SvgDecoder.Factory()) // Suporte a SVG
                .size(Size.ORIGINAL) // Tamanho original da imagem
                .build()
        )
        // Imagem que ocupa o espaço da barra
        Image(
            painter = svgPainter,
            contentDescription = null, // Descrição para acessibilidade
            modifier = Modifier
                .width(60.dp)
                .fillMaxHeight(),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.width(10.dp))

        // Checkbox e nome da equipa
        // Imagem da equipa
        Image(
            painter = rememberAsyncImagePainter(teamSymbol),
            contentDescription = null, // Descrição para acessibilidade
            modifier = Modifier
                .size(26.dp) // Tamanho da imagem
                .padding(end = 4.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = teamName,
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        // Pontuações
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = teamHalfScore.toString(), fontSize = 16.sp, color = Color.DarkGray)
            Text(text = teamFinalScore.toString(), fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
        }

        Spacer(modifier = Modifier.width(16.dp))
    }
}


/**
 * Box(
 *         modifier = Modifier
 *             .fillMaxWidth()
 *             .background(color = Color.LightGray),
 *     ) {
 *         AsyncImage(
 *             model = match.area.flag,
 *             contentDescription = "League Image",
 *             contentScale = ContentScale.Crop,
 *             modifier = modifier.size(14.dp)
 *         )
 *
 *             Row(modifier = modifier.padding(8.dp).fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
 *
 *                 Column(modifier = modifier.padding(2.dp), verticalArrangement = Arrangement.Center ) {
 *                     Row(modifier = modifier.padding(2.dp).fillMaxWidth()) {
 *                         AsyncImage(
 *                         model = match.homeTeam.crest,
 *                         contentDescription = "Home Team Image",
 *                         modifier = modifier
 *                             .size(14.dp)
 *                             .padding(top = 2.dp)
 *                     )
 *                         Spacer(modifier = Modifier.size(4.dp))
 *                         Text(text = match.homeTeam.name)
 *                         Spacer(modifier = Modifier.size(4.dp))
 *                             Text(text = "${match.score.halfTime.home}")
 *                             Spacer(modifier = Modifier.size(2.dp))
 *                             Text(text = "${match.score.fullTime.home}")
 *
 *                     }
 *                     Row(modifier = modifier.padding(2.dp).fillMaxWidth()) {
 *                         AsyncImage(
 *                             model = match.awayTeam.crest,
 *                             contentDescription = "Away Team Image",
 *                             modifier = modifier
 *                                 .size(14.dp)
 *                                 .padding(top = 2.dp)
 *                         )
 *                         Spacer(modifier = Modifier.size(4.dp))
 *                         Text(text = match.awayTeam.name)
 *                         Text(text = "${match.score.halfTime.home}")
 *                         Spacer(modifier = Modifier.size(2.dp))
 *                         Text(text = "${match.score.fullTime.home}")
 *                     }
 *             }
 *         }
 *     }
 */

