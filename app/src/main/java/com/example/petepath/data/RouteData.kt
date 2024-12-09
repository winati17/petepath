package com.example.petepath.data

object RouteRepository {
    private val routes = listOf(
        Route("02", "Unhas-Veteran", "Rp5.000"),
        Route("05", "Unhas-Cendrawasih", "Rp5.000"),
        Route("07", "Unhas-Pettarani", "Rp5.000"),
        Route("A", "Minasaupa-Pasar Butung", "Rp5.000"),
        Route("B", "Tamalate-Pasar Butung", "Rp5.000"),
        Route("C", "KH.Wahid Hasyim-Rappokalling", "Rp5.000"),
        Route("D", "Daya-Selatan Makassar Mall", "Rp5.000"),
        Route("E", "Terminal Panakkukang-Timur Makassar Mall", "Rp5.000"),
        Route("F", "Terminal Tamalate-Timur Makassar Mall", "Rp5.000"),
        Route("G", "Daya-Pasar Butung", "Rp5.000"),
        Route("H", "Perumnas Antang-Pasar Butung", "Rp5.000"),
        Route("I", "Terminal Panakkukang-Pasar Baru", "Rp5.000"),
        Route("J", "Terminal Panakkukang-Nusakambangan", "Rp5.000"),
        Route("K", "Terminal Panaikang-Terminal Tamalate", "Rp5.000"),
        Route("L", "Terminal Tamalate-Pasar Butung", "Rp5.000"),
        Route("M", "Terminal Panaikang-Tanjung Alang", "Rp5.000"),
        Route("N", "Terminal Tamalate-Terminal Panakkukang", "Rp5.000"),
        Route("O", "Terminal Panaikang-Pasar Butung", "Rp5.000"),
        Route("I", "Terminal Panakkukang-Pasar Baru", "Rp5.000"),
        Route("P", "Terminal Panaikang-Terminal Tamalate", "Rp5.000"),
        Route("U", "Pasar Pannampu-Terminal Tamalate", "Rp5.000"),
        Route("R", "Pasar Baru-Kampus Unhas", "Rp5.000"),
        Route("V1", "Terminal Daya-Mangga Tiga", "Rp5.000"),
        Route("V2", "Sudiang-Terminal Daya", "Rp5.000"),
        Route("V3", "Pasar Daya-Moncongloe", "Rp5.000"),
        Route("W", "Terminal Daya-Desa Nelayan", "Rp5.000")
        )

    fun getRouteById(ruteId: String): Route? {
        return routes.find { it.id == ruteId }
    }

    fun getAllRoutes(): List<Route> = routes
}

fun getRoutesByRuteId(ruteId: String): List<String> {
    return when (ruteId) {
        "05" -> listOf(
            "Terminal Tamalate", "Malengkeri",
            "Dg. Tata", "Abd. Kadir", "Dangko",
            "Cendrawasih", "Arif Rate",
            "Botolempangan", "Kartini",
            "Bawakaraeng", "Urip Sumoharjo",
            "Perintis Kemerdekaan", "Kampus Unhas"
        )
        "02" -> listOf(
            "Terminal Tamalate", "Mallengkeri",
            "Dg. Tata", "M. Tahir",
            "Kumala", "Veteran",
            "Masjid Raya", "Urip Sumoharjo",
            "Perintis Kemerdekaan", "Kampus Unhas"
        )
        "07" -> listOf(
            "Panakukkang", "Toddopuli Raya",
            "Perumnas", "Hertasning",
            "A.P. Pettarani", "Gunung Sari",
            "Kampus IKIP", "Abdullah Dg. Sirua",
            "PLTU", "Urip Sumoharjo",
            "Perintis Kemerdekaan", "Kampus Unhas"
        )
        "A" -> listOf(
            "BTN Minasa Upa", "Syech Yusuf",
            "Sultan Alauddin", "Andi Tonro",
            "Kumala", "Ratulangi",
            "Jendral Sudirman (Karebosi Timur)",
            "HOS Cokroaminoto (Sentral)",
            "K.H. Wahid Hasyim",
            "Wahidin Sudirohusodo", "Pasar Butung"
        )
        "B" -> listOf(
            "Terminal Tamalate", "Malengkeri",
            "Dg. Tata", "Abdul Kadir",
            "Dangko", "Cendrawasih",
            "Arif Rate", "Sultan Hasanuddin",
            "Pattimura", "Ujung Pandang",
            "Riburane", "Jendral Ahmad Yani (Balaikota)",
            "Pasar Butung"
        )
        "C" -> listOf(
            "K.H. Wahid Hasyim", "Dr. Wahidin Sudirohusodo", "Bandang", "Masjid Raya", "Cumi Cumi",
            "Pongtiku", "Ujung Pandang Baru", "Gatot Subroto", "Juanda", "Regge", "Rappokalling"
        )
        "D" -> listOf(
            "Terminal Daya", "Perintis Kemerdekaan", "Urip Sumoharjo", "A.P. Pettarani", "Bawakaraeng",
            "Latimojong", "Andalas", "Laiya", "Selatan Makassar Mall"
        )
        "E" -> listOf(
            "Terminal Panakkukkang", "Toddopuli", "Tamalate", "Emmy Saelan", "Mapala", "A.P. Pettarani",
            "Maccini Raya", "Urip Sumoharjo", "Bawakaraeng", "Latimojong", "Andalas", "Laiya", "K.H. Agus Salim",
            "Timur Makassar Mall"
        )
        "F" -> listOf(
            "Terminal Tamalate", "Mallengkeri", "Daeng Tata", "Daeng Ngeppe", "Kumala", "Veteran", "Bandang",
            "Buru", "Andalas", "Satangnga", "K.H. Agus Salim", "Timur Makassar Mall"
        )
        "G" -> listOf(
            "Terminal Daya", "Kima", "Tol Ir. Sutami", "Tinumbu", "Cakalang", "Yos Sudarso", "Tentara Pelajar",
            "Kalimantan", "Pasar Butung"
        )
        "H" -> listOf(
            "Perumnas Antang", "Antang Raya", "Urip Sumoharjo", "Bawakaraeng", "Jendral Sudirman",
            "Dr. Wahidin Sudirohusodo", "Satando", "Kalimantan", "Pasar Butung"
        )
        "I" -> listOf(
            "Terminal Panakkukang", "Toddopuli Raya", "Borong", "Batua Raya", "Abdullah Daeng Sirua",
            "A.P. Pettarani", "Pelita Raya", "Sungai Saddang Baru", "Karungrung", "Arif Rate", "Sultan Hasanuddin",
            "Pattimura", "Pasar Baru"
        )
        "J" -> listOf(
            "Terminal Panakkukang", "Toddopuli Raya", "Tamalate", "Emmy Saelan", "Sultan Alauddin", "Andi Tonro",
            "Kumala", "Ratulangi", "Jendral Sudirman", "HOS Cokroaminoto", "Nusakambangan"
        )
        "K" -> listOf(
            "Terminal Panaikang", "Urip Sumoharjo", "Taman Makam Pahlawan", "Abd. Daeng Sirua", "Adiyaksa",
            "Terminal Panakkukang", "Toddopuli Raya", "Tamalate", "Emmy Saelan", "Sultan Alauddin", "Terminal Tamalate"
        )
        "L" -> listOf(
            "Terminal Tamalate", "Mallengkeri", "Dg. Tata", "Daeng Ngeppe", "Kumala", "Mallong Bassang", "Mappaoddang",
            "Mangerangi", "Baji Ateka", "Baji Minasa", "Nuri", "Rajawali", "Penghibur", "Pasar Ikan", "Ujung Pandang",
            "Nusantara", "Butung", "Pasar Butung"
        )
        "M" -> listOf(
            "Terminal Panaikang", "Urip Sumoharjo", "A.P. Pettarani", "Rappocini Raya", "Veteran",
            "Ratulangi", "Kakatua", "Cendrawasih", "Tanjung Alang"
        )
        "N" -> listOf(
            "Terminal Tamalate", "Sultan Alauddin", "Syekh Yusuf", "Jipang Raya", "SMA 9", "Tidung Raya",
            "Tamalate", "Toddopuli Raya", "Terminal Panakkukang"
        )
        "O" -> listOf(
            "Terminal Panaikang", "Urip Sumoharjo", "Taman Makam Pahlawan", "Batua Raya", "Toddopuli Raya",
            "A.P. Pettarani", "Urip Sumoharjo", "Bawakaraeng", "Veteran Utara", "Bandang", "Ujung", "Yos Sudarso",
            "Tarakan", "Kalimantan", "Pasar Butung"
        )
        "P" -> listOf(
            "Terminal Panaikang", "Urip Sumoharjo", "A.P. Pettarani", "Landak Baru", "Veteran", "Dr. Sam Ratulangi",
            "Landak", "Landak Baru", "A.P. Pettarani", "Urip Sumoharjo", "Terminal Panaikang"
        )
        "U" -> listOf(
            "Pasar Pannampu", "Tinumbu", "Cakalang", "Yos Sudarso", "Andalas", "Latimojong", "Bulukunyi", "Rusa",
            "Lanto Dg. Pasewang", "Dr. Sam Ratulangi", "Landak", "Veteran", "Sultan Alauddin", "Terminal Tamalate"
        )
        "R" -> listOf(
            "Pasar Baru", "Ujung Pandang", "Nusantara", "Pasar Butung", "Tentara Pelajar", "Kalimantan",
            "Satando", "Yos Sudarso", "Ujung", "Bandang", "Masjid Raya", "Urip Sumoharjo", "Perintis Kemerdekaan",
            "Kampus Unhas"
        )
        "V1" -> listOf(
            "Terminal Daya", "Paccerakkang", "Mangga Tiga"
        )
        "V2" -> listOf(
            "Sudiang", "KNPI", "Terminal Daya"
        )
        "V3" -> listOf(
            "Pasar Daya", "Paccerakkang", "Moncongloe", "Pangnyangkallang"
        )
        "W" -> listOf(
            "Terminal Daya", "KIMA", "Kapasa", "SMA 6", "Ir. Sutami", "Salodong", "Desa Nelayan"
        )

        else -> listOf("No routes available")
    }
}