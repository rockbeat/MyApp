package com.example.myapp.model

data class Beer(
    var id: Int,
    var name: String? = null,
    var tagline: String? = null,
    var first_brewed: String? = null,
    var description: String? = null,
    var image_url: String? = null,
    var abv : Double = 0.0,
    var ibu : Double = 0.0,
    var target_fg : Int = 0,
    var target_og : Double = 0.0,
    var ebc : Int = 0,
    var srm : Double = 0.0,
    var ph : Double = 0.0,
    var attenuation_level : Double = 0.0,
    var volume: Volume? = null,
    var boil_volume: BoilVolume? = null,
    var method: Method? = null,
    var ingredients: Ingredients? = null,
    var food_pairing: ArrayList<String>? = null,
    var brewers_tips: String? = null,
    var contributed_by: String? = null
)

class Amount {
    var value = 0.0
    var unit: String? = null
}

class BoilVolume {
    var value = 0
    var unit: String? = null
}

class Fermentation {
    var temp: Temp? = null
}

class Hop {
    var name: String? = null
    var amount: Amount? = null
    var add: String? = null
    var attribute: String? = null
}

class Ingredients {
    var malt: ArrayList<Malt>? = null
    var hops: ArrayList<Hop>? = null
    var yeast: String? = null
}

class Malt {
    var name: String? = null
    var amount: Amount? = null
}

class MashTemp {
    var temp: Temp? = null
    var duration = 0
}

class Method {
    var mash_temp: ArrayList<MashTemp>? = null
    var fermentation: Fermentation? = null
    var twist: Any? = null
}

class Temp {
    var value = 0
    var unit: String? = null
}

class Volume {
    var value = 0
    var unit: String? = null
}

