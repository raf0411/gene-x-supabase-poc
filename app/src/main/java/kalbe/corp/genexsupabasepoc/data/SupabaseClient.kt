package kalbe.corp.genexsupabasepoc.data

import io.github.jan.supabase.annotations.SupabaseInternal
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.realtime.Realtime
import io.ktor.client.plugins.websocket.WebSockets
import kalbe.corp.genexsupabasepoc.utils.SUPABASE_ANON_KEY
import kalbe.corp.genexsupabasepoc.utils.SUPABASE_URL

@OptIn(SupabaseInternal::class)
val supabaseClient = createSupabaseClient(
    supabaseKey = SUPABASE_ANON_KEY,
    supabaseUrl = SUPABASE_URL,
){
    install(Realtime)
    install(Postgrest)
    install(Auth)
    httpConfig {
        this.install(WebSockets)
    }
}