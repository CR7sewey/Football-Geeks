package com.example.footballgeeks.competitionsList.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.footballgeeks.common.remote.model.Competition
import com.example.footballgeeks.common.remote.model.CompetitionDetails
import com.example.footballgeeks.common.remote.model.TeamDetails
import com.example.footballgeeks.competitionsList.presentation.CompetitionsListViewModel

@Composable
fun CompetitionsScreen(competitionsListViewModel: CompetitionsListViewModel, navHostController: NavHostController ,modifier: Modifier = Modifier) {
    val uiCompetitions by competitionsListViewModel.uiCompetitions.collectAsState()

    CompetitionsListContent(uiCompetitions, competitionsListViewModel, navHostController, onClick = {itemClicked: CompetitionDetails -> navHostController.navigate(route = "competition/${itemClicked.code}")})
}

@Composable
fun CompetitionsListContent(competitions: CompetitionsListUIState, competitionsListViewModel: CompetitionsListViewModel, navHostController: NavHostController, onClick: (itemClicked: CompetitionDetails) -> Unit, modifier: Modifier = Modifier) {
    var query by remember { mutableStateOf("") }
    var competitionsShown: CompetitionDetails? by remember { mutableStateOf(null) }
    Column(modifier = Modifier
        .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Box {
            Column(modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
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
                    }


                    /*Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = movie?.title ?: "Empty title", fontSize = 48.sp,

                )*/
                }}
        }
        ERSearchBar("Search a competition", query, onValueChange = {
            query = it
            var competition: CompetitionDetails? = null
            competition = competitions.list.find { it -> it.name == query }
            competitionsShown = competition }, onSearchClicked = {

        })
        LazyColumn(modifier = modifier.padding(8.dp)) {
            if (query.isEmpty()) {
                items(competitions.list) { current ->
                    EachRowGameDisplay(current, onClick)
                    Spacer(modifier = Modifier.size(8.dp))
                }
            }

        }
        if (!query.trim().isEmpty()) {
            EachRowGameDisplay(competitionsShown, onClick)
        }

    }

}

@Composable
fun EachRowGameDisplay(competition: CompetitionDetails?, onClick: (itemClicked: CompetitionDetails) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(Color.LightGray).padding(4.dp).clickable { if(competition != null) {
                onClick.invoke(competition)
            }},
        verticalAlignment = Alignment.CenterVertically
    ) {


        Spacer(modifier = Modifier.width(10.dp))

        // Checkbox e nome da equipa
        // Imagem da equipa
        Image(
            painter = rememberAsyncImagePainter(competition?.emblem),
            contentDescription = null, // Descrição para acessibilidade
            modifier = Modifier
                .size(26.dp) // Tamanho da imagem
                .padding(end = 4.dp),
            contentScale = ContentScale.Crop
        )
        Text(
            text = competition?.name ?: "Searching...",
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