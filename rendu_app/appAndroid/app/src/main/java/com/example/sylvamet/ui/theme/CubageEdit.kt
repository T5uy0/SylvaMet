package com.example.sylvamet.ui.theme
import com.example.sylvamet.R
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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.border
import androidx.compose.ui.unit.sp
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CubageEdit (navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.extendedColors.primaryTitle,
                ),
                title = {
                    androidx.compose.material3.Text(
                        "Cubages",
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
        ) {
            InputBar("Date*","01/01/25")
            InputBar("Coupe*","Coupe 1")
            InputBar("Numéro*","9999")
            InputBar("Desccription","Essai")

            ImageSelector()
            TextSelector()
            SaveDeleteButtons()
        }
    }
}

@Composable
fun InputBar(title: String, text: String) {
    val placeholderColor = MaterialTheme.colorScheme.onSurfaceVariant

    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium.copy(fontSize = 16.sp),
        modifier = Modifier.padding(start = 16.dp, bottom = 3.dp)
    )

    TextField(
        value = "",
        onValueChange = { /* Gérer la saisie */ },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start =16.dp, end = 16.dp, bottom = 8.dp)
            .border(2.dp, Color.Black, RoundedCornerShape(8.dp)),
        placeholder = {
            Text(
                text = text,
                color = placeholderColor
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent
        )
    )
}

@Composable
fun ImageSelector() {

    var selectedIndex by remember { mutableStateOf(0) }


    val imageResources = listOf(
        Pair(R.drawable.tree, "Tige"),
        Pair(R.drawable.multitree, "Pile")
    )


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 50.dp, end = 50.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        imageResources.forEachIndexed { index, imageResource ->
            val (imageId, label) = imageResource


            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { selectedIndex = index }
                    .border(
                        width = 4.dp,
                        color = if (index == selectedIndex) Color(0xFFFFA500) else Color.Gray,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(8.dp)
            ) {
                Image(
                    painter = painterResource(id = imageId),
                    contentDescription = label,
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
                Text(
                    text = label,
                    color = if (index == selectedIndex) Color(0xFFFFA500) else Color.Gray,
                    fontSize = 14.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}
@Composable
fun TextSelector() {

    var selectedIndex by remember { mutableStateOf(0) }
    val texts = listOf("sur écorce", "écorcer")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 37.dp, end = 50.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        texts.forEachIndexed { index, textLabel ->

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { selectedIndex = index }
                    .border(
                        width = 4.dp,
                        color = if (index == selectedIndex) Color(0xFFFFA500) else Color.Gray,
                        shape = RoundedCornerShape(16.dp)
                    )
                    .padding(16.dp)
            ) {
                Text(
                    text = textLabel,
                    color = if (index == selectedIndex) Color(0xFFFFA500) else Color.Gray,
                    fontSize = 18.sp,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
fun SaveDeleteButtons() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF4CAF50),
                contentColor = Color.White
            ),
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Sauvegarder")
        }

        Spacer(modifier = Modifier.width(16.dp))

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF44336),
                contentColor = Color.White
            ),
            modifier = Modifier.weight(1f)
        ) {
            Text(text = "Supprimer")
        }
    }
}