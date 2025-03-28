package com.example.landingpage.landingPage.presentation.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.landingpage.landingPage.presentation.LandingPageViewModel
import com.example.landingpage.landingPage.remote.model.Match
import com.example.landingpage.landingPage.theme.gamesColor

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
private fun LandingScreenContent(
    matches: MatchesListUiState, onClick: (Match) -> Unit,
    navHostController: NavHostController, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        if (matches.isError == false && !matches.list.isEmpty()) {
            LazyColumn {
                items(matches.list) { current ->
                    MatchDisplay(current, onClick= onClick)
                    Spacer(modifier = Modifier.size(8.dp))
                }
            }
        }
        else if (matches.isError == false) {
            Text("To be done later ...")
            Button(onClick = {navHostController.navigate("teams")}) { }
        }
        else if (matches.isLoading) {
            Text("Loading...")
        }
        else if (matches.isError) {
            Toast.makeText(context, matches.errorMessage, Toast.LENGTH_LONG).show()
        }

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

