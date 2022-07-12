package com.example.myapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.myapp.dto.BeersResponse
import com.example.myapp.model.Beer
import com.example.myapp.services.APIServices

class MainViewModel : ViewModel() {


    suspend fun getBeers() : MutableList<Beer> {

        var response = APIServices().getBeers()
        var beersList: MutableList<Beer> = ArrayList(response)
        return beersList


    }

    /*suspend fun getBeers(): MutableList<Beer> {

        getBers()
        var beersList: MutableList<Beer> = ArrayList()
        beersList.add(
            Beer(
                1,
                "Buzz",
                "A Real Bitter Experience.",
                "https://images.punkapi.com/v2/keg.png"
            )
        )
        beersList.add(
            Beer(
                2,
                "Trashy Blonde",
                "You Know You Shouldn't",
                "https://images.punkapi.com/v2/2.png"
            )
        )
        beersList.add(
            Beer(
                3,
                "Berliner Weisse With Yuzu - B-Sides",
                "Japanese Citrus Berliner Weisse.",
                "https://images.punkapi.com/v2/keg.png"
            )
        )
        beersList.add(
            Beer(
                4,
                "Pilsen Lager",
                "Unleash the Yeast Series.",
                "https://images.punkapi.com/v2/4.png"
            )
        )
        beersList.add(
            Beer(
                5,
                "Avery Brown Dredge",
                "Bloggers' Imperial Pilsner.",
                "https://images.punkapi.com/v2/5.png"
            )
        )
        beersList.add(
            Beer(
                6,
                "Electric India",
                "Vibrant Hoppy Saison.",
                "https://images.punkapi.com/v2/6.png"
            )
        )
        beersList.add(
            Beer(
                7,
                "AB:12",
                "Imperial Black Belgian Ale.",
                "https://images.punkapi.com/v2/7.png"
            )
        )
        beersList.add(
            Beer(
                8,
                "Fake Lager",
                "Bohemian Pilsner.",
                "https://images.punkapi.com/v2/8.png"
            )
        )
        beersList.add(
            Beer(
                9,
                "AB:07",
                "Whisky Cask-Aged Scotch Ale.",
                "https://images.punkapi.com/v2/9.png"
            )
        )
        beersList.add(
            Beer(
                10,
                "Bramling X",
                "Single Hop IPA Series - 2011.",
                "https://images.punkapi.com/v2/10.png"
            )
        )
        beersList.add(
            Beer(
                11,
                "Misspent Youth",
                "Milk & Honey Scotch Ale.",
                "https://images.punkapi.com/v2/keg.png"
            )
        )
        beersList.add(
            Beer(
                12,
                "Arcade Nation",
                "Seasonal Black IPA.",
                "https://images.punkapi.com/v2/12.png"
            )
        )
        beersList.add(
            Beer(
                13,
                "Movember",
                "Moustache-Worthy Beer.",
                "https://images.punkapi.com/v2/13.png"
            )
        )
        beersList.add(
            Beer(
                14,
                "Alpha Dog",
                "Existential Red Ale.",
                "https://images.punkapi.com/v2/14.png"
            )
        )
        beersList.add(
            Beer(
                15,
                "Mixtape 8",
                "An Epic Fusion Of Old Belgian, American New Wave, And Scotch Whisky.",
                "https://images.punkapi.com/v2/15.png"
            )
        )
        beersList.add(
            Beer(
                16,
                "Libertine Porter",
                "Dry-Hopped Aggressive Porter.",
                "https://images.punkapi.com/v2/16.png"
            )
        )
        beersList.add(
            Beer(
                17,
                "AB:06",
                "Imperial Black IPA.",
                "https://images.punkapi.com/v2/17.png"
            )
        )
        beersList.add(
            Beer(
                18,
                "Russian Doll – India Pale Ale",
                "Nesting Hop Bomb.",
                "https://images.punkapi.com/v2/18.png"
            )
        )
        beersList.add(
            Beer(
                19,
                "Hello My Name Is Mette-Marit",
                "Lingonberry Double IPA.",
                "https://images.punkapi.com/v2/19.png"
            )
        )
        beersList.add(
            Beer(
                20,
                "Rabiator",
                "Imperial Wheat Beer",
                "https://images.punkapi.com/v2/keg.png"
            )
        )
        beersList.add(
            Beer(
                21,
                "Vice Bier",
                "Hoppy Wheat Bier.",
                "https://images.punkapi.com/v2/keg.png"
            )
        )
        beersList.add(
            Beer(
                22,
                "Devine Rebel (w/ Mikkeller)",
                "Oak-aged Barley Wine.",
                "https://images.punkapi.com/v2/22.png"
            )
        )
        beersList.add(
            Beer(
                23,
                "Storm",
                "Islay Whisky Aged IPA.",
                "https://images.punkapi.com/v2/23.png"
            )
        )
        beersList.add(
            Beer(
                24,
                "The End Of History",
                "The World's Strongest Beer.",
                "https://images.punkapi.com/v2/24.png"
            )
        )
        beersList.add(
            Beer(
                25,
                "Bad Pixie",
                "Spiced Wheat Beer.",
                "https://images.punkapi.com/v2/25.png"
            )
        )


        return beersList
    }
*/
}