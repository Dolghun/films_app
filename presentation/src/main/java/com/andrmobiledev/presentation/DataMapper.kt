package com.andrmobiledev.presentation

import com.andrmobiledev.domain.entities.Show
import com.andrmobiledev.presentation.uidata.ShowDetailUI
import com.andrmobiledev.presentation.uidata.ShowListItem

fun Show.toUI() = ShowListItem(id, name, image?.medium)
fun Show.toDetailUI() = ShowDetailUI(name, image?.original, rating, summary, officialSite, premiered)