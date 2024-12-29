package com.example.sylvamet.ui.theme

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.background
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.nativeCanvas

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Saisir (navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.extendedColors.primaryTitle,
                ),
                title = {
                    androidx.compose.material3.Text(
                        "Martelage 44fa3...",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("cubages") }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Retour à l'accueil" ,
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* Action de recherche */ }) {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Recherche",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                },
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .verticalScroll(rememberScrollState()),
        ) {
            PieChartWithDetailsInCard()
            ProgressBarInCard()
            EssenceDropdown()
            DeleteToggle()
            GridWithIncrementButtons()
        }
    }
}

@Composable
fun PieChartWithDetailsInCard() {
    val data = listOf(
        Pair("Feuillu", 4.6f),
        Pair("Epicéa", 13.7f),
        Pair("Résineux", 36.5f),
        Pair("Hêtre", 19.3f),
        Pair("Cerisier", 22.3f),
        Pair("Bouleau", 3.6f)
    )

    val colors = listOf(
        Color(0xFFB9E081), // Feuillu
        Color(0xFFA0DA7A), // Epicéa
        Color(0xFFFFC66B), // Résineux
        Color(0xFFFAB177), // Hêtre
        Color(0xFF71B874), // Cerisier
        Color(0xFFFFF1A4)  // Bouleau
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Carte contenant le graphique et ses détails
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(6.dp),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                // Graphique camembert avec les proportions
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Canvas(modifier = Modifier.size(200.dp)) {
                        var startAngle = 0f
                        val total = data.map { it.second }.sum()

                        data.forEachIndexed { index, pair ->
                            val sweepAngle = (pair.second / total) * 360f
                            val middleAngle = startAngle + sweepAngle / 2
                            val radius = size.minDimension / 2 + 40.dp.toPx()

                            drawArc(
                                color = colors[index],
                                startAngle = startAngle,
                                sweepAngle = sweepAngle,
                                useCenter = true
                            )

                            val labelX = center.x + radius * kotlin.math.cos(Math.toRadians(middleAngle.toDouble())).toFloat()
                            val labelY = center.y + radius * kotlin.math.sin(Math.toRadians(middleAngle.toDouble())).toFloat()

                            drawContext.canvas.nativeCanvas.apply {
                                drawText(
                                    "${pair.first} ${"%.1f".format(pair.second)}%",
                                    labelX,
                                    labelY,
                                    android.graphics.Paint().apply {
                                        color = android.graphics.Color.BLACK
                                        textSize = 30f
                                        textAlign = android.graphics.Paint.Align.CENTER
                                    }
                                )
                            }

                            startAngle += sweepAngle
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Total : 251.4",
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 18.sp
                    )

                    Button(
                        onClick = { /* Action pour afficher les détails */ },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFE3A467),
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(text = "Voir détail")
                    }
                }
            }
        }
    }
}

@Composable
fun ProgressBarInCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {

            Text(
                text = "Bois coupé à l'année",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .align(Alignment.Start)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                contentAlignment = Alignment.Center
            ) {
                // Barre de progression
                LinearProgressIndicator(
                    progress = 0.79f,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    color = Color(0xFFE3A467),
                    trackColor = Color(0xFFF5F5F5)
                )

                Text(
                    text = "79%",
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun EssenceDropdown() {
    // Liste des options dans le dropdown
    val options = listOf("Feuillu", "Epicéa", "Résineux", "Hêtre", "Cerisier")

    // États pour le menu déroulant
    var expanded by remember { mutableStateOf(false) } // Contrôle l'ouverture du menu
    var selectedOption by remember { mutableStateOf(options[3]) } // Valeur sélectionnée (par défaut "Hêtre")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Label "Essence *"
        Text(
            text = "Essence *",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 16.sp,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        // Zone du dropdown (affichage de l'option sélectionnée + bouton pour ouvrir)
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = true },
            shape = RoundedCornerShape(12.dp),
            color = Color.White,
            shadowElevation = 4.dp
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = selectedOption,
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 18.sp,
                    color = Color.Black
                )

                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = "Dropdown",
                    tint = Color.Gray
                )
            }
        }

        // Menu déroulant
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    onClick = {
                        selectedOption = option // Met à jour l'option sélectionnée
                        expanded = false
                    },
                    text = {
                        Text(
                            text = option,
                            style = MaterialTheme.typography.bodyLarge,
                            fontSize = 16.sp
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun DeleteToggle() {
    // État du switch
    var isActivated by remember { mutableStateOf(false) } // Désactivé par défaut

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        // Label
        Text(
            text = "Supprimer une tige",
            style = MaterialTheme.typography.bodyLarge,
            fontSize = 18.sp,
            color = Color.Black
        )

        // Bouton slider
        Switch(
            checked = isActivated,
            onCheckedChange = { isActivated = it },
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color.White,
                checkedTrackColor = Color(0xFF4CAF50),
                uncheckedThumbColor = Color.White,
                uncheckedTrackColor = Color(0xFFE57373)
            )
        )
    }
}

@Composable
fun GridWithIncrementButtons() {
    // Créer un tableau pour gérer les compteurs pour les 16 boutons
    val counters = remember { mutableStateListOf(*Array(16) { 0 }) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Grille de boutons (4x4)
        for (row in 0 until 4) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween // Espacement égal entre les colonnes
            ) {
                for (col in 0 until 4) {
                    val index = row * 4 + col // Calcul de l'index unique pour chaque bouton
                    ButtonWithCounter(
                        number = index + 1,
                        count = counters[index],
                        onClick = { counters[index]++ }
                    )
                }
            }
        }
    }
}

@Composable
fun ButtonWithCounter(
    number: Int,
    count: Int,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .size(80.dp)
            .clickable(onClick = onClick), // Action pour incrémenter
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White) // Fond blanc
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

            Text(
                text = number.toString(),
                style = MaterialTheme.typography.headlineLarge,
                fontSize = 24.sp,
                color = Color(0xFFE3A467)
            )

            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(Color(0xFFE3A467), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = count.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    fontSize = 18.sp,
                    color = Color.White
                )
            }
        }
    }
}