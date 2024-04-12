package com.hgshkt.pokedex.ui.listDetail

import androidx.activity.compose.BackHandler
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.hgshkt.domain.model.Pokemon
import com.hgshkt.pokedex.ui.list.ListScreen
import com.hgshkt.pokedex.ui.detail.DetailScreen

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun MainScreen() {
    var selectedItem: PokemonSaver? by rememberSaveable(stateSaver = PokemonSaver.Saver) {
        mutableStateOf(null)
    }
    val navigator = rememberListDetailPaneScaffoldNavigator<Nothing>()

    BackHandler(navigator.canNavigateBack()) {
        navigator.navigateBack()
    }
    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        detailPane = {
            AnimatedPane(Modifier) {
                selectedItem?.let { item ->
                    DetailScreen(item.pokemon)
                }
            }
        },
        listPane = {
            AnimatedPane(Modifier) {
                ListScreen(
                    onItemClick = { pokemon ->
                        selectedItem = pokemon
                        navigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
                    },
                )
            }
        },
    )
}

class PokemonSaver(val pokemon: Pokemon) {
    companion object {
        val Saver: Saver<PokemonSaver?, Pokemon> = Saver(
            { it?.pokemon },
            ::PokemonSaver,
        )
    }
}