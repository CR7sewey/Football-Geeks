package com.example.footballgeeks.teamsList.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.footballgeeks.teamsList.presentation.TeamsListViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import coil.size.Size
import com.example.footballgeeks.common.remote.model.Team
import com.example.footballgeeks.common.remote.model.TeamDetails
import com.example.footballgeeks.ui.theme.colorNav
import com.example.footballgeeks.ui.theme.gamesColor
import kotlinx.coroutines.flow.MutableStateFlow
import kotlin.text.capitalize

@Composable
fun TeamsListScreen(teamsListViewModel: TeamsListViewModel = hiltViewModel<TeamsListViewModel>(), navHostController: NavHostController, modifier: Modifier = Modifier) {
    val uiTeams by teamsListViewModel.uiTeams.collectAsState()

    TeamsListContent(uiTeams, teamsListViewModel, navHostController)
}

@Composable
fun TeamsListContent(teams: TeamsListUIState, teamsListViewModel: TeamsListViewModel, navHostController: NavHostController, modifier: Modifier = Modifier) {
    var query by remember { mutableStateOf("") }
    var teamsShown: TeamDetails? by remember { mutableStateOf(null) }
    Column(modifier = Modifier
        .fillMaxWidth(),
         horizontalAlignment = Alignment.CenterHorizontally) {
        Box {
            Column(modifier = Modifier
                .fillMaxWidth()
                .background(colorNav)
                .padding(12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = {
                        navHostController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back Button"
                        )
                    }}
                    /*Row(modifier = Modifier,
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val indexes = listOf(0,1,2)
                        val navbar = listOf("landingPage","teams","competitions")
                        val labels = listOf("matches","teams","competitions")
                        val emblems = listOf("","https://crests.football-data.org/2061.png", "https://crests.football-data.org/PL.png")
                        Box(modifier = modifier.padding(8.dp).clickable {navHostController.navigate(route = navbar[1])}) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) {
                                Image(
                                    painter = rememberAsyncImagePainter(emblems[1]),
                                    contentDescription = null, // Descrição para acessibilidade
                                    modifier = Modifier
                                        .size(72.dp) // Tamanho da imagem
                                    ,
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = modifier.size(2.dp))
                                Text(text = "${labels[1].capitalize()}", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                            }
                        }
                    }*/

                    /*Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = movie?.title ?: "Empty title", fontSize = 48.sp,

                )*/
                }
        }
        ERSearchBar("Search a team", query, onValueChange = {
            query = it
            var team: TeamDetails? = null
            team = teams.list.find { it -> it.name == query }
            teamsShown = team }, onSearchClicked = {

        })
        LazyColumn(modifier = modifier.padding(8.dp)) {
            if (query.isEmpty()) {
                items(teams.list) { current ->
                    EachRowGameDisplay(current)
                    Spacer(modifier = Modifier.size(8.dp))
                }
            }

        }
        if (!query.trim().isEmpty()) {
            EachRowGameDisplay(teamsShown)
        }

    }
    
}

@Composable
fun EachRowGameDisplay(team: TeamDetails?) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(gamesColor).padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {


        Spacer(modifier = Modifier.width(10.dp))

        // Checkbox e nome da equipa
        // Imagem da equipa
        Image(
            painter = rememberAsyncImagePainter(team?.crest),
            contentDescription = null, // Descrição para acessibilidade
            modifier = Modifier
                .size(26.dp) // Tamanho da imagem
                .padding(end = 4.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = team?.name ?: "Searching...",
            style = MaterialTheme.typography.bodyMedium,
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(16.dp))

    }
}



@Composable
fun ERSearchBar(placeholder: String, query: String, modifier: Modifier = Modifier, onValueChange: (String) -> Unit, onSearchClicked: () -> Unit) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current


    OutlinedTextField(
        modifier = modifier.fillMaxWidth().padding(16.dp).focusRequester(focusRequester),
        value = query,
        onValueChange = onValueChange,
        /*colors = CardDefaults.cardColors(
            backgroundColor = Color(0XFF101921),
            placeholderColor = Color(0XFF888D91),
            leadingIconColor = Color(0XFF888D91),
            trailingIconColor = Color(0XFF888D91),
            textColor = Color.White,
            focusedIndicatorColor = Color.Transparent, cursorColor = Color(0XFF070E14)
        ),*/
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search,
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
                focusRequester.freeFocus()
                focusManager.clearFocus()
                onSearchClicked.invoke()
            }
        ),
        leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon") },
        placeholder = { Text(text = placeholder, fontSize = 16.sp) },
        shape = RoundedCornerShape(8.dp)
    )
}