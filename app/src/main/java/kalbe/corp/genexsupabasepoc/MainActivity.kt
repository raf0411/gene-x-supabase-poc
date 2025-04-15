package kalbe.corp.genexsupabasepoc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import kalbe.corp.genexsupabasepoc.navigation.NavGraph
import kalbe.corp.genexsupabasepoc.ui.theme.GeneXSupabasePOCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeneXSupabasePOCTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    NavGraph()
}