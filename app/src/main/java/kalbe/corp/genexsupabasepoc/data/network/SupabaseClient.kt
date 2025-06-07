package kalbe.corp.genexsupabasepoc.data.network

import io.github.jan.supabase.annotations.SupabaseInternal
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.ktor.client.plugins.websocket.WebSockets
import kalbe.corp.genexsupabasepoc.BuildConfig

@OptIn(SupabaseInternal::class)
val supabaseClient = createSupabaseClient(
    supabaseKey = BuildConfig.SUPABASE_ANON_KEY,
    supabaseUrl = BuildConfig.SUPABASE_URL,
){
    install(Realtime)
    install(Postgrest)
    install(Auth){
        autoLoadFromStorage = true
        alwaysAutoRefresh = true
    }
    httpConfig {
        this.install(WebSockets)
    }
}