package com.example.cheas_stoeckli.data.Fake

import com.example.cheas_stoeckli.domain.models.Cheese
import com.example.cheas_stoeckli.ui.enums.MilkType

    val fakeCheese = Cheese(
        name = "Schwellbrunner",
        description = """
Der letzte Appenzeller mit reiner Appenzeller Rohmilch, affiniert von Rolf Beeler und bei Chäs Stöckli herrlich reif ausgelagert.

Passt wunderbar zu: Rotwein, etc.
""".trimMargin(),
        milkType = MilkType.COW
    )

