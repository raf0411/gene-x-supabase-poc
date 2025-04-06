package kalbe.corp.genexsupabasepoc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import io.github.jan.supabase.SupabaseClient
import kalbe.corp.genexsupabasepoc.data.supabaseClient
import kalbe.corp.genexsupabasepoc.navigation.NavGraph
import kalbe.corp.genexsupabasepoc.ui.theme.GeneXSupabasePOCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GeneXSupabasePOCTheme {
                MyApp(supabaseClient)
            }
        }
    }
}

@Composable
fun MyApp(supabaseClient: SupabaseClient) {
    NavGraph(supabaseClient = supabaseClient)
}