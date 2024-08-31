package com.hgshkt.pokedex.ui.screens.listDetail

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.hgshkt.pokedex.ui.data.model.UiSimplePokemon
import com.hgshkt.pokedex.ui.screens.list.ListScreen
import com.hgshkt.pokedex.ui.screens.detail.DetailScreen

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun MainScreen() {
    var selectedItem: PokemonSaver? by rememberSaveable(stateSaver = PokemonSaver.Saver) {
        mutableStateOf(null)
    }
    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }
    val listState = rememberSaveable(saver = LazyGridState.Saver) {
        LazyGridState()
    }

    NavigableListDetailPaneScaffold(
        navigator = navigator,
        detailPane = {
            AnimatedPane(Modifier) {
                selectedItem?.let { item ->
                    DetailScreen(item.pokemon.id)
                }
            }
        },
        listPane = {
            AnimatedPane(Modifier) {
                ListScreen(
                    listState = listState,
                    onItemClick = { pokemon ->
                        selectedItem = pokemon
                        navigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
                    },
                )
            }
        }
    )
}

class PokemonSaver(val pokemon: UiSimplePokemon) {
    companion object {
        val Saver: Saver<PokemonSaver?, UiSimplePokemon> = Saver(
            { it?.pokemon },
            ::PokemonSaver,
        )
    }
}