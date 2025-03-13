package kalbe.corp.genexsupabasepoc

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.jan.supabase.createSupabaseClient
import kalbe.corp.genexsupabasepoc.ui.theme.GeneXSupabasePOCTheme
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable

val supabase = createSupabaseClient(
    supabaseUrl = "https://mlktzwbclfjkzrqavmqo.supabase.co",
    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im1sa3R6d2JjbGZqa3pycWF2bXFvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDE4ODI1ODIsImV4cCI6MjA1NzQ1ODU4Mn0.dQX8CKPAR7aHwa8iK3if2tCq90GpyULX75_jnf0CGS8"
) {
    install(Postgrest)
}

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GeneXSupabasePOCTheme {
                Surface (
                    modifier = Modifier.fillMaxSize().padding(top = 32.dp),
                    color = MaterialTheme.colorScheme.background
                ){
                    InstrumentsList()
                }
            }
        }
    }
}

@Composable
fun InstrumentsList() {
    var instruments by remember { mutableStateOf<List<Instrument>>(listOf()) }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.IO) {
            instruments = supabase.from("instruments").select().decodeList<Instrument>()
        }
    }
    LazyColumn {
        items(
            instruments,
            key = { instrument -> instrument.id },
        ) { instrument ->
            Text(
                instrument.name,
                modifier = Modifier.padding(8.dp),
            )
        }
    }
}