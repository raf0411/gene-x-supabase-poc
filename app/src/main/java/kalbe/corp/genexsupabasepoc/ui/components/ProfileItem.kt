package kalbe.corp.genexsupabasepoc.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kalbe.corp.genexsupabasepoc.models.Profile

@Composable
fun ProfileItem(
    profile: Profile,
    onProfileClick: () -> Unit,
){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .width(300.dp)
            .clip(RoundedCornerShape(12.dp))
            .clickable(onClick = onProfileClick)
            .shadow(12.dp, MaterialTheme.shapes.medium)
            .background(Color.White, shape = RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center
    ){
        Row (

        ){
            Text("Test")
            Text("Test2")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileItemPreview(){
    val profile: Profile = Profile(
        id = "1",
        name = "Raffi",
        nutrition = "Needed",
        calories = 12,
        max_heart_rate = 15,
        protein_take = 15f,
        weight = 80.0f,
        height = 168.0f,
    )

    ProfileItem(
        profile = profile,
        onProfileClick = {},
    )
}