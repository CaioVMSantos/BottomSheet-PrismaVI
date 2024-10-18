package com.example.bottomsheetprismavi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bottomsheetprismavi.ui.theme.BottomSheetPrismaVITheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    // Usando a anotação OptIn para habilitar recursos experimentais Material3
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        // Definindo o conteúdo da atividade
        setContent {
            BottomSheetPrismaVITheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), //Ocupa toda a tela
                    color = MaterialTheme.colorScheme.background //Cor de fundo
                ) {

                    //Inicializando o estado do ModalBottomSheet
                    val sheetState = rememberModalBottomSheetState()
                    var isSheetOpen by remember { mutableStateOf(false) } //Variável de estado para controlar se o BottomSheet está aberto ou fechado
                    val scaffoldState = rememberBottomSheetScaffoldState() // Inicializa o estado do BottomSheetScaffold
                    val scope = rememberCoroutineScope() //Criando um escopo de Corrotinas para controlar a animação do BottomSheet

                    BottomSheetScaffold(
                        scaffoldState = scaffoldState,
                        sheetContent = {

                            // Conteúdo do BottomSheet
                            Column (
                                modifier = Modifier
                                    .fillMaxWidth() //Coluna ocupa toda a largura
                                    .padding(16.dp), //Adiciona um espaçamento interno
                                horizontalAlignment = Alignment.CenterHorizontally // Alinha o conteúdo horizontalmente no centro
                            ){
                                Text("Color Name")

                                //Linha com Imagem e Textos
                                Row ( modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 8.dp), //Adicionando um prreenchimento interno acima
                                verticalAlignment = Alignment.CenterVertically //Alinhamento vertical no centro
                                ){
                                    Image(
                                        painter = painterResource(id = R.drawable.colorimage),
                                        contentDescription = "Image below and left of text",
                                        modifier = Modifier
                                            .size(130.dp),
                                        contentScale = ContentScale.Crop //Define a escala da imagem para cortar
                                    )
                                    Column(
                                        modifier = Modifier
                                            .padding(start = 40.dp) // Espaçamento entre a imagem e a coluna de textos
                                    ) {
                                        Text(text = "HEX: #000000")
                                        Text(text = "RGB: (0, 0, 0)")
                                        Text(text = "RYB: (0%, 0%, 0%)")
                                    }

                                }

                            }
                        },

                            )
                    {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ){
                            Button(onClick = {
                                scope.launch { //Lança uma corrotina no escopo
                                    scaffoldState.bottomSheetState.expand()
                                }
                            }) {
                                Text(text = "Open Bottom Sheet")
                            }
                        }
                    }
                }
            }
        }
    }
}

